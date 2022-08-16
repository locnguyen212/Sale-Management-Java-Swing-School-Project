package gui.product;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.ProductPropertiesDao;
import dao.PropertiesDao;
import entity.ProductProperties;
import gui.productProperties.PpInsertFrame;
import gui.productProperties.PpMainFrame;
import helper.HelperFunction;
import helper.loc.ComboItem;
import helper.loc.CrudValidate;
import javax.swing.border.EtchedBorder;

public class SearchByPropertiesAddFrame extends JFrame {

	private JPanel contentPane;
	private JLabel parentPropertyLbl;
	private JComboBox parentPropertyCb;
	private JLabel childPropertyLbl;
	private JComboBox childPropertyCb;
	private JButton submitBtn;
	private SearchByPropertiesFrame mainFrame;
	private JLabel childPropertyValidate;
	private JTable table;
	private JLabel noteLbl;
	
	

	public void setTable(JTable table) {
		this.table = table;
	}

	public void setMainFrame(SearchByPropertiesFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchByPropertiesAddFrame frame = new SearchByPropertiesAddFrame();
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
	public SearchByPropertiesAddFrame() {
		setTitle("Search properties choser");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 202);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Chose a property add", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		parentPropertyLbl = new JLabel("Parent property");
		GridBagConstraints gbc_parentPropertyLbl = new GridBagConstraints();
		gbc_parentPropertyLbl.insets = new Insets(0, 0, 5, 5);
		gbc_parentPropertyLbl.anchor = GridBagConstraints.EAST;
		gbc_parentPropertyLbl.gridx = 0;
		gbc_parentPropertyLbl.gridy = 0;
		contentPane.add(parentPropertyLbl, gbc_parentPropertyLbl);
		
		parentPropertyCb = new JComboBox();
		parentPropertyCb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parentPropertyCbActionPerformed(e);
			}
		});
	
		GridBagConstraints gbc_parentPropertyCb = new GridBagConstraints();
		gbc_parentPropertyCb.insets = new Insets(0, 0, 5, 0);
		gbc_parentPropertyCb.fill = GridBagConstraints.HORIZONTAL;
		gbc_parentPropertyCb.gridx = 1;
		gbc_parentPropertyCb.gridy = 0;
		contentPane.add(parentPropertyCb, gbc_parentPropertyCb);
		
		childPropertyLbl = new JLabel("Child property");
		GridBagConstraints gbc_childPropertyLbl = new GridBagConstraints();
		gbc_childPropertyLbl.anchor = GridBagConstraints.EAST;
		gbc_childPropertyLbl.insets = new Insets(0, 0, 5, 5);
		gbc_childPropertyLbl.gridx = 0;
		gbc_childPropertyLbl.gridy = 1;
		contentPane.add(childPropertyLbl, gbc_childPropertyLbl);
		
		childPropertyCb = new JComboBox();
		GridBagConstraints gbc_childPropertyCb = new GridBagConstraints();
		gbc_childPropertyCb.insets = new Insets(0, 0, 5, 0);
		gbc_childPropertyCb.fill = GridBagConstraints.HORIZONTAL;
		gbc_childPropertyCb.gridx = 1;
		gbc_childPropertyCb.gridy = 1;
		contentPane.add(childPropertyCb, gbc_childPropertyCb);
		
		submitBtn = new JButton("Add");
		submitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submitBtnActionPerformed(e);
			}
		});
		
		childPropertyValidate = new JLabel("");
		childPropertyValidate.setForeground(Color.RED);
		GridBagConstraints gbc_childPropertyValidate = new GridBagConstraints();
		gbc_childPropertyValidate.gridwidth = 2;
		gbc_childPropertyValidate.insets = new Insets(0, 0, 5, 0);
		gbc_childPropertyValidate.gridx = 0;
		gbc_childPropertyValidate.gridy = 2;
		contentPane.add(childPropertyValidate, gbc_childPropertyValidate);
		GridBagConstraints gbc_submitBtn = new GridBagConstraints();
		gbc_submitBtn.insets = new Insets(0, 0, 5, 0);
		gbc_submitBtn.gridwidth = 2;
		gbc_submitBtn.gridx = 0;
		gbc_submitBtn.gridy = 3;
		contentPane.add(submitBtn, gbc_submitBtn);
		
		noteLbl = new JLabel("Note: You can only chose one child property per parent property");
		GridBagConstraints gbc_noteLbl = new GridBagConstraints();
		gbc_noteLbl.anchor = GridBagConstraints.WEST;
		gbc_noteLbl.gridwidth = 2;
		gbc_noteLbl.insets = new Insets(0, 0, 0, 5);
		gbc_noteLbl.gridx = 0;
		gbc_noteLbl.gridy = 4;
		contentPane.add(noteLbl, gbc_noteLbl);
		
		this.addWindowListener(new WindowAdapter() {
			 @Override
	            public void windowClosed(WindowEvent e) {
	                mainFrame.setEnabled(true);
	                mainFrame.setVisible(true);
	            }		
		});
	}
	
	public void setupData() {
		var dao = new PropertiesDao();
		parentPropertyCb.addItem(new ComboItem("", 0));
		
//		for(var pro : dao.getDb()) {	
//			if(pro.getParentId() == 0) {
//				parentPropertyCb.addItem(new ComboItem(pro.getName(), pro.getId()));
//			}
//		}
		
		dao.getDb().forEach(element -> {
			if(element.getParentId() == 0) {
				parentPropertyCb.addItem(new ComboItem(element.getName(), element.getId()));
			}
		});
	}


	protected void parentPropertyCbActionPerformed(ActionEvent e) {
		var dao = new PropertiesDao();
		childPropertyCb.removeAllItems();
		if(((ComboItem)parentPropertyCb.getSelectedItem()).getValue() != 0) {
			for(var pro : dao.getDb()) {	
				if(pro.getParentId() == ((ComboItem)parentPropertyCb.getSelectedItem()).getValue())	{
					childPropertyCb.addItem(new ComboItem(pro.getName(), pro.getId()));
				}
			}
		}else {
			childPropertyCb.removeAllItems();
		}
	}
	
	
	protected void submitBtnActionPerformed(ActionEvent e) {
		if(((ComboItem)childPropertyCb.getSelectedItem())!=null && ((ComboItem)parentPropertyCb.getSelectedItem())!=null) {
			int propertyId = ((ComboItem)childPropertyCb.getSelectedItem()).getValue();
			int parentId = ((ComboItem)parentPropertyCb.getSelectedItem()).getValue();

			
			boolean isDuplicate = isDuplicate(table, childPropertyValidate, propertyId);
			boolean isUnique = false;
			
			if(!isDuplicate) {
				isUnique = isUnique(table, childPropertyValidate, parentId);
				if(isUnique) {
					var property = HelperFunction.getProperties(propertyId);
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.addRow(new Object[]{
							property.getId(),
							property.getName(),
							property.getParentId(),
							HelperFunction.getProperties(parentId).getName(),
							false
					});
					mainFrame.setEnabled(true);
					this.dispose();
				}
			}	
		}else {
			childPropertyValidate.setText("*Please select a parent and child property");
		}
	
	}
	
	public boolean isUnique(JTable table, JLabel lbl, int parentId) {
		boolean validate = true;
		
		if(table.getRowCount() > 0) {
			for(int i = 0; i < table.getRowCount(); i++) {	
					if(Integer.parseInt(table.getValueAt(i, 2).toString()) == parentId) {
						validate = false;
						lbl.setText("*You can only chose one child property from one root property");
						break;
					}
			}
		}

		
		return validate;
	}
	
	public boolean isDuplicate(JTable table, JLabel lbl, int id) {
		boolean validate = false;
		
		if(table.getRowCount() > 0) {
			for(int i = 0; i < table.getRowCount(); i++) {	
					if(Integer.parseInt(table.getValueAt(i, 0).toString()) == id) {
						validate = true;
						lbl.setText("*Duplicate property");
						break;
					}
			}
		}

		
		return validate;
	}
}
