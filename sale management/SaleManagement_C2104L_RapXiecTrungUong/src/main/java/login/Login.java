package login;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import dao.StaffDao;
import entity.ProductName;
import entity.Staff;


import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Font;
import javax.swing.border.MatteBorder;

import MainFrame.Main;
import keeptoo.KGradientPanel;


public class Login {

	private JFrame frame;
	private JTextField textFieldUserName;
	private JPasswordField textFieldPassword;
	private List<Staff> listStaff;
	private JLabel icon1;
	private JLabel icon2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
		this.icon1.setVisible(false);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\ADMIN\\Desktop\\source final\\source\\SaleManagement_C2104L_RapXiecTrungUong\\image\\Coffee-break-icon.png"));
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setResizable(false);
		frame.setBounds(100, 100, 1000, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblUserName = new JLabel("Username: ");
		lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUserName.setForeground(Color.WHITE);
		lblUserName.setBackground(Color.WHITE);
		lblUserName.setBounds(306, 177, 75, 32);
		
		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setBounds(306, 225, 75, 32);
		
		textFieldUserName = new JTextField();
		textFieldUserName.setOpaque(false);
		textFieldUserName.setForeground(Color.WHITE);
		textFieldUserName.setCaretColor(Color.WHITE);
		textFieldUserName.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		textFieldUserName.setBackground(Color.BLACK);
		textFieldUserName.setBounds(391, 178, 297, 35);
		textFieldUserName.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setForeground(Color.BLACK);
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLogin.setBounds(306, 270, 182, 35);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaffDao dao = new StaffDao();
				String passwordLoggin = textFieldPassword.getText();
				String usernameLoggin = textFieldUserName.getText();
				if(passwordLoggin.isEmpty() || usernameLoggin.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please enter your username and password","Warning",
					        JOptionPane.WARNING_MESSAGE);
					return;
				}
				Staff staff = dao.getListStaff().stream()
		    			  .filter(st -> usernameLoggin.equals(st.getName()))
		    			  .findAny()
		    			  .orElse(null);
				if(staff ==null) {
					JOptionPane.showMessageDialog(null, "UserName non-existent","Warning",
					        JOptionPane.WARNING_MESSAGE);
				}
				else if(staff.getPassword().equals(passwordLoggin) && staff.getName().equals(usernameLoggin)) {
					Main maf = new Main(staff);
					frame.setVisible(false);
					maf.main(null);				
				}else{
					JOptionPane.showMessageDialog(null, "Invalid Login","Warning",
					        JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		JButton btnExit = new JButton("Exit");
		btnExit.setForeground(Color.BLACK);
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exitActionPerformed(e);
			}
		});
		btnExit.setBounds(516, 270, 171, 35);
		icon1 = new JLabel("");
		icon1.setBounds(700, 226, 44, 35);
		icon1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				icon1mousePressed(e);
			}
		});
		ImageIcon imageIcon = new ImageIcon(new ImageIcon(Login.class.getResource("/iconLogin/eye.png")).getImage().getScaledInstance(30, 20, Image.SCALE_SMOOTH));
		icon1.setIcon(imageIcon);
		
		icon2 = new JLabel("");
		icon2.setBounds(700, 226, 44, 35);
		icon2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				icon2MousePressed(e);
			}
		});
		ImageIcon imageIcon2 = new ImageIcon(new ImageIcon(Login.class.getResource("/iconLogin/invisible.png")).getImage().getScaledInstance(30, 20, Image.SCALE_SMOOTH));
		icon2.setIcon(imageIcon2);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(lblUserName);
		frame.getContentPane().add(textFieldUserName);
		frame.getContentPane().add(btnLogin);
		frame.getContentPane().add(btnExit);
		frame.getContentPane().add(lblPassword);
		frame.getContentPane().add(icon2);
		frame.getContentPane().add(icon1);
		
		KGradientPanel gradientPanel = new KGradientPanel();
		gradientPanel.setBounds(0, 0, 1049, 463);
		frame.getContentPane().add(gradientPanel);
		gradientPanel.setLayout(null);
		
		textFieldPassword = new JPasswordField();
		textFieldPassword.setBounds(391, 235, 296, 15);
		gradientPanel.add(textFieldPassword);
		textFieldPassword.setOpaque(false);
		textFieldPassword.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		textFieldPassword.setBackground(Color.BLACK);
		textFieldPassword.setForeground(Color.WHITE);
		textFieldPassword.setColumns(10);
	}
	protected void icon1mousePressed(MouseEvent e) {
		icon2.setVisible(true);
		icon1.setVisible(false);
		textFieldPassword.setEchoChar((char)0);
	}
	protected void icon2MousePressed(MouseEvent e) {
		icon1.setVisible(true);
		icon2.setVisible(false);
		textFieldPassword.setEchoChar('*');
	}
	protected void exitActionPerformed(ActionEvent e) {
		System.exit(0);
	}
}
