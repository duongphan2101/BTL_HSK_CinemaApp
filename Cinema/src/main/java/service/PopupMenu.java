package service;

import javax.swing.*;

import enities.CurrentEmp;
import ui.CinemaLogin;
import ui.Infor_employee;
import ui.frm_DoiMatKhau;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;

public class PopupMenu {
	
	 private JFrame currentFrame;

	 public PopupMenu(JFrame currentFrame) {
	     this.currentFrame = currentFrame;
	 }


    public void showPopup(Component com, int x, int y, CurrentEmp nv) {
        JPopupMenu popupMenu = new JPopupMenu();

        JMenuItem itemThongTinCaNhan = new JMenuItem("Thông tin cá nhân");
        JMenuItem itemDoiMatKhau = new JMenuItem("Đổi Mật Khẩu");
        JMenuItem itemDangXuat = new JMenuItem("Đăng Xuất");

        popupMenu.add(itemThongTinCaNhan);
        popupMenu.add(itemDoiMatKhau);
        popupMenu.add(itemDangXuat);

        itemThongTinCaNhan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
					Infor_employee i = new Infor_employee(nv);
					i.setVisible(true);
				} catch (SQLException | ParseException e1) {
					e1.printStackTrace();
				}
            }
        });

        itemDoiMatKhau.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frm_DoiMatKhau frm = new frm_DoiMatKhau();
                frm.setVisible(true);
            }
        });

        itemDangXuat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn đăng xuất?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                	currentFrame.dispose();
                    CinemaLogin frm = new CinemaLogin();
                    frm.setVisible(true);
                }
            }
        });

        popupMenu.show(com, x, y);
    }

}

