package ui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.DAO_Account;
import enities.Account;
import enities.CurrentEmp;
import service.CustomOptionPane;
import service.RoundedPanel;
import service.backGround_Color;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class frm_DoiMatKhau extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtOld;
	private JTextField txtNew;
	private JTextField txtRe;
	private RoundedPanel btn;
	private DAO_Account dao;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frm_DoiMatKhau frame = new frm_DoiMatKhau();
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
	public frm_DoiMatKhau() {
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 500, 350);
		contentPane = new JPanel();
		setResizable(false);
		contentPane.setBackground(new Color(255, 255, 255));
		setLocationRelativeTo(null);
		contentPane.setBorder(new EmptyBorder(10,0,10,0));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel head = new JPanel();
		contentPane.add(head, BorderLayout.NORTH);
		head.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("ĐỔI MẬT KHẨU");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		head.add(lblNewLabel, BorderLayout.NORTH);
		head.setBackground(backGround_Color.bg_color_deep);
		
		JPanel body = new JPanel();
		body.setBackground(new Color(255, 255, 255));
		contentPane.add(body, BorderLayout.CENTER);
		body.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Mật Khẩu Cũ:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(13, 23, 108, 20);
		body.add(lblNewLabel_1);
		
		txtOld = new JTextField();
		txtOld.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtOld.setBounds(171, 23, 300, 25);
		body.add(txtOld);
		txtOld.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Mật Khẩu Mới:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(13, 75, 108, 20);
		body.add(lblNewLabel_1_1);
		
		txtNew = new JTextField();
		txtNew.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtNew.setColumns(10);
		txtNew.setBounds(171, 75, 300, 25);
		body.add(txtNew);
		
		JLabel lblNewLabel_1_2 = new JLabel("Nhập Lại Mật Khẩu:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_2.setBounds(13, 132, 151, 20);
		body.add(lblNewLabel_1_2);
		
		txtRe = new JTextField();
		txtRe.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtRe.setColumns(10);
		txtRe.setBounds(174, 132, 300, 25);
		body.add(txtRe);
		
		btn = new RoundedPanel(15);
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dao = new DAO_Account();
				String old = txtOld.getText().trim();
				String newP = txtNew.getText().trim();
				String re = txtRe.getText().trim();
				
				if(newP.equals(re)) {
					Account a = dao.getAccountbyEmployeeId(CurrentEmp.getEmployeeId());
					if(old.equals(a.getPassword())) {
						dao.updatePassword(newP, a.getAccount_id());
						dispose();
					}else {
						CustomOptionPane.showNotice("Mật Khẩu cũ không đúng!");
					}
				}else {
					CustomOptionPane.showNotice("Mật Khẩu nhập lại không khớp!");
				}
			}
		});
		btn.setBounds(13, 199, 461, 40);
		body.add(btn);
		btn.setLayout(new BorderLayout(0, 0));
		btn.setBackground(backGround_Color.bg_color_deep);
		
		JLabel lblNewLabel_2 = new JLabel("Đổi Mật Khẩu");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		btn.add(lblNewLabel_2, BorderLayout.CENTER);
		
		
	}
}
