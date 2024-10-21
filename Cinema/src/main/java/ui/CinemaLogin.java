package ui;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

import dao.DAO_Account;
import enities.Account;
import enities.CurrentEmp;
import service.CustomOptionPane;
import service.RoundedPanel;
import service.backGround_Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CinemaLogin extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtUser;
	private JPasswordField txtPass;
	private RoundedPanel btnLogin;
	private String user, pwd;
	private char [] pass;
	private DAO_Account dao;
	
	public CinemaLogin() {
        setTitle("Cinema Star - Login");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());

        JPanel leftPanel = new JPanel();
        leftPanel.setBorder(new EmptyBorder(160, 0, 0, 0));
        leftPanel.setPreferredSize(new Dimension(700, 700));
        leftPanel.setBackground(new Color(211, 47, 47));
        
        JLabel cinemaTitle = new JLabel("CINEMA STAR");
        cinemaTitle.setFont(new Font("Arial", Font.BOLD, 40));
        cinemaTitle.setForeground(Color.WHITE);

        JPanel rightPanel = new JPanel();
        rightPanel.setBorder(new EmptyBorder(80, 20, 20, 20));
        rightPanel.setPreferredSize(new Dimension(500, 700));
        rightPanel.setBackground(Color.WHITE);

        getContentPane().add(leftPanel, BorderLayout.WEST);
        leftPanel.setLayout(new BorderLayout(0, 0));
        
        JLabel lblNewLabel = new JLabel("CINEMA STAR");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 64));
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        leftPanel.add(lblNewLabel, BorderLayout.NORTH);
        
        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon(CinemaLogin.class.getResource("/icon/icons8-star-300.png")));
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        leftPanel.add(lblNewLabel_1, BorderLayout.CENTER);
        getContentPane().add(rightPanel, BorderLayout.CENTER);
        rightPanel.setLayout(new BorderLayout(0, 0));
        
        JLabel lblNewLabel_2 = new JLabel("Login");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 54));
        rightPanel.add(lblNewLabel_2, BorderLayout.NORTH);
        lblNewLabel_2.setForeground(backGround_Color.bg_color);
        
        JPanel b = new JPanel();
        b.setBackground(new Color(255, 255, 255));
        b.setBorder(new EmptyBorder(50, 0, 0, 0));
        rightPanel.add(b, BorderLayout.CENTER);
        b.setLayout(new BoxLayout(b, BoxLayout.Y_AXIS));
        
        Box b1 = Box.createVerticalBox();
        Box b2 = Box.createVerticalBox();
        Box b3 = Box.createVerticalBox();
        
        b1.setPreferredSize(new Dimension(300, 100));
        b1.setMaximumSize(new Dimension(300, 100));
        b1.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        txtUser = new JTextField();
        txtUser.setPreferredSize(new Dimension(600, 30));
        txtUser.setMaximumSize(txtUser.getPreferredSize());
        b1.add(new JLabel("Tên Đăng Nhập"));
        b1.add(txtUser);
        
        b2.setPreferredSize(new Dimension(300, 100));
        b2.setMaximumSize(new Dimension(300, 100));
        b2.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        txtPass = new JPasswordField();
        txtPass.setPreferredSize(new Dimension(600, 30));
        txtPass.setMaximumSize(txtUser.getPreferredSize());
        b2.add(new JLabel("Mật Khẩu"));
        b2.add(txtPass);
        
        b3.setPreferredSize(new Dimension(320, 50));
        b3.setMaximumSize(new Dimension(320, 50));
        b3.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLogin = new RoundedPanel(20);
        btnLogin.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		user = txtUser.getText().trim();
        		pass = txtPass.getPassword();
        		pwd = new String(pass);
        		Checkin(user, pwd);
        	}
        });
        btnLogin.setBackground(backGround_Color.bg_color);
        b3.add(btnLogin);
        btnLogin.setLayout(new BorderLayout(0, 0));
        
        JLabel lblNewLabel_3 = new JLabel("Đăng Nhập");
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_3.setForeground(new Color(255, 255, 255));
        lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 32));
        btnLogin.add(lblNewLabel_3, BorderLayout.CENTER);

        b.add(Box.createHorizontalGlue());
        b.add(b1);
        b.add(b2);
        b.add(b3);
        b.add(Box.createHorizontalGlue());
        
        JLabel lblNewLabel_4 = new JLabel("Nhóm 11 - Quản Lý Bán Vé Rạp Phim");
        lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 24));
        rightPanel.add(lblNewLabel_4, BorderLayout.SOUTH);
        lblNewLabel_4.setForeground(backGround_Color.bg_color);
	}
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CinemaLogin loginForm = new CinemaLogin();
            loginForm.setVisible(true);
        });
    }
    
    public void Checkin(String user, String pwd) {
    	dao = new DAO_Account();
    	Account a = dao.getAccount(user, pwd);
    	if(a!=null) {	
    		if(CurrentEmp.getEmployeeType().getTypeId()==1) {
    			CustomOptionPane.showNotice("Đăng Nhập Thành Công!");
    			main_ql frm = new main_ql();
    			frm.setVisible(true);  			
    			dispose();
    		}else {
    			CustomOptionPane.showNotice("Đăng Nhập Thành Công!");
    			main_nv frm = new main_nv();
    			frm.setVisible(true);    			
    			dispose();
    		}
    	}else {
    		CustomOptionPane.showMess("Đăng Nhập Không Thành Công!");
    	}
    }
}

