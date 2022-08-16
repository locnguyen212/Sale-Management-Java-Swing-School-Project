package gui.orderDetail;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.swing.DefaultRowSorter;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.OrderDao;
import dao.OrderDetailDao;
import entity.Order;
import entity.OrderDetail;
import entity.Staff;

import javax.swing.border.EtchedBorder;
import java.awt.Toolkit;

public class MainFrameOD extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable table;
	private JPanel panel;
	private JLabel searchLabel;
	private JTextField searchTf;
	private JLabel searchValidate;
	private JButton loadDbBtn;
	private JButton btnInsertOrderDetail;
	private JButton btnOrderDetail;
	private JButton btnDeleteAccount;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem infoMi;
	private JButton exitBtn;
	private Order order;
	private JCheckBox selectCheck;
	private JLabel selectAll;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrameOD frame = new MainFrameOD();
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
	public MainFrameOD() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\ADMIN\\Desktop\\source final\\source\\SaleManagement_C2104L_RapXiecTrungUong\\image\\Coffee-break-icon.png"));
		loadComponent();
	}
	public MainFrameOD(Order od) {
		order = od;
		loadComponent();
		OrderDetailDao dao = new OrderDetailDao();
		loadData(dao);
	}
	public void loadComponent() {
		setTitle("Main OrderDetail");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1050, 600);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		menu = new JMenu("Help");
		menuBar.add(menu);
		
		infoMi = new JMenuItem("Get Creator Information");
		infoMi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				infoMiActionPerformed(e);
			}
		});
		menu.add(infoMi);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Orders Detail List", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		scrollPane.setBounds(10, 11, 749, 502);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(table);
		
		panel = new JPanel();
		panel.setBounds(792, 22, 271, 263);
		contentPane.add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 137, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		selectAll = new JLabel("Check All");
		GridBagConstraints gbc_selectAll = new GridBagConstraints();
		gbc_selectAll.insets = new Insets(0, 0, 5,80);
		gbc_selectAll.gridx = 1;
		gbc_selectAll.gridy = 0;
		panel.add(selectAll, gbc_selectAll);
		
		selectCheck = new JCheckBox();
		selectCheck.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectCheckMouseClicked(e);
			}
		});
		GridBagConstraints gbc_selectCheck = new GridBagConstraints();
		gbc_selectCheck.fill = GridBagConstraints.HORIZONTAL;
		gbc_selectCheck.insets = new Insets(0, 0, 5, 5);
		gbc_selectCheck.gridx = 0;
		gbc_selectCheck.gridy = 0;
		panel.add(selectCheck, gbc_selectCheck);
		
		searchLabel = new JLabel("Search");
		GridBagConstraints gbc_searchLabel = new GridBagConstraints();
		gbc_searchLabel.insets = new Insets(0, 0, 5, 5);
		gbc_searchLabel.gridx = 0;
		gbc_searchLabel.gridy = 1;
		panel.add(searchLabel, gbc_searchLabel);
		
		searchTf = new JTextField();
		searchTf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldActionPerformed(e);
			}
		});
		searchTf.setColumns(10);
		GridBagConstraints gbc_searchTf = new GridBagConstraints();
		gbc_searchTf.fill = GridBagConstraints.HORIZONTAL;
		gbc_searchTf.insets = new Insets(0, 0, 5, 0);
		gbc_searchTf.gridx = 1;
		gbc_searchTf.gridy = 1;
		panel.add(searchTf, gbc_searchTf);
		
		
		loadDbBtn = new JButton("Load OrderDetail");
		loadDbBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadDbBtnActionPerformed(e);
			}
		});
		GridBagConstraints gbc_loadDbBtn = new GridBagConstraints();
		gbc_loadDbBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_loadDbBtn.insets = new Insets(0, 0, 5, 0);
		gbc_loadDbBtn.gridwidth = 2;
		gbc_loadDbBtn.gridx = 0;
		gbc_loadDbBtn.gridy = 3;
		panel.add(loadDbBtn, gbc_loadDbBtn);
		
		btnInsertOrderDetail = new JButton("Insert OrderDetail");
		btnInsertOrderDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnInsertOrderDetailActionPerformed(e);
			}
		});
		GridBagConstraints gbc_btnInsertOrderDetail = new GridBagConstraints();
		gbc_btnInsertOrderDetail.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnInsertOrderDetail.gridwidth = 2;
		gbc_btnInsertOrderDetail.insets = new Insets(0, 0, 5, 0);
		gbc_btnInsertOrderDetail.gridx = 0;
		gbc_btnInsertOrderDetail.gridy = 4;
		panel.add(btnInsertOrderDetail, gbc_btnInsertOrderDetail);
		
		
		btnOrderDetail = new JButton("Update OrderDetail");
		btnOrderDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateOrderDetailActionPerformed(e);
			}
		});
		GridBagConstraints gbc_btnOrderDetail = new GridBagConstraints();
		gbc_btnOrderDetail.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnOrderDetail.gridwidth = 2;
		gbc_btnOrderDetail.insets = new Insets(0, 0, 5, 0);
		gbc_btnOrderDetail.gridx = 0;
		gbc_btnOrderDetail.gridy = 6;
		panel.add(btnOrderDetail, gbc_btnOrderDetail);
		
		
		btnDeleteAccount = new JButton("Delete OrderDetail");
		btnDeleteAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDeleteOrderDetailActionPerformed(e);
			}
		});
		GridBagConstraints gbc_btnDeleteAccount = new GridBagConstraints();
		gbc_btnDeleteAccount.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDeleteAccount.gridwidth = 2;
		gbc_btnDeleteAccount.insets = new Insets(0, 0, 5, 0);
		gbc_btnDeleteAccount.gridx = 0;
		gbc_btnDeleteAccount.gridy = 8;
		panel.add(btnDeleteAccount, gbc_btnDeleteAccount);
		
		
		exitBtn = new JButton("Exit");
		exitBtn.setBounds(797, 489, 176, 21);
		contentPane.add(exitBtn);
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				exitBtnActionPerformed(e);
			}
		});
	}
	public void loadData(OrderDetailDao dao) {
		DefaultTableModel model = new DefaultTableModel() {
			
			@Override
			public Class<?> getColumnClass(int column) {
				switch(column) {
				case 0: return Integer.class;
				case 1 :return Integer.class;
				case 2: return String.class;
				case 3: return Integer.class;
				case 4: return Integer.class;
				case 5: return Integer.class;
				case 6 : return LocalDateTime.class;
				case 7 : return LocalDateTime.class;
				case 8 : return Boolean.class;
				
				 default:
					 return String.class;
				}
				}
			@Override
			public boolean isCellEditable(int row, int col) {
				switch(col) {
				case 0: return false;
				case 1: return false;
				case 2: return false;
				case 3: return false;
				case 5: return false;
				case 6: return false;
				case 7: return false;
					default: return true;
				}
			}
			};
			
			model.addColumn("ID");
			model.addColumn("Order ID");
			model.addColumn("Product Name");
			model.addColumn("Quanity");
			model.addColumn("Prices");
			model.addColumn("Total");
			model.addColumn("Created At");
			model.addColumn("Update At");
			model.addColumn("Select");
		    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		    DecimalFormat formatter = new DecimalFormat("###,###,###");

		    if(order == null) {
		    	dao.selectOrderDetail().forEach(od->model.addRow(new Object[] {
					od.getId(),
					od.getOrderId(),
					od.getProductName(),
					od.getQuantity(),
					formatter.format(od.getPrice()),
					formatter.format(od.getPrice()*od.getQuantity()),
					od.getCreatedAt()==null?"":od.getCreatedAt().format(format),
					od.getUpdatedAt()==null?"":od.getUpdatedAt().format(format),
					false
		    }));
		    }else{
		    	dao.slectOrderDetailWithOrderID(order.getId()).forEach(od->model.addRow(new Object[] {
						od.getId(),
						od.getOrderId(),
						od.getProductName(),
						od.getQuantity(),
						formatter.format(od.getPrice()),
						formatter.format(od.getPrice()*od.getQuantity()),
						od.getCreatedAt()==null?"":od.getCreatedAt().format(format),
						od.getUpdatedAt()==null?"":od.getUpdatedAt().format(format),
						false
			}));
	}
		  
			table.setModel(model);
	}
	protected void loadDbBtnActionPerformed(ActionEvent e) {
		OrderDetailDao dao = new OrderDetailDao();
		loadData(dao);
	}
	protected void UpdateOrderDetailActionPerformed(ActionEvent e) {
		if(countCheck() ==0 || countCheck() >1 ){
			  JOptionPane.showMessageDialog(null, "Only Update 1 OrderDetail One Time","Warning",
				        JOptionPane.WARNING_MESSAGE);
			  return;
		    }for (int i = 0; i < table.getRowCount(); i++) {
				   Boolean isChecked =  Boolean.valueOf(table.getValueAt(i, 8).toString());
				   int odId = Integer.parseInt(table.getValueAt(i,0).toString());
				   if (isChecked) { 
					   int result  = JOptionPane.showConfirmDialog(null, "Would you like to Update OrderDetail", "Yes/No Question", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					   if(result  == JOptionPane.YES_OPTION) {		
						   OrderDetailDao dao = new OrderDetailDao();
						   OrderDetail odd =  dao.selectOrderDetail().stream()
		    			  .filter(od -> odId == od.getId())
		    			  .findAny()
		    			  .orElse(null);	
						   UpdateOrderDetail up = new UpdateOrderDetail(odd);
						   up.setMainFrame(this);
						   up.setVisible(true);
				   }   
				}	
		}
	}
	protected void btnDeleteOrderDetailActionPerformed(ActionEvent e) {
		List<Integer> list = new ArrayList<Integer>();
		OrderDetailDao dao = new OrderDetailDao();
		if(countCheck() ==0){
			  JOptionPane.showMessageDialog(null, "No OrderDetail to Delete","Warning",
				        JOptionPane.WARNING_MESSAGE);
			  return;
		    }else {			
			   for (int i = 0; i < table.getRowCount(); i++) {
				   Boolean isChecked =  Boolean.valueOf(table.getValueAt(i, 8).toString());
				   int id = Integer.parseInt(table.getValueAt(i, 0).toString());
			     if (isChecked) { 
			    		 list.add(id);
			    	 }
			    	}
		int result  = JOptionPane.showConfirmDialog(null, "Would you like to Delete OrderDetail", "Yes/No Question", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if(result  == JOptionPane.YES_OPTION) {	
				dao.deleteOrderDetail(list);
				this.loadData(dao);
				JOptionPane.showMessageDialog(null, "Delete Success");
				}
		    }
	}
	protected void textFieldActionPerformed(ActionEvent e) {
		if(table.getColumnCount()>=1) {
			String find = searchTf.getText();
			DefaultRowSorter<?, ?> sorter = (DefaultRowSorter<?, ?>) table.getRowSorter();
			sorter.setRowFilter(RowFilter.regexFilter("(?i)" + find));
			sorter.setSortKeys(null);
		}else {
			JOptionPane.showMessageDialog(null, "Load Data First","Warning",
			        JOptionPane.WARNING_MESSAGE);
			return;
		}
	}
	
	protected void btnInsertOrderDetailActionPerformed(ActionEvent e) {
		
		if(countCheck() >1) {
			JOptionPane.showMessageDialog(null, "Just Insert OD for For 1 Order","Warning",
			        JOptionPane.WARNING_MESSAGE);
			return;
			}
		else if(table.getRowCount()==0){
			JOptionPane.showMessageDialog(null, "Load Data First","Warning",
			        JOptionPane.WARNING_MESSAGE);
			return;
		}
		else if(countCheck() >0 && order ==null){
			OrderDetailDao dao = new OrderDetailDao();
			OrderDao orderdao = new OrderDao();
		for (int i = 0; i < table.getRowCount(); i++) {
		Boolean isChecked =  Boolean.valueOf(table.getValueAt(i, 8).toString());
		int id = Integer.parseInt(table.getValueAt(i, 0).toString());
		if(isChecked) {
		OrderDetail oderdetail =  dao.selectOrderDetail().stream()
  			  .filter(oddl -> id ==(oddl.getId()))
  			  .findAny()
  			  .orElse(null);
		Order order = orderdao.getListOrder().stream()
	  			  .filter(oders -> oderdetail.getOrderId() ==oders.getId())
	  			  .findAny()
	  			  .orElse(null);
		InsertOrderDetail ins = new InsertOrderDetail(order);
		ins.setMainFrame(this);
		ins.setVisible(true);
		}
		}
		}
		else if(order !=null) {
			InsertOrderDetail ins = new InsertOrderDetail(order);
			ins.setMainFrame(this);
			ins.setVisible(true);
		}
	}
	
	public int countCheck() {
		List check = new ArrayList();
		 for (int i = 0; i < table.getRowCount(); i++) {
		     Boolean isChecked = Boolean.valueOf(table.getValueAt(i,8).toString());
		     int count = 0;
		     if (isChecked) { 	
			    count ++;
			    check.add(count);
		      }
		     }
		 	int total = check.size();
		 return total ;
	}
	protected void selectCheckMouseClicked(MouseEvent e) {
		if(selectCheck.isSelected()) {
	 		for(int i=0;i<table.getRowCount();i++)
            table.getModel().setValueAt(true, i,8);
 }else{
	 		for(int i=0;i<table.getRowCount();i++) {
            table.getModel().setValueAt(false, i,8);                   
    }       
}
	}
}
