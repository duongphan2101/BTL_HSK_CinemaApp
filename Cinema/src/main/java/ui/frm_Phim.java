package ui;

import java.awt.BorderLayout;
//import java.awt.Component;
import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
//import javax.swing.event.ChangeEvent;
//import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeListener;

import service.Tag_Link;

public class frm_Phim extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTabbedPane tabbedPane;
	private JPanel PhimDangChieu;
	private JPanel All_Phim;

	/**
	 * Create the panel.
	 */
	public frm_Phim() {
		setLayout(new BorderLayout());
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane, BorderLayout.CENTER);
		PhimDangChieu = new PhimDangChieu();
		tabbedPane.addTab("Phim Đang Chiếu", null, PhimDangChieu, null);
		
		All_Phim = new AllPhim();
		tabbedPane.addTab("Tất Cả Phim", null, All_Phim, null);
		
		tabbedPane.addChangeListener(new ChangeListener() {
		    @Override
		    public void stateChanged(ChangeEvent e) {
		        Component selectedComponent = tabbedPane.getSelectedComponent();
		        
		        if (selectedComponent == PhimDangChieu) {
		            Tag_Link.setTag_link("Quản Lý/Phim Đang Chiếu");
		        } else if (selectedComponent == All_Phim) {
		            Tag_Link.setTag_link("Quản Lý/Tất Cả Phim");
		        }
		    }
		});

	}

}
