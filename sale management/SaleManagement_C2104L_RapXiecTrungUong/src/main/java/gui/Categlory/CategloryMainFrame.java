package gui.Categlory;

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
import javax.swing.table.DefaultTableModel;

import dao.CategoryDao;
import dao.OrderDao;
import dao.OrderDetailDao;
import entity.Category;
import entity.Order;
import gui.order.InsertOrder;
import helper.HelperFunction;

import java.awt.event.ActionListener;
import java.sql.Date;
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
import java.awt.Toolkit;
public class CategloryMainFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton loadDbBtn;
	private JButton btnInsert;
	private JButton btnUpdate;
	private JLabel updateValidate;
	private JButton btnDeleteOrder;
	private JButton btnViewReport;

	private JButton exitBtn;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CategloryMainFrame frame = new CategloryMainFrame();
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
	public CategloryMainFrame() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\ADMIN\\Desktop\\source final\\Sale Management Java\\SaleManagement_C2104L_RapXiecTrungUong\\image\\Coffee-break-icon.png"));
		loadComponent();

}	
	public CategloryMainFrame(Integer idStaff) {
	
		loadComponent();
	}

	public void loadComponent() {
		setTitle("Categlory");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1066, 667);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Categlory List", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		scrollPane.setBounds(10, 11, 749, 502);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(table);
		
		btnUpdate = new JButton("Update Order");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				updateOderActionPerformed(e);
			}
		});
		
		
		exitBtn = new JButton("Exit");
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exitBtnActionPerformed(e);
				
				
			}
		});
		exitBtn.setBounds(788, 489, 223, 23);
		contentPane.add(exitBtn);
		
		btnInsert = new JButton("Insert Categlory");
		btnInsert.setBounds(783, 23, 228, 21);
		contentPane.add(btnInsert);
		
		btnViewReport = new JButton("Update Category");
		btnViewReport.setBounds(783, 110, 228, 21);
		contentPane.add(btnViewReport);
		btnViewReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				updateActionPerformed(e);
			}
		});
		
				btnViewReport.setToolTipText("");
				btnDeleteOrder = new JButton("Delete categlory");
				btnDeleteOrder.setBounds(783, 79, 228, 21);
				contentPane.add(btnDeleteOrder);
				
				loadDbBtn = new JButton("Load Categlory");
				loadDbBtn.setBounds(783, 48, 228, 21);
				contentPane.add(loadDbBtn);
				loadDbBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						loadCateActionPerformed(e);
					}
				});
				btnDeleteOrder.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						deleteOderActionPerformed(e);
					}
				});
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertCatActionPerformed(e);
			}
		});
	}
	
public void loadData(CategoryDao dao) { 

		DefaultTableModel model = new DefaultTableModel() {
			
		@Override
		public Class<?> getColumnClass(int column) {
			switch(column) {
			case 0: return Integer.class;
			case 1 :return String.class;
			case 2: return LocalDateTime.class;
			case 3 : return LocalDateTime.class;
			case 4 : return LocalDateTime.class;
			case 5 : return Boolean.class;
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
				default: return true;
			}
		}
		};
		
		model.addColumn("ID");
		model.addColumn("Name Categlory");
		model.addColumn("Created At");
		model.addColumn("Update At");
		model.addColumn("Update Delete");
		model.addColumn("Select");
		
	    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");  
		dao.getlistCate().forEach(Categlory->model.addRow(new Object[] {
				Categlory.getId(),
				Categlory.getName(),
				Categlory.getCreated_at()==null?"":Categlory.getCreated_at().format(format),
				Categlory.getUpdated_at()==null?"":Categlory.getUpdated_at().format(format),
				Categlory.getDeleted_at()==null?"":Categlory.getDeleted_at().format(format),
				false
		}));
		table.setModel(model);
	}
	
	 
	protected void loadCateActionPerformed(ActionEvent e) {
		CategoryDao dao = new CategoryDao();
		loadData(dao);
	}

	protected void insertCatActionPerformed(ActionEvent e) {
		
		int result  = JOptionPane.showConfirmDialog(null, "Would you like to insert Order", "Yes/No Question", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if(result  == JOptionPane.YES_OPTION) {
			
			
			
	
		
			insertCateglory ins = new insertCateglory();
			ins.setVisible(true);
		}else {
			return;
		}
	}
	
	protected void deleteOderActionPerformed (ActionEvent e) {
		CategoryDao CategoryDao = new CategoryDao();
	
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
				
				
				CategoryDao.deleteCat(list);
				JOptionPane.showMessageDialog(null, "Delete Success");
			}
		}
	}
	
	
	protected void updateActionPerformed (ActionEvent e) {
		if(countCheck() == 0 || countCheck() >1){
			JOptionPane.showMessageDialog(null, "Only Update One Category","Warning",
			        JOptionPane.WARNING_MESSAGE);
			return ;
		}else{
			int id = HelperFunction.getId(table);
			for (int i = 0; i < table.getRowCount(); i++) {
			Boolean isChecked = Boolean.valueOf(table.getValueAt(i, 5).toString());
			Category category = new Category();
			category.setId(Integer.parseInt(table.getValueAt(i,0).toString()));	
			category.setName(table.getValueAt(i , 1).toString());
			if(isChecked) {
				int result  = JOptionPane.showConfirmDialog(null, "Would you like to update Category", "Yes/No Question", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(result  == JOptionPane.YES_OPTION) {
					UpdateCategory ip = new UpdateCategory(category);
					ip.setId(id);
					ip.setVisible(true);
					
					
					
				}
			}		
			}
		}
	}
	

	
	public int countCheck() {
		List check = new ArrayList();
		 for (int i = 0; i < table.getRowCount(); i++) {
		     Boolean isChecked = Boolean.valueOf(table.getValueAt(i,5).toString());
		     int count = 0;
		     if (isChecked) { 	
			    count ++;
			    check.add(count);
		      }
		     }
		 	int total = check.size();
		 return total ;
	}

	protected void exitBtnActionPerformed(ActionEvent e) {
		this.dispose();
	}
	

}
