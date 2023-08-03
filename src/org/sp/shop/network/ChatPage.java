package org.sp.shop.network;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.sp.shop.admin.domain.Emp;

//채팅 페이지 
public class ChatPage extends JPanel{
	ChatMain chatMain;
	JPanel p_north;
	JLabel la_back; //뒤로가기
	JPanel p_center; //상세내용
	Emp emp;
	
	public ChatPage(ChatMain chatMain) {
		this.chatMain =chatMain;
		p_north = new JPanel();
		la_back = new JLabel("");
		p_center = new JPanel();
		
		//style
		p_center.setBackground(Color.YELLOW);
		la_back.setPreferredSize(new Dimension(150, 40));
		
		//조립
		this.setLayout(new BorderLayout());
		p_north.add(la_back);
		
		add(p_north, BorderLayout.NORTH);
		add(p_center);		
		
		this.setPreferredSize(new Dimension(350, 450));
		
		la_back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				chatMain.showHide(0);
			}
		});
	}
	public void setTitle(Emp emp) {
		this.emp=emp;
		la_back.setText("◀ "+emp.getName());
	}
	
}


