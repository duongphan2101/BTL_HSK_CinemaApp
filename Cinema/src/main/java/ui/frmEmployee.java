package ui;

import javax.swing.JPanel;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dao.DAO_Employee;
import enities.Account;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.Color;

import javax.swing.ImageIcon;
import service.RoundedPanel;
import service.ScrollPaneWin11;
import java.awt.Dimension;
import java.awt.BorderLayout;

public class frmEmployee extends JPanel {

	private static final long serialVersionUID = 1L;
	private ScrollPaneWin11 scrollPane;
	private JTable table;
	private DefaultTableModel model;
	private DAO_Employee dao;
	private List<Account> list;
	/**
	 * Create the panel.
	 */
	public frmEmployee() {

		setSize(955, 500);
		setLayout(new BorderLayout());

		

		JPanel employeePanel = new JPanel();
		employeePanel.setLayout(null);
		createEmployeePanel(employeePanel);

		
		add(employeePanel, BorderLayout.CENTER);

		loadTable();

	}

	private void loadTable() {
		dao = new DAO_Employee();
		list = dao.getAllEmployee();
	
		for (Account e : list) {
			String employeeType = "";
			if (e.getEmployee().getEmployeeType() != null) {
				employeeType = e.getEmployee().getEmployeeType().getTypeId() == 1 ? "Quản lý" : "Nhân viên";
			}

			String[] row = { String.valueOf(e.getEmployee().getEmployeeId()), e.getEmployee().getName(), employeeType, e.getEmployee().getBirthDate(),
					e.getEmployee().getPhone(), e.getEmployee().getEmail(),e.getUsername() };
			model.addRow(row);
//			System.out.println(e);
		}
//		System.out.println(list);
	}

	private void createEmployeePanel(JPanel panel) {
		RoundedPanel btnAddEmployee = new RoundedPanel(15);
		btnAddEmployee.setPreferredSize(new Dimension(200, 60));
		btnAddEmployee.setBackground(new Color(190, 6, 43));
		btnAddEmployee.setBounds(685, 74, 208, 48);
		panel.add(btnAddEmployee);
		btnAddEmployee.setLayout(new BorderLayout(0, 0));
		btnAddEmployee.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				openAddEmployeeForm();
			}
		});

		JLabel lblThemNhanVien = new JLabel("Thêm Nhân Viên");
		btnAddEmployee.add(lblThemNhanVien, BorderLayout.CENTER);
		lblThemNhanVien.setIcon(new ImageIcon(frmEmployee.class.getResource("/icon/icons8-plus-30.png")));
		lblThemNhanVien.setHorizontalAlignment(SwingConstants.CENTER);
		lblThemNhanVien.setForeground(Color.WHITE);
		lblThemNhanVien.setFont(new Font("Tahoma", Font.BOLD, 18));

		RoundedPanel btnPen = new RoundedPanel(15);
		btnPen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow();
				String id = model.getValueAt(index, 0).toString();
				dao = new DAO_Employee();
				
				frm_EditEmployee frm = new frm_EditEmployee(dao.getEmployeebyID(Integer.parseInt(id)));
				frm.setVisible(true);
				
			}
		});
		btnPen.setPreferredSize(new Dimension(200, 60));
		btnPen.setBackground(new Color(190, 6, 43));
		btnPen.setBounds(619, 74, 48, 48);
		panel.add(btnPen);
		btnPen.setLayout(new BorderLayout(0, 0));

		JLabel lblPen = new JLabel("");
		btnPen.add(lblPen, BorderLayout.CENTER);
		lblPen.setIcon(new ImageIcon(frmEmployee.class.getResource("/icon/icons8-pencil-50.png")));
		lblPen.setHorizontalAlignment(SwingConstants.CENTER);
		lblPen.setForeground(Color.WHITE);
		lblPen.setFont(new Font("Tahoma", Font.BOLD, 18));

		String[] columnNames = { "Mã NV", "Tên NV", "Loại NV", "Ngày Bắt Đầu", "SĐT", "Email", "Tài Khoản" };
		model = new DefaultTableModel(columnNames, 0);

		table = new JTable(model);
		table.setFillsViewportHeight(true);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i < columnNames.length; i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}

		scrollPane = new ScrollPaneWin11();
		scrollPane.setViewportView(table);
		scrollPane.setBounds(0, 150, 955, 350);
		panel.add(scrollPane);

	}

	private void openAddEmployeeForm() {
		frm_addEmployee addEmployeeForm = new frm_addEmployee();
		addEmployeeForm.setVisible(true);
	}

}