package ui;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.border.EmptyBorder;
import dao.DAO_Movie;
import enities.Movie;
import service.CustomOptionPane;
import service.RoundedPanel;
import service.ScrollPaneWin11;
import service.backGround_Color;
import service.changeColor;

import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class AllPhim extends JPanel {

	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPane;
	private DAO_Movie dao;
	private JPanel panel;
	private JPanel btnAdd;
	private JLabel lblNewLabel;

	/**
	 * Create the panel.
	 */
	public AllPhim() {
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setBackground(new Color(255, 255, 255));
		setLayout(new BorderLayout(0, 0));
		
		scrollPane = new ScrollPaneWin11();
		add(scrollPane, BorderLayout.CENTER);
		
		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		scrollPane.setViewportView(panel);
		panel.setLayout(new GridLayout(0, 4, 20, 20));
		scrollPane.setBorder(null);
		
		JPanel head = new JPanel();
		head.setBorder(new EmptyBorder(10, 0, 10, 20));
		head.setBackground(new Color(255, 255, 255));
		add(head, BorderLayout.NORTH);
		head.setPreferredSize(new Dimension(0, 60));
		head.setLayout(new BorderLayout(0, 0));
		
		btnAdd = new RoundedPanel(15);
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frm_addMovie frm = new frm_addMovie();
				frm.setVisible(true);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				changeColor.changCorlor(btnAdd, backGround_Color.bg_color);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				changeColor.changCorlor(btnAdd, backGround_Color.bg_color_deep);
			}
		});
		head.add(btnAdd, BorderLayout.EAST);
		btnAdd.setLayout(new BorderLayout(0, 0));
		btnAdd.setBackground(backGround_Color.bg_color_deep);
		btnAdd.setPreferredSize(new Dimension(200, 60));
		
		lblNewLabel = new JLabel("Thêm Phim");
		lblNewLabel.setIcon(new ImageIcon(AllPhim.class.getResource("/icon/icons8-plus-30.png")));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		btnAdd.add(lblNewLabel, BorderLayout.CENTER);
		
		dao = new DAO_Movie();
		List<Movie> lst = dao.getAllMovie();
		draw(lst);	
		
	}
	
	public void draw(List<Movie> lst) {
	    for (Movie movie : lst) {
	        Movie m = movie;
	        
	        JPanel item = new JPanel();
	        item.setPreferredSize(new Dimension(180, 200));
	        item.setMinimumSize(getPreferredSize());
	        item.setMaximumSize(getPreferredSize());
	        item.setBorder(new EmptyBorder(10, 20, 10, 20));
	        item.setLayout(new BorderLayout());

	        JLabel lblName = new JLabel(m.getTitle());
	        lblName.setHorizontalAlignment(SwingConstants.CENTER);
	        item.add(lblName, BorderLayout.SOUTH);

	        JLabel lblImg = new JLabel();
	        if (m.getImg() != null) {
	            File imageFile = new File("src/main/java/imgs/" + m.getImg());
	            if (imageFile.exists()) {
	                lblImg.setIcon(new ImageIcon(imageFile.getAbsolutePath()));
	                lblImg.setHorizontalAlignment(SwingConstants.CENTER);
	            } else {
	                lblImg.setText("Hình Ảnh không tồn tại");
	                lblImg.setHorizontalAlignment(SwingConstants.CENTER);
	            }
	        } else {
	            lblImg.setText("Hình Ảnh");
	            lblImg.setHorizontalAlignment(SwingConstants.CENTER);
	        }
	        item.add(lblImg, BorderLayout.CENTER);

	        panel.add(item);
	        JPopupMenu popup = new JPopupMenu();
	        JMenuItem pop1 = new JMenuItem("Sửa Thông Tin Phim");
	        JMenuItem pop2 = new JMenuItem("Xóa Phim");
	        JMenuItem pop3 = new JMenuItem("Chuyển trạng thái chiếu");
	        JMenuItem pop4 = new JMenuItem("Ngưng Chiếu");

	        popup.add(pop1);
	        popup.add(pop2);

	        pop1.addActionListener(e -> {
	            frm_editMoive frm = new frm_editMoive(m);
	            frm.setVisible(true);
	        });

	        pop2.addActionListener(e -> {
	            dao = new DAO_Movie();
	            int yes = CustomOptionPane.showCustomerNotFoundDialog("Bạn có chắc muốn xóa phim này không!");
	            if (yes == JOptionPane.YES_OPTION) {
	                dao.deleteMovie(m.getMovieId()); 
	            }
	        });

	        pop3.addActionListener(e -> {
	            dao = new DAO_Movie();
	            dao.DangChieu(m.getMovieId());
	        });

	        pop4.addActionListener(e -> {
	            dao = new DAO_Movie();
	            dao.NgungChieu(m.getMovieId()); 
	        });


	        item.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mousePressed(MouseEvent e) {
	                if (m.getMovieStatus().getStatusId() != 1) {
	                    if (e.isPopupTrigger()) {
	                        popup.show(e.getComponent(), e.getX(), e.getY());
	                    }
	                    popup.add(pop3);
	                    popup.remove(pop4);
	                } else {
	                    if (e.isPopupTrigger()) {
	                        popup.show(e.getComponent(), e.getX(), e.getY());
	                    }
	                    popup.add(pop4);
	                    popup.remove(pop3);
	                }
	            }

	            @Override
	            public void mouseReleased(MouseEvent e) {
	                if (m.getMovieStatus().getStatusId() != 1) {
	                    if (e.isPopupTrigger()) {
	                        popup.show(e.getComponent(), e.getX(), e.getY());
	                    }
	                    popup.add(pop3);
	                    popup.remove(pop4);
	                } else {
	                    if (e.isPopupTrigger()) {
	                        popup.show(e.getComponent(), e.getX(), e.getY());
	                    }
	                    popup.add(pop4);
	                    popup.remove(pop3);
	                }
	            }
	        });
	    }
	}


	

}
