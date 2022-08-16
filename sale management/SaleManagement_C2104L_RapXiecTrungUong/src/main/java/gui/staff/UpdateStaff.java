package gui.staff;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.OrderDao;
import dao.StaffDao;
import entity.Staff;
import helper.Validation;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class UpdateStaff extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldName;
	private JTextField textFieldPassword;
	private JComboBox comboBoxLevel;
	private Staff staff;
	static int id;
	private StaffMainFrame mainFrame;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					UpdateStaff frame = new UpdateStaff();
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
	public UpdateStaff() {
		loadComponent();
	}
	
	public void setMainFrame(StaffMainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	public void loadComponent() {
		setTitle("Update Staff");
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblName = new JLabel("Name : ");
		
		JLabel lblPassword = new JLabel("Password : ");
		
		textFieldName = new JTextField();
		textFieldName.setColumns(10);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setColumns(10);
		
		JButton Update = new JButton("Update");
		Update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateActionPerformed(e);
			}
		});
		
		JLabel lblLevel = new JLabel("Level : ");
		
		comboBoxLevel = new JComboBox();
		comboBoxLevel.setModel(new DefaultComboBoxModel(new String[] {"Qu\u1EA3n L\u00FD ", "Nh\u00E2n Vi\u00EAn"}));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(166)
							.addComponent(Update))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(69)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lblName, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblPassword, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
								.addComponent(lblLevel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(comboBoxLevel, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(textFieldName)
								.addComponent(textFieldPassword, GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE))))
					.addContainerGap(10, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(47)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addGap(30)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLevel)
						.addComponent(comboBoxLevel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPassword))
					.addGap(28)
					.addComponent(Update)
					.addGap(32))
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
		public UpdateStaff(Staff stf) {
			loadComponent();
			staff = stf;
			id = staff.getId(); 
			textFieldName.setText(staff.getName());
			if(staff.getLevel()==0) {
				comboBoxLevel.setSelectedIndex(0);
			}else {
			comboBoxLevel.setSelectedIndex(1);
			}
			textFieldPassword.setText(staff.getPassword());
		}
	protected void updateActionPerformed(ActionEvent e) {	
		StaffDao dao = new StaffDao();
		Staff st = new Staff();
		if(Validation.checkTextStaff(textFieldName)&&Validation.checkTextStaff(textFieldPassword)&&
			Validation.checkElementUserName(textFieldName, dao.getListStaff(),staff.getName())){
			st.setName(textFieldName.getText());
			st.setLevel(comboBoxLevel.getSelectedIndex());
			st.setPassword(textFieldPassword.getText());
			st.setId(id);
		int result  = JOptionPane.showConfirmDialog(null, "Would you like to Update Staff", "Yes/No Question", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if(result  == JOptionPane.YES_OPTION) {
			StaffMainFrame main = new StaffMainFrame();
			dao.updateStaff(st);
			this.setVisible(false);
			mainFrame.setVisible(true);
			mainFrame.loadData(dao);
			JOptionPane.showMessageDialog(main, "Update Success");
			}
		}
	}
	
}
