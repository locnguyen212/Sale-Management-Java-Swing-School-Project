package MainFrame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Component;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import dao.ProductDao;
import entity.Product;
import entity.Staff;
import gui.properties.PropertiesMainFrame;
import gui.order.MainFrame;
import gui.Categlory.CategloryMainFrame;
import gui.staff.StaffMainFrame;

import gui.image.ImageMainFrame;
import helper.HelperFunction;
import gui.product.ProductInsertFrame;
import gui.product.ProductMainFrame;

import entity.OrderDetail;
import dao.OrderDetailDao;

import com.jgoodies.forms.layout.FormSpecs;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.format.DateTimeFormatter;
import java.util.List;

import keeptoo.KGradientPanel;
import java.awt.Toolkit;




//import dao.ProductDao;
//import entity.Product;


public class Main extends JFrame {
	
	public JPanel Indexframe;
	private JLabel lblNewLabel_1;
	static Staff staff;
	static JLabel lblName;
	private KGradientPanel gradientPanel;
	private JLabel lblNewLabel_3;
	private KGradientPanel gradientPanel_1;
	private JLabel count_Product;
	private List<Product> list_count;
	private JLabel lblNewLabel_4;
	private KGradientPanel gradientPanel_3;
	private JLabel lblNewLabel_6;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main(Staff st) {
		staff =st;
		loadData();
		
	}
	public Main() {
		setTitle("Home ");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\ADMIN\\Desktop\\source final\\Sale Management Java\\SaleManagement_C2104L_RapXiecTrungUong\\image\\Coffee-break-icon.png"));;
	loadData();
	}
	public void loadData() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 639, 709);
		Indexframe = new JPanel();
		Indexframe.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Indexframe);
		Indexframe.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel_1MouseClicked(e);
			}
		});
		panel_1.setBackground(Color.BLUE);
		panel_1.setAutoscrolls(true);
		panel_1.setBounds(37, 298, 159, 131);
		Indexframe.add(panel_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\ADMIN\\Desktop\\source final\\source\\SaleManagement_C2104L_RapXiecTrungUong\\image\\house-solid.png"));
		
		lblNewLabel_1 = new JLabel("image");
		lblNewLabel_1.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(55)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(32)
					.addComponent(lblNewLabel)
					.addGap(10)
					.addComponent(lblNewLabel_1))
		);
		panel_1.setLayout(gl_panel_1);
		
		JPanel Properties = new JPanel();
		Properties.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PropertiesMainFrame PF = new PropertiesMainFrame();
				PF.setVisible(true);
			}
		});
		Properties.setBackground(Color.BLUE);
		Properties.setAutoscrolls(true);
		Properties.setBounds(215, 298, 159, 131);
		Indexframe.add(Properties);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\ADMIN\\Desktop\\source final\\source\\SaleManagement_C2104L_RapXiecTrungUong\\image\\house-solid-1.png"));
		lblNewLabel_2.setAlignmentX(1.0f);
		
		JLabel lblNewLabel_1_1 = new JLabel("Properties");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1_1.setAlignmentX(1.0f);
		GroupLayout gl_Properties = new GroupLayout(Properties);
		gl_Properties.setHorizontalGroup(
			gl_Properties.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Properties.createSequentialGroup()
					.addGroup(gl_Properties.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_Properties.createSequentialGroup()
							.addGap(55)
							.addComponent(lblNewLabel_2))
						.addGroup(gl_Properties.createSequentialGroup()
							.addGap(41)
							.addComponent(lblNewLabel_1_1)))
					.addContainerGap(41, Short.MAX_VALUE))
		);
		gl_Properties.setVerticalGroup(
			gl_Properties.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Properties.createSequentialGroup()
					.addGap(32)
					.addComponent(lblNewLabel_2)
					.addGap(10)
					.addComponent(lblNewLabel_1_1)
					.addGap(24))
		);
		Properties.setLayout(gl_Properties);
		
		JPanel Orders = new JPanel();
		Orders.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainFrame Or = new MainFrame(staff.getId());
				Or.setVisible(true);

		
			}
		});
		Orders.setBackground(Color.BLUE);
		Orders.setAutoscrolls(true);
		Orders.setBounds(396, 298, 159, 131);
		Indexframe.add(Orders);
		
		JLabel lblNewLabel_2_1 = new JLabel("");
		lblNewLabel_2_1.setIcon(new ImageIcon("C:\\Users\\ADMIN\\Desktop\\source final\\source\\SaleManagement_C2104L_RapXiecTrungUong\\image\\house-solid-2.png"));
		lblNewLabel_2_1.setAlignmentX(1.0f);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Orders");
		lblNewLabel_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1_1_1.setAlignmentX(1.0f);
		GroupLayout gl_Orders = new GroupLayout(Orders);
		gl_Orders.setHorizontalGroup(
			gl_Orders.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_Orders.createSequentialGroup()
					.addGap(50)
					.addGroup(gl_Orders.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1_1_1)
						.addComponent(lblNewLabel_2_1))
					.addContainerGap(59, Short.MAX_VALUE))
		);
		gl_Orders.setVerticalGroup(
			gl_Orders.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_Orders.createSequentialGroup()
					.addContainerGap(30, Short.MAX_VALUE)
					.addComponent(lblNewLabel_2_1)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel_1_1_1)
					.addGap(26))
		);
		Orders.setLayout(gl_Orders);
		
		JPanel panel_1_1_1_1 = new JPanel();
		panel_1_1_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CategloryMainFrame OF = new CategloryMainFrame();
				OF.setVisible(true);
