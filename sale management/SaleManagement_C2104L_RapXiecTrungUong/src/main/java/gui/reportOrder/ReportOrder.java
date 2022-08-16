package gui.reportOrder;

import java.awt.BorderLayout;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.OrderDetailDao;
import entity.Order;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;
import java.time.format.DateTimeFormatter;

public class ReportOrder extends JFrame {

	private JPanel contentPane;
	static Order order;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ReportOrder frame = new ReportOrder();
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
//	public ReportOrder() {
//		loadComponent();
//	}
	public ReportOrder(Order ord) {
		order = ord;
		loadComponent();
	}
	public void loadComponent() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Report Order");
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		showReport();
	}
	
	public void showReport() {
		try {
			List<Map<String,?>> list = new ArrayList<Map<String,?>>();
			OrderDetailDao dao = new OrderDetailDao();
			DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm  dd/MM/yyyy"); 
//			 DecimalFormat formatter = new DecimalFormat("### ### ###");
			for(var detail : dao.slectOrderDetailWithOrderID(order.getId())) {
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("id", detail.getId());
				map.put("productName", detail.getProductName());
				map.put("quantity", detail.getQuantity());
				map.put("totalCash", Integer.valueOf(order.getTolTalCash()));
				map.put("orderNumber", order.getOrderNumber());
				map.put("staffName", order.getNameStaff());
				map.put("createdAt", order.getCreatedAt().format(format));
				list.add(map);
			}
			JRDataSource datasource = new JRBeanCollectionDataSource(list);
			JasperReport rp = JasperCompileManager.compileReport("reportorder/ReportOrder.jrxml");
			JasperPrint print = JasperFillManager.fillReport(rp, null,datasource);
			this.getContentPane().add(new JRViewer(print));
			this.pack();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
