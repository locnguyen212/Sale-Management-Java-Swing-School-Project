package gui.order;

import java.awt.Checkbox;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.DefaultRowSorter;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
import javax.swing.table.DefaultTableModel;

import dao.OrderDao;
import dao.OrderDetailDao;
import entity.Order;
import entity.Staff;
import gui.orderDetail.InsertOrderDetail;
import gui.reportOrder.ReportOrder;

import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.UIManager;
public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable table;
	private JPanel panel;
	private JLabel searchLabel;
	private JTextField searchTf;
	private JLabel searchValidate;
	private JButton loadDbBtn;
	private JButton btnInsert;
	private JLabel insertValidate;
	private JButton btnUpdate;
	private JButton btnDeleteOrder;
	private JButton btnViewReport;
	private JLabel reportValidate;
	private JButton exitBtn;
	static Integer staffId;
	private JButton btnViewOrderDetail;
	private JCheckBox selectCheck;
	private JLabel selectAll;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		loadComponent();

}	
	public MainFrame(Integer idStaff) {
		staffId = idStaff;
		loadComponent();
	}

	public void loadComponent() {
		setTitle("Order");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		setBounds(100, 100, 1050, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Order List", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
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
		gbc_searchTf.insets = new Insets(0, 0, 5, 5);
		gbc_searchTf.gridx = 1;
		gbc_searchTf.gridy = 1;
		panel.add(searchTf, gbc_searchTf);
		
		searchValidate = new JLabel("");
		searchValidate.setForeground(Color.RED);
		GridBagConstraints gbc_searchValidate = new GridBagConstraints();
		gbc_searchValidate.insets = new Insets(0, 0, 5, 5);
		gbc_searchValidate.gridx = 1;
		gbc_searchValidate.gridy = 2;
		panel.add(searchValidate, gbc_searchValidate);
		
		loadDbBtn = new JButton("Load Order");
		loadDbBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadOrderActionPerformed(e);
			}
		});
		
		GridBagConstraints gbc_loadDbBtn = new GridBagConstraints();
		gbc_loadDbBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_loadDbBtn.insets = new Insets(0, 0, 5, 5);
		gbc_loadDbBtn.gridwidth = 2;
		gbc_loadDbBtn.gridx = 0;
		gbc_loadDbBtn.gridy = 3;
		panel.add(loadDbBtn, gbc_loadDbBtn);
		
		
		btnInsert = new JButton("Insert Order");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertOderActionPerformed(e);
			}
		});

		GridBagConstraints gbc_btnInsert = new GridBagConstraints();
		gbc_btnInsert.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnInsert.gridwidth = 2;
		gbc_btnInsert.insets = new Insets(0, 0, 5, 5);
		gbc_btnInsert.gridx = 0;
		gbc_btnInsert.gridy = 4;
		panel.add(btnInsert, gbc_btnInsert);
		
		insertValidate = new JLabel("");
		insertValidate.setForeground(Color.RED);
		GridBagConstraints gbc_insertValidate = new GridBagConstraints();
		gbc_insertValidate.gridwidth = 2;
		gbc_insertValidate.insets = new Insets(0, 0, 5, 5);
		gbc_insertValidate.gridx = 0;
		gbc_insertValidate.gridy = 5;
		panel.add(insertValidate, gbc_insertValidate);
		
		btnUpdate = new JButton("Update Order");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateOderActionPerformed(e);
			}
		});
		btnDeleteOrder = new JButton("Delete Order");
		btnDeleteOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteOderActionPerformed(e);
			}
		});

		GridBagConstraints gbc_btnDeleteOrder = new GridBagConstraints();
		gbc_btnDeleteOrder.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDeleteOrder.gridwidth = 2;
		gbc_btnDeleteOrder.insets = new Insets(0, 0, 5, 5);
		gbc_btnDeleteOrder.gridx = 0;
		gbc_btnDeleteOrder.gridy = 6;
		panel.add(btnDeleteOrder, gbc_btnDeleteOrder);
		
		
		btnViewReport = new JButton("View Report");
		btnViewReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnViewReportActionPerformed(e);
			}
		});

		btnViewReport.setToolTipText("");
		GridBagConstraints gbc_btnViewReport = new GridBagConstraints();
		gbc_btnViewReport.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnViewReport.gridwidth = 2;
		gbc_btnViewReport.insets = new Insets(0, 0, 5, 5);
		gbc_btnViewReport.gridx = 0;
		gbc_btnViewReport.gridy = 7;
		panel.add(btnViewReport, gbc_btnViewReport);
		
			
		btnViewOrderDetail = new JButton("View Order Detail");
		btnViewOrderDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewOrderDetailActionPerformed(e);
			}
		});
		btnViewOrderDetail.setToolTipText("");
		GridBagConstraints gbc_btnViewOrderDetail = new GridBagConstraints();
		gbc_btnViewOrderDetail.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnViewOrderDetail.gridwidth = 2;
		gbc_btnViewOrderDetail.insets = new Insets(0, 0, 5, 5);
		gbc_btnViewOrderDetail.gridx = 0;
		gbc_btnViewOrderDetail.gridy = 8;
		panel.add(btnViewOrderDetail, gbc_btnViewOrderDetail);
		
	
		exitBtn = new JButton("Exit");
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exitBtnActionPerformed(e);
			}
		});
		
		
		
		exitBtn.setBounds(797, 489, 176, 21);
		contentPane.add(exitBtn);
	}
	//============================
		//============================Exit btn
		//============================
		
		protected void exitBtnActionPerformed(ActionEvent e) {
			this.dispose();
		}
	public void loadData(OrderDao dao) { 

		DefaultTableModel model = new DefaultTableModel() {
			
		@Override
		public Class<?> getColumnClass(int column) {
			switch(column) {
			case 0: return Integer.class;
			case 1 :return String.class;
			case 2: return Integer.class;
			case 3 : return String.class;
			case 4: return LocalDateTime.class;
			case 5 : return LocalDateTime.class;
			case 6 : return Boolean.class;
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
			case 4: return false;
			case 5: return false;
				default: return true;
			}
		}
		};
		
		model.addColumn("ID");
		model.addColumn("Name Staff");
		model.addColumn("Order Number");
		model.addColumn("Total Cash");
		model.addColumn("Created At");
		model.addColumn("Update At");
		model.addColumn("Select");
		 DecimalFormat formatter = new DecimalFormat("###,###,###");
	    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");  
		dao.getListOrder().forEach(order->model.addRow(new Object[] {
				
				order.getId(),
				order.getNameStaff(),
				order.getOrderNumber(),
				formatter.format(Integer.valueOf(order.getTolTalCash()))+" VND",
				order.getCreatedAt()==null?"":order.getCreatedAt().format(format),
				order.getUpdateAt()==null?"":order.getUpdateAt().format(format),
				false
		}));
		table.setModel(model);
	}
	
	 
	protected void loadOrderActionPerformed(ActionEvent e) {
		OrderDao dao = new OrderDao();
		loadData(dao);
	}
	protected void insertOderActionPerformed(ActionEvent e) {
		if(table.getRowCount()==0){
			JOptionPane.showMessageDialog(null, "Load Data First","Warning",
			        JOptionPane.WARNING_MESSAGE);
			return;
		}else {
		int result  = JOptionPane.showConfirmDialog(null, "Would you like to insert Order", "Yes/No Question", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if(result  == JOptionPane.YES_OPTION) {
			OrderDao orderDao = new OrderDao();
			Order order = new Order();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");  
			LocalDate date = LocalDate.now();
			int int_random = (int)Math.floor(Math.random()*(100-0+1)+0);
			int order_number = Integer.valueOf(int_random) + Integer.valueOf(dtf.format(date));
			order.setStaffId(staffId);
		    order.setOrderNumber(order_number);
		    orderDao.insertOrder(order);
		InsertOrder ins = new InsertOrder(staffId);
		ins.setMainFrame(this);
		ins.setVisible(true);
		}
	}
	}
	
	protected void deleteOderActionPerformed (ActionEvent e) {
		OrderDetailDao orderDetailDao = new OrderDetailDao();
		OrderDao orderDao = new OrderDao();
		List<Integer> list = new ArrayList<Integer>();
		if(countCheck() == 0){
			JOptionPane.showMessageDialog(null, "NO OrderDetail to Delete","Warning",
			        JOptionPane.WARNING_MESSAGE);
			return ;
		}else{
			for (int i = 0; i < table.getRowCount(); i++) {
			Boolean isChecked = Boolean.valueOf(table.getValueAt(i, 5).toString());
			int orderId= Integer.parseInt(table.getValueAt(i,0).toString());	
			if(isChecked) {
				list.add(orderId);
				}
			}
			int result  = JOptionPane.showConfirmDialog(null, "Would you like to insert Order", "Yes/No Question", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(result  == JOptionPane.YES_OPTION) {
				
				orderDetailDao.deleteOrderDetailWithOrderId(list);
				orderDao.deleteOrder(list);
				this.loadData(orderDao);
				JOptionPane.showMessageDialog(null, "Delete Success");
			}
		}
	}
	protected void viewOrderDetailActionPerformed(ActionEvent e) {
		if(countCheck() > 1 || countCheck() == 0){
			JOptionPane.showMessageDialog(null, "Limit ","Warning",
			        JOptionPane.WARNING_MESSAGE);
			return ;
		}else{
			for (int i = 0; i < table.getRowCount(); i++) {
			Boolean isChecked = Boolean.valueOf(table.getValueAt(i, 6).toString());
			int orderId= Integer.parseInt(table.getValueAt(i,0).toString());
			if(isChecked) {
				OrderDao dao = new OrderDao();
				Order od = dao.getListOrder().stream()
						.filter(order -> orderId==(order.getId()))
						.findAny()
						.orElse(null);
		gui.orderDetail.MainFrameOD mainFrameOD = new gui.orderDetail.MainFrameOD(od);
		this.setVisible(false);
		mainFrameOD.setVisible(true);
			}
	}
		}}
	protected void updateOderActionPerformed(ActionEvent e) {
		int result  = JOptionPane.showConfirmDialog(null, "Would you like to Update Order", "Yes/No Question", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if(result  == JOptionPane.YES_OPTION) {		
		}else {
			return;
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
	public int countCheck() {
		List check = new ArrayList();
		 for (int i = 0; i < table.getRowCount(); i++) {
		     Boolean isChecked = Boolean.valueOf(table.getValueAt(i,6).toString());
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
	            table.getModel().setValueAt(true, i, 6);
	            }else{
		 		for(int i=0;i<table.getRowCount();i++) {
	            table.getModel().setValueAt(false, i, 6);                   
	    }       
	}
		}
	protected void btnViewReportActionPerformed(ActionEvent e) {
		if(countCheck() > 1 || countCheck() == 0){
			JOptionPane.showMessageDialog(null, "Just Report One Order For One Time ","Warning",
			        JOptionPane.WARNING_MESSAGE);
			return ;
		}else{
			for (int i = 0; i < table.getRowCount(); i++) {
			Boolean isChecked = Boolean.valueOf(table.getValueAt(i, 6).toString());
			int orderId= Integer.parseInt(table.getValueAt(i,0).toString());
			if(isChecked) {
				OrderDao dao = new OrderDao();
				Order oder = dao.getListOrder().stream()
		  			  .filter(ord -> orderId==(ord.getId()))
		  			  .findAny()
		  			  .orElse(null);
				ReportOrder rp = new ReportOrder(oder);
				rp.setVisible(true);
		}
	}
	}
	}
}
