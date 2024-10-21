package ui;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;
import com.raven.datechooser.DateChooser;
import dao.DAO_Movie;
import service.RoundedPanel;
import service.backGround_Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class frm_ThongKe extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel head;
	private JTextField txtStart;
	private DateChooser dateChooser;
	private JLabel lblStart;
	private JLabel lblEnd;
	private Component horizontalStrut;
	private JTextField txtEnd;
	private JPanel body;
	private JFreeChart chart;
	private JPanel bottom;
	private JPanel btnThongKe;
	private JLabel lblNewLabel;
	private String startDay;
	private String endDay;
	private DateChooser dateChooserEnd;
	private ChartPanel ChartPanel;

	public frm_ThongKe() {
		setBackground(new Color(255, 255, 255));
		setLayout(new BorderLayout(0, 0));
		
		head = new JPanel();
		head.setForeground(new Color(0, 0, 0));
		head.setBorder(new EmptyBorder(20, 30, 20, 30));
		add(head, BorderLayout.NORTH);
		head.setLayout(new BoxLayout(head, BoxLayout.X_AXIS));
		
		lblStart = new JLabel("Ngày bắt Đầu");
		lblStart.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		txtStart = new JTextField();
		txtStart.setPreferredSize(new Dimension(200, 30));
		txtStart.setMaximumSize(txtStart.getPreferredSize());
		txtStart.setHorizontalAlignment(SwingConstants.CENTER);
		txtStart.setFont(new Font("Arial", Font.PLAIN, 20));
		dateChooser = new DateChooser();
		dateChooser.setTextRefernce(txtStart);
		txtStart.setBackground(Color.white);
		head.add(lblStart);
		head.add(Box.createHorizontalStrut(10));
		head.add(txtStart);
		head.add(Box.createHorizontalStrut(250));
		
		lblEnd = new JLabel("Ngày Kết Thúc");
		lblEnd.setFont(new Font("Tahoma", Font.PLAIN, 16));
		head.add(lblEnd);
		
		horizontalStrut = Box.createHorizontalStrut(10);
		head.add(horizontalStrut);
		
		txtEnd = new JTextField();
		txtEnd.setPreferredSize(new Dimension(200, 30));
		txtEnd.setMaximumSize(new Dimension(200, 30));
		txtEnd.setHorizontalAlignment(SwingConstants.CENTER);
		txtEnd.setFont(new Font("Arial", Font.PLAIN, 20));
		txtEnd.setBackground(Color.WHITE);
		dateChooserEnd = new DateChooser();
		dateChooserEnd.setTextRefernce(txtEnd);
		head.add(txtEnd);
		
		body = new JPanel();
		body.setBackground(new Color(255, 255, 255));
		body.setBorder(new EmptyBorder(10, 10, 10, 10));
		add(body, BorderLayout.CENTER);
		body.setLayout(new BorderLayout(0, 0));
		
		startDay = txtStart.getText();
		endDay = txtEnd.getText();
		createBarChart(startDay, endDay);
		
		bottom = new JPanel();
		bottom.setBackground(new Color(255, 255, 255));
		bottom.setBorder(new EmptyBorder(20, 100, 20, 100));
		add(bottom, BorderLayout.SOUTH);
		bottom.setLayout(new BorderLayout(0, 0));
		
		btnThongKe = new RoundedPanel(15);
		btnThongKe.setBackground(backGround_Color.bg_color);
		btnThongKe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				startDay = txtStart.getText();
				endDay = txtEnd.getText();
				createBarChart(startDay, endDay);
			}
		});
		btnThongKe.setBorder(new EmptyBorder(10, 0, 10, 0));
		bottom.add(btnThongKe, BorderLayout.CENTER);
		btnThongKe.setLayout(new BorderLayout(0, 0));
		
		lblNewLabel = new JLabel("Thống Kê");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		btnThongKe.add(lblNewLabel, BorderLayout.NORTH);
	}
	
	public void createBarChart(String ns, String ne) {
		DAO_Movie dao = new DAO_Movie();
		DefaultCategoryDataset dataset = dao.getListMovieSeller(ns, ne);
		chart = ChartFactory.createBarChart("Số Vé Bán Được Theo Phim", "Phim", "Số Vé", dataset, 
				PlotOrientation.VERTICAL, true, true, false);
		CategoryPlot plot = chart.getCategoryPlot();
	    plot.setBackgroundPaint(Color.WHITE);
	    plot.setRangeGridlinePaint(Color.BLACK);
	    BarRenderer renderer = (BarRenderer) plot.getRenderer();
	    renderer.setBarPainter(new StandardBarPainter());
	    renderer.setSeriesPaint(0, backGround_Color.bg_color);     
	    renderer.setDrawBarOutline(false);
	    body.removeAll();
		chart.getCategoryPlot().getRenderer().setSeriesOutlinePaint(0, null);
		chart.getCategoryPlot().setOutlineVisible(true);
		ChartPanel = new ChartPanel(chart);
		ChartPanel.setBackground(new Color(255, 255, 255));
		body.add(ChartPanel, BorderLayout.CENTER);
		body.revalidate();
	    body.repaint();
	}

}
