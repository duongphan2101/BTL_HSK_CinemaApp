package ui;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import service.ScrollPaneWin11;
import service.backGround_Color;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Date;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.raven.datechooser.DateChooser;

import dao.DAO_Movie;
import dao.DAO_ShowTime;
import dao.DAO_Theater;
import enities.Movie;
import enities.Showtime;
import enities.Theater;

import java.awt.Font;
import javax.swing.border.EmptyBorder;

import service.CustomOptionPane;
import service.RoundedPanel;
import service.getDate;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class frm_SuatChieu extends JPanel implements MouseListener{

	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel model;
	private JScrollPane scrollPane;
	private List<Showtime> list;
	private DAO_ShowTime dao;
	private JPanel btnAdd;
	private JLabel lblSutChiu;
	private JPanel panel;
	private RoundedPanel btnEdit;
	private JLabel lblNewLabel;
	private JTextField txtNgay;
	private DateChooser dateChooser;
	private JButton btnDay;
	private JPanel pn_panelDate;
	private JPopupMenu popup;
	private JMenuItem pop1;
	static Showtime show;
	/**
	 * Create the panel.
	 */
	public frm_SuatChieu(boolean index) {
		if(index) {
			
			popup = new JPopupMenu();
		    pop1 = new JMenuItem("Xóa Xuất Chiếu!");
		    popup.add(pop1);

		    pop1.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					dao = new DAO_ShowTime();
					dao.deleteShowTime(show.getShowtimeId());
				}
			});   
		    
			setLayout(new BorderLayout(0, 0));
			
			scrollPane = new ScrollPaneWin11();
			add(scrollPane, BorderLayout.CENTER);
			
			table = new JTable();
			table.setFont(new Font("Tahoma", Font.PLAIN, 16));
			table.setBorder(null);
			scrollPane.setViewportView(table);
			
			model = new DefaultTableModel();
			
			model.addColumn("Mã Suất Chiếu");
			model.addColumn("Thời Gian Bắt Đầu");
			model.addColumn("Tên Phim");
			model.addColumn("Phòng Chiếu");
			model.addColumn("Thời Lượng");
			
			table.setModel(model);
			table.setRowHeight(30);
			
			JPanel pn_btn = new JPanel();
			pn_btn.setBorder(new EmptyBorder(10, 0, 10, 20));
			pn_btn.setBackground(new Color(255, 255, 255));
			add(pn_btn, BorderLayout.NORTH);
			pn_btn.setLayout(new BorderLayout(0, 0));
			
			btnAdd = new RoundedPanel(15);
			btnAdd.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					frm_controlSuatChieu frm = new frm_controlSuatChieu();
					frm.setVisible(true);
				}
			});
			btnAdd.setPreferredSize(new Dimension(200, 60));
			btnAdd.setBackground(new Color(190, 6, 43));
			pn_btn.add(btnAdd, BorderLayout.EAST);
			btnAdd.setLayout(new BorderLayout(0, 0));
			
			lblSutChiu = new JLabel("Suất Chiếu");
			lblSutChiu.setForeground(new Color(255, 255, 255));
			lblSutChiu.setFont(new Font("Tahoma", Font.BOLD, 24));
			lblSutChiu.setIcon(new ImageIcon(frm_SuatChieu.class.getResource("/icon/icons8-plus-30.png")));
			lblSutChiu.setHorizontalAlignment(SwingConstants.CENTER);
			btnAdd.add(lblSutChiu, BorderLayout.CENTER);
			
			panel = new JPanel();
			panel.setBackground(new Color(255, 255, 255));
			panel.setBorder(new EmptyBorder(0, 20, 0, 20));
			pn_btn.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));
			
			txtNgay = new JTextField();
			txtNgay.setPreferredSize(new Dimension(170, 20));
			txtNgay.setHorizontalAlignment(SwingConstants.CENTER);
			txtNgay.setFont(new Font("Arial", Font.PLAIN, 20));
			dateChooser = new DateChooser();
			dateChooser.setTextRefernce(txtNgay);
			txtNgay.setBackground(Color.white);
			panel.add(txtNgay, BorderLayout.WEST);
