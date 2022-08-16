package gui.product;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.DefaultRowSorter;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dao.ProductDao;
import dao.PropertiesDao;
import entity.Product;
import gui.productProperties.PpMainFrame;
import gui.properties.PropertiesInsertFrame;
import gui.properties.PropertiesUpdateFrame;
import gui.properties.PropertiesUpdateManyFrame;

import gui.productName.NameProductMainFrame;

import helper.HelperFunction;
import helper.Validation;
import helper.loc.CrudValidate;
import service.jFrame.MainFrame;
import javax.swing.JTabbedPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



public class ProductMainFrame extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable table;
	private JPanel panel;
	private JLabel searchLbl;
	private JTextField searchTf;
	private JLabel searchValidate;
	private JButton loadBtn;
	private JLabel loadValidate;
	private JButton insertBtn;
	private JLabel insertValidate;
	private JButton updateBtn;
	private JLabel updateValidate;
	private JButton deleteBtn;
	private JLabel deleteValidate;
	private JButton exitBtn;
	private JButton viewPropertiesBtn;
	private JLabel viewPropertiesValidate;
	private List<Product> proList;
	private JButton viewInfoBtn;
	private JLabel viewInfoValidate;
	private JButton searchByPropertiesBtn;
	private JLabel noteLbl;
	private JButton ProductName;
	private ProductDao productDao;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductMainFrame frame = new ProductMainFrame();
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
	public ProductMainFrame() {
		setTitle("Product");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1100, 579);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(new TitledBorder(null, "Data", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane.setBounds(10, 11, 826, 477);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(table);
		
		panel = new JPanel();
		panel.setBounds(874, 65, 184, 357);
		contentPane.add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		searchTf = new JTextField();
		searchTf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchTfActionPerformed(e);
			}
		});
		
		searchLbl = new JLabel("Search");
		GridBagConstraints gbc_searchLbl = new GridBagConstraints();
		gbc_searchLbl.insets = new Insets(0, 0, 5, 5);
		gbc_searchLbl.gridx = 0;
		gbc_searchLbl.gridy = 0;
		panel.add(searchLbl, gbc_searchLbl);
		GridBagConstraints gbc_searchTf = new GridBagConstraints();
		gbc_searchTf.insets = new Insets(0, 0, 5, 0);
		gbc_searchTf.fill = GridBagConstraints.HORIZONTAL;
		gbc_searchTf.gridx = 1;
		gbc_searchTf.gridy = 0;
		panel.add(searchTf, gbc_searchTf);
		searchTf.setColumns(10);
		
		searchValidate = new JLabel("");
		searchValidate.setForeground(Color.RED);
		GridBagConstraints gbc_searchValidate = new GridBagConstraints();
		gbc_searchValidate.insets = new Insets(0, 0, 5, 0);
		gbc_searchValidate.gridwidth = 2;
		gbc_searchValidate.gridx = 0;
		gbc_searchValidate.gridy = 1;
		panel.add(searchValidate, gbc_searchValidate);
		
		loadBtn = new JButton("Load/Refresh data");
		loadBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadBtnActionPerformed(e);
			}
		});
		GridBagConstraints gbc_loadBtn = new GridBagConstraints();
		gbc_loadBtn.insets = new Insets(0, 0, 5, 0);
		gbc_loadBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_loadBtn.gridwidth = 2;
		gbc_loadBtn.gridx = 0;
		gbc_loadBtn.gridy = 2;
		panel.add(loadBtn, gbc_loadBtn);
		
		loadValidate = new JLabel("");
		loadValidate.setForeground(Color.RED);
		GridBagConstraints gbc_loadValidate = new GridBagConstraints();
		gbc_loadValidate.insets = new Insets(0, 0, 5, 0);
		gbc_loadValidate.gridwidth = 2;
		gbc_loadValidate.gridx = 0;
		gbc_loadValidate.gridy = 3;
		panel.add(loadValidate, gbc_loadValidate);
		
		insertBtn = new JButton("Insert");
		insertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertBtnActionPerformed(e);
			}
		});
		GridBagConstraints gbc_insertBtn = new GridBagConstraints();
		gbc_insertBtn.insets = new Insets(0, 0, 5, 0);
		gbc_insertBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_insertBtn.gridwidth = 2;
		gbc_insertBtn.gridx = 0;
		gbc_insertBtn.gridy = 4;
		panel.add(insertBtn, gbc_insertBtn);
		
		insertValidate = new JLabel("");
		insertValidate.setForeground(Color.RED);
		GridBagConstraints gbc_insertValidate = new GridBagConstraints();
		gbc_insertValidate.insets = new Insets(0, 0, 5, 0);
		gbc_insertValidate.gridwidth = 2;
		gbc_insertValidate.gridx = 0;
		gbc_insertValidate.gridy = 5;
		panel.add(insertValidate, gbc_insertValidate);
		
		updateBtn = new JButton("Update");
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateBtnActionPerformed(e);
			}
		});
		GridBagConstraints gbc_updateBtn = new GridBagConstraints();
		gbc_updateBtn.insets = new Insets(0, 0, 5, 0);
		gbc_updateBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_updateBtn.gridwidth = 2;
		gbc_updateBtn.gridx = 0;
		gbc_updateBtn.gridy = 6;
		panel.add(updateBtn, gbc_updateBtn);
		
		updateValidate = new JLabel("");
		updateValidate.setForeground(Color.RED);
		GridBagConstraints gbc_updateValidate = new GridBagConstraints();
		gbc_updateValidate.insets = new Insets(0, 0, 5, 0);
		gbc_updateValidate.gridwidth = 2;
		gbc_updateValidate.gridx = 0;
		gbc_updateValidate.gridy = 7;
		panel.add(updateValidate, gbc_updateValidate);
		
		deleteBtn = new JButton("Delete");
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteBtnActionPerformed(e);
			}
		});
		GridBagConstraints gbc_deleteBtn = new GridBagConstraints();
		gbc_deleteBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_deleteBtn.insets = new Insets(0, 0, 5, 0);
		gbc_deleteBtn.gridwidth = 2;
		gbc_deleteBtn.gridx = 0;
		gbc_deleteBtn.gridy = 8;
		panel.add(deleteBtn, gbc_deleteBtn);
		
		deleteValidate = new JLabel("");
		deleteValidate.setForeground(Color.RED);
		GridBagConstraints gbc_deleteValidate = new GridBagConstraints();
		gbc_deleteValidate.insets = new Insets(0, 0, 5, 0);
		gbc_deleteValidate.gridwidth = 2;
		gbc_deleteValidate.gridx = 0;
		gbc_deleteValidate.gridy = 9;
		panel.add(deleteValidate, gbc_deleteValidate);
		
		viewPropertiesBtn = new JButton("View properties");
		viewPropertiesBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewPropertiesBtnActionPerformed(e);
			}
		});
		GridBagConstraints gbc_viewPropertiesBtn = new GridBagConstraints();
		gbc_viewPropertiesBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_viewPropertiesBtn.insets = new Insets(0, 0, 5, 0);
		gbc_viewPropertiesBtn.gridwidth = 2;
		gbc_viewPropertiesBtn.gridx = 0;
		gbc_viewPropertiesBtn.gridy = 10;
		panel.add(viewPropertiesBtn, gbc_viewPropertiesBtn);
		
		viewPropertiesValidate = new JLabel("");
		viewPropertiesValidate.setForeground(Color.RED);
		GridBagConstraints gbc_viewPropertiesValidate = new GridBagConstraints();
		gbc_viewPropertiesValidate.insets = new Insets(0, 0, 5, 0);
		gbc_viewPropertiesValidate.gridwidth = 2;
		gbc_viewPropertiesValidate.gridx = 0;
		gbc_viewPropertiesValidate.gridy = 11;
		panel.add(viewPropertiesValidate, gbc_viewPropertiesValidate);
		
		viewInfoBtn = new JButton("View info");
		viewInfoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewInfoBtnActionPerformed(e);
			}
		});
		GridBagConstraints gbc_viewInfoBtn = new GridBagConstraints();
		gbc_viewInfoBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_viewInfoBtn.insets = new Insets(0, 0, 5, 0);
		gbc_viewInfoBtn.gridwidth = 2;
		gbc_viewInfoBtn.gridx = 0;
		gbc_viewInfoBtn.gridy = 12;
		panel.add(viewInfoBtn, gbc_viewInfoBtn);
		
		viewInfoValidate = new JLabel("");
		viewInfoValidate.setForeground(Color.RED);
		GridBagConstraints gbc_viewInfoValidate = new GridBagConstraints();
		gbc_viewInfoValidate.insets = new Insets(0, 0, 5, 0);
		gbc_viewInfoValidate.gridwidth = 2;
		gbc_viewInfoValidate.gridx = 0;
		gbc_viewInfoValidate.gridy = 13;
		panel.add(viewInfoValidate, gbc_viewInfoValidate);
		
		exitBtn = new JButton("Exit");
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exitBtnActionPerformed(e);
			}
		});
		exitBtn.setBounds(874, 465, 184, 23);
		contentPane.add(exitBtn);
		
		searchByPropertiesBtn = new JButton("Search by properties");
		searchByPropertiesBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchByPropertiesBtnActionPerformed(e);
			}
		});
		searchByPropertiesBtn.setBounds(874, 11, 184, 23);
		contentPane.add(searchByPropertiesBtn);
		
		noteLbl = new JLabel("<html>"
				+ "Note: You cannot view many properties and info"
				+ "</html>");
		noteLbl.setBounds(10, 499, 826, 34);
		contentPane.add(noteLbl);
		
		ProductName = new JButton("insert Product Name");
		ProductName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				NameProductMainFrame NM = new NameProductMainFrame();
				NM.setVisible(true);
			}
		});
		ProductName.setBounds(874, 39, 184, 21);
		contentPane.add(ProductName);
		 
		
		

	}
	
		//============================
		//============================Universal method
		//============================

		public void loadDb() {
			var dao = new ProductDao();
			productDao = dao;
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"); 
			DefaultTableModel model = new DefaultTableModel(){
			    @Override
			    public Class<?> getColumnClass(int column) {
			        switch (column) {
				        case 0: return Integer.class;
				        case 3: return Integer.class;
				        case 4: return Integer.class;
				        case 6: return Integer.class;
				        case 7: return Integer.class;
			            case 11: return Boolean.class;
			            default: return String.class;
			        }
			    }
			};
			
			for(var column : HelperFunction.getColumnList("product")) {
				model.addColumn(column);
			}
			
			model.addColumn("Selected");
			
			proList = productDao.getDb();
	 
			proList.forEach(element -> {
				model.addRow(new Object[] {
									element.getId(),
									HelperFunction.getCategory(element.getCategoryId()).getName(),
									HelperFunction.getProductName(element.getProductNameId()).getName(),
									element.getQuantity(),
									element.getQuantitySold(),
									element.isAvailable()? "Yes" : "No",
									element.getBuyPrice(),
									element.getPrice(),
									element.getInformation().equals("")? "No" : "Yes",
									element.getCreatedAt()==null? "None" : dtf.format(element.getCreatedAt()), 
									element.getUpdatedAt()==null? "None" : dtf.format(element.getUpdatedAt()),
									false
									});
			});
			table.setDefaultEditor(Object.class, null);
			table.setModel(model);
			
			var centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(JLabel.CENTER);
			table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
			table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
			table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
			table.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
			table.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
			
		}
		
		public void loadDbSearch(String idsList, int idsListSize) {
			var dao = new ProductDao();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"); 
			DefaultTableModel model = new DefaultTableModel(){
			    @Override
			    public Class<?> getColumnClass(int column) {
			        switch (column) {
			            case 11: return Boolean.class;
			            default: return String.class;
			        }
			    }
			};;
			
			for(var column : HelperFunction.getColumnList("product")) {
				model.addColumn(column);
			}
			
			model.addColumn("Selected");
			
			proList = dao.getDbSearch(idsList, idsListSize);
	 
			proList.forEach(element -> {
				model.addRow(new Object[] {
									element.getId(),
									HelperFunction.getCategory(element.getCategoryId()).getName(),
									HelperFunction.getProductName(element.getProductNameId()).getName(),
									element.getQuantity(),
									element.getQuantitySold(),
									element.isAvailable()? "Yes" : "No",
									element.getBuyPrice(),
									element.getPrice(),
									element.getInformation().equals("")? "No" : "Yes",
									element.getCreatedAt()==null? "None" : dtf.format(element.getCreatedAt()), 
									element.getUpdatedAt()==null? "None" : dtf.format(element.getUpdatedAt()),
									false
									});
			});
			table.setDefaultEditor(Object.class, null);
			table.setModel(model);
			
		}
	//============================
	//============================Load btn
	//============================
	
	protected void loadBtnActionPerformed(ActionEvent e) {
		loadDb();
		deleteValidate.setText("");
		insertValidate.setText("");
		updateValidate.setText("");
		searchValidate.setText("");
		viewInfoValidate.setText("");
		viewPropertiesValidate.setText("");
	}
	
	//============================
	//============================Insert btn
	//============================
		
	protected void insertBtnActionPerformed(ActionEvent e) {
		deleteValidate.setText("");
		updateValidate.setText("");
		searchValidate.setText("");
		viewInfoValidate.setText("");
		viewPropertiesValidate.setText("");
		if(table.getColumnCount() == 0) {
			insertValidate.setText("*Please load data first!");
		}else {
			insertValidate.setText("");
			
			var insert = new ProductInsertFrame();
			insert.setMainFrame(this);

			insert.setupData();
			insert.setLocation(this.getX(), this.getY());
			insert.setVisible(true);
			this.setEnabled(false);
		}
	}
	
	//============================
	//============================Update btn
	//============================
	
	protected void updateBtnActionPerformed(ActionEvent e) {
		deleteValidate.setText("");
		insertValidate.setText("");
		searchValidate.setText("");
		viewInfoValidate.setText("");
		viewPropertiesValidate.setText("");
		if(Validation.isSelectedActived(table) && Validation.selectedCount(table) == 1) {
			updateValidate.setText("");

			//get property id then get property
			int id = HelperFunction.getId(table);
			var pro = HelperFunction.getProduct(id);
			
			//call the update frame
			var upd = new ProductUpdateFrame();
			upd.setProduct(pro);
			upd.setMainFrame(this);
			upd.setupData();
			upd.setLocation(this.getX(), this.getY());
			upd.setVisible(true);
			this.setEnabled(false);	
			
			
		}else if(Validation.isSelectedActived(table)  && Validation.selectedCount(table) >= 2){		
			updateValidate.setText("");
					
			//call the update many frame
			String id = HelperFunction.getIdsString(table);
			var upd = new ProductUpdateManyFrame();
			upd.setIds(id);
			upd.setupData();
			upd.setMainFrame(this);
			upd.setLocation(this.getX(), this.getY());
			upd.setVisible(true);
			this.setEnabled(false);	

		}else{
			updateValidate.setText("*Please select a row to update!");
		}	
	}
	
	//============================
	//============================Delete btn
	//============================
	
	protected void deleteBtnActionPerformed(ActionEvent e) {
		updateValidate.setText("");
		insertValidate.setText("");
		viewInfoValidate.setText("");
		searchValidate.setText("");
		viewPropertiesValidate.setText("");
		if(Validation.isSelectedActived(table)  && Validation.selectedCount(table) == 1) {
			deleteValidate.setText("");
			
			//get id
			int id = HelperFunction.getId(table);
			//validate if product contain order detail
			boolean isContainOd = CrudValidate.isProductContainOd(id, deleteValidate);
			//validate if product contain product-properties
			boolean isContainPp = CrudValidate.isProductContainPp(id, deleteValidate);
			
			if(!isContainOd && !isContainPp) {
				//call the delete method
				int i = JOptionPane.showConfirmDialog(this, "Are you sure?", "Info", JOptionPane.OK_OPTION);
				if(i == JOptionPane.OK_OPTION) {
					var dao = new ProductDao();
					dao.delete("product", id);
					loadDb();
					JOptionPane.showMessageDialog(this, "Done!");
				}
			}
	
		}else if(Validation.isSelectedActived(table)  && Validation.selectedCount(table) >= 2){
			deleteValidate.setText("");		
			
			//get list of id
			List<Integer> ids = HelperFunction.getIdsList(table);
			
			//validate if product contain order detail
			boolean isContainOd = CrudValidate.isProductContainOd(ids, deleteValidate);
			//validate if product contain product-properties
			boolean isContainPp = CrudValidate.isProductContainPp(ids, deleteValidate);
			
			if(!isContainOd && !isContainPp) {
				//call the delete many method
				int i = JOptionPane.showConfirmDialog(this, "Are you sure?", "Info", JOptionPane.OK_OPTION);
				if(i == JOptionPane.OK_OPTION) {
					var dao = new ProductDao();
					String idString = HelperFunction.getIdsString(table);
					dao.delete(idString);
					loadDb();
					JOptionPane.showMessageDialog(this, "Done!");
				}
			}	
		}else {
			deleteValidate.setText("*Please select a row to delete!");
		}
	}
	
	//============================
	//============================Search 
	//============================
	
	protected void searchTfActionPerformed(ActionEvent e) {
		if(table.getColumnCount()>=1) {
			searchValidate.setText("");
			String find = searchTf.getText();
			DefaultRowSorter<?, ?> sorter = (DefaultRowSorter<?, ?>) table.getRowSorter();
			sorter.setRowFilter(RowFilter.regexFilter(find));
			sorter.setSortKeys(null);
		}else {
			searchValidate.setText("*There is no data yet");
		}
	}
	
	//============================
	//============================Exit btn
	//============================
	
	protected void exitBtnActionPerformed(ActionEvent e) {
		this.dispose();
	}
	
	//============================
	//============================View info btn
	//============================
	
	protected void viewInfoBtnActionPerformed(ActionEvent e) {	
		deleteValidate.setText("");
		insertValidate.setText("");
		updateValidate.setText("");
		searchValidate.setText("");
		viewPropertiesValidate.setText("");
		if(Validation.isSelectedActived(table) && Validation.selectedCount(table) == 1) {
			viewInfoValidate.setText("");
			//get property id then get property
			int id = HelperFunction.getId(table);
			var pro = HelperFunction.getProduct(id);
			
			if(!pro.getInformation().equals("")) {
				var view = new ProductViewInfoFrame();
				view.setMainFrame(this);
				view.setup(pro.getInformation());
				view.setLocation(this.getX(), this.getY());
				view.setVisible(true);
				this.setEnabled(false);	
			}else {
				viewInfoValidate.setText("*There is no info to view");
			}
		}else if (Validation.isSelectedActived(table)  && Validation.selectedCount(table) >= 2) {
			viewInfoValidate.setText("*You can view one info only");
		}else {
			viewInfoValidate.setText("*Select a product to view info");
		}
	}
	
	//============================
	//============================View properties btn
	//============================
	
	protected void viewPropertiesBtnActionPerformed(ActionEvent e) {
		updateValidate.setText("");
		insertValidate.setText("");
		viewInfoValidate.setText("");
		searchValidate.setText("");
		deleteValidate.setText("");
		if(Validation.isSelectedActived(table)  && Validation.selectedCount(table) == 1) {
			viewPropertiesValidate.setText("");
			
			//get id
			int id = HelperFunction.getId(table);
	
			//call the view method
			var view = new PpMainFrame();
			view.setMainFrame(this);
			view.setProductId(id);
			view.loadDb();
			view.setLocation(this.getX(), this.getY());
			view.setVisible(true);
			this.setEnabled(false);

	
		}else if(Validation.isSelectedActived(table)  && Validation.selectedCount(table) >= 2){
			viewPropertiesValidate.setText("<html>*You can view properties<br/> of one product only</html>");		
		}else {
			viewPropertiesValidate.setText("*Please select a row to view!");
		}
	}
	
	//============================
	//============================Search by properties btn
	//============================
	
	protected void searchByPropertiesBtnActionPerformed(ActionEvent e) {
		var search = new SearchByPropertiesFrame();
		search.setMainFrame(this);
		search.setLocation(this.getX(), this.getY());
		search.setVisible(true);
		this.setEnabled(false);
	}

	
}
