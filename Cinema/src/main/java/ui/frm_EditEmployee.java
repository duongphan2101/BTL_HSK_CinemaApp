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

import dao.DAO_Employee;
import enities.Employee;
import enities.EmployeeType;
import service.RoundedPanel;
import service.backGround_Color;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JTextField;

public class frm_EditEmployee extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel head;
	private JPanel body;
	private JLabel lblNewLabel_1_2;
	private JComboBox<String> txtChucVu;
	private JLabel lblNewLabel_1_3;
	private JTextField txtNgay;
	private DateChooser dateChooser;
	private RoundedPanel btnAdd;
	private JLabel lblNewLabel_2;
	private static Employee emp;
	private JTextField txtTen;
	private JTextField txtSDT;
	private JLabel lblNewLabel_1_4;
	private JTextField txtEmail;
	private DAO_Employee dao;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {


			public void run() {
				try {
					frm_EditEmployee frame = new frm_EditEmployee(emp);
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
	public frm_EditEmployee(Employee emp) {
		frm_EditEmployee.emp = emp;
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		setLocationRelativeTo(null);
		head = new JPanel();
		head.setBorder(new EmptyBorder(10, 10, 10, 10));
		head.setLayout(new BorderLayout(0, 0));
		head.setBackground(backGround_Color.bg_color);
		
		JLabel lblNewLabel = new JLabel("Nhân Viên");
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
		
		JLabel lblNewLabel_1 = new JLabel("Tên Nhân Viên");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(45, 34, 139, 22);
		body.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Số Điện Thoại");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(45, 127, 162, 22);
		body.add(lblNewLabel_1_1);

		
		lblNewLabel_1_2 = new JLabel("Chức vụ");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_2.setBounds(427, 127, 228, 22);
		body.add(lblNewLabel_1_2);
		
		String [] chucVu = {
			"Quản lý", "Nhân viên bán vé", "Kỹ thuật viên"
		};
		
		txtChucVu = new JComboBox<String>(chucVu);
		txtChucVu.setBounds(427, 159, 200, 30);
		body.add(txtChucVu);
		
		lblNewLabel_1_3 = new JLabel("Ngày Sinh");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_3.setBounds(427, 34, 228, 22);
		body.add(lblNewLabel_1_3);
		
		txtNgay = new JTextField();
		txtNgay.setHorizontalAlignment(SwingConstants.CENTER);
		txtNgay.setBounds(427, 65, 200, 30);
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
		lblNewLabel_2.setIcon(new ImageIcon(frm_EditEmployee.class.getResource("/icon/icons8-pencil-50.png")));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		btnAdd.add(lblNewLabel_2, BorderLayout.CENTER);
		
		RoundedPanel btnXoa = new RoundedPanel(15);
		btnXoa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dao = new DAO_Employee();
				dao.deleteAccount(emp.getEmployeeId());;
				dao.deleteEmployee(emp.getEmployeeId());
				dispose();
			}
		});
		btnXoa.setPreferredSize(new Dimension(200, 60));
		btnXoa.setBackground(new Color(190, 6, 43));
		btnXoa.setBounds(547, 312, 108, 60);
		body.add(btnXoa);
		btnXoa.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(frm_EditEmployee.class.getResource("/icon/icons8-close-30.png")));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		btnXoa.add(lblNewLabel_3, BorderLayout.CENTER);
		
		txtTen = new JTextField();
		txtTen.setBounds(45, 65, 200, 30);
		body.add(txtTen);
		txtTen.setColumns(10);
		
		txtSDT = new JTextField();
		txtSDT.setColumns(10);
		txtSDT.setBounds(45, 159, 200, 30);
		body.add(txtSDT);
		
		lblNewLabel_1_4 = new JLabel("Email");
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_4.setBounds(45, 219, 162, 22);
		body.add(lblNewLabel_1_4);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(45, 251, 500, 30);
		body.add(txtEmail);
		
		txtTen.setText(emp.getName());
		txtNgay.setText(emp.getBirthDate());
		txtEmail.setText(emp.getEmail());
		if(emp.getEmployeeType().getTypeId() == 1) {
			txtChucVu.setSelectedItem(chucVu[0]);
		}else if(emp.getEmployeeType().getTypeId() == 2) {
			txtChucVu.setSelectedItem(chucVu[1]);
		}else{
			txtChucVu.setSelectedItem(chucVu[2]);
		}
		txtSDT.setText(emp.getPhone());
	}
	
	public void control_Phim() {
		String name = txtTen.getText().trim();
        String birthDate = txtNgay.getText().trim();
        String phone = txtSDT.getText().trim();
        String email = txtEmail.getText().trim();
        int employeeTypeId = 0;
        if(txtChucVu.getSelectedItem().equals("Quản lý")) {
        	employeeTypeId = 1;
        }else if(txtChucVu.getSelectedItem().equals("Nhân viên bán vé")) {
        	employeeTypeId = 2;
        }else {
        	employeeTypeId = 3;
        }
      
        EmployeeType employeeType = new EmployeeType(employeeTypeId);
        Employee employee = new Employee(emp.getEmployeeId(), name, employeeType, birthDate, phone, email);
        System.out.println(emp.getEmployeeId());
        dao = new DAO_Employee();
        dao.updateEmployee(employee);
	}
}
