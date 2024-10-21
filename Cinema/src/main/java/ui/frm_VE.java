package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import service.backGround_Color;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.text.DecimalFormat;

import javax.swing.border.EmptyBorder;

import dao.DAO_Ticktet_Invoice;
import enities.Ticket;

public class frm_VE extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel head;
	private JLabel lblPhong;
	private JLabel lblNgay;
	private JLabel lblGhe;
	private JLabel lblDonGia;
	private JLabel lblPhim;
	DecimalFormat df = new DecimalFormat("###,###,### VND");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frm_VE frame = new frm_VE();
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
	public frm_VE() {
		setType(Type.UTILITY);
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 500, 600);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		head = new JPanel();
		head.setBounds(0, 36, 486, 150);
		contentPane.add(head);
		head.setBackground(backGround_Color.bg_color);
		head.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("CINEMA TICKET");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		head.add(lblNewLabel, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(10, 20, 20, 20));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 184, 486, 379);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblPhong = new JLabel("Phòng Chiếu:");
		lblPhong.setBounds(10, 73, 236, 25);
		lblPhong.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(lblPhong);
		
		lblNgay = new JLabel("Ngày Lập :");
		lblNgay.setBounds(10, 10, 466, 25);
		lblNgay.setHorizontalAlignment(SwingConstants.CENTER);
		lblNgay.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(lblNgay);
		
		lblGhe = new JLabel("Phòng Chiếu:");
		lblGhe.setHorizontalAlignment(SwingConstants.TRAILING);
		lblGhe.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblGhe.setBounds(240, 73, 236, 25);
		panel.add(lblGhe);
		
		lblDonGia = new JLabel("Đơn Giá:");
		lblDonGia.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDonGia.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDonGia.setBounds(240, 227, 236, 25);
		panel.add(lblDonGia);
		
		lblPhim = new JLabel("Phim:");
		lblPhim.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPhim.setBounds(10, 158, 236, 25);
		panel.add(lblPhim);
		
		JLabel lblCinemaStarXin = new JLabel("CINEMA STAR XIN CÁM ƠN!");
		lblCinemaStarXin.setHorizontalAlignment(SwingConstants.CENTER);
		lblCinemaStarXin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCinemaStarXin.setBounds(10, 344, 466, 25);
		panel.add(lblCinemaStarXin);
	}
	
	public void setVe(int maVe) {
		DAO_Ticktet_Invoice dao = new DAO_Ticktet_Invoice();
		Ticket ticket = dao.getTicketbyID(maVe);
		
		lblNgay.setText("Ngày Lập: " +ticket.getPurchaseTime());
		lblPhong.setText("Phòng Chiếu: "+ticket.getTheater().getName());
		lblGhe.setText("Số Ghế: "+ticket.getSeatNumber());
		lblPhim.setText("Phim: "+ticket.getShowtime().getMovie().getTitle());
		lblDonGia.setText("Đơn Giá: "+df.format(ticket.getPrice()));
	}
}
