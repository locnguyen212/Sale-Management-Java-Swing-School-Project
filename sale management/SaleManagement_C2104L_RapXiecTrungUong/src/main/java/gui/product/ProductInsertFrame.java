package gui.product;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.border.TitledBorder;

import MainFrame.Main;
import dao.ProductDao;
import dao.ProductNameDao;
import dao.PropertiesDao;
import entity.Product;
import entity.Properties;
import helper.HelperFunction;
import helper.Validation;
import helper.loc.AutoCompletion;
import helper.loc.ComboItem;
import helper.loc.CrudValidate;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class ProductInsertFrame extends JFrame {

	private JPanel contentPane;
	private JLabel nameLbl;
	private JComboBox nameCb;
	private JLabel categoryLbl;
	private JComboBox categoryCb;
	private JLabel quantityLbl;
	private JTextField quantityTf;
	private JLabel quantityValidate;
	private JLabel isAvailableLbl;
	private JComboBox isAvailableCb;
	private JLabel buyPriceLbl;
	private JTextField buyPriceTf;
	private JLabel buyPriceValidate;
	private JLabel priceLbl;
	private JTextField priceTf;
	private JLabel priceValidate;
	private JLabel infoLbl;
	private JLabel infoValidate;
	private JButton submitBtn;
	private ProductMainFrame mainFrame;
	private JScrollPane scrollPane;
	private JTextArea infoTa;
	private Main homeFrame;

	public void setMainFrame(ProductMainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
	

	public void setHomeFrame(Main homeFrame) {
		this.homeFrame = homeFrame;
	}


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductInsertFrame frame = new ProductInsertFrame();
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
	public ProductInsertFrame() {
		setTitle("Product insert");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 418);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(null, "Insert", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 79, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		nameLbl = new JLabel("Name");
		GridBagConstraints gbc_nameLbl = new GridBagConstraints();
		gbc_nameLbl.insets = new Insets(0, 0, 5, 5);
		gbc_nameLbl.anchor = GridBagConstraints.WEST;
		gbc_nameLbl.gridx = 0;
		gbc_nameLbl.gridy = 0;
		contentPane.add(nameLbl, gbc_nameLbl);
		
		nameCb = new JComboBox();
		AutoCompletion.enable(nameCb);
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
		
		quantityLbl = new JLabel("Quantity");
		GridBagConstraints gbc_quantityLbl = new GridBagConstraints();
		gbc_quantityLbl.anchor = GridBagConstraints.WEST;
		gbc_quantityLbl.insets = new Insets(0, 0, 5, 5);
		gbc_quantityLbl.gridx = 0;
		gbc_quantityLbl.gridy = 2;
		contentPane.add(quantityLbl, gbc_quantityLbl);
		
		quantityTf = new JTextField();
		GridBagConstraints gbc_quantityTf = new GridBagConstraints();
		gbc_quantityTf.insets = new Insets(0, 0, 5, 0);
		gbc_quantityTf.fill = GridBagConstraints.HORIZONTAL;
		gbc_quantityTf.gridx = 1;
		gbc_quantityTf.gridy = 2;
		contentPane.add(quantityTf, gbc_quantityTf);
		quantityTf.setColumns(10);
		
		quantityValidate = new JLabel("");
		quantityValidate.setForeground(Color.RED);
		GridBagConstraints gbc_quantityValidate = new GridBagConstraints();
		gbc_quantityValidate.insets = new Insets(0, 0, 5, 0);
		gbc_quantityValidate.gridwidth = 2;
		gbc_quantityValidate.gridx = 0;
		gbc_quantityValidate.gridy = 3;
		contentPane.add(quantityValidate, gbc_quantityValidate);
		
		isAvailableLbl = new JLabel("Is available");
		GridBagConstraints gbc_isAvailableLbl = new GridBagConstraints();
		gbc_isAvailableLbl.anchor = GridBagConstraints.WEST;
		gbc_isAvailableLbl.insets = new Insets(0, 0, 5, 5);
		gbc_isAvailableLbl.gridx = 0;
		gbc_isAvailableLbl.gridy = 4;
		contentPane.add(isAvailableLbl, gbc_isAvailableLbl);
		
		isAvailableCb = new JComboBox();
		isAvailableCb.setModel(new DefaultComboBoxModel(new String[] {"Yes", "No"}));
		GridBagConstraints gbc_isAvailableCb = new GridBagConstraints();
		gbc_isAvailableCb.insets = new Insets(0, 0, 5, 0);
		gbc_isAvailableCb.fill = GridBagConstraints.HORIZONTAL;
		gbc_isAvailableCb.gridx = 1;
		gbc_isAvailableCb.gridy = 4;
		contentPane.add(isAvailableCb, gbc_isAvailableCb);
		
		buyPriceLbl = new JLabel("Buy price");
		GridBagConstraints gbc_buyPriceLbl = new GridBagConstraints();
		gbc_buyPriceLbl.anchor = GridBagConstraints.WEST;
		gbc_buyPriceLbl.insets = new Insets(0, 0, 5, 5);
		gbc_buyPriceLbl.gridx = 0;
		gbc_buyPriceLbl.gridy = 5;
		contentPane.add(buyPriceLbl, gbc_buyPriceLbl);
		
		buyPriceTf = new JTextField();
		GridBagConstraints gbc_buyPriceTf = new GridBagConstraints();
		gbc_buyPriceTf.insets = new Insets(0, 0, 5, 0);
		gbc_buyPriceTf.fill = GridBagConstraints.HORIZONTAL;
		gbc_buyPriceTf.gridx = 1;
		gbc_buyPriceTf.gridy = 5;
		contentPane.add(buyPriceTf, gbc_buyPriceTf);
		buyPriceTf.setColumns(10);
		
		buyPriceValidate = new JLabel("");
		buyPriceValidate.setForeground(Color.RED);
		GridBagConstraints gbc_buyPriceValidate = new GridBagConstraints();
		gbc_buyPriceValidate.insets = new Insets(0, 0, 5, 0);
		gbc_buyPriceValidate.gridwidth = 2;
		gbc_buyPriceValidate.gridx = 0;
		gbc_buyPriceValidate.gridy = 6;
		contentPane.add(buyPriceValidate, gbc_buyPriceValidate);
		
		priceLbl = new JLabel("Price");
		GridBagConstraints gbc_priceLbl = new GridBagConstraints();
		gbc_priceLbl.anchor = GridBagConstraints.WEST;
		gbc_priceLbl.insets = new Insets(0, 0, 5, 5);
		gbc_priceLbl.gridx = 0;
		gbc_priceLbl.gridy = 7;
		contentPane.add(priceLbl, gbc_priceLbl);
		
		priceTf = new JTextField();
		GridBagConstraints gbc_priceTf = new GridBagConstraints();
		gbc_priceTf.insets = new Insets(0, 0, 5, 0);
		gbc_priceTf.fill = GridBagConstraints.HORIZONTAL;
		gbc_priceTf.gridx = 1;
		gbc_priceTf.gridy = 7;
		contentPane.add(priceTf, gbc_priceTf);
		priceTf.setColumns(10);
		
		priceValidate = new JLabel("");
		priceValidate.setForeground(Color.RED);
		GridBagConstraints gbc_priceValidate = new GridBagConstraints();
		gbc_priceValidate.insets = new Insets(0, 0, 5, 0);
		gbc_priceValidate.gridwidth = 2;
		gbc_priceValidate.gridx = 0;
		gbc_priceValidate.gridy = 8;
		contentPane.add(priceValidate, gbc_priceValidate);
		
		infoLbl = new JLabel("Info (Optional)");
		GridBagConstraints gbc_infoLbl = new GridBagConstraints();
		gbc_infoLbl.anchor = GridBagConstraints.WEST;
		gbc_infoLbl.insets = new Insets(0, 0, 5, 5);
		gbc_infoLbl.gridx = 0;
		gbc_infoLbl.gridy = 9;
		contentPane.add(infoLbl, gbc_infoLbl);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 9;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		infoTa = new JTextArea();
		infoTa.setLineWrap(true);
		scrollPane.setViewportView(infoTa);
		
		infoValidate = new JLabel("");
		infoValidate.setForeground(Color.RED);
		GridBagConstraints gbc_infoValidate = new GridBagConstraints();
		gbc_infoValidate.insets = new Insets(0, 0, 5, 0);
		gbc_infoValidate.gridwidth = 2;
		gbc_infoValidate.gridx = 0;
		gbc_infoValidate.gridy = 10;
		contentPane.add(infoValidate, gbc_infoValidate);
		
		submitBtn = new JButton("Submit");
		submitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submitBtnActionPerformed(e);
			}
		});
		GridBagConstraints gbc_submitBtn = new GridBagConstraints();
		gbc_submitBtn.gridwidth = 2;
		gbc_submitBtn.gridx = 0;
		gbc_submitBtn.gridy = 11;
		contentPane.add(submitBtn, gbc_submitBtn);
		
		this.addWindowListener(new WindowAdapter() {
			 @Override
	            public void windowClosed(WindowEvent e) {
	                mainFrame.setEnabled(true);
	                mainFrame.setVisible(true);
//	                homeFrame.setEnabled(true);
//	                homeFrame.setVisible(true);
	            }		
		});
	}
	
	public void setupData() {
		var cateDb = HelperFunction.getCategoryDb();
		var dao = new ProductNameDao();
		var proNameDb = dao.getDb();
		
		for(var pro : proNameDb) {
			nameCb.addItem(new ComboItem(pro.getName(), pro.getId()));			
		}
		
		for(var cate : cateDb) {
			categoryCb.addItem(new ComboItem(cate.getName(), cate.getId()));
		}
		
	}

	protected void submitBtnActionPerformed(ActionEvent e) {
		var dao = new ProductDao();
		var pro = new Product();
		
		boolean quantityVali = Validation.isPositiveNumber(quantityTf, quantityValidate);
		boolean buyPriceVali = Validation.isPositiveNumber(buyPriceTf, buyPriceValidate);
		boolean priceVali = Validation.isPositiveNumber(priceTf, priceValidate);	
		boolean infoVali = Validation.stringLengthValidate(infoTa, infoValidate, 200);
		
		if(quantityVali) {
			pro.setQuantity(Integer.parseInt(quantityTf.getText()));
		}
		
		if(buyPriceVali) {
			pro.setBuyPrice(Integer.parseInt(buyPriceTf.getText()));
		}
		
		if(priceVali) {
			pro.setPrice(Integer.parseInt(priceTf.getText()));
		}
		
		if(infoVali) {
			pro.setInformation(infoTa.getText());
		}
		
		pro.setProductNameId(((ComboItem)nameCb.getSelectedItem()).getValue());
		
		pro.setCategoryId(((ComboItem)categoryCb.getSelectedItem()).getValue());
		
		pro.setAvailable(isAvailableCb.getSelectedItem().toString().equals("Yes")? true : false);
		
		

		if(quantityVali && buyPriceVali && priceVali && infoVali) {
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
