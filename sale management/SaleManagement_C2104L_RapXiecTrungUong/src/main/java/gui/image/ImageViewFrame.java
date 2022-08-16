package gui.image;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

public class ImageViewFrame extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JLabel imageLbl;
	private String dbPath;
	private static ImageViewFrame frame;
	
	public static ImageViewFrame getInstance() {
		if(frame == null) {
			frame = new ImageViewFrame();
		}
		return frame;
	}
	

	public void setDbPath(String dbPath) {
		this.dbPath = dbPath;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ImageViewFrame frame = new ImageViewFrame();
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
	public ImageViewFrame() {
		setTitle("View Image");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		imageLbl = new JLabel("");
		scrollPane.setViewportView(imageLbl);
	}

	public void viewImage() {
		imageLbl.setIcon(new ImageIcon(
				new ImageIcon(System.getProperty("user.dir")+dbPath).getImage().getScaledInstance(490, 490, Image.SCALE_SMOOTH)
		));
	}
}
