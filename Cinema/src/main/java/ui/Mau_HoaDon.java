package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dialog.ModalExclusionType;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.LineBorder;
import dao.DAO_Ticktet_Invoice;
import enities.CurrentEmp;
import enities.Invoice;
import enities.InvoiceDetail;

public class Mau_HoaDon extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lbl_tongTien, lbl_nvLap, lbl_ngayLap;
	DecimalFormat df = new DecimalFormat("###,###,### VND");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mau_HoaDon frame = new Mau_HoaDon();
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
	public Mau_HoaDon() {
		setResizable(false);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 650, 770);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(null);
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("HÓA ĐƠN");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 50));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 35, 636, 91);
		contentPane.add(lblNewLabel);
		
		lbl_ngayLap = new JLabel("Ngày Lâp: 27:04:2025 19:36:59 PM");
		lbl_ngayLap.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_ngayLap.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_ngayLap.setBounds(0, 143, 636, 27);
		contentPane.add(lbl_ngayLap);
		
		lbl_nvLap = new JLabel("Nhân Viên lập hóa đơn: Nguyễn Văn A");
		lbl_nvLap.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_nvLap.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_nvLap.setBounds(0, 171, 636, 27);
		contentPane.add(lbl_nvLap);
		
		JLabel lblNewLabel_1 = new JLabel("Mã Vé");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(0, 266, 210, 30);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Đơn Giá");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(211, 266, 210, 30);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Số Lượng");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_2.setBounds(426, 266, 210, 30);
		contentPane.add(lblNewLabel_1_2);
		
		JPanel line = new JPanel();
		line.setBorder(new LineBorder(new Color(0, 0, 0)));
		line.setBounds(10, 306, 616, 1);
		contentPane.add(line);
				
		
		lbl_tongTien = new JLabel("Tổng Tiền: 105,000 NVD");
		lbl_tongTien.setHorizontalAlignment(SwingConstants.TRAILING);
		lbl_tongTien.setFont(new Font("Tahoma", Font.BOLD, 18));
		lbl_tongTien.setBounds(379, 475, 243, 28);
		contentPane.add(lbl_tongTien);		
		
		JLabel lblNewLabel_2 = new JLabel("----------CENIMA STAR----------");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(0, 612, 636, 54);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("12, Nguyễn Văn Bảo, Phường 4, Gò Vấp, Tp. HCM");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2_1.setBounds(0, 676, 636, 54);
		contentPane.add(lblNewLabel_2_1);
	}
	
	public void setHoaDon(int maHD) {
    	DAO_Ticktet_Invoice hddao = new DAO_Ticktet_Invoice();
	    Invoice hds = hddao.getInvoicebyID(maHD);
	    lbl_ngayLap.setText("Ngày Lập: " + hds.getPurchaseTime());
	    hds.getEmployee();
		lbl_nvLap.setText("Nhân Viên lập hóa đơn: " + CurrentEmp.getName());
	    lbl_tongTien.setText("Tổng Tiền: " + df.format(hds.getTotalAmount()));

	    List<InvoiceDetail> listcthd = hddao.getDetailbyInvoiceId(maHD);
	    int labelY = 328;
	    int labelHeight = 30;
	    int labelSpacing = 20;

	    for (InvoiceDetail tdon : listcthd) {
	        JLabel lbl_ten = new JLabel(tdon.getTicket().getTicketId()+"");
	        lbl_ten.setHorizontalAlignment(SwingConstants.CENTER);
	        lbl_ten.setFont(new Font("Tahoma", Font.PLAIN, 20));
	        lbl_ten.setBounds(0, labelY, 210, labelHeight);
	        contentPane.add(lbl_ten);
	     
	        JLabel lbl_gia = new JLabel(df.format(tdon.getPricePerTicket()));
	        lbl_gia.setHorizontalAlignment(SwingConstants.CENTER);
	        lbl_gia.setFont(new Font("Tahoma", Font.PLAIN, 20));
	        lbl_gia.setBounds(211, labelY, 210, labelHeight);
	        contentPane.add(lbl_gia);

	        JLabel lbl_soLuong = new JLabel(tdon.getQuantity() + "");
	        lbl_soLuong.setHorizontalAlignment(SwingConstants.CENTER);
	        lbl_soLuong.setFont(new Font("Tahoma", Font.PLAIN, 20));
	        lbl_soLuong.setBounds(426, labelY, 210, labelHeight);
	        contentPane.add(lbl_soLuong);
	        
			JPanel line_1 = new JPanel();
			line_1.setBorder(new LineBorder(new Color(0, 0, 0)));
			line_1.setBounds(11, labelY+40, 616, 1);
			contentPane.add(line_1);
			
			lbl_tongTien.setBounds(379, labelY+80, 243, 28);
			
	        labelY += labelHeight + labelSpacing;
	    }
	}

}
