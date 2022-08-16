package gui.properties;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.border.TitledBorder;

import dao.ProductNameDao;
import dao.PropertiesDao;
import entity.ProductName;
import entity.Properties;
import gui.productName.NameProductMainFrame;
import helper.loc.ComboItem;
import helper.loc.CrudValidate;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PropertiesInsertFrame extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JLabel nameLbl;
	private JTextField nameTf;
	private JLabel nameValidate;
	private JLabel uniqueValidate;
	private JLabel parentIdLbl;
	private JComboBox parentIdCb;
	private JButton submitBtn;
	private PropertiesMainFrame mainFrame;
	private List<Properties> proList;
	
	public void setProList(List<Properties> proList) {
		this.proList = proList;
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
					PropertiesInsertFrame frame = new PropertiesInsertFrame();
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
	public PropertiesInsertFrame() {
		setTitle("Properties insert");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 198);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "insert", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		nameLbl = new JLabel("Name");
		GridBagConstraints gbc_nameLbl = new GridBagConstraints();
		gbc_nameLbl.insets = new Insets(0, 0, 5, 5);
		gbc_nameLbl.anchor = GridBagConstraints.EAST;
		gbc_nameLbl.gridx = 0;
		gbc_nameLbl.gridy = 0;
		panel.add(nameLbl, gbc_nameLbl);
		
		nameTf = new JTextField();
		GridBagConstraints gbc_nameTf = new GridBagConstraints();
		gbc_nameTf.insets = new Insets(0, 0, 5, 0);
		gbc_nameTf.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameTf.gridx = 1;
		gbc_nameTf.gridy = 0;
		panel.add(nameTf, gbc_nameTf);
		nameTf.setColumns(10);
		
		nameValidate = new JLabel("");
		nameValidate.setForeground(Color.RED);
		GridBagConstraints gbc_nameValidate = new GridBagConstraints();
		gbc_nameValidate.insets = new Insets(0, 0, 5, 0);
		gbc_nameValidate.gridwidth = 2;
		gbc_nameValidate.gridx = 0;
		gbc_nameValidate.gridy = 1;
		panel.add(nameValidate, gbc_nameValidate);
		
		uniqueValidate = new JLabel("");
		uniqueValidate.setForeground(Color.RED);
		GridBagConstraints gbc_uniqueValidate = new GridBagConstraints();
		gbc_uniqueValidate.insets = new Insets(0, 0, 5, 0);
		gbc_uniqueValidate.gridwidth = 2;
		gbc_uniqueValidate.gridx = 0;
		gbc_uniqueValidate.gridy = 2;
		panel.add(uniqueValidate, gbc_uniqueValidate);
		
		parentIdLbl = new JLabel("Parent id");
		GridBagConstraints gbc_parentIdLbl = new GridBagConstraints();
		gbc_parentIdLbl.anchor = GridBagConstraints.EAST;
		gbc_parentIdLbl.insets = new Insets(0, 0, 5, 5);
		gbc_parentIdLbl.gridx = 0;
		gbc_parentIdLbl.gridy = 3;
		panel.add(parentIdLbl, gbc_parentIdLbl);
		
		parentIdCb = new JComboBox();
		GridBagConstraints gbc_parentIdCb = new GridBagConstraints();
		gbc_parentIdCb.insets = new Insets(0, 0, 5, 0);
		gbc_parentIdCb.fill = GridBagConstraints.HORIZONTAL;
		gbc_parentIdCb.gridx = 1;
		gbc_parentIdCb.gridy = 3;
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
		gbc_submitBtn.gridy = 4;
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
		parentIdCb.addItem(new ComboItem("Root", 0));
		for(var pro : proList) {
			if(pro.getParentId() == 0) {
				parentIdCb.addItem(new ComboItem(pro.getName(), pro.getId()));
			}
			
		}
	}

	protected void submitBtnActionPerformed(ActionEvent e) {
		var dao = new PropertiesDao();
		var pro = new Properties();
		
		boolean nameVal  = CrudValidate.nameValidate(nameTf, nameValidate);
		boolean isUnique = CrudValidate.isPropertiesUnique(nameTf, proList, null);
		
		if(isUnique) {
			if(nameVal) {
				pro.setName(nameTf.getText());
			}
			uniqueValidate.setText("");
		}else{
			uniqueValidate.setText("*The name is already exist!");
		}
		
		pro.setParentId(((ComboItem)parentIdCb.getSelectedItem()).getValue());

		if(nameVal && isUnique) {
			int i = JOptionPane.showConfirmDialog(this, "Are you sure?", "Info", JOptionPane.OK_OPTION);
			if(i == JOptionPane.OK_OPTION) {
				dao.insert(pro);
				
				mainFrame.setEnabled(true);
				mainFrame.loadDb();
				this.dispose();
				JOptionPane.showMessageDialog(mainFrame, "Done!");
			}
		}

	}
}
