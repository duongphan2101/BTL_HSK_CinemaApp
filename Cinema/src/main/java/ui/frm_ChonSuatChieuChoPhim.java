package ui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import java.util.Date;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import dao.DAO_ShowTime;
import enities.Movie;
import enities.Showtime;
import service.RoundedPanel;
import service.ScrollPaneWin11;
import service.backGround_Color;
import service.getDate;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class frm_ChonSuatChieuChoPhim extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblName;
	private JPanel body;
	private JScrollPane scrollPane;
	private JPanel list_Pane;
	private JLabel lblPhong;

	/**
	 * Create the panel.
	 */
	public frm_ChonSuatChieuChoPhim(main_nv app, Movie m) {
		setBackground(new Color(255, 255, 255));
		setLayout(new BorderLayout(0, 0));
		
		body = new JPanel();
		body.setBorder(new EmptyBorder(50, 50, 50, 50));
		body.setBackground(new Color(255, 255, 255));
		add(body, BorderLayout.CENTER);
		
		JPanel head = new JPanel();
		head.setBorder(new EmptyBorder(0, 10, 0, 0));
		add(head, BorderLayout.NORTH);
		head.setLayout(new BorderLayout(0, 0));
		head.setBackground(backGround_Color.bg_color_deep);
		
		JLabel lbl_prev = new JLabel("");
		lbl_prev.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				app.switchPanel(new PhimDangChieu_NhanVien(app));
			}
		});
		lbl_prev.setIcon(new ImageIcon(frm_ChonSuatChieuChoPhim.class.getResource("/icon/icons8-left-arrow-50.png")));
		head.add(lbl_prev, BorderLayout.WEST);
		
		DAO_ShowTime dao = new DAO_ShowTime();
		Date today = new Date();
		List<Showtime> lst = dao.getAllShowTimebyMovieandDate(m.getMovieId(), getDate.toDateTime(today));
	
		body.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new ScrollPaneWin11();
		body.add(scrollPane);
		scrollPane.setBorder(null);
		list_Pane = new JPanel();
		list_Pane.setBackground(new Color(255, 255, 255));
		scrollPane.setViewportView(list_Pane);
		list_Pane.setLayout(new GridLayout(0, 2, 50, 50));
		draw(lst);
	}
	public void draw(List<Showtime> lst) {
		for(Showtime showtime : lst) {
			Font myFont = new Font("Arial", Font.BOLD, 24);
			JPanel item = new RoundedPanel(15);
			item.setPreferredSize(new Dimension(180, 200));
			item.setBorder(new EmptyBorder(10, 20, 10, 20));
			item.setLayout(new BorderLayout());
			item.add(lblName = new JLabel(showtime.getStartTime()), BorderLayout.CENTER);
			lblName.setHorizontalAlignment(SwingConstants.CENTER);
			
			item.add(lblPhong = new JLabel(showtime.getTheater().getName()), BorderLayout.NORTH);
			lblPhong.setHorizontalAlignment(SwingConstants.CENTER);
			
			lblName.setFont(myFont);
			lblPhong.setFont(myFont);
			list_Pane.add(item);
			
			item.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					frm_ChonPhongChieu frm = new frm_ChonPhongChieu(showtime);
					frm.setVisible(true);
				}
			});
		}
	}

}
