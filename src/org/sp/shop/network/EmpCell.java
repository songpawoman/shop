package org.sp.shop.network;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import org.sp.shop.admin.domain.Emp;

import util.ImageUtil;

public class EmpCell extends JPanel{
	ChatMain chatMain;
	JLabel la_icon;
	JLabel la_name;
	Emp emp;
	
	public EmpCell(ChatMain chatMain, Emp emp) {
		this.chatMain=chatMain;
		la_icon = new JLabel(new ImageIcon(ImageUtil.getImage(emp.getPhoto(), 50, 50)));
		la_icon.setPreferredSize(new Dimension(50, 50));
		
		la_name = new JLabel(emp.getName());
		
		setLayout(null);
		add(la_icon);
		add(la_name);
		la_icon.setBounds(10,10,50,50);
		la_name.setBounds(80,5,100,50);		
		
		setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
		setPreferredSize(new Dimension(350, 60));
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//채팅내용 페이지 보여주기 
				chatMain.chatPage.setTitle(emp);
				chatMain.showHide(1);
			}
		});
	}
	
}
