package gui.staff;

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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
import dao.StaffDao;
import entity.Staff;
import gui.orderDetail.MainFrameOD;
import java.awt.Font;
import java.awt.Toolkit;


public class StaffMainFrame extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable table;
	private JPanel panel;
	private JLabel searchLabel;
	private JTextField searchTf;
	private JLabel searchValidate;
	private JButton loadDbBtn;
	private JButton btnInsertStaff;
	private JLabel insertValidate;
	private JButton btnUpdateStaff;
	private JLabel updateValidate;
	private JButton btnDeleteStaff;
	private JButton exitBtn;
	private Staff staff;
	private JCheckBox selectCheck;
	private JLabel selectAll;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StaffMainFrame frame = new StaffMainFrame();
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
	public StaffMainFrame() {
		loadComponent();
	}
	public StaffMainFrame(Staff st) {
		staff =st;
		loadComponent();
		if(staff.getLevel()==0) {
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
			
			
			btnInsertStaff = new JButton("Insert Staff");
			btnInsertStaff.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					insertActionPerformed(e);
				}
			});
			
			GridBagConstraints gbc_btnInsertStaff = new GridBagConstraints();
			gbc_btnInsertStaff.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnInsertStaff.gridwidth = 2;
			gbc_btnInsertStaff.insets = new Insets(0, 0, 5, 0);
			gbc_btnInsertStaff.gridx = 0;
			gbc_btnInsertStaff.gridy = 3;
			panel.add(btnInsertStaff, gbc_btnInsertStaff);
			
			insertValidate = new JLabel("");
			insertValidate.setForeground(Color.RED);
			GridBagConstraints gbc_insertValidate = new GridBagConstraints();
			gbc_insertValidate.gridwidth = 2;
			gbc_insertValidate.insets = new Insets(0, 0, 5, 0);
			gbc_insertValidate.gridx = 0;
			gbc_insertValidate.gridy = 4;
			panel.add(insertValidate, gbc_insertValidate);
			
			btnUpdateStaff = new JButton("Update Staff");
			btnUpdateStaff.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					updateActionPerformed(e);
				}
			});
			GridBagConstraints gbc_btnUpdateStaff = new GridBagConstraints();
			gbc_btnUpdateStaff.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnUpdateStaff.gridwidth = 2;
			gbc_btnUpdateStaff.insets = new Insets(0, 0, 5, 0);
			gbc_btnUpdateStaff.gridx = 0;
			gbc_btnUpdateStaff.gridy = 5;
			panel.add(btnUpdateStaff, gbc_btnUpdateStaff);
			
			updateValidate = new JLabel("");
			updateValidate.setForeground(Color.RED);
			GridBagConstraints gbc_updateValidate = new GridBagConstraints();
			gbc_updateValidate.gridwidth = 2;
			gbc_updateValidate.insets = new Insets(0, 0, 5, 0);
			gbc_updateValidate.gridx = 0;
			gbc_updateValidate.gridy = 6;
			panel.add(updateValidate, gbc_updateValidate);
			
			btnDeleteStaff = new JButton("Delete Staff");
			btnDeleteStaff.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					deleteActionPerformed(e);
				}
			});

			GridBagConstraints gbc_btnDeleteStaff = new GridBagConstraints();
			gbc_btnDeleteStaff.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnDeleteStaff.gridwidth = 2;
			gbc_btnDeleteStaff.insets = new Insets(0, 0, 5, 0);
			gbc_btnDeleteStaff.gridx = 0;
			gbc_btnDeleteStaff.gridy = 7;
			panel.add(btnDeleteStaff, gbc_btnDeleteStaff);
			}
	}

	public void loadComponent() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\ADMIN\\Desktop\\source final\\Sale Management Java\\SaleManagement_C2104L_RapXiecTrungUong\\image\\Coffee-break-icon.png"));
		setTitle("Staff");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1050, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(new TitledBorder(null, "Account List", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
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
		
		loadDbBtn = new JButton("Load Staff");
		loadDbBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadStaffActionPerformed(e);
			}
		});
		
		GridBagConstraints gbc_loadDbBtn = new GridBagConstraints();
		gbc_loadDbBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_loadDbBtn.gridwidth = 2;
		gbc_loadDbBtn.insets = new Insets(0, 0, 5, 0);
		gbc_loadDbBtn.gridx = 0;
		gbc_loadDbBtn.gridy = 2;
		panel.add(loadDbBtn, gbc_loadDbBtn);
		
		
		exitBtn = new JButton("Exit");
		exitBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exitBtnActionPerformed(e);
			}
		});
