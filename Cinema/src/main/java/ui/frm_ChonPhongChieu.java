package ui;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import dao.DAO_Theater;
import dao.DAO_Ticktet_Invoice;
import enities.CurrentEmp;
import enities.Employee;
import enities.EmployeeType;
import enities.Invoice;
import enities.InvoiceDetail;
import enities.Seat;
import enities.Showtime;
import enities.Theater;
import enities.Ticket;
import service.CustomOptionPane;
import service.RoundedPanel;
import service.ScrollPaneWin11;
import service.backGround_Color;
import service.getDate;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;

public class frm_ChonPhongChieu extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel lblTitle;
    static Showtime showtime;
    private JPanel mangHinh;
    private JLabel lblNewLabel, lblName;
    private JLabel lblNewLabel_1;
    private JScrollPane scrollPane;
    private JPanel panel_Ghe;
    private Set<Seat> selectedSeats = new HashSet<>();
    private JPanel bottom;
    private JPanel btn_XacNhan;
    private JLabel lblNewLabel_2;
	private Theater t;
	private DAO_Ticktet_Invoice dao;
	private Date timeToDay;
	private float total = 0;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frm_ChonPhongChieu frame = new frm_ChonPhongChieu(showtime);
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
    public frm_ChonPhongChieu(Showtime showtime) {
        setType(Type.UTILITY);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(0, 0, 900, 700);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JPanel body = new JPanel();
        body.setBackground(new Color(0, 0, 0));
        body.setBorder(new EmptyBorder(30, 10, 10, 10));
        contentPane.add(body, BorderLayout.CENTER);
        body.setLayout(new BorderLayout(0, 0));

        mangHinh = new JPanel();
        mangHinh.setBackground(new Color(0, 0, 0));
        body.add(mangHinh, BorderLayout.NORTH);
        mangHinh.setLayout(new BorderLayout(0, 0));

        lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(frm_ChonPhongChieu.class.getResource("/icon/manghinh.png")));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mangHinh.add(lblNewLabel, BorderLayout.NORTH);

        lblNewLabel_1 = new JLabel("Màng Hình");
        lblNewLabel_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setBorder(new EmptyBorder(5, 5, 5, 5));
        mangHinh.add(lblNewLabel_1, BorderLayout.CENTER);

        scrollPane = new ScrollPaneWin11();
        body.add(scrollPane, BorderLayout.CENTER);

        panel_Ghe = new JPanel();
        panel_Ghe.setBackground(new Color(0, 0, 0));
        scrollPane.setBorder(null);
        scrollPane.setViewportView(panel_Ghe);
        panel_Ghe.setLayout(new GridLayout(0, 5, 20, 20));
        
        bottom = new JPanel();
        bottom.setBorder(new EmptyBorder(10, 0, 0, 0));
        bottom.setBackground(new Color(0, 0, 0));
        body.add(bottom, BorderLayout.SOUTH);
        bottom.setLayout(new BorderLayout(0, 0));
        
        btn_XacNhan = new RoundedPanel(15);
        btn_XacNhan.setPreferredSize(new Dimension(250, 50));
        btn_XacNhan.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                    DAO_Ticktet_Invoice dao = new DAO_Ticktet_Invoice();        
//                    DAO_Employee daoEmp = new DAO_Employee();

                    timeToDay = new Date();
                    Employee emp = new Employee(CurrentEmp.getEmployeeId(), CurrentEmp.getName(), new EmployeeType(CurrentEmp.getEmployeeType().getTypeId()), CurrentEmp.getBirthDate(), CurrentEmp.getPhone(), CurrentEmp.getEmail());
                    Invoice invoice = new Invoice(1, 0, getDate.toDateTime(timeToDay), emp);
                    dao.addInvoice(invoice);

                        int invoiceId = dao.getLastInvoiceId();
                        Invoice hd = invoice;
                        hd.setInvoiceId(invoiceId);
                        for (Seat s : selectedSeats) {
                            Ticket ve = newTicket(s.getSeatNumber(), showtime);
                            int quantity = 1;
                            int veId = dao.getLastTicketId();
                            ve.setTicketId(veId);

                            InvoiceDetail detail = new InvoiceDetail(hd, ve, quantity, 80);
                            dao.addInvoiceDetail(detail);

                            total += quantity * 80;
                            dao.updateInvoiceTotalAmount(invoice.getInvoiceId(), total);
                            DAO_Theater daotheater = new DAO_Theater();
                            daotheater.ChuyenTrangThaiGhe(s.getSeatId());
                           frm_VE frm = new frm_VE();
                           frm.setVe(veId);
                           frm.setVisible(true);
                           dispose();
                        }
                        CustomOptionPane.showNotice("Đặt Vé Thành Công!");
                        Mau_HoaDon frm = new Mau_HoaDon();
                        frm.setHoaDon(invoiceId);
                        frm.setVisible(true);
                    }

        });
        bottom.add(btn_XacNhan, BorderLayout.NORTH);
        btn_XacNhan.setLayout(new BorderLayout(0, 0));
        btn_XacNhan.setBackground(backGround_Color.bg_color_deep);
        
        lblNewLabel_2 = new JLabel("Xác Nhận");
        lblNewLabel_2.setForeground(new Color(255, 255, 255));
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
        btn_XacNhan.add(lblNewLabel_2, BorderLayout.CENTER);

        JPanel head = new JPanel();
        head.setBorder(new EmptyBorder(0, 10, 5, 50));
        contentPane.add(head, BorderLayout.NORTH);
        head.setLayout(new BorderLayout(0, 0));

        lblTitle = new JLabel("Phong Chieu");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);

        head.add(lblTitle, BorderLayout.NORTH);

        t = showtime.getTheater();
        lblTitle.setText(t.getName());
        DAO_Theater daotheater = new DAO_Theater();
        List<Seat> lstSeat = daotheater.getAllSeatfromTheater(t.getTheaterId());
        draw(lstSeat);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                selectedSeats.clear();
                System.out.println("Set đã được xóa: " + selectedSeats);
            }
        });
    }

    public void draw(List<Seat> lst) {        
        for (Seat seat : lst) {
            Font myFont = new Font("Arial", Font.BOLD, 24);
            JPanel item = new RoundedPanel(15);
            item.setPreferredSize(new Dimension(50, 50));
            item.setBorder(new EmptyBorder(10, 20, 10, 20));
            if (seat.getSeatStatus().getStatus_id() != 1) {
                item.setBackground(backGround_Color.bg_color);      
            } else {
                item.setBackground(Color.white); 
                final boolean[] isColorToggled = {false};
                item.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (isColorToggled[0]) {
                            item.setBackground(Color.white); 
                            selectedSeats.remove(seat);       
                        } else {  
                            item.setBackground(backGround_Color.bg_color_deep);
                            selectedSeats.add(seat);
                        }
                        isColorToggled[0] = !isColorToggled[0];
                    }
                });
            }

            item.setLayout(new BorderLayout());
            lblName = new JLabel(seat.getSeatNumber());
            lblName.setFont(myFont);
            lblName.setHorizontalAlignment(SwingConstants.CENTER);
            item.add(lblName, BorderLayout.CENTER);
            panel_Ghe.add(item);
        }
    }
    
    private Ticket newTicket(String seatNumber, Showtime show) {
    	dao = new DAO_Ticktet_Invoice();
    	timeToDay = new Date();
    	Ticket ticket = new Ticket(1, show, t, seatNumber, 80, getDate.toTime(timeToDay));
    	dao.addTicket(ticket);
    	return ticket;
    }

}
