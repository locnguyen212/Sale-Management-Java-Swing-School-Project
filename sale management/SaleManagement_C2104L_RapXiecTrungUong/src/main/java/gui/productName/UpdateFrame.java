package gui.productName;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import dao.ProductNameDao;
import entity.ProductName;
import helper.loc.CrudValidate;
import javax.swing.border.EtchedBorder;

public class UpdateFrame extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JLabel nameLbl;
	private JTextField nameTf;
	private JLabel nameValidate;
	private JButton submitBtn;
	private NameProductMainFrame mainFrame;
	private ProductName pro;
	private JLabel uniqueValidate;
	private List<ProductName> proList;

	public void setProList(List<ProductName> proList) {
		this.proList = proList;
	}
	
	public void setPro(ProductName pro) {
		this.pro = pro;
	}

	public void setMainFrame(NameProductMainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateFrame frame = new UpdateFrame();
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
	public UpdateFrame() {
		setTitle("Product name update");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 172);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Update", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPane.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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
		
		submitBtn = new JButton("Submit");
		submitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submitBtnActionPerformed(e);
			}
		});
		
		uniqueValidate = new JLabel("");
		uniqueValidate.setForeground(Color.RED);
		GridBagConstraints gbc_uniqueValidate = new GridBagConstraints();
		gbc_uniqueValidate.gridwidth = 2;
		gbc_uniqueValidate.insets = new Insets(0, 0, 5, 0);
		gbc_uniqueValidate.gridx = 0;
		gbc_uniqueValidate.gridy = 2;
		panel.add(uniqueValidate, gbc_uniqueValidate);
		GridBagConstraints gbc_submitBtn = new GridBagConstraints();
		gbc_submitBtn.gridwidth = 2;
		gbc_submitBtn.gridx = 0;
		gbc_submitBtn.gridy = 3;
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
		nameTf.setText(pro.getName());
	}

	protected void submitBtnActionPerformed(ActionEvent e) {
		var dao = new ProductNameDao();
		
		boolean nameVal  = CrudValidate.nameValidate(nameTf, nameValidate);
		boolean isUnique = CrudValidate.isProductNameUnique(nameTf, proList, pro);

		if(isUnique) {
			if(nameVal) {
				pro.setName(nameTf.getText());
			}
		}else{
			uniqueValidate.setText("*The name is already exist!");
		}			
		
		if(nameVal && isUnique) {
			int i = JOptionPane.showConfirmDialog(this, "Are you sure?", "Info", JOptionPane.OK_OPTION);
			if(i == JOptionPane.OK_OPTION) {
				dao.update(pro);
				
				mainFrame.setEnabled(true);
				mainFrame.loadDb();
				this.dispose();
				JOptionPane.showMessageDialog(mainFrame, "Done!");
			}
		}
	}

}