//			
			pn_panelDate = new JPanel();
			pn_panelDate.setLayout(new BorderLayout(0, 0));
			pn_panelDate.setBackground(new Color(255, 255, 255));
			btnDay = new JButton("Lọc Theo Ngày");
			btnDay.setForeground(Color.WHITE);
			btnDay.setBackground(backGround_Color.bg_color_deep);
			btnDay.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
//					dateChooser.showPopup();
					table.removeAll();
					dao = new DAO_ShowTime();
					list = dao.getAllShowTimebyShowDate(txtNgay.getText());
					loadTable(list);
				}
			});
			btnDay.setPreferredSize(new Dimension(30, 30));
			pn_panelDate.add(btnDay, BorderLayout.WEST);
			panel.add(pn_panelDate);
			btnEdit = new RoundedPanel(15);
			btnEdit.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					frm_EditSuatChieu frm = new frm_EditSuatChieu(show);
					frm.setVisible(true);
				}
			});
			btnEdit.setPreferredSize(new Dimension(60, 60));
			btnEdit.setBackground(new Color(190, 6, 43));
			panel.add(btnEdit, BorderLayout.EAST);
			btnEdit.setLayout(new BorderLayout(0, 0));
			
			lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon(frm_SuatChieu.class.getResource("/icon/icons8-pencil-50.png")));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			btnEdit.add(lblNewLabel, BorderLayout.CENTER);
			
			 DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		     centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

		     for (int i = 0; i < table.getColumnCount(); i++) {
		    	 table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		     }
			
			JTableHeader header = table.getTableHeader();
			header.setPreferredSize(new Dimension(header.getPreferredSize().width, 40));
			header.setBackground(Color.white);
			header.setFont(new Font("Arial", Font.BOLD, 18));
			
			dao = new DAO_ShowTime();
			Date today = new Date();
			list = dao.getAllShowTimebyShowDate(getDate.toDateTime(today));
			loadTable(list);
			
			table.addMouseListener(this);

		}else {
			setLayout(new BorderLayout(0, 0));
			
			scrollPane = new ScrollPaneWin11();
			add(scrollPane, BorderLayout.CENTER);
			
			table = new JTable();
			table.setFont(new Font("Tahoma", Font.PLAIN, 16));
			table.setBorder(null);
			scrollPane.setViewportView(table);
			
			model = new DefaultTableModel();
			
			model.addColumn("Mã Suất Chiếu");
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
			dao = new DAO_ShowTime();
			Date today = new Date();
			list = dao.getAllShowTimebyShowDate(getDate.toDateTime(today));
			loadTable(list);
		}
		
		
	}
	
	public void loadTable(List<Showtime> list) {
		model.setRowCount(0);
		for(Showtime s : list) {
			String [] row = {s.getShowtimeId()+"",s.getStartTime(), s.getMovie().getTitle(), s.getTheater().getName(), s.getMovie().getDuration()+"/phút"};
			model.addRow(row);
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		dao = new DAO_ShowTime();
		DAO_Movie daoMovie = new DAO_Movie();
		DAO_Theater daoTheater = new DAO_Theater();
		if(row != -1) {
			int id = Integer.parseInt((String) table.getModel().getValueAt(row, 0));
			show = dao.getShowTimebyID(id);
			String tenPhim = (String) table.getModel().getValueAt(row, 2);
			int idPhim = daoMovie.getMovieIdByTitle(tenPhim);
			Movie movie = daoMovie.getMoviebyID(idPhim);
			
			String tenPhong = (String) table.getModel().getValueAt(row, 3);
			int idPhong = daoTheater.getTheaterIdByTitle(tenPhong);
			Theater theater = daoTheater.getTheaterbyID(idPhong);
			
			String date = txtNgay.getText();
			String time = (String) table.getModel().getValueAt(row, 1);
			show.setMovie(movie);
			show.setTheater(theater);
			show.setShowDate(date);
			show.setStartTime(time);
		}
		else {
			CustomOptionPane.showMess("Chọn một suất chiếu để chỉnh sửa!");
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	
	

}
