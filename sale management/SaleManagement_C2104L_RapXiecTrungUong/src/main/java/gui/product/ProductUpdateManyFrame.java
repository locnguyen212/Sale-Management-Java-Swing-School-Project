package gui.product;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import dao.ProductDao;
import dao.ProductNameDao;
import entity.Product;
import helper.HelperFunction;
import helper.Validation;
import helper.loc.ComboItem;

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ProductUpdateManyFrame extends JFrame {

	private JPanel contentPane;
	private JLabel nameLbl;
	private JComboBox nameCb;
	private JLabel categoryLbl;
	private JComboBox categoryCb;
	private JLabel isAvailableLbl;
	private JComboBox isAvailableCb;
	private JButton submitBtn;
	private ProductMainFrame mainFrame;
	private String ids;
	private JLabel noteLbl;
	private JLabel validateLbl;
	
	

	public void setIds(String ids) {
		this.ids = ids;
	}

	public void setMainFrame(ProductMainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductUpdateManyFrame frame = new ProductUpdateManyFrame();
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
	public ProductUpdateManyFrame() {
		setTitle("Product update many");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 481, 241);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Update", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		nameLbl = new JLabel("Name");
		GridBagConstraints gbc_nameLbl = new GridBagConstraints();
		gbc_nameLbl.insets = new Insets(0, 0, 5, 5);
		gbc_nameLbl.anchor = GridBagConstraints.WEST;
		gbc_nameLbl.gridx = 0;
		gbc_nameLbl.gridy = 0;
		contentPane.add(nameLbl, gbc_nameLbl);
		
		nameCb = new JComboBox();
		GridBagConstraints gbc_nameCb = new GridBagConstraints();
		gbc_nameCb.insets = new Insets(0, 0, 5, 0);
		gbc_nameCb.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameCb.gridx = 1;
		gbc_nameCb.gridy = 0;
		contentPane.add(nameCb, gbc_nameCb);
		
		categoryLbl = new JLabel("Category");
		GridBagConstraints gbc_categoryLbl = new GridBagConstraints();
		gbc_categoryLbl.anchor = GridBagConstraints.WEST;
		gbc_categoryLbl.insets = new Insets(0, 0, 5, 5);
		gbc_categoryLbl.gridx = 0;
		gbc_categoryLbl.gridy = 1;
		contentPane.add(categoryLbl, gbc_categoryLbl);
		
		categoryCb = new JComboBox();
		GridBagConstraints gbc_categoryCb = new GridBagConstraints();
		gbc_categoryCb.insets = new Insets(0, 0, 5, 0);
		gbc_categoryCb.fill = GridBagConstraints.HORIZONTAL;
		gbc_categoryCb.gridx = 1;
		gbc_categoryCb.gridy = 1;
		contentPane.add(categoryCb, gbc_categoryCb);
		
		isAvailableLbl = new JLabel("Is available");
		GridBagConstraints gbc_isAvailableLbl = new GridBagConstraints();
		gbc_isAvailableLbl.anchor = GridBagConstraints.WEST;
		gbc_isAvailableLbl.insets = new Insets(0, 0, 5, 5);
		gbc_isAvailableLbl.gridx = 0;
		gbc_isAvailableLbl.gridy = 2;
		contentPane.add(isAvailableLbl, gbc_isAvailableLbl);
		
		isAvailableCb = new JComboBox();
		isAvailableCb.setModel(new DefaultComboBoxModel(new String[] {"", "Yes", "No"}));
		GridBagConstraints gbc_isAvailableCb = new GridBagConstraints();
		gbc_isAvailableCb.insets = new Insets(0, 0, 5, 0);
		gbc_isAvailableCb.fill = GridBagConstraints.HORIZONTAL;
		gbc_isAvailableCb.gridx = 1;
		gbc_isAvailableCb.gridy = 2;
		contentPane.add(isAvailableCb, gbc_isAvailableCb);
		
		submitBtn = new JButton("Submit");
		submitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submitBtnActionPerformed(e);
			}
		});
		
		validateLbl = new JLabel("");
		validateLbl.setForeground(Color.RED);
		GridBagConstraints gbc_validateLbl = new GridBagConstraints();
		gbc_validateLbl.gridwidth = 2;
		gbc_validateLbl.insets = new Insets(0, 0, 5, 5);
		gbc_validateLbl.gridx = 0;
		gbc_validateLbl.gridy = 3;
		contentPane.add(validateLbl, gbc_validateLbl);
		GridBagConstraints gbc_submitBtn = new GridBagConstraints();
		gbc_submitBtn.insets = new Insets(0, 0, 5, 0);
		gbc_submitBtn.gridwidth = 2;
		gbc_submitBtn.gridx = 0;
		gbc_submitBtn.gridy = 4;
		contentPane.add(submitBtn, gbc_submitBtn);
		
		noteLbl = new JLabel(
				"Note: Chose a field to update, chose blank if you don't want to updatethat field"
				);
		GridBagConstraints gbc_noteLbl = new GridBagConstraints();
		gbc_noteLbl.gridwidth = 2;
		gbc_noteLbl.gridx = 0;
		gbc_noteLbl.gridy = 5;
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
		var cateDb = HelperFunction.getCategoryDb();
		var dao = new ProductNameDao();
		var proNameDb = dao.getDb();
		
		nameCb.addItem(new ComboItem("", 0));	
		for(var pro : proNameDb) {
			nameCb.addItem(new ComboItem(pro.getName(), pro.getId()));			
		}
		
		categoryCb.addItem(new ComboItem("", 0));	
		for(var cate : cateDb) {
			categoryCb.addItem(new ComboItem(cate.getName(), cate.getId()));
		}
		
	}

	/**
	 * @param e
	 */
	protected void submitBtnActionPerformed(ActionEvent e) {
		var dao = new ProductDao();
		
		String name = null;
		String category = null;
		String isAvailable = null;
		
		if(((ComboItem)nameCb.getSelectedItem()).getValue() != 0) {
			name = ((ComboItem)nameCb.getSelectedItem()).getValue()+"";
		}
		
		if(((ComboItem)categoryCb.getSelectedItem()).getValue() != 0) {
			category = ((ComboItem)categoryCb.getSelectedItem()).getValue()+"";
		}
		
		if(!isAvailableCb.getSelectedItem().toString().equals("")) {
			isAvailable = isAvailableCb.getSelectedItem().toString().equals("Yes")? "True":"False";
		}

		if(name!=null || category!=null || isAvailable!=null) {
			int i = JOptionPane.showConfirmDialog(this, "Are you sure?", "Info", JOptionPane.OK_OPTION);
			if(i == JOptionPane.OK_OPTION) {
				dao.update(category, name, isAvailable, ids);
				
				mainFrame.setEnabled(true);
				mainFrame.loadDb();
				this.dispose();
				JOptionPane.showMessageDialog(mainFrame, "Done!");
			}
		}else {
			validateLbl.setText("*Please chose at least a field to update");
		}
		

	}

}
