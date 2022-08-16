package gui.properties;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.border.TitledBorder;

import dao.PropertiesDao;
import entity.Properties;
import helper.loc.ComboItem;
import helper.loc.CrudValidate;

import javax.swing.JComboBox;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.awt.event.ActionEvent;

public class PropertiesUpdateManyFrame extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JLabel parentIdLbl;
	private JComboBox parentIdCb;
	private JButton submitBtn;
	private PropertiesMainFrame mainFrame;
	private String id;
	private List<Properties> proList;
	
	public void setProList(List<Properties> proList) {
		this.proList = proList;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public void setMainFrame(PropertiesMainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PropertiesUpdateManyFrame frame = new PropertiesUpdateManyFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PropertiesUpdateManyFrame() {
		setTitle("Properties update many");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 419, 132);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Update", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		parentIdLbl = new JLabel("Parent id");
		GridBagConstraints gbc_parentIdLbl = new GridBagConstraints();
		gbc_parentIdLbl.insets = new Insets(0, 0, 5, 5);
		gbc_parentIdLbl.anchor = GridBagConstraints.EAST;
		gbc_parentIdLbl.gridx = 0;
		gbc_parentIdLbl.gridy = 0;
		panel.add(parentIdLbl, gbc_parentIdLbl);
		
		parentIdCb = new JComboBox();
		GridBagConstraints gbc_parentIdCb = new GridBagConstraints();
		gbc_parentIdCb.insets = new Insets(0, 0, 5, 0);
		gbc_parentIdCb.fill = GridBagConstraints.HORIZONTAL;
		gbc_parentIdCb.gridx = 1;
		gbc_parentIdCb.gridy = 0;
		panel.add(parentIdCb, gbc_parentIdCb);
		
		submitBtn = new JButton("Submit");
		submitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submitBtnActionPerformed(e);
			}
		});
		GridBagConstraints gbc_submitBtn = new GridBagConstraints();
		gbc_submitBtn.gridwidth = 2;
		gbc_submitBtn.insets = new Insets(0, 0, 0, 5);
		gbc_submitBtn.gridx = 0;
		gbc_submitBtn.gridy = 1;
		panel.add(submitBtn, gbc_submitBtn);
		
		this.addWindowListener(new WindowAdapter() {
			 @Override
	            public void windowClosed(WindowEvent e) {
	                mainFrame.setEnabled(true);
	                mainFrame.setVisible(true);
	            }		
		});
	}
	
	public void setupData() {
		for(var pro : proList) {
			if(pro.getParentId() == 0) {
				parentIdCb.addItem(new ComboItem(pro.getName(), pro.getId()));
			}
			
		}
	}

	protected void submitBtnActionPerformed(ActionEvent e) {
		var dao = new PropertiesDao();		
		int parentId = ((ComboItem)parentIdCb.getSelectedItem()).getValue();

		int i = JOptionPane.showConfirmDialog(this, "Are you sure?", "Info", JOptionPane.OK_OPTION);
		if(i == JOptionPane.OK_OPTION) {
			dao.update(id, parentId);
			
			mainFrame.setEnabled(true);
			mainFrame.loadDb();
			this.dispose();
			JOptionPane.showMessageDialog(mainFrame, "Done!");
		}

	}
}
