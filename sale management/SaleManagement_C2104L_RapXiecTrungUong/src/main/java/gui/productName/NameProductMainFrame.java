package gui.productName;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

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

import dao.ImageDao;
import dao.ProductDao;
import dao.ProductNameDao;
import entity.ProductName;
import helper.HelperFunction;
import helper.Validation;
import helper.loc.CrudValidate;

import java.awt.event.ActionListener;
import java.io.File;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.awt.event.ActionEvent;


public class NameProductMainFrame extends JFrame {

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
	private JLabel noteLbl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NameProductMainFrame frame = new NameProductMainFrame();
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
	public NameProductMainFrame() {
		setResizable(false);
		setTitle("Product Name");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 625);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(new TitledBorder(null, "Data", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane.setBounds(10, 11, 755, 477);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(table);
		
		panel = new JPanel();
		panel.setBounds(775, 11, 184, 387);
		contentPane.add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		searchLbl = new JLabel("Search");
		GridBagConstraints gbc_searchLbl = new GridBagConstraints();
		gbc_searchLbl.insets = new Insets(0, 0, 5, 5);
		gbc_searchLbl.anchor = GridBagConstraints.EAST;
		gbc_searchLbl.gridx = 0;
		gbc_searchLbl.gridy = 0;
		panel.add(searchLbl, gbc_searchLbl);
		
		searchTf = new JTextField();
		searchTf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchTfActionPerformed(e);
			}
		});
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
		gbc_deleteValidate.gridwidth = 2;
		gbc_deleteValidate.insets = new Insets(0, 0, 0, 5);
		gbc_deleteValidate.gridx = 0;
		gbc_deleteValidate.gridy = 9;
		panel.add(deleteValidate, gbc_deleteValidate);
		
		exitBtn = new JButton("Exit");
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exitBtnActionPerformed(e);
			}
		});
		exitBtn.setBounds(775, 465, 184, 23);
		contentPane.add(exitBtn);
		
		noteLbl = new JLabel("<html>-You can't update multiple rows<br/>-Since the Product Name contain important data, you can only delete one row at a time, and only when the Product Name contain no Product<br/>-Delete Product Name mean you will delete all Images belong to it</html>");
		noteLbl.setBounds(20, 499, 745, 63);
		contentPane.add(noteLbl);
	}
	
	//============================
	//============================Universal method
	//============================
	
	public void loadDb() {
		var dao = new ProductNameDao();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"); 
		DefaultTableModel model = new DefaultTableModel(){
		    @Override
		    public Class<?> getColumnClass(int column) {
		        switch (column) {
		        	case 0: return Integer.class;
		            case 4: return Boolean.class;
		            default: return String.class;
		        }
		    }
		};;
		
		for(var column : HelperFunction.getColumnList("product_name")) {
			model.addColumn(column);
		}
		
		model.addColumn("Selected");
		
		List<ProductName> proList = dao.getDb();

		proList.forEach(element -> {
			model.addRow(new Object[] {
								element.getId(),
								element.getName(), 
								element.getCreatedAt()==null? "None" : dtf.format(element.getCreatedAt()), 
								element.getUpdatedAt()==null? "None" : dtf.format(element.getUpdatedAt()),
								false
								});
		});
		table.setDefaultEditor(Object.class, null);
		table.setModel(model);
		
		var centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
	}
	
	public void deleteImage(int id) {
		var imgDao = new ImageDao();
		for(var img : imgDao.getDb()) {
			if(img.getProductNameId() == id) {
				File f = new File(System.getProperty("user.dir") + img.getName());
				f.delete();
				imgDao.delete("image", img.getId());
			}
		}
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
	}
	
	//============================
	//============================Insert btn
	//============================
	
	protected void insertBtnActionPerformed(ActionEvent e) {
		deleteValidate.setText("");
		updateValidate.setText("");
		searchValidate.setText("");
				
		if(table.getColumnCount() == 0) {
			insertValidate.setText("*Please load data first!");
		}else {
			var dao = new ProductNameDao();
			List<ProductName> proList = dao.getDb();
			
			insertValidate.setText("");
			InsertFrame insert = new InsertFrame();
			insert.setMainFrame(this);
			insert.setProList(proList);
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
		if(Validation.isSelectedActived(table) && Validation.selectedCount(table) == 1) {
			updateValidate.setText("");
			
			var dao = new ProductNameDao();
			List<ProductName> proList = dao.getDb();
			
			//get id then get product name
			int id = HelperFunction.getId(table);
			var pro = HelperFunction.getProductName(id, dao);
			
			var upd = new UpdateFrame();
			upd.setProList(proList);
			upd.setPro(pro);
			upd.setupData();
			upd.setMainFrame(this);
			upd.setLocation(this.getX(), this.getY());
			upd.setVisible(true);
			this.setEnabled(false);	
			
			
		}else if(Validation.isSelectedActived(table)  && Validation.selectedCount(table) >= 2){		
			updateValidate.setText("*You can update one row only!");			
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
		if(Validation.isSelectedActived(table)  && Validation.selectedCount(table) == 1) {
			deleteValidate.setText("");
			var productDao = new ProductDao();
			
			//get id of product name then find all product the the id from product name			
			int id = HelperFunction.getId(table);
			boolean isContainProduct = CrudValidate.isProductNameContainProduct(id, productDao.getDb(), deleteValidate);
			
			
			if(!isContainProduct) {
				int i = JOptionPane.showConfirmDialog(this, "Are you sure?", "Info", JOptionPane.OK_OPTION);
				if(i == JOptionPane.OK_OPTION) {
					//delete image the belong to the product name
					deleteImage(id);
					var dao = new ProductNameDao();
					dao.delete("product_name", id);
					loadDb();
					JOptionPane.showMessageDialog(this, "Done!");
				}
			}
	
		}else if(Validation.isSelectedActived(table)  && Validation.selectedCount(table) >= 2){
			deleteValidate.setText("<html>*You can delete only one<br/> row at a time</html>");
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
}
