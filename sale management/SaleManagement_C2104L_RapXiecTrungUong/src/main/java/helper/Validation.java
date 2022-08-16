package helper;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import entity.Category;
import entity.Staff;

public class Validation {

	public static boolean checkTextQuantity(JComponent input,Integer quantity,Integer oldQuantity,Integer quantitySold) {
		String text = ((JTextField) input).getText().trim();
		
		if(text.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Enter Quantity","Warning",
			        JOptionPane.WARNING_MESSAGE);
			return false;
		}else if(text.matches("[0-9]+")){
				int orderQuantity = Integer.valueOf(text)+oldQuantity+quantitySold;
				if(orderQuantity>quantity) {
					JOptionPane.showMessageDialog(null, "Quantity can not greater-than mark Quantity","Warning",
			        JOptionPane.WARNING_MESSAGE);
					return false;
				}else if(text.matches("[0-9]+") && orderQuantity<quantity) {
					return true;
		}
		}else {
			JOptionPane.showMessageDialog(null, "Enter Quantity By Number","Warning",
			        JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return false;
	}
	
	public static boolean checkTextStaff(JComponent input) {
		String text = ((JTextField) input).getText();
		if(text.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Enter FullName and Password","Warning",
			        JOptionPane.WARNING_MESSAGE);
			return false;
		}else if(text.matches("^(([a-zA-Z0-9]\\s*){3,25}$)")) {
			return true;
		}else {
		JOptionPane.showMessageDialog(null, "FullName and Password 3 Words","Warning",
		        JOptionPane.WARNING_MESSAGE);
		return false;
		}
	}
	
	public static boolean checkElementUserName(JComponent input,List<Staff> list,String currentData) {
		String text = ((JTextField) input).getText().toLowerCase();
		if(text.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Enter User","Warning",
			        JOptionPane.WARNING_MESSAGE);
			return false;
		}else {
			for(Staff staff : list) {
				if(currentData.toLowerCase().equals(text)&& !currentData.equals("")) {
					return true;
				}else if(staff.getName().toLowerCase().equals(text) && !currentData.toLowerCase().equals(text)){
						JOptionPane.showMessageDialog(null, "Your Username is have already","Warning",
						        JOptionPane.WARNING_MESSAGE);
					return false;
				}
		}
			return true;
  }
		}
	public static boolean checkElementCategory(JComponent input,List<Category> list,String currentData) {
		String text = ((JTextField) input).getText().toLowerCase();
		if(text.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Enter Category","Warning",
			        JOptionPane.WARNING_MESSAGE);
		return false;
		}else {
			for(Category cate : list) {
				if(currentData.toLowerCase().equals(text)&& !currentData.equals("")) {
					return true;
				}else if(cate.getName().toLowerCase().equals(text) && !currentData.toLowerCase().equals(text)){
						JOptionPane.showMessageDialog(null, "Your Username is have already","Warning",
						        JOptionPane.WARNING_MESSAGE);
					return false;
				}
		}
			return true;
  }}


	

//  variable anh loc
	public static boolean isEmptyValidate(JTextField textField) {
		boolean isEmpty = true;
		if(textField.getText().trim().length()>0) {
			isEmpty = false;
		}
		return isEmpty;
	}
	
	public static boolean isEmptyValidate(JTextArea ta) {
		boolean isEmpty = true;
		if(ta.getText().trim().length()>0) {
			isEmpty = false;
		}
		return isEmpty;
	}
	
	public static boolean characterValidate(JTextField textField) {
		boolean isCharOnly = false;
		for(int i = textField.getText().length(); i>0; i--) {
			char c = textField.getText().charAt(i-1);
			if(Character.isAlphabetic(c) || c == ' ') {			
				isCharOnly = true;
			}else {
				isCharOnly = false;
				break;
			}
		}
		return isCharOnly;
	}
	
	public static boolean stringLengthValidate(JTextArea ta, JLabel lbl, int length) {
		boolean validate = false;	
		if(ta.getText().length() > length) {
			lbl.setText("*Length must be less than 200 characters");
		}else {
			validate = true;
		}
		return validate;
	}
	
	public static boolean stringLengthValidate(JTextField tf, int length) {
		boolean validate = false;	
		if(!Validation.isEmptyValidate(tf)) {
			if(tf.getText().length() <= length) {
				validate = true;
			}
		}
		return validate;
	}
	
	public static boolean isNumber(JTextField textField) {
		boolean isNumber = false;
		try {
			Integer.parseInt(textField.getText());
			isNumber = true;
		} catch (Exception e) {
			isNumber = false;
		}
		
		return isNumber;
	}
	
	public static boolean isSelectedActived(JTable table) {
		boolean isSelectedActived = false;
		for(int i = 0; i < table.getRowCount(); i++) {
			if((Boolean)table.getValueAt(i, table.getColumnCount()-1)) {
				isSelectedActived = true;
				break;
			}
		}
		return isSelectedActived;
	}
	
	public static int selectedCount(JTable table) {
		int count = 0;
		for(int i = 0; i < table.getRowCount(); i++) {
			if((Boolean)table.getValueAt(i, table.getColumnCount()-1)) {
				count++;
			}
		}
		return count;
	}
	
	public static boolean imageValidate(String sourcePath, String newDirPath, String dbPath, String fileName) {
		boolean validate = false;
		if(sourcePath != null && newDirPath != null && dbPath != null && fileName != null) {
			validate = true;
		}
		return validate;
	}
	
	public static boolean isPositiveNumber(JTextField tf, JLabel lbl) {
		boolean validate = false;
		if(!Validation.isEmptyValidate(tf)) {
			if(Validation.isNumber(tf)) {
				int number = Integer.parseInt(tf.getText());
				if(number < 0 ) {
					lbl.setText("*The number must be positive");
				}else {
					validate = true;
				}
			}else {
				lbl.setText("*Must be a number");
			}
		}else {
			lbl.setText("*Please enter the field!");
		}
		return validate;
	}


	
	
	
}