//				CategloryMainFrame Or = new CategloryMainFrame();
//				Or.setVisible(true);
			}
		});
		panel_1_1_1_1.setBackground(Color.BLUE);
		panel_1_1_1_1.setAutoscrolls(true);
		panel_1_1_1_1.setBounds(37, 456, 159, 131);
		Indexframe.add(panel_1_1_1_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("");
		lblNewLabel_2_1_1.setIcon(new ImageIcon("C:\\Users\\ADMIN\\Desktop\\source final\\source\\SaleManagement_C2104L_RapXiecTrungUong\\image\\house-solid-3.png"));
		lblNewLabel_2_1_1.setAlignmentX(1.0f);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Categlory");
		lblNewLabel_1_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1_1_1_1.setAlignmentX(1.0f);
		GroupLayout gl_panel_1_1_1_1 = new GroupLayout(panel_1_1_1_1);
		gl_panel_1_1_1_1.setHorizontalGroup(
			gl_panel_1_1_1_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel_1_1_1_1.createSequentialGroup()
					.addGap(45)
					.addComponent(lblNewLabel_1_1_1_1, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(79, Short.MAX_VALUE))
				.addGroup(gl_panel_1_1_1_1.createSequentialGroup()
					.addContainerGap(91, Short.MAX_VALUE)
					.addComponent(lblNewLabel_2_1_1)
					.addGap(53))
		);
		gl_panel_1_1_1_1.setVerticalGroup(
			gl_panel_1_1_1_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1_1_1_1.createSequentialGroup()
					.addGap(32)
					.addComponent(lblNewLabel_2_1_1)
					.addGap(18)
					.addComponent(lblNewLabel_1_1_1_1)
					.addGap(16))
		);
		panel_1_1_1_1.setLayout(gl_panel_1_1_1_1);
		
		JPanel panel_1_1_1_2_1 = new JPanel();
		panel_1_1_1_2_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ProductMainFrame PM = new ProductMainFrame();
				PM.setVisible(true);
				