//		exitBtn.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				homeActionPerformed(e);
//			}
//		});
		exitBtn.setBounds(792, 492, 182, 21);
		contentPane.add(exitBtn);
	}
	public void loadData(StaffDao dao) { 
			if(staff.getLevel() == 0) {
		DefaultTableModel model = new DefaultTableModel() {
			
		@Override
		public Class<?> getColumnClass(int column) {
			switch(column) {
			case 0: return Integer.class;
			case 1 :return String.class;
			case 2: return String.class;
			case 3: return String.class;
			case 4: return LocalDateTime.class;
			case 5 : return LocalDateTime.class;
			case 6 : return LocalDateTime.class;
			case 7:  return Boolean.class;
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
			case 6: return false;
				default: return true;
			}
		}
		};
		
		model.addColumn("ID");
		model.addColumn("Name");
		model.addColumn("level");
		model.addColumn("password");
		model.addColumn("Created At");
		model.addColumn("Update At");
		model.addColumn("Delete At");
		model.addColumn("Select");
	    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");  
		dao.getListStaff().forEach(staff -> model.addRow(new Object[] {
				
				staff.getId(),
				staff.getName(),
				staff.getLevel()==0?"QL":"NV",
			    staff.getPassword(),
				staff.getCreateAt().format(format),
				staff.getUpdateAt()==null?"":staff.getUpdateAt().format(format),
				staff.getDeleteAt()==null?"":staff.getDeleteAt().format(format),
				false
		}));
		table.setModel(model);
	}else {
		DefaultTableModel model = new DefaultTableModel() {
			
			@Override
			public Class<?> getColumnClass(int column) {
				switch(column) {
				case 0: return Integer.class;
				case 1 :return String.class;
				case 2: return String.class;
				case 3: return String.class;
				case 4: return LocalDateTime.class;
				case 5 : return LocalDateTime.class;
				case 6 : return LocalDateTime.class;
				case 7:  return Boolean.class;
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
			model.addColumn("Name");
			model.addColumn("level");
			model.addColumn("Created At");
			model.addColumn("Update At");
			model.addColumn("Delete At");
			model.addColumn("Select");
		    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");  
			dao.getListStaff().forEach(staff -> model.addRow(new Object[] {
					
					staff.getId(),
					staff.getName(),
					staff.getLevel()==0?"QL":"NV",
					staff.getCreateAt().format(format),
					staff.getUpdateAt()==null?"":staff.getUpdateAt().format(format),
					staff.getDeleteAt()==null?"":staff.getDeleteAt().format(format),
					false
			}));
			table.setModel(model);
	}
	}
	
	protected void loadStaffActionPerformed(ActionEvent e) {
	StaffDao dao = new StaffDao();
	loadData(dao);
	}
	
	protected void insertActionPerformed(ActionEvent e) {
		if(table.getRowCount()==0){
			JOptionPane.showMessageDialog(null, "Load Data First","Warning",
			        JOptionPane.WARNING_MESSAGE);
			return;
		}else {
		InsertStaff ins = new InsertStaff();
		ins.setMainFrame(this);
		ins.setVisible(true);
		}
	}
	protected void updateActionPerformed(ActionEvent e) {
		if(countCheck() > 1 || countCheck() == 0){
			JOptionPane.showMessageDialog(null, "Just Update 1 Staff For One Time","Warning",
			        JOptionPane.WARNING_MESSAGE);
			return ;
		}for (int i = 0; i < table.getRowCount(); i++) {
			Boolean isChecked = Boolean.valueOf(table.getValueAt(i, 7).toString());
		StaffDao dao = new StaffDao();
		Staff staff = new Staff();
		staff.setId(Integer.parseInt(table.getValueAt(i, 0).toString()));
		staff.setName(table.getValueAt(i, 1).toString());
		staff.setLevel(table.getValueAt(i, 2)=="QL"?0:1);
		staff.setPassword(table.getValueAt(i,3).toString());
			if(isChecked) {
		 UpdateStaff up  = new UpdateStaff(staff);
		 up.setMainFrame(this);
		 up.setVisible(true);
			}
		}
		}
	protected void deleteActionPerformed(ActionEvent e) {
		if(countCheck() ==0){
			  JOptionPane.showMessageDialog(null, "No Account to Delete","Warning",
				        JOptionPane.WARNING_MESSAGE);
			  return;
		    }else {		
		    	List<Integer> list = new ArrayList<Integer>();
			   for (int i = 0; i < table.getRowCount(); i++) {
			     Boolean isChecked = Boolean.valueOf(table.getValueAt(i, 7).toString());
			     int id = Integer.parseInt(table.getValueAt(i, 0).toString());
			     if(i == 1 && isChecked || id == staff.getId() && isChecked ) {
				   JOptionPane.showMessageDialog(null, "Can't Delete this Account","Warning",
					        JOptionPane.WARNING_MESSAGE);
				   return;
			   }else if (isChecked) { 
			    		 list.add(id);
			    	 }
			    	}
			   int result  = JOptionPane.showConfirmDialog(null, "Would you like to delete Staff", "Yes/No Question", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		    	if(result  == JOptionPane.YES_OPTION) {
		    		 StaffDao dao = new StaffDao();
		    		 dao.deleteStaff(list);
		    		 JOptionPane.showMessageDialog(null, "Delete Success");
		    		 this.loadData(dao);
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
	protected void exitBtnActionPerformed(ActionEvent e) {
		this.dispose();
	}
//	protected void homeActionPerformed(ActionEvent e) {
//		this.setVisible(false);
//		HomeFrame home = new HomeFrame();
//		home.setVisible(true);
//	}
	public int countCheck() {
		List check = new ArrayList();
		 for (int i = 0; i < table.getRowCount(); i++) {
		     Boolean isChecked = Boolean.valueOf(table.getValueAt(i,7 ).toString());
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
	            table.getModel().setValueAt(true, i, 7);
	 }else{
		 		for(int i=0;i<table.getRowCount();i++) {
	            table.getModel().setValueAt(false, i, 7);                   
	    }       
	}
		}
}
