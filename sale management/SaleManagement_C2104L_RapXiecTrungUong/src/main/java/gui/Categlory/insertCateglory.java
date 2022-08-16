package gui.Categlory;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.CategoryDao;
import entity.Category;
import entity.Staff;
import gui.Categlory.CategloryMainFrame;
import helper.Validation;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class insertCateglory extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					insertCateglory frame = new insertCateglory();
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
	public insertCateglory() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 611, 356);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Categlory Name");
		lblNewLabel.setBounds(103, 103, 105, 30);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(218, 104, 255, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("AddItems");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertData(e);
				
			}
			
		});
		btnNewButton.setBounds(93, 142, 115, 36);
		contentPane.add(btnNewButton);
	}
	
	
	protected void insertData (ActionEvent e) {
		
		CategoryDao dao = new CategoryDao();
		Category category = new Category(); 
		CategloryMainFrame main = new CategloryMainFrame();
		main.loadData(dao);
		category.setName(textField.getText());
		int result  = JOptionPane.showConfirmDialog(null, "Would you like to insert Categlory", "Yes/No Question", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if(Validation.checkElementCategory(textField , dao.getlistCate() ,"")) {
		dao.insertCateglory(category);
		this.setVisible(false);
		JOptionPane.showMessageDialog(main, "Insert Success");
		}
	
	}
		
	
}
