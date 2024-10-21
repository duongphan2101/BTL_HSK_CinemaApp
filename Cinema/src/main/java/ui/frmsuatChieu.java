package ui;

import javax.swing.JPanel;
import service.ScrollPaneWin11;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import dao.DAO_ShowTime;
import enities.Showtime;
import java.awt.Font;

public class frmsuatChieu extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel model;
	private JScrollPane scrollPane;
	private List<Showtime> list;
	private DAO_ShowTime dao;
	/**
	 * Create the panel.
	 */
	public frmsuatChieu() {
		setLayout(new BorderLayout(0, 0));
		
		scrollPane = new ScrollPaneWin11();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 16));
		table.setBorder(null);
		scrollPane.setViewportView(table);
		
		model = new DefaultTableModel();
		
		model.addColumn("Thời Gian Bắt Đầu");
		model.addColumn("Tên Phim");
		model.addColumn("Phòng Chiếu");
		model.addColumn("Thời Lượng");
		
		table.setModel(model);
		table.setRowHeight(30);
		
		 DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	     centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

	     for (int i = 0; i < table.getColumnCount(); i++) {
	    	 table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
	     }
		
		JTableHeader header = table.getTableHeader();
		header.setPreferredSize(new Dimension(header.getPreferredSize().width, 40));
		header.setBackground(Color.white);
		header.setFont(new Font("Arial", Font.BOLD, 18));
		
		loadTable();

	}
	
	public void loadTable() {
		dao = new DAO_ShowTime();
		list = dao.getAllShowTime();
		
		for(Showtime s : list) {
			String [] row = {s.getStartTime(), s.getMovie().getTitle(), s.getTheater().getName(), s.getMovie().getDuration()+"/phút"};
			model.addRow(row);
		}
		
	}
	
}
