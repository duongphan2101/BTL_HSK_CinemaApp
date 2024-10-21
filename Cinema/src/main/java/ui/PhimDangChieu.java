package ui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.border.EmptyBorder;

import dao.DAO_Movie;
import enities.Movie;
import service.ScrollPaneWin11;

import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
public class PhimDangChieu extends JPanel {

	private static final long serialVersionUID = 1L;
	private DAO_Movie dao;
	private JLabel lblName;
	private JPanel panel;
	private JLabel lblImg;
	private JScrollPane scrollPane;

	/**
	 * Create the panel.
	 */
	public PhimDangChieu() {
		setBorder(new EmptyBorder(20, 20, 20, 20));
		setBackground(new Color(255, 255, 255));
		setLayout(new BorderLayout(0, 0));
		
		scrollPane = new ScrollPaneWin11();
		add(scrollPane, BorderLayout.CENTER);
		
		panel = new JPanel();
		
		panel.setBackground(new Color(255, 255, 255));
		scrollPane.setViewportView(panel);
		panel.setLayout(new GridLayout(0, 4, 50, 50));
		scrollPane.setBorder(null);
		
		dao = new DAO_Movie();
		List<Movie> lst = dao.getAllMovieDangChieu();
		draw(lst);	
		
	}
	
	public void draw(List<Movie> lst) {
		for(Movie movie : lst) {
			JPanel item = new JPanel();
			item.setPreferredSize(new Dimension(180, 200));
			item.setBorder(new EmptyBorder(10, 20, 10, 20));
			item.setLayout(new BorderLayout());
			item.add(lblName = new JLabel(movie.getTitle()), BorderLayout.SOUTH);
			lblName.setHorizontalAlignment(SwingConstants.CENTER);
			
			if(movie.getImg() != null) {
				lblImg = new JLabel();
				lblImg.setIcon(new ImageIcon(this.getClass().getResource("/imgs/"+movie.getImg())));
				item.add(lblImg, BorderLayout.CENTER);
				lblImg.setHorizontalAlignment(SwingConstants.CENTER);
			}else {
				lblImg = new JLabel();
				lblImg.setText("Hình Ảnh");
				item.add(lblImg, BorderLayout.CENTER);
				lblImg.setHorizontalAlignment(SwingConstants.CENTER);
			}
			
			panel.add(item);
			
			item.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
				}
				@Override
		        public void mousePressed(MouseEvent e) {
		                
		            }

		        @Override
		       public void mouseReleased(MouseEvent e) {
		                
		           }
			});

		}
	}

}
