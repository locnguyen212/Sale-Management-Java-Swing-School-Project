package service.jFrame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.JTable;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable table;
	private JPanel panel;
	private JLabel searchLbl;
	private JTextField searchTf;
	private JLabel searchValidate;
	private JButton loadBtn;
	private JLabel loadValidate;
	private JButton insertBtn;
	private JLabel insertValidate;
	private JButton updateBtn;
	private JLabel updateValidate;
	private JButton deleteBtn;
	private JLabel deleteValidate;
	private JButton exitBtn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(new TitledBorder(null, "Data", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane.setBounds(10, 11, 755, 477);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(table);
		
		panel = new JPanel();
		panel.setBounds(775, 11, 184, 387);
		contentPane.add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		searchLbl = new JLabel("Search");
		GridBagConstraints gbc_searchLbl = new GridBagConstraints();
		gbc_searchLbl.insets = new Insets(0, 0, 5, 5);
		gbc_searchLbl.anchor = GridBagConstraints.EAST;
		gbc_searchLbl.gridx = 0;
		gbc_searchLbl.gridy = 0;
		panel.add(searchLbl, gbc_searchLbl);
		
		searchTf = new JTextField();
		searchTf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchTfActionPerformed(e);
			}
		});
		GridBagConstraints gbc_searchTf = new GridBagConstraints();
		gbc_searchTf.insets = new Insets(0, 0, 5, 0);
		gbc_searchTf.fill = GridBagConstraints.HORIZONTAL;
		gbc_searchTf.gridx = 1;
		gbc_searchTf.gridy = 0;
		panel.add(searchTf, gbc_searchTf);
		searchTf.setColumns(10);
		
		searchValidate = new JLabel("");
		searchValidate.setForeground(Color.RED);
		GridBagConstraints gbc_searchValidate = new GridBagConstraints();
		gbc_searchValidate.insets = new Insets(0, 0, 5, 0);
		gbc_searchValidate.gridwidth = 2;
		gbc_searchValidate.gridx = 0;
		gbc_searchValidate.gridy = 1;
		panel.add(searchValidate, gbc_searchValidate);
		
		loadBtn = new JButton("Load/Refresh data");
		loadBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadBtnActionPerformed(e);
			}
		});
		GridBagConstraints gbc_loadBtn = new GridBagConstraints();
		gbc_loadBtn.insets = new Insets(0, 0, 5, 0);
		gbc_loadBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_loadBtn.gridwidth = 2;
		gbc_loadBtn.gridx = 0;
		gbc_loadBtn.gridy = 2;
		panel.add(loadBtn, gbc_loadBtn);
		
		loadValidate = new JLabel("");
		loadValidate.setForeground(Color.RED);
		GridBagConstraints gbc_loadValidate = new GridBagConstraints();
		gbc_loadValidate.insets = new Insets(0, 0, 5, 0);
		gbc_loadValidate.gridwidth = 2;
		gbc_loadValidate.gridx = 0;
		gbc_loadValidate.gridy = 3;
		panel.add(loadValidate, gbc_loadValidate);
		
		insertBtn = new JButton("Insert");
		insertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertBtnActionPerformed(e);
			}
		});
		GridBagConstraints gbc_insertBtn = new GridBagConstraints();
		gbc_insertBtn.insets = new Insets(0, 0, 5, 0);
		gbc_insertBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_insertBtn.gridwidth = 2;
		gbc_insertBtn.gridx = 0;
		gbc_insertBtn.gridy = 4;
		panel.add(insertBtn, gbc_insertBtn);
		
		insertValidate = new JLabel("");
		insertValidate.setForeground(Color.RED);
		GridBagConstraints gbc_insertValidate = new GridBagConstraints();
		gbc_insertValidate.insets = new Insets(0, 0, 5, 0);
		gbc_insertValidate.gridwidth = 2;
		gbc_insertValidate.gridx = 0;
		gbc_insertValidate.gridy = 5;
		panel.add(insertValidate, gbc_insertValidate);
		
		updateBtn = new JButton("Update");
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateBtnActionPerformed(e);
			}
		});
		GridBagConstraints gbc_updateBtn = new GridBagConstraints();
		gbc_updateBtn.insets = new Insets(0, 0, 5, 0);
		gbc_updateBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_updateBtn.gridwidth = 2;
		gbc_updateBtn.gridx = 0;
		gbc_updateBtn.gridy = 6;
		panel.add(updateBtn, gbc_updateBtn);
		
		updateValidate = new JLabel("");
		updateValidate.setForeground(Color.RED);
		GridBagConstraints gbc_updateValidate = new GridBagConstraints();
		gbc_updateValidate.insets = new Insets(0, 0, 5, 0);
		gbc_updateValidate.gridwidth = 2;
		gbc_updateValidate.gridx = 0;
		gbc_updateValidate.gridy = 7;
		panel.add(updateValidate, gbc_updateValidate);
		
		deleteBtn = new JButton("Delete");
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteBtnActionPerformed(e);
			}
		});
		GridBagConstraints gbc_deleteBtn = new GridBagConstraints();
		gbc_deleteBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_deleteBtn.insets = new Insets(0, 0, 5, 0);
		gbc_deleteBtn.gridwidth = 2;
		gbc_deleteBtn.gridx = 0;
		gbc_deleteBtn.gridy = 8;
		panel.add(deleteBtn, gbc_deleteBtn);
		
		deleteValidate = new JLabel("");
		deleteValidate.setForeground(Color.RED);
		GridBagConstraints gbc_deleteValidate = new GridBagConstraints();
		gbc_deleteValidate.gridwidth = 2;
		gbc_deleteValidate.insets = new Insets(0, 0, 0, 5);
		gbc_deleteValidate.gridx = 0;
		gbc_deleteValidate.gridy = 9;
		panel.add(deleteValidate, gbc_deleteValidate);
		
		exitBtn = new JButton("Exit");
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exitBtnActionPerformed(e);
			}
		});
		exitBtn.setBounds(775, 465, 184, 23);
		contentPane.add(exitBtn);
	}
	protected void loadBtnActionPerformed(ActionEvent e) {
	}
	protected void insertBtnActionPerformed(ActionEvent e) {
	}
	protected void updateBtnActionPerformed(ActionEvent e) {
	}
	protected void deleteBtnActionPerformed(ActionEvent e) {
	}
	protected void exitBtnActionPerformed(ActionEvent e) {
	}
	protected void searchTfActionPerformed(ActionEvent e) {
	}
}
