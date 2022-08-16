package gui.Categlory;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.CategoryDao;
import entity.Category;

import gui.Categlory.CategloryMainFrame;
import helper.Validation;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class UpdateCategory extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private Category categorys;
	private JButton update;
	private int id;
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateCategory frame = new UpdateCategory();
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
	public UpdateCategory(Category cate) {
		categorys = cate;
		loadComponent();
		textField.setText(categorys.getName());
	}
	public UpdateCategory() {
		setResizable(false);
		loadComponent();
	}

	private void loadComponent() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 611, 356);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Categlory");
		lblNewLabel.setBounds(101, 103, 105, 30);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(216, 104, 255, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		
		update = new JButton("update");
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButtonActionPerformed(e);
			}
		});
		update.setBounds(91, 143, 115, 36);
		contentPane.add(update);
	}
	public void btnNewButtonActionPerformed(ActionEvent e) {
		
		CategoryDao dao = new CategoryDao();
		Category category = new Category(); 
		CategloryMainFrame main = new CategloryMainFrame();
		category.setName(textField.getText());
		List<Category> list = new ArrayList<Category>();
		int result  = JOptionPane.showConfirmDialog(null, "Would you like to Update Categlory", "Yes/No Question", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if(result  == JOptionPane.YES_OPTION) {
			if(Validation.checkElementCategory(textField , dao.getlistCate() , categorys.getName())) {
			category.setId(id);
			dao.updateCateglory(category);
			this.setVisible(false);
			JOptionPane.showMessageDialog(main, "update Success");
			main.loadData(dao);	
		}
		}
		
		
	}
}
