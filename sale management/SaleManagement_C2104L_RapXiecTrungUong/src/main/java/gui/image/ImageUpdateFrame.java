package gui.image;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import dao.ImageDao;
import dao.ProductNameDao;
import entity.ProductName;
import helper.Validation;
import helper.loc.AutoCompletion;
import helper.loc.ComboItem;

import javax.swing.border.EtchedBorder;

public class ImageUpdateFrame extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JLabel imageLbl;
	private JButton loadImgBtn;
	private JScrollPane scrollPane;
	private JLabel imageValidate;
	private JButton submitBtn;
	private JLabel loadImgLbl;
	private ImageMainFrame mainFrame;
	private String sourcePath;
	private String newDirPath;
	private String oldSourcePath;
	private String oldDbPath;
	private String dbPath;
	private String fileName;
	private JLabel productNameLbl;
	private JComboBox productNameCb;
	private entity.Image oldImage;
	private ProductName proName;
	
	
	
	

	public void setProName(ProductName proName) {
		this.proName = proName;
	}

	public void setOldImage(entity.Image oldImage) {
		this.oldImage = oldImage;
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
					ImageUpdateFrame frame = new ImageUpdateFrame();
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
	public ImageUpdateFrame() {
		setTitle("Image update");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Update", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPane.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		productNameLbl = new JLabel("Product Name");
		GridBagConstraints gbc_productNameLbl = new GridBagConstraints();
		gbc_productNameLbl.anchor = GridBagConstraints.EAST;
		gbc_productNameLbl.insets = new Insets(0, 0, 5, 5);
		gbc_productNameLbl.gridx = 0;
		gbc_productNameLbl.gridy = 0;
		panel.add(productNameLbl, gbc_productNameLbl);
		
		productNameCb = new JComboBox();
		AutoCompletion.enable(productNameCb);
		GridBagConstraints gbc_productNameCb = new GridBagConstraints();
		gbc_productNameCb.insets = new Insets(0, 0, 5, 0);
		gbc_productNameCb.fill = GridBagConstraints.HORIZONTAL;
		gbc_productNameCb.gridx = 1;
		gbc_productNameCb.gridy = 0;
		panel.add(productNameCb, gbc_productNameCb);
		
		imageLbl = new JLabel("Image");
		GridBagConstraints gbc_imageLbl = new GridBagConstraints();
		gbc_imageLbl.insets = new Insets(0, 0, 5, 5);
		gbc_imageLbl.gridx = 0;
		gbc_imageLbl.gridy = 1;
		panel.add(imageLbl, gbc_imageLbl);
		
		loadImgBtn = new JButton("Load Image");
		loadImgBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadImgBtnActionPerformed(e);
			}
		});
		GridBagConstraints gbc_loadImgBtn = new GridBagConstraints();
		gbc_loadImgBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_loadImgBtn.insets = new Insets(0, 0, 5, 0);
		gbc_loadImgBtn.gridx = 1;
		gbc_loadImgBtn.gridy = 1;
		panel.add(loadImgBtn, gbc_loadImgBtn);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		panel.add(scrollPane, gbc_scrollPane);
		
		loadImgLbl = new JLabel("");
		scrollPane.setViewportView(loadImgLbl);
		
		imageValidate = new JLabel("");
		imageValidate.setForeground(Color.RED);
		GridBagConstraints gbc_imageValidate = new GridBagConstraints();
		gbc_imageValidate.insets = new Insets(0, 0, 5, 0);
		gbc_imageValidate.gridwidth = 2;
		gbc_imageValidate.gridx = 0;
		gbc_imageValidate.gridy = 3;
		panel.add(imageValidate, gbc_imageValidate);
		
		submitBtn = new JButton("Submit");
		submitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submitBtnActionPerformed(e);
			}
		});
		GridBagConstraints gbc_submitBtn = new GridBagConstraints();
		gbc_submitBtn.gridwidth = 2;
		gbc_submitBtn.gridx = 0;
		gbc_submitBtn.gridy = 4;
		panel.add(submitBtn, gbc_submitBtn);
		
		this.addWindowListener(new WindowAdapter() {
			 @Override
	            public void windowClosed(WindowEvent e) {
	                mainFrame.setEnabled(true);
	                mainFrame.setVisible(true);
	            }		
		});
	}
	
	public void setupData() {
		var dao = new ProductNameDao();
		for(var pro : dao.getDb()) {	
			productNameCb.addItem(new ComboItem(pro.getName(), pro.getId()));		
		}
		
		productNameCb.getModel().setSelectedItem(new ComboItem(proName.getName(), proName.getId()));
		
		oldSourcePath = System.getProperty("user.dir") + oldImage.getName();		
		oldDbPath = oldImage.getName();
		
		loadImgLbl.setIcon(new ImageIcon(
				new ImageIcon(oldSourcePath).getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH)
		));		
	}

	protected void loadImgBtnActionPerformed(ActionEvent e) {
		imageValidate.setText("");
		JFileChooser img = new JFileChooser();
		img.setDialogTitle("Choose an image");
		img.setFileFilter(new FileNameExtensionFilter("image with (jpg, png, gif)", "jpg", "png", "gif"));
		img.setAcceptAllFileFilterUsed(false);
		int result = img.showOpenDialog(null);
		
		if(result == img.APPROVE_OPTION) {
			File f = img.getSelectedFile();
			String fileNameOld = f.getName();
	        String fileExtension = fileNameOld.substring(fileNameOld.lastIndexOf("."),fileNameOld.length());
	        sourcePath = f.getAbsolutePath();
	        newDirPath = System.getProperty("user.dir") + "\\image";
	        fileName = new Date().getTime() + fileExtension;
	        dbPath = "\\image\\" + fileName;
       

			loadImgLbl.setIcon(new ImageIcon(
					new ImageIcon(sourcePath).getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH)
			));					
		}
	}
	
	protected void submitBtnActionPerformed(ActionEvent e) {	
		var dao = new ImageDao();
		var img = new entity.Image();

		boolean imageVal = Validation.imageValidate(sourcePath, newDirPath, dbPath, fileName);
		
		if(imageVal) {
			imageValidate.setText("");
			img.setName(dbPath);
		}else {
			img.setName(oldDbPath);
		}
				
		img.setProductNameId(((ComboItem)productNameCb.getSelectedItem()).getValue());
		img.setId(oldImage.getId());
		
		if(imageVal) {
			int i = JOptionPane.showConfirmDialog(this, "Are you sure?", "Info", JOptionPane.OK_OPTION);
			if(i == JOptionPane.OK_OPTION) {
				dao.update(img);
				
				Path newDir = Paths.get(newDirPath);
				Path source = Paths.get(sourcePath);
				try {
					Files.copy(source, newDir.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
					File f = new File(oldSourcePath);
					f.delete();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				mainFrame.setEnabled(true);
				mainFrame.loadDb();
				this.dispose();
				JOptionPane.showMessageDialog(mainFrame, "Done!");
			}
		}else {
			int i = JOptionPane.showConfirmDialog(this, "Are you sure?", "Info", JOptionPane.OK_OPTION);
			if(i == JOptionPane.OK_OPTION) {
				dao.update(img);
				
				mainFrame.setEnabled(true);
				mainFrame.loadDb();
				this.dispose();
				JOptionPane.showMessageDialog(mainFrame, "Done!");
			}
		}
	}

}
