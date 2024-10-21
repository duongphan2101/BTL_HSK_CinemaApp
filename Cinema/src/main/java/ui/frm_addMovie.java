package ui;

import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.EmptyBorder;

import service.CustomOptionPane;
import service.RoundedPanel;
import service.backGround_Color;
import service.control_Image;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.SwingConstants;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.JFrame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import com.raven.datechooser.*;

import dao.DAO_Movie;
import enities.Movie;
import enities.MovieStatus;

public class frm_addMovie extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel head;
	private JPanel body;
	private JPanel pn_img;
	private JPanel content;
	private JPanel border_img;
	private JPanel btnChonAnh, btnAdd;
	private JLabel lblNewLabel_2;
	private JLabel lblImage;
	private String linkImg, linkIMG;
	private JComboBox<String> txtGenre;
	private JTextField txtTenPhim;
	private JTextField txtDaoDien;
	private JTextField txtNgay;
	private JTextField txtThoiLuong;
	private JButton btnDay;
	private JLabel lblNewLabel_3;
	private DateChooser dateChooser;

	/**
	 * Create the panel.
	 */
	public frm_addMovie() {
		setSize(700, 500);
		setLocationRelativeTo(null);
		getContentPane().setBackground(new Color(255, 255, 255));
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		head = new JPanel();
		head.setBorder(new EmptyBorder(10, 10, 10, 10));
		getContentPane().add(head, BorderLayout.NORTH);
		head.setLayout(new BorderLayout(0, 0));
		head.setBackground(backGround_Color.bg_color);

		JLabel lblNewLabel = new JLabel("Thêm Phim");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		head.add(lblNewLabel, BorderLayout.CENTER);

		body = new JPanel();
		body.setBorder(new EmptyBorder(0, 30, 0, 30));
		getContentPane().add(body, BorderLayout.CENTER);
		body.setLayout(new BorderLayout(0, 0));

		pn_img = new JPanel();
		body.add(pn_img, BorderLayout.EAST);
		pn_img.setLayout(new BoxLayout(pn_img, BoxLayout.Y_AXIS));

		border_img = new JPanel();
		border_img.setBorder(new LineBorder(new Color(0, 0, 0)));
		border_img.setLayout(new BorderLayout());
		border_img.setPreferredSize(new Dimension(180, 200));
		border_img.setMaximumSize(new Dimension(180, 200));
		border_img.setMinimumSize(new Dimension(180, 200));
		pn_img.add(Box.createVerticalStrut(100));
		pn_img.add(border_img);

		lblImage = new JLabel();
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		border_img.add(lblImage, BorderLayout.CENTER);
		pn_img.add(Box.createVerticalStrut(20));
		btnChonAnh = new RoundedPanel(15);
		btnChonAnh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				control_Image i = new control_Image();
				linkImg = i.copyImages();
				frm_addMovie frame = new frm_addMovie();
				frame.revalidate();
				frame.repaint();
				setImg(linkImg);
				linkIMG = linkImg;
				linkImg = null;
			}
		});
		btnChonAnh.setBorder(new EmptyBorder(10, 10, 10, 10));
		pn_img.add(btnChonAnh);

		btnChonAnh.setLayout(new BorderLayout(0, 0));
		btnChonAnh.setBackground(backGround_Color.bg_color_deep);
		btnChonAnh.setPreferredSize(new Dimension(100, 40));
		btnChonAnh.setMaximumSize(new Dimension(80, 40));
		btnChonAnh.setMinimumSize(new Dimension(80, 40));
		lblNewLabel_2 = new JLabel("Chọn Ảnh");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		btnChonAnh.add(lblNewLabel_2, BorderLayout.CENTER);

		content = new JPanel();
		body.add(content, BorderLayout.CENTER);

		String[] movieGenres = { "Khoa học viễn tưởng", "Hành động", "Lãng mạn", "Kinh dị", "Tâm lý", "Tội phạm" };

		btnAdd = new RoundedPanel(15);
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnAdd.setBounds(106, 349, 250, 40);
		btnAdd.setBackground(backGround_Color.bg_color_deep);
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				insertPhim(linkIMG);
				dispose();
			}
		});
		content.setLayout(null);
		content.add(btnAdd);
		btnAdd.setLayout(new BorderLayout(0, 0));

		lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setIcon(new ImageIcon(frm_addMovie.class.getResource("/icon/icons8-plus-30.png")));
		btnAdd.add(lblNewLabel_3, BorderLayout.CENTER);

		JLabel lblNewLabel_1 = new JLabel("Tên Phim");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(22, 93, 142, 20);
		content.add(lblNewLabel_1);

		txtTenPhim = new JTextField();
		txtTenPhim.setBounds(22, 123, 200, 30);
		content.add(txtTenPhim);
		txtTenPhim.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("Đạo Diễn");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(232, 93, 142, 20);
		content.add(lblNewLabel_1_1);

		txtDaoDien = new JTextField();
		txtDaoDien.setColumns(10);
		txtDaoDien.setBounds(232, 123, 200, 30);
		content.add(txtDaoDien);

		JLabel lblNewLabel_1_2 = new JLabel("Thời Lượng");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_2.setBounds(22, 183, 142, 20);
		content.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_1_1 = new JLabel("Ngày Phát Hành");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1_1.setBounds(232, 183, 142, 20);
		content.add(lblNewLabel_1_1_1);

		txtNgay = new JTextField();
		txtNgay.setColumns(10);
		txtNgay.setBounds(232, 213, 169, 30);
		content.add(txtNgay);

		dateChooser = new DateChooser();
		dateChooser.setTextRefernce(txtNgay);
		txtThoiLuong = new JTextField();
		txtThoiLuong.setColumns(10);
		txtThoiLuong.setBounds(22, 213, 200, 30);
		content.add(txtThoiLuong);

		txtGenre = new JComboBox<>(movieGenres);
		txtGenre.setBounds(22, 283, 410, 30);
		content.add(txtGenre);

		btnDay = new JButton("...");
		btnDay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dateChooser.showPopup();
			}
		});
		btnDay.setBounds(402, 212, 30, 30);
		content.add(btnDay);

	}

	private void setImg(String link) {
		try {
			String filePath = "src/main/java/imgs/" + link;
			lblImage.setIcon(new ImageIcon(filePath));
		} catch (Exception e) {
			e.printStackTrace();
			dispose();
		}
	}

	public void insertPhim(String anh) {
		try {
			String ten = txtTenPhim.getText();
			String daoDien = txtDaoDien.getText();
			int thoiLuong = Integer.parseInt(txtThoiLuong.getText());
			String loai = txtGenre.getSelectedItem().toString();
			String ngay = txtNgay.getText();
			DAO_Movie dao = new DAO_Movie();
			Movie e = new Movie(0, ten, loai, thoiLuong, ngay, daoDien, new MovieStatus(2), anh);
			dao.addMovie(e);
			CustomOptionPane.showNotice("Thêm Thành Công");
		} catch (Exception e) {
			CustomOptionPane.showMess("Sai định dạng!");
		}

	}
}