//				MainFrame Or = new MainFrame();
//				Or.setVisible(true);
				
			}
		});
		panel_1_1_1_2_1.setBackground(Color.BLUE);
		panel_1_1_1_2_1.setAutoscrolls(true);
		panel_1_1_1_2_1.setBounds(396, 456, 159, 131);
		Indexframe.add(panel_1_1_1_2_1);
		
		JLabel lblNewLabel_2_1_2_1 = new JLabel("");
		lblNewLabel_2_1_2_1.setIcon(new ImageIcon("C:\\Users\\ADMIN\\Desktop\\source final\\source\\SaleManagement_C2104L_RapXiecTrungUong\\image\\product222.png"));
		lblNewLabel_2_1_2_1.setAlignmentX(1.0f);
		
		JLabel lblNewLabel_1_1_1_2_1_1_1 = new JLabel("Product");
		lblNewLabel_1_1_1_2_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1_2_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1_1_1_2_1_1_1.setAlignmentX(1.0f);
		GroupLayout gl_panel_1_1_1_2_1 = new GroupLayout(panel_1_1_1_2_1);
		gl_panel_1_1_1_2_1.setHorizontalGroup(
			gl_panel_1_1_1_2_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1_1_1_2_1.createSequentialGroup()
					.addGap(55)
					.addGroup(gl_panel_1_1_1_2_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1_1_1_2_1_1_1, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2_1_2_1))
					.addGap(33))
		);
		gl_panel_1_1_1_2_1.setVerticalGroup(
			gl_panel_1_1_1_2_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1_1_1_2_1.createSequentialGroup()
					.addGap(32)
					.addComponent(lblNewLabel_2_1_2_1)
					.addGap(18)
					.addComponent(lblNewLabel_1_1_1_2_1_1_1, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addGap(16))
		);
		panel_1_1_1_2_1.setLayout(gl_panel_1_1_1_2_1);
		
		JPanel panel_1_1_1_2_1_1 = new JPanel();
		panel_1_1_1_2_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				StaffMainFrame SM = new StaffMainFrame(staff);
				SM.setVisible(true);

			}
		});
		panel_1_1_1_2_1_1.setBackground(Color.BLUE);
		panel_1_1_1_2_1_1.setAutoscrolls(true);
		panel_1_1_1_2_1_1.setBounds(215, 456, 159, 131);
		Indexframe.add(panel_1_1_1_2_1_1);
		
		JLabel lblNewLabel_2_1_2_1_1 = new JLabel("");
		lblNewLabel_2_1_2_1_1.setIcon(new ImageIcon("C:\\Users\\ADMIN\\Desktop\\source final\\source\\SaleManagement_C2104L_RapXiecTrungUong\\image\\stafff.png"));
		lblNewLabel_2_1_2_1_1.setAlignmentX(1.0f);
		
		JLabel lblNewLabel_1_1_1_2_1_1 = new JLabel("Staff");
		lblNewLabel_1_1_1_2_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1_1_1_2_1_1.setAlignmentX(1.0f);
		GroupLayout gl_panel_1_1_1_2_1_1 = new GroupLayout(panel_1_1_1_2_1_1);
		gl_panel_1_1_1_2_1_1.setHorizontalGroup(
			gl_panel_1_1_1_2_1_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1_1_1_2_1_1.createSequentialGroup()
					.addGap(55)
					.addGroup(gl_panel_1_1_1_2_1_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1_1_1_2_1_1.createSequentialGroup()
							.addGap(10)
							.addComponent(lblNewLabel_1_1_1_2_1_1, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblNewLabel_2_1_2_1_1))
					.addGap(44))
		);
		gl_panel_1_1_1_2_1_1.setVerticalGroup(
			gl_panel_1_1_1_2_1_1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_1_1_1_2_1_1.createSequentialGroup()
					.addContainerGap(29, Short.MAX_VALUE)
					.addComponent(lblNewLabel_2_1_2_1_1, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel_1_1_1_2_1_1)
					.addGap(18))
		);
		panel_1_1_1_2_1_1.setLayout(gl_panel_1_1_1_2_1_1);
		
		gradientPanel = new KGradientPanel();
		gradientPanel.setBounds(-33, 0, 675, 40);
		Indexframe.add(gradientPanel);
		gradientPanel.setLayout(null);
		
		lblName = new JLabel("");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblName.setForeground(Color.WHITE);
		lblName.setBounds(522, 12, 129, 13);
		gradientPanel.add(lblName);
		lblName.setText(staff.getName());
		
		lblNewLabel_3 = new JLabel("Xin ChÃ o ");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(449, 12, 70, 13);
		gradientPanel.add(lblNewLabel_3);
		
		gradientPanel_1 = new KGradientPanel();
		gradientPanel_1.setBounds(41, 112, 160, 135);
		Indexframe.add(gradientPanel_1);
		gradientPanel_1.setLayout(null);
		
		
		


		var dao = new ProductDao();
		int kichthuoc = dao.getDb().size();
		String value1 =String.valueOf(kichthuoc);
		 var  dao1 = new OrderDetailDao();
		 var TopProduct =  dao.TopOneProduct();
		 var Report_Don_Hang =dao1.Report_Me();

		
		

		 
		 
		
		JLabel lbl_count_product = new JLabel((String) null);
		lbl_count_product.setForeground(Color.WHITE);
		lbl_count_product.setFont(new Font("Tahoma", Font.BOLD, 30));
		lbl_count_product.setBounds(54, 71, 46, 39);
		gradientPanel_1.add(lbl_count_product);
		lbl_count_product.setText(value1);
		
		JLabel lblNewLabel_5 = new JLabel("Tá»•ng sáº£n pháº©m");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setBounds(22, 40, 114, 29);
		gradientPanel_1.add(lblNewLabel_5);
		
		
		
		
		

		
		

		
		
		
		

		
		lblNewLabel_4 = new JLabel("Sáº£n Pháº©m");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setBounds(45, 81, 71, 13);

		
		gradientPanel_3 = new KGradientPanel();
		gradientPanel_3.setLayout(null);
		gradientPanel_3.setBounds(216, 114, 169, 135);
		Indexframe.add(gradientPanel_3);
		
		
		
		
		
		
		JLabel lbl_count_orders = new JLabel("43");
		lbl_count_orders.setForeground(Color.WHITE);
		lbl_count_orders.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_count_orders.setBounds(10, 76, 132, 21);
		gradientPanel_3.add(lbl_count_orders);
		lbl_count_orders.setText(TopProduct);
		
		KGradientPanel gradientPanel_3_1 = new KGradientPanel();
		gradientPanel_3_1.setLayout(null);
		gradientPanel_3_1.setBounds(395, 117, 160, 130);
		Indexframe.add(gradientPanel_3_1);
		
		JLabel lbl_count_buy = new JLabel("DELL Gaming");
		lbl_count_buy.setForeground(Color.WHITE);
		lbl_count_buy.setFont(new Font("Tahoma", Font.BOLD, 20));
		lbl_count_buy.setBounds(10, 71, 137, 21);
		gradientPanel_3_1.add(lbl_count_buy);
		
		JLabel lblNewLabel_5_1 = new JLabel("Sáº£n Pháº©m bÃ¡n cháº¡y");
		lblNewLabel_5_1.setBounds(18, 31, 132, 29);
		gradientPanel_3_1.add(lblNewLabel_5_1);
		lblNewLabel_5_1.setForeground(Color.WHITE);
		lblNewLabel_5_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_count_orders.setText(Report_Don_Hang);
		
		JLabel lblNewLabel_5_1_1 = new JLabel("Tá»•ng Doanh Thu\r\n");
		lblNewLabel_5_1_1.setBounds(19, 37, 130, 29);
		gradientPanel_3.add(lblNewLabel_5_1_1);
		lblNewLabel_5_1_1.setForeground(Color.WHITE);
		lblNewLabel_5_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel đ = new JLabel("VNĐ");
		đ.setBounds(123, 75, 37, 21);
		gradientPanel_3.add(đ);
		đ.setForeground(Color.WHITE);
		đ.setFont(new Font("Tahoma", Font.BOLD, 17));
		
		lblNewLabel_6 = new JLabel("<html>Note: If you refresh data report please exit button and open app</html>");
		lblNewLabel_6.setBounds(37, 615, 522, 47);
		Indexframe.add(lblNewLabel_6);
//		
//		
		
	
//	

		
//		
		


		
		

// DONE Tá»”NG Sáº¢N PHáº¢M 
		ProductInsertFrame ins = new ProductInsertFrame();
		ins.setHomeFrame(this);
	

				
	}
	
	
	protected void panel_1MouseClicked(MouseEvent e) {
		
		ImageMainFrame IM = new ImageMainFrame();
		IM.setVisible(true);
		
	}
	}



	