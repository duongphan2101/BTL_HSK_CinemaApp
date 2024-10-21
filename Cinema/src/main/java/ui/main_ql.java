package ui;

import java.awt.EventQueue;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import javax.swing.Timer;

import service.backGround_Color;
import service.changeColor;
import service.clock;
import service.PopupMenu;
import service.Tag_Link;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.EmptyBorder;

import dao.DAO_Employee;
import dao.DAO_ShowTime;
import enities.CurrentEmp;


public class main_ql extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel Menu;
	private JLabel logo;
	private JPanel btnPhim;
	private JPanel btnLogo;
	private JLabel lblNewLabel;
	private JPanel btnSuatChieu;
	private JLabel lblNewLabel_1;
	private JPanel btnNhanSu;
	private JPanel btnThongKe;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JPanel MenuList;
	private JLabel lbl_clock;
	private Timer timer;
	private JPanel head;
	private JPanel link;
	private JPanel white_space;
	private JPanel Right;
	private JLabel lblLink;
	private JPanel content;
	private JPanel frm_Phim;
	private JLabel lblLogo;
	private DAO_Employee daoEmp = new DAO_Employee();
	private CurrentEmp emp;
	private PopupMenu popupMenu;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main_ql frame = new main_ql();
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
	public main_ql() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 700);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		setMinimumSize(new Dimension(700, 500));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		contentPane.setBackground(backGround_Color.bg_color);
		head = new JPanel();
		head.setPreferredSize(new Dimension(1200 - 250, 100));
		head.setLayout(new BorderLayout(0, 0));

		link = new JPanel();
		link.setBorder(null);
		head.add(link, BorderLayout.CENTER);
		link.setPreferredSize(new Dimension(1200 - 250, 80));
		link.setBackground(backGround_Color.bg_color_deep);
		link.setLayout(new BorderLayout(0, 0));

		lblLink = new JLabel("Quản Lý/Phim Đang Chiếu");
		lblLink.setForeground(new Color(255, 255, 255));
		lblLink.setFont(new Font("Tahoma", Font.PLAIN, 18));
		link.add(lblLink);
		lblLink.setBorder(new EmptyBorder(0, 30, 0, 0));

		white_space = new JPanel();
		white_space.setBackground(new Color(255, 255, 255));
		head.add(white_space, BorderLayout.NORTH);
		white_space.setPreferredSize(new Dimension(1200 - 250, 20));

		Right = new JPanel();
		Menu = new JPanel();

		contentPane.add(Menu);
		contentPane.add(Right);

		Right.setLayout(new BorderLayout());
		Right.add(head, BorderLayout.NORTH);

		content = new JPanel();
		Right.add(content, BorderLayout.CENTER);
		content.setLayout(new BorderLayout(0, 0));


		Menu.setPreferredSize(new Dimension(250, getHeight()));
		Menu.setBackground(backGround_Color.bg_color);
		Menu.setLayout(new BorderLayout());
		Menu.setMaximumSize(new Dimension(250, this.getHeight()));

		btnLogo = new JPanel();
		btnLogo.setPreferredSize(new Dimension(getWidth(), 100));
		btnLogo.setMaximumSize(btnLogo.getPreferredSize());
		btnLogo.setBackground(backGround_Color.bg_color);
		btnLogo.setLayout(new BorderLayout(0, 0));

		logo = new JLabel("");
		logo.setPreferredSize(new Dimension(2500, 100));
		logo.setIcon(new ImageIcon(main_ql.class.getResource("/icon/user-50.png")));
		logo.setHorizontalAlignment(SwingConstants.CENTER);
		
		frm_Phim = new frm_Phim();
		content.add(frm_Phim, BorderLayout.CENTER);


		btnPhim = new JPanel();
		btnPhim.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				switchPanel(new frm_Phim());
				lblLink.setText(Tag_Link.getTag_link());
				changeColor.changCorlor(btnNhanSu, backGround_Color.bg_color);
				changeColor.changCorlor(btnPhim, backGround_Color.bg_color_deep);
				changeColor.changCorlor(btnSuatChieu, backGround_Color.bg_color);
				changeColor.changCorlor(btnThongKe, backGround_Color.bg_color);
			}
		});
		btnPhim.setPreferredSize(new Dimension(getWidth(), 100));
		btnPhim.setMaximumSize(btnPhim.getPreferredSize());
		btnPhim.setBackground(backGround_Color.bg_color_deep);

		btnPhim.setLayout(new BorderLayout(0, 0));

		lblNewLabel = new JLabel("Phim");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setIcon(new ImageIcon(main_ql.class.getResource("/icon/icons8-film-50.png")));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

		btnPhim.add(lblNewLabel, BorderLayout.CENTER);

		btnSuatChieu = new JPanel();
		btnSuatChieu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblLink.setText("Quản Lý/Suất Chiếu");
				switchPanel(new frm_SuatChieu(true));
				changeColor.changCorlor(btnNhanSu, backGround_Color.bg_color);
				changeColor.changCorlor(btnPhim, backGround_Color.bg_color);
				changeColor.changCorlor(btnSuatChieu, backGround_Color.bg_color_deep);
				changeColor.changCorlor(btnThongKe, backGround_Color.bg_color);
			}
		});
		btnSuatChieu.setPreferredSize(new Dimension(1200, 100));
		btnSuatChieu.setMaximumSize(new Dimension(1200, 100));
		btnSuatChieu.setBackground(backGround_Color.bg_color);

		btnSuatChieu.setLayout(new BorderLayout(0, 0));

		lblNewLabel_1 = new JLabel("Suất Chiếu");
		lblNewLabel_1.setIcon(new ImageIcon(main_ql.class.getResource("/icon/icons8-list-50.png")));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		btnSuatChieu.add(lblNewLabel_1, BorderLayout.CENTER);

		btnNhanSu = new JPanel();
		btnNhanSu.setPreferredSize(new Dimension(1200, 100));
		btnNhanSu.setMaximumSize(new Dimension(1200, 100));
		btnNhanSu.setBackground(backGround_Color.bg_color);
		btnNhanSu.setLayout(new BorderLayout(0, 0));

		lblNewLabel_2 = new JLabel("Nhân Sự");
		lblNewLabel_2.setIcon(new ImageIcon(main_ql.class.getResource("/icon/people50.png")));
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		btnNhanSu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmEmployee frmAddEmployee = new frmEmployee();
				switchPanel(frmAddEmployee);
				lblLink.setText("Quản Lý/Nhân Sự/Nhân Viên");

				changeColor.changCorlor(btnNhanSu, backGround_Color.bg_color_deep);
				changeColor.changCorlor(btnPhim, backGround_Color.bg_color);
				changeColor.changCorlor(btnSuatChieu, backGround_Color.bg_color);
				changeColor.changCorlor(btnThongKe, backGround_Color.bg_color);

			}
		});
		btnNhanSu.add(lblNewLabel_2, BorderLayout.CENTER);

		btnThongKe = new JPanel();
		btnThongKe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				switchPanel(new frm_ThongKe());
				lblLink.setText("Quản Lý/Thống Kê");
				changeColor.changCorlor(btnNhanSu, backGround_Color.bg_color);
				changeColor.changCorlor(btnPhim, backGround_Color.bg_color);
				changeColor.changCorlor(btnSuatChieu, backGround_Color.bg_color);
				changeColor.changCorlor(btnThongKe, backGround_Color.bg_color_deep);
			}
		});
		btnThongKe.setPreferredSize(new Dimension(1200, 100));
		btnThongKe.setMaximumSize(new Dimension(1200, 100));
		btnThongKe.setBackground(backGround_Color.bg_color);

		btnThongKe.setLayout(new BorderLayout(0, 0));

		lblNewLabel_3 = new JLabel("Thống Kê");
		lblNewLabel_3.setIcon(new ImageIcon(main_ql.class.getResource("/icon/icons8-chart-50.png")));
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		btnThongKe.add(lblNewLabel_3, BorderLayout.CENTER);

		Menu.add(btnLogo, BorderLayout.NORTH);
		popupMenu = new PopupMenu(this);
		lblLogo = new JLabel("");
		lblLogo.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
                    popupMenu.showPopup(lblLogo, e.getX(), e.getY(), emp);
                }
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
                    popupMenu.showPopup(lblLogo, e.getX(), e.getY(), emp);
                }
			}
		});
		lblLogo.setIcon(new ImageIcon(main_ql.class.getResource("/icon/people50.png")));
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		btnLogo.add(lblLogo, BorderLayout.CENTER);
		MenuList = new JPanel();
		MenuList.setBackground(Menu.getBackground());
		MenuList.setLayout(new BoxLayout(MenuList, BoxLayout.Y_AXIS));
		MenuList.add(btnPhim);
		MenuList.add(btnSuatChieu);
		MenuList.add(btnNhanSu);
		MenuList.add(btnThongKe);
		Menu.add(MenuList, BorderLayout.CENTER);

		lbl_clock = new JLabel("New label");
		lbl_clock.setForeground(new Color(255, 255, 255));
		lbl_clock.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbl_clock.setHorizontalAlignment(SwingConstants.CENTER);
		Menu.add(lbl_clock, BorderLayout.SOUTH);

		timer = new Timer(0, this);
        timer.start();
        
        daoEmp = new DAO_Employee();
        emp = daoEmp.getEmployeeByID(CurrentEmp.getEmployeeId());
        
        DAO_ShowTime dao = new DAO_ShowTime();
        dao.startShowtimeChecker();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		lbl_clock.setText(clock.updateClock());
	}
	
    private void switchPanel(JPanel panel) {
        content.remove(frm_Phim);
        frm_Phim = panel;
        content.add(frm_Phim, BorderLayout.CENTER);
        content.revalidate();
        content.repaint();
    }



}
