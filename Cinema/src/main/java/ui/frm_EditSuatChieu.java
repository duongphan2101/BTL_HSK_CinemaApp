package ui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import com.raven.datechooser.DateChooser;

import dao.DAO_Movie;
import dao.DAO_ShowTime;
import dao.DAO_Theater;
import enities.Movie;
import enities.Showtime;
import enities.Theater;
import service.CustomOptionPane;
import service.RoundedPanel;
import service.backGround_Color;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JTextField;
public class frm_EditSuatChieu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel head;
	private JPanel body;
	private JComboBox<String> txtTitle;
	private DAO_Movie dao;
	private DAO_Theater daoTheater;
	private JComboBox<String> txtPhongChieu;
	private JLabel lblNewLabel_1_2;
	private JComboBox<String> txtTimeStart;
	private JLabel lblNewLabel_1_3;
	private JTextField txtNgay;
	private DateChooser dateChooser;
	private RoundedPanel btnAdd;
	private JLabel lblNewLabel_2;
	private DAO_ShowTime daoShowTime;
	private static Showtime show;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {


			public void run() {
				try {
					frm_EditSuatChieu frame = new frm_EditSuatChieu(show);
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
	public frm_EditSuatChieu(Showtime show) {
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		setLocationRelativeTo(null);
		head = new JPanel();
		head.setBorder(new EmptyBorder(10, 10, 10, 10));
		head.setLayout(new BorderLayout(0, 0));
		head.setBackground(backGround_Color.bg_color);
		
		JLabel lblNewLabel = new JLabel("Suất Chiếu");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		head.add(lblNewLabel, BorderLayout.CENTER);
		
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(head, BorderLayout.NORTH);
		
		body = new JPanel();
		contentPane.add(body, BorderLayout.CENTER);
		body.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Chọn Phim");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(45, 34, 93, 22);
		body.add(lblNewLabel_1);
		dao = new DAO_Movie();
		String [] lstTitle = dao.getAllTitleMovieDangChieu();
		txtTitle = new JComboBox<>(lstTitle);
		txtTitle.setLocation(45, 66);
		txtTitle.setSize(250, 40);
		body.add(txtTitle);
		
		JLabel lblNewLabel_1_1 = new JLabel("Chọn Phòng Chiếu");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(405, 34, 162, 22);
		body.add(lblNewLabel_1_1);
		daoTheater = new DAO_Theater();
		String [] lstTheater = daoTheater.getAllTheater();
		txtPhongChieu = new JComboBox<String>(lstTheater);
		txtPhongChieu.setBounds(405, 66, 250, 40);
		body.add(txtPhongChieu);
		
		lblNewLabel_1_2 = new JLabel("Chọn Thời Gian Bắt Đầu");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_2.setBounds(405, 177, 228, 22);
		body.add(lblNewLabel_1_2);
		
		String [] timestart = {
				"16:00", "18:00","20:00", "22:00"
		};
		
		txtTimeStart = new JComboBox<String>(timestart);
		txtTimeStart.setBounds(405, 209, 250, 40);
		body.add(txtTimeStart);
		
		lblNewLabel_1_3 = new JLabel("Chọn Ngày");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_3.setBounds(45, 184, 228, 22);
		body.add(lblNewLabel_1_3);
		
		txtNgay = new JTextField();
		txtNgay.setHorizontalAlignment(SwingConstants.CENTER);
		txtNgay.setBounds(45, 215, 200, 30);
		body.add(txtNgay);
		txtNgay.setColumns(10);
		dateChooser = new DateChooser();
		dateChooser.setTextRefernce(txtNgay);
		txtNgay.setBackground(Color.white);
		
		btnAdd = new RoundedPanel(15);
		btnAdd.setLocation(203, 312);
		btnAdd.setSize(250, 60);
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				control_Phim();
				dispose();
			}
		});
		btnAdd.setPreferredSize(new Dimension(200, 60));
		btnAdd.setBackground(new Color(190, 6, 43));
		body.add(btnAdd);
		btnAdd.setLayout(new BorderLayout(0, 0));
		
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(frm_EditSuatChieu.class.getResource("/icon/icons8-pencil-50.png")));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		btnAdd.add(lblNewLabel_2, BorderLayout.CENTER);
		
		txtTitle.setSelectedItem(show.getMovie().getTitle());
		txtNgay.setText(show.getShowDate());
		txtTimeStart.setSelectedItem(show.getStartTime());
		txtPhongChieu.setSelectedItem(show.getTheater().getName());
		
		RoundedPanel btnXoa = new RoundedPanel(15);
		btnXoa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				daoShowTime = new DAO_ShowTime();
				daoShowTime.deleteShowTime(show.getShowtimeId());
				dispose();
			}
		});
		btnXoa.setPreferredSize(new Dimension(200, 60));
		btnXoa.setBackground(new Color(190, 6, 43));
		btnXoa.setBounds(547, 312, 108, 60);
		body.add(btnXoa);
		btnXoa.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(frm_EditSuatChieu.class.getResource("/icon/icons8-close-30.png")));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		btnXoa.add(lblNewLabel_3, BorderLayout.CENTER);
		System.out.println(show.getShowtimeId());
	}
	
	public void control_Phim() {
		dao = new DAO_Movie();
		daoTheater = new DAO_Theater();
		daoShowTime = new DAO_ShowTime();
		try {
			String tenPhim = txtTitle.getSelectedItem().toString();
			int idPhim = dao.getMovieIdByTitle(tenPhim);
			Movie movie = dao.getMoviebyID(idPhim);
			
			String tenPhong = txtPhongChieu.getSelectedItem().toString();
			int idPhong = daoTheater.getTheaterIdByTitle(tenPhong);
			Theater theater = daoTheater.getTheaterbyID(idPhong);
			
			String date = txtNgay.getText();
			String time = txtTimeStart.getSelectedItem().toString();
			Showtime s = new Showtime(1, movie, theater, date, time);
			
			daoShowTime.updateShowTime(s);;
			
		} catch (Exception e) {
			CustomOptionPane.showMess("Lỗi!");
		}
		
	}
}
