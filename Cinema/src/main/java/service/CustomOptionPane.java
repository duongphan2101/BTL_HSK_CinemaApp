package service;

import javax.swing.JOptionPane;

public class CustomOptionPane {
	
    public static int showCustomerNotFoundDialog(String mess) {
        return JOptionPane.showConfirmDialog(
            null, 
            mess, 
            "Warning", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.WARNING_MESSAGE
        );
    }
    public static int showMess(String mess) {
        return JOptionPane.showConfirmDialog(
            null, 
            mess, 
            "Warning", 
            JOptionPane.CANCEL_OPTION, 
            JOptionPane.WARNING_MESSAGE
        );
    }
    
    public static int showNotice(String mess) {
        return JOptionPane.showConfirmDialog(
            null, 
            mess, 
            "Thông Báo", 
            JOptionPane.CANCEL_OPTION, 
            JOptionPane.QUESTION_MESSAGE
        );
    }
}

