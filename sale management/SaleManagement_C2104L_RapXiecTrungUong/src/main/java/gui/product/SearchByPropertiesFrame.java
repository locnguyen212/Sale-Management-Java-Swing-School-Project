package gui.product;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dao.PropertiesDao;
import gui.productProperties.PpMainFrame;
import helper.HelperFunction;
import helper.Validation;
import service.jFrame.MainFrame;

import javax.swing.JList;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.border.EtchedBorder;

public class SearchByPropertiesFrame extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable table;
	private JPanel panel;
	private JButton addBtn;
	private JButton deleteBtn;
	private ProductMainFrame mainFrame;
	private JButton searchBtn;
	private JLabel deleteValidate;
	private JLabel searchValidate;
	private JLabel noteLbl;
	
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
					SearchByPropertiesFrame frame = new SearchByPropertiesFrame();
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
	public SearchByPropertiesFrame() {
		setTitle("Advance search");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 568, 558);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "List of properties to search", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		scrollPane.setBounds(10, 11, 338, 391);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(table);
		
		panel = new JPanel();
		panel.setBounds(358, 11, 184, 147);
		contentPane.add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		addBtn = new JButton("Add");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addBtnActionPerformed(e);
			}
		});

		GridBagConstraints gbc_addBtn = new GridBagConstraints();
		gbc_addBtn.insets = new Insets(0, 0, 5, 0);
		gbc_addBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_addBtn.gridwidth = 2;
		gbc_addBtn.gridx = 0;
		gbc_addBtn.gridy = 0;
		panel.add(addBtn, gbc_addBtn);
		
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
		gbc_deleteBtn.gridy = 1;
		panel.add(deleteBtn, gbc_deleteBtn);
		
		deleteValidate = new JLabel("");
		deleteValidate.setForeground(Color.RED);
		GridBagConstraints gbc_deleteValidate = new GridBagConstraints();
		gbc_deleteValidate.gridwidth = 2;
		gbc_deleteValidate.insets = new Insets(0, 0, 5, 0);
		gbc_deleteValidate.gridx = 0;
		gbc_deleteValidate.gridy = 2;
		panel.add(deleteValidate, gbc_deleteValidate);
		
		searchBtn = new JButton("Search");
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchBtnActionPerformed(e);
			}
		});
		GridBagConstraints gbc_searchBtn = new GridBagConstraints();
		gbc_searchBtn.insets = new Insets(0, 0, 5, 0);
		gbc_searchBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_searchBtn.gridwidth = 2;
		gbc_searchBtn.gridx = 0;
		gbc_searchBtn.gridy = 3;
		panel.add(searchBtn, gbc_searchBtn);
		
		searchValidate = new JLabel("");
		searchValidate.setForeground(Color.RED);
		GridBagConstraints gbc_searchValidate = new GridBagConstraints();
		gbc_searchValidate.gridwidth = 2;
		gbc_searchValidate.insets = new Insets(0, 0, 5, 5);
		gbc_searchValidate.gridx = 0;
		gbc_searchValidate.gridy = 4;
		panel.add(searchValidate, gbc_searchValidate);
		
		noteLbl = new JLabel("<html>"
				+ "Note:<br/>"
				+ "- To delete many, click the check box then press delete, you can't update, so delete and add again<br/>"
				+ "- After you are done chosing field to search, press the search button<br/>"
				+ "- You don't need to press the checkbox when pressing search button<br/>"
				+ "</html>");
		noteLbl.setBounds(10, 413, 532, 86);
		contentPane.add(noteLbl);
		
		this.addWindowListener(new WindowAdapter() {
			 @Override
	            public void windowClosed(WindowEvent e) {
	                mainFrame.setEnabled(true);
	                mainFrame.setVisible(true);
	            }		
		});
		load();
	}
	
	public void load() {
		var dao = new PropertiesDao();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"); 
		DefaultTableModel model = new DefaultTableModel(){
		    @Override
		    public Class<?> getColumnClass(int column) {
		        switch (column) {
			        case 0: return Integer.class;
			        case 2: return Integer.class;
		            case 4: return Boolean.class;
		            default: return String.class;
		        }
		    }
		};;
				
		model.addColumn("id");
		model.addColumn("name");
		model.addColumn("parent_id");
		model.addColumn("parent_name");
		model.addColumn("Selected");
		table.setDefaultEditor(Object.class, null);
		table.setModel(model);
		
		var centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
	}
	
	protected void addBtnActionPerformed(ActionEvent e) {
		var add = new SearchByPropertiesAddFrame();
		add.setTable(table);
		add.setupData();
		add.setMainFrame(this);
		add.setLocation(this.getX(), this.getY());
		add.setVisible(true);
		this.setEnabled(false);
	}
	
	protected void deleteBtnActionPerformed(ActionEvent e) {
		searchValidate.setText("");

		if(Validation.isSelectedActived(table)  && Validation.selectedCount(table) >= 1) {
			deleteValidate.setText("");		
			for(int i=table.getRowCount() - 1; i >= 0; i--) {
				if((Boolean)table.getValueAt(i, table.getColumnCount()-1)) {
					((DefaultTableModel)table.getModel()).removeRow(i);
				}
			}
		}else {
			deleteValidate.setText("*Please select a row to delete!");
		}
		
	}
	
	protected void searchBtnActionPerformed(ActionEvent e) {
		if(table.getRowCount()!=0) {
			String list = HelperFunction.getAllIdsString(table);
			
			List<Integer> ids = HelperFunction.getAllIdsList(table);
			mainFrame.loadDbSearch(list, ids.size());
			mainFrame.setEnabled(true);
			this.dispose();
		}else {
			searchValidate.setText("<html>*Please chose properties<br/> to search</html>");
		}
	}
}
