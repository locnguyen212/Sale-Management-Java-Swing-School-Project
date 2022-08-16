package gui.orderDetail;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.CategoryDao;
import dao.OrderDao;
import dao.OrderDetailDao;
import dao.ProductDao;
import dao.ProductNameDao;
import entity.Category;
import entity.OrderDetail;
import entity.Product;
import entity.ProductName;
import entity.Staff;
import helper.Validation;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;

public class UpdateOrderDetail extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JComboBox comboBoxCategory;
	private JComboBox comboBoxProduct;
	private OrderDetail oderdetail;
	private MainFrameOD mainFrame;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					InsertOrderDetail frame = new InsertOrderDetail();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	
	public void setMainFrame(MainFrameOD mainFrame) {
		this.mainFrame = mainFrame;
	}
	public UpdateOrderDetail() {
		loadComponent();
	}
	public UpdateOrderDetail(OrderDetail odd) {
		oderdetail = odd;
		loadComponent();
		textField.setText(String.valueOf(oderdetail.getQuantity()));
	}

	public void loadComponent() {
		setTitle("Update OrderDetail");
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblProductId = new JLabel("Product: ");
		
		JLabel lblQuantity = new JLabel("Quantity: ");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {btnUpdateActionPerformed(e);
			}
		});
		
		JLabel lblCategory = new JLabel("Category: ");
		
	    comboBoxCategory = new JComboBox();
	    loadCategory();
		
		comboBoxProduct = new JComboBox();
		loadProduct();
		comboBoxCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxProduct.removeAllItems();		
				loadProduct();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(94)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblCategory, GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
						.addComponent(lblProductId, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblQuantity, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(comboBoxCategory, 0, 274, Short.MAX_VALUE)
						.addComponent(comboBoxProduct, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(textField))
					.addContainerGap(123, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(255, Short.MAX_VALUE)
					.addComponent(btnUpdate, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
					.addGap(224))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(80)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCategory, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBoxCategory, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(20)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblProductId, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBoxProduct, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblQuantity, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(63)
					.addComponent(btnUpdate)
					.addContainerGap(86, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		
		this.addWindowListener(new WindowAdapter() {
			 @Override
	            public void windowClosed(WindowEvent e) {
	                mainFrame.setEnabled(true);
	                mainFrame.setVisible(true);
	            }		
		});
	}

	protected void btnUpdateActionPerformed(ActionEvent e)  {
		int result  = JOptionPane.showConfirmDialog(null, "Would you like to update OrderDetail", "Yes/No Question", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if(result  == JOptionPane.YES_OPTION) {
			OrderDetailDao orderDetailDao = new OrderDetailDao();
			ProductDao productDao = new ProductDao();
			Product product = productDao.getDb().stream()
		  .filter(pro -> Integer.valueOf(((ComboItem)comboBoxProduct.getSelectedItem()).getValue())==(pro.getId()))
		  .findAny()
		  .orElse(null);
			int quantity = 0;
			if(oderdetail.getProductId()==product.getId()) {
				quantity = -oderdetail.getQuantity();
			}else {
			    quantity = 0;
			}
					OrderDetail od = new OrderDetail();
				if(Validation.checkTextQuantity(textField,product.getQuantity(),quantity,product.getQuantitySold())) {
					od.setProductId(product.getId());
					od.setQuantity(Integer.parseInt(textField.getText()));
					od.setOrderId(oderdetail.getOrderId());
					od.setId(oderdetail.getId());
					orderDetailDao.updateOrderDetail(od);
			if(oderdetail.getProductId()==product.getId()) {
				orderDetailDao.InsertQuantitySoldUpdate(Integer.parseInt(textField.getText())-oderdetail.getQuantity(),product.getId());
				JOptionPane.showMessageDialog(null, "Updated Succes");
			}else{
				Product pro = productDao.getDb().stream()
						  .filter(product1 -> oderdetail.getProductId()==(product1.getId()))
						  .findAny()
						  .orElse(null);
				orderDetailDao.InsertQuantitySoldUpdate(0-oderdetail.getQuantity(),pro.getId());
				orderDetailDao.InsertQuantitySoldUpdate(Integer.parseInt(textField.getText()),product.getId());
				JOptionPane.showMessageDialog(null, "Updated Succes");	
			}
			this.setVisible(false);
			
			}
			
		}
	}
    public void loadCategory() {
    	CategoryDao catedao = new CategoryDao();
    	ProductDao prodao = new ProductDao();
    	for(var cate:catedao.getlistCate()) {
    		comboBoxCategory.addItem(cate.getName());
    	}
    	Product pro = prodao.getDb().stream()
    			  .filter(product -> oderdetail.getProductId()==(product.getId()))
    			  .findAny()
    			  .orElse(null);	
    	Category cate = catedao.getlistCate().stream()
  			  .filter(category -> pro.getCategoryId()==category.getId())
  			  .findAny()
  			  .orElse(null);	
    	
    	comboBoxCategory.setSelectedItem(cate.getName());;
    }
    public void loadProduct() {
    	CategoryDao dao = new CategoryDao();
    	String value = comboBoxCategory.getSelectedItem().toString();
    	Category category = dao.getlistCate().stream()
    			  .filter(cate -> value.equals(cate.getName()))
    			  .findAny()
    			  .orElse(null);
    	ProductNameDao prnamedao = new ProductNameDao();
    	ProductDao prdao = new ProductDao();
    	for(var productname : prnamedao.selectProductNameWithIdCategory(category.getId())){
    		Product pro = prdao.getDb().stream()
  	  			  .filter(product -> productname.getId()==(product.getProductNameId()))
  	  			  .findAny()
  	  			  .orElse(null);
    		comboBoxProduct.addItem(new ComboItem(String.valueOf(productname.getId())+"-"+productname.getName(),String.valueOf(pro.getId())));
    		}
    	Product pro = prdao.getDb().stream()
	  			  .filter(product -> product.getId()==(oderdetail.getProductId()))
	  			  .findAny()
	  			  .orElse(null);
    	ProductName proname =prnamedao.getDb().stream()
	  			  .filter(productname -> productname.getId()==(pro.getProductNameId()))
	  			  .findAny()
	  			  .orElse(null);
    	var selectedName = new ComboItem(String.valueOf(proname.getId())+"-"+proname.getName(), String.valueOf(oderdetail.getProductId()));
    	comboBoxProduct.getModel().setSelectedItem(selectedName);
    	
   

   }


}
