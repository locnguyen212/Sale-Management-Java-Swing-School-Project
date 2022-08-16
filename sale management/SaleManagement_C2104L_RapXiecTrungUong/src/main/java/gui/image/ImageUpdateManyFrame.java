package gui.image;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.border.TitledBorder;

import dao.ImageDao;
import dao.ProductNameDao;
import helper.Validation;
import helper.loc.AutoCompletion;
import helper.loc.ComboItem;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.awt.event.ActionEvent;

public class ImageUpdateManyFrame extends JFrame {

	private JPanel contentPane;
	private JLabel productNameLbl;
	private JComboBox productNameCb;
	private JButton submitBtn;
	private ImageMainFrame mainFrame;
	private String idsString;
	
	
	public void setIdsString(String idsString) {
		this.idsString = idsString;
	}

	public void setMainFrame(ImageMainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ImageUpdateManyFrame frame = new ImageUpdateManyFrame();
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
	public ImageUpdateManyFrame() {
		setTitle("Image update many");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 118);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(null, "Update many", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		productNameLbl = new JLabel("Product Name");
		GridBagConstraints gbc_productNameLbl = new GridBagConstraints();
		gbc_productNameLbl.insets = new Insets(0, 0, 5, 5);
		gbc_productNameLbl.anchor = GridBagConstraints.EAST;
		gbc_productNameLbl.gridx = 0;
		gbc_productNameLbl.gridy = 0;
		contentPane.add(productNameLbl, gbc_productNameLbl);
		
		productNameCb = new JComboBox();
		AutoCompletion.enable(productNameCb);
		GridBagConstraints gbc_productNameCb = new GridBagConstraints();
		gbc_productNameCb.insets = new Insets(0, 0, 5, 0);
		gbc_productNameCb.fill = GridBagConstraints.HORIZONTAL;
		gbc_productNameCb.gridx = 1;
		gbc_productNameCb.gridy = 0;
		contentPane.add(productNameCb, gbc_productNameCb);
		
		submitBtn = new JButton("Submit");
		submitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submitBtnActionPerformed(e);
			}
		});
		GridBagConstraints gbc_submitBtn = new GridBagConstraints();
		gbc_submitBtn.gridwidth = 2;
		gbc_submitBtn.insets = new Insets(0, 0, 0, 5);
		gbc_submitBtn.gridx = 0;
		gbc_submitBtn.gridy = 1;
		contentPane.add(submitBtn, gbc_submitBtn);
		
		this.addWindowListener(new WindowAdapter() {
			 @Override
	            public void windowClosed(WindowEvent e) {
	                mainFrame.setEnabled(true);
	                mainFrame.setVisible(true);
	            }		
		});
		
		setupData();
	}
	
	public void setupData() {
		var dao = new ProductNameDao();
		for(var pro : dao.getDb()) {	
			productNameCb.addItem(new ComboItem(pro.getName(), pro.getId()));		
		}
	}
	
	protected void submitBtnActionPerformed(ActionEvent e) {
		var dao = new ImageDao();
		var img = new entity.Image();
				
		int productNameId = ((ComboItem)productNameCb.getSelectedItem()).getValue();

		
		int i = JOptionPane.showConfirmDialog(this, "Are you sure?", "Info", JOptionPane.OK_OPTION);
		if(i == JOptionPane.OK_OPTION) {
			dao.update(idsString, productNameId);
			
			mainFrame.setEnabled(true);
			mainFrame.loadDb();
			this.dispose();
			JOptionPane.showMessageDialog(mainFrame, "Done!");
		}

	}
}
