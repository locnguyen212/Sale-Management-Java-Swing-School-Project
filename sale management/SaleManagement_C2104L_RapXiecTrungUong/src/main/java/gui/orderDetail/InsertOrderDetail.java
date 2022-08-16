package gui.orderDetail;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import dao.CategoryDao;
import dao.OrderDao;
import dao.OrderDetailDao;
import dao.ProductDao;
import dao.ProductNameDao;
import entity.Category;
import entity.Order;
import entity.OrderDetail;
import entity.Product;
import entity.ProductName;
import entity.Staff;
import gui.order.MainFrame;
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
import java.text.DecimalFormat;
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
import java.awt.Dimension;

public class InsertOrderDetail extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	private JComboBox comboBoxCategory;
	private JComboBox comboBoxProduct;
	static Order order;
	private JLabel lblTotal ;
	private JComboBox comboBoxOrderNumber;
	private MainFrameOD mainFrame;
	public List<ShopCart> list = new ArrayList<ShopCart>();

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
	
	public InsertOrderDetail() {
		loadComponent();
	}
	public InsertOrderDetail(Order od) {
		order = od;
		loadComponent();
	}

	public void loadComponent() {
		setTitle("Insert OrderDetail");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		this.addWindowListener(new WindowAdapter() {
			 @Override
	            public void windowClosed(WindowEvent e) {
	                mainFrame.setEnabled(true);
	                mainFrame.setVisible(true);
	            }		
		});
		
		
		JLabel lblProductId = new JLabel("Product: ");
		
		JLabel lblQuantity = new JLabel("Quantity: ");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setPreferredSize(new Dimension(97, 23));
		btnSubmit.setMinimumSize(new Dimension(97, 23));
		btnSubmit.setMaximumSize(new Dimension(97, 23));
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {btnSubmitActionPerformed(e);
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
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnAdd = new JButton("Add Item");
		btnAdd.setMaximumSize(new Dimension(97, 23));
		btnAdd.setMinimumSize(new Dimension(97, 23));
		btnAdd.setPreferredSize(new Dimension(97, 23));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAddActionPerformed(e);
			}
		});
		
		JLabel lblNewLabel = new JLabel("Total :");
		
		lblTotal = new JLabel("");
		
		JButton btnRemove = new JButton("Remove Item");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeItemActionPerformed(e);
			}
		});
		
		JLabel lblOrderNumber = new JLabel("Order Number");
		
		comboBoxOrderNumber = new JComboBox();
		auto() ;
		comboBoxOrderNumber.setSelectedItem(order.getOrderNumber());
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(128, Short.MAX_VALUE)
					.addComponent(btnRemove)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(100)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(31)
							.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTotal, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSubmit, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE))
					.addGap(32))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(31, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 511, GroupLayout.PREFERRED_SIZE)
					.addGap(32))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(91)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblOrderNumber, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(comboBoxOrderNumber, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblQuantity, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblCategory, GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
								.addComponent(lblProductId))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textField)
								.addComponent(comboBoxCategory, 0, 274, Short.MAX_VALUE)
								.addComponent(comboBoxProduct, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
					.addContainerGap(126, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(59)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblOrderNumber, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBoxOrderNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCategory, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBoxCategory, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(20)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblProductId, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBoxProduct, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblQuantity)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTotal, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnRemove)
						.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSubmit, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		
		this.addWindowListener(new WindowAdapter() {
			 @Override
	            public void windowClosed(WindowEvent e) {
	                mainFrame.setEnabled(true);
	                mainFrame.setVisible(true);
	            }		
		});
		
		
	}

	protected void btnSubmitActionPerformed(ActionEvent e)  {
		if(table.getRowCount()==0) {	
			JOptionPane.showMessageDialog(null, "No Data to Submit","Warning",
			        JOptionPane.WARNING_MESSAGE);
			return;
		}
		int result  = JOptionPane.showConfirmDialog(null, "Would you like to insert Order", "Yes/No Question", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if(result  == JOptionPane.YES_OPTION) {
			OrderDao orderDao = new OrderDao();
		    List<OrderDetail> listOrderDetail = new ArrayList<OrderDetail>();
		    OrderDetailDao orderDetailDao = new OrderDetailDao();  
		    ProductNameDao productNameDao = new ProductNameDao();
		    ProductDao proDao = new ProductDao();
			for (int i = 0; i < table.getRowCount(); i++) {
			    OrderDetail od = new OrderDetail();
			    String products = table.getValueAt(i, 0).toString();
			    String[] arrOfproductnameId = products.split("-", 2);
			    String productNameID = arrOfproductnameId[0];
			    ProductName proname = productNameDao.getDb().stream()
		    			  .filter(pro -> Integer.valueOf(productNameID)==(pro.getId()))
		    			  .findAny()
		    			  .orElse(null);
			    Product pro = proDao.getDb().stream()
		    			  .filter(product -> proname.getId()==(product.getProductNameId()))
		    			  .findAny()
		    			  .orElse(null);
			    Order order = orderDao.getListOrder().stream()
		    			  .filter(odr -> (comboBoxOrderNumber.getSelectedItem()).equals(odr.getOrderNumber()))
		    			  .findAny()
		    			  .orElse(null);
				od.setOrderId(order.getId());
				od.setProductId(pro.getId());
				od.setQuantity(Integer.parseInt(table.getValueAt(i, 1).toString()));
				listOrderDetail.add(od);
				orderDetailDao.InsertQuantitySoldUpdate(Integer.parseInt(table.getValueAt(i, 1).toString()),pro.getId());
			}
			orderDetailDao.insertOrderDetail(listOrderDetail);
			
			this.setVisible(false);
			mainFrame.setVisible(true);
			mainFrame.loadData(orderDetailDao);
			JOptionPane.showMessageDialog(null, "Inserted Succes");
		}
	}
	protected void btnAddActionPerformed(ActionEvent e)  {
    	DefaultTableModel model = (DefaultTableModel)table.getModel();
	    Object[] columns = {"Product Name","Quantity","Prices"};
	    ProductDao prodao = new ProductDao();
	    ProductNameDao proNameDao = new ProductNameDao();
	    ShopCart shop = new ShopCart();
	    DecimalFormat formatter = new DecimalFormat("###,###,###");
	    String key = ((ComboItem)comboBoxProduct.getSelectedItem()).getValue();
	    Product pro = prodao.getDb().stream()
	  			  .filter(product -> Integer.valueOf(key)==(product.getId()))
	  			  .findAny()
	  			  .orElse(null);  
	    model.setColumnIdentifiers(columns);
	    String value = ((ComboItem)comboBoxProduct.getSelectedItem()).getKey();
	    	
	    	ShopCart item = list.stream()
	    			  .filter(st -> value.equals(st.getName()))
	    			  .findAny()
	    			  .orElse(null);
	    	if(item!=null) {
	    		if(Validation.checkTextQuantity(textField,pro.getQuantity(),item.getQuatity(),pro.getQuantitySold())) {
	    		shop.setName(value);
	    		shop.setQuatity(item.getQuatity()+Integer.valueOf(textField.getText().toString()));
	    		shop.setPrice(pro.getPrice());
	    		list.remove(item);
	    		list.add(shop);
	    	}}else {
	    		if(Validation.checkTextQuantity(textField,pro.getQuantity(),0,pro.getQuantitySold())) {
	    		shop.setName(value);
		    	shop.setQuatity(Integer.valueOf(textField.getText().toString()));
		    	shop.setPrice(pro.getPrice());
		    	list.add(shop);
	    	}
	    	}
    		for (int i = model.getRowCount() - 1; i >= 0; i--) {
    		model.removeRow(i);
    			}
    			 list.forEach(od->model.addRow(new Object[] {
    		    			od.getName(),
    		    			od.getQuatity(),
    		    			od.getPrice()
    		    			}));

	    int total = 0;
	    for(int i = 0; i<model.getRowCount();i++) {
	    	total +=  Integer.parseInt(table.getValueAt(i, 2).toString().replace(",", "")) * Integer.parseInt(table.getValueAt(i, 1).toString()) ;    	
	    }
	    
	    String totalformat = String.valueOf(formatter.format(total)+" VND");
	    lblTotal.setText(totalformat);
	    }
    public void loadCategory() {
    	CategoryDao dao = new CategoryDao();
    	ProductDao prodao = new ProductDao();
    	ProductNameDao proNameDao = new ProductNameDao();
    	for(var cate:dao.getlistCate()) {
    		comboBoxCategory.addItem(cate.getName());
    	}
    	comboBoxCategory.setSelectedIndex(0);
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
    }
    public void removeItemActionPerformed(ActionEvent e) {
    	DefaultTableModel model = (DefaultTableModel)table.getModel();
    	if(table.getSelectedRow() != -1) {
    	model.removeRow(table.getSelectedRow());
    	}
    	return;
    }
    public void auto() {
    	OrderDao dao = new OrderDao();
    	for(var numberod : dao.getListOrderNumber()){
    		comboBoxOrderNumber.addItem(numberod);
    	}
    	AutoCompleteDecorator.decorate(comboBoxOrderNumber);
    }
}
