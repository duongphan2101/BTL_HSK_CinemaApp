package service;
import javax.swing.JPanel;

public class PanelSwitcher {

    // Phương thức chuyển đổi giữa các panel
    public static void switchPanel(JPanel form, JPanel deskPane, JPanel newPanel) {
        form.remove(deskPane);  
        deskPane = newPanel;     
        form.add(deskPane);      
        form.revalidate();       
        form.repaint();          
    }
}

