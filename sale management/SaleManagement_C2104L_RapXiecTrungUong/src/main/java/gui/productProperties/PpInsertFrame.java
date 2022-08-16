package gui.productProperties;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import dao.ProductDao;
import dao.ProductNameDao;
import dao.ProductPropertiesDao;
import dao.PropertiesDao;
import entity.Product;
import entity.ProductProperties;
import helper.HelperFunction;
import helper.Validation;
import helper.loc.ComboItem;
import helper.loc.CrudValidate;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class PpInsertFrame extends JFrame {

	private JPanel contentPane;
	private JLabel parentPropertyLbl;
	private JComboBox parentPropertyCb;
	private JLabel childPropertyLbl;
	private JComboBox childPropertyCb;
	private JButton submitBtn;
	private PpMainFrame mainFrame;
	private int productId;
	private List<ProductProperties> ppList;
	private JLabel childPropertyValidate;
	private JLabel noteLbl;
	
	

	public void setPpList(List<ProductProperties> ppList) {
		this.ppList = ppList;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public void setMainFrame(PpMainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PpInsertFrame frame = new PpInsertFrame();
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
	public PpInsertFrame() {
		setTitle("Product-properties insert");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 217);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(null, "Insert", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		parentPropertyLbl = new JLabel("Parent property");
		GridBagConstraints gbc_parentPropertyLbl = new GridBagConstraints();
		gbc_parentPropertyLbl.insets = new Insets(0, 0, 5, 5);
		gbc_parentPropertyLbl.anchor = GridBagConstraints.EAST;
		gbc_parentPropertyLbl.gridx = 0;
		gbc_parentPropertyLbl.gridy = 0;
		contentPane.add(parentPropertyLbl, gbc_parentPropertyLbl);
		
		parentPropertyCb = new JComboBox();
		parentPropertyCb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parentPropertyCbActionPerformed(e);
			}
		});
	
		GridBagConstraints gbc_parentPropertyCb = new GridBagConstraints();
		gbc_parentPropertyCb.insets = new Insets(0, 0, 5, 0);
		gbc_parentPropertyCb.fill = GridBagConstraints.HORIZONTAL;
		gbc_parentPropertyCb.gridx = 1;
		gbc_parentPropertyCb.gridy = 0;
		contentPane.add(parentPropertyCb, gbc_parentPropertyCb);
		
		childPropertyLbl = new JLabel("Child property");
		GridBagConstraints gbc_childPropertyLbl = new GridBagConstraints();
		gbc_childPropertyLbl.anchor = GridBagConstraints.EAST;
		gbc_childPropertyLbl.insets = new Insets(0, 0, 5, 5);
		gbc_childPropertyLbl.gridx = 0;
		gbc_childPropertyLbl.gridy = 1;
		contentPane.add(childPropertyLbl, gbc_childPropertyLbl);
		
		childPropertyCb = new JComboBox();
		GridBagConstraints gbc_childPropertyCb = new GridBagConstraints();
		gbc_childPropertyCb.insets = new Insets(0, 0, 5, 0);
		gbc_childPropertyCb.fill = GridBagConstraints.HORIZONTAL;
		gbc_childPropertyCb.gridx = 1;
		gbc_childPropertyCb.gridy = 1;
		contentPane.add(childPropertyCb, gbc_childPropertyCb);
		
		submitBtn = new JButton("Submit");
		submitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submitBtnActionPerformed(e);
			}
		});
		
		childPropertyValidate = new JLabel("");
		childPropertyValidate.setForeground(Color.RED);
		GridBagConstraints gbc_childPropertyValidate = new GridBagConstraints();
		gbc_childPropertyValidate.gridwidth = 2;
		gbc_childPropertyValidate.insets = new Insets(0, 0, 5, 0);
		gbc_childPropertyValidate.gridx = 0;
		gbc_childPropertyValidate.gridy = 2;
		contentPane.add(childPropertyValidate, gbc_childPropertyValidate);
		GridBagConstraints gbc_submitBtn = new GridBagConstraints();
		gbc_submitBtn.insets = new Insets(0, 0, 5, 0);
		gbc_submitBtn.gridwidth = 2;
		gbc_submitBtn.gridx = 0;
		gbc_submitBtn.gridy = 3;
		contentPane.add(submitBtn, gbc_submitBtn);
		
		noteLbl = new JLabel("Note: You can only chose one child property per parent property");
		GridBagConstraints gbc_noteLbl = new GridBagConstraints();
		gbc_noteLbl.anchor = GridBagConstraints.WEST;
		gbc_noteLbl.gridwidth = 2;
		gbc_noteLbl.insets = new Insets(0, 0, 0, 5);
		gbc_noteLbl.gridx = 0;
		gbc_noteLbl.gridy = 4;
		contentPane.add(noteLbl, gbc_noteLbl);
		
		this.addWindowListener(new WindowAdapter() {
			 @Override
	            public void windowClosed(WindowEvent e) {
	                mainFrame.setEnabled(true);
	                mainFrame.setVisible(true);
	            }		
		});
	}
	
	public void setupData() {
		var dao = new PropertiesDao();
		parentPropertyCb.addItem(new ComboItem("", 0));
		for(var pro : dao.getDb()) {	
			if(pro.getParentId() == 0) {
				parentPropertyCb.addItem(new ComboItem(pro.getName(), pro.getId()));
			}
		}
	}


	protected void parentPropertyCbActionPerformed(ActionEvent e) {
		var dao = new PropertiesDao();
		childPropertyCb.removeAllItems();
		if(((ComboItem)parentPropertyCb.getSelectedItem()).getValue() != 0) {
			for(var pro : dao.getDb()) {	
				if(pro.getParentId() == ((ComboItem)parentPropertyCb.getSelectedItem()).getValue())	{
					childPropertyCb.addItem(new ComboItem(pro.getName(), pro.getId()));
				}
			}
		}else {
			childPropertyCb.removeAllItems();
		}
	}
	
	
	protected void submitBtnActionPerformed(ActionEvent e) {
		if(((ComboItem)childPropertyCb.getSelectedItem())!=null && ((ComboItem)parentPropertyCb.getSelectedItem())!=null) {
			var dao = new ProductPropertiesDao();
			var pro = new ProductProperties();
			int propertyId = ((ComboItem)childPropertyCb.getSelectedItem()).getValue();
			var property = HelperFunction.getProperties(propertyId);
			
			boolean isDuplicate = CrudValidate.isChoosenPropertyDuplicate(property, ppList, childPropertyValidate);
			boolean isUnique = false;
			
			if(!isDuplicate) {
				isUnique = CrudValidate.isChoosenPropertyUnique(property, ppList, childPropertyValidate);
				if(isUnique) {
					pro.setProductId(productId);
					pro.setPropertiesId(((ComboItem)childPropertyCb.getSelectedItem()).getValue());
				}
			}		


			if(!isDuplicate && isUnique) {
				int i = JOptionPane.showConfirmDialog(this, "Are you sure?", "Info", JOptionPane.OK_OPTION);
				if(i == JOptionPane.OK_OPTION) {
					dao.insert(pro);
					
					mainFrame.setEnabled(true);
					mainFrame.loadDb();
					this.dispose();
					JOptionPane.showMessageDialog(mainFrame, "Done!");
				}
			}
		}else {
			childPropertyValidate.setText("*Please select a parent and child property");
		}
		
	}
	

}
