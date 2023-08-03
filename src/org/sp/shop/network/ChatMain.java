package org.sp.shop.network;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import util.ImageUtil;

public class ChatMain extends JFrame{
	JPanel p_container; //페이지 교체용 컨테이너
	
	JPanel[] pages=new JPanel[2];
	FriendPage friendPage;
	ChatPage chatPage;//채팅내용을 보여줄 패널 페이지
	JScrollPane scroll;
	
	JPanel p_south; //하단 네비게이션 영역
	ArrayList<JLabel> navi=new ArrayList<JLabel>();
	
	public ChatMain() {
		p_container = new JPanel();
		
		//페이지 생성 및 부착 
		friendPage=new FriendPage(this);
		chatPage=new ChatPage(this);
		pages[0]=friendPage;
		pages[1]=chatPage;
		
		for(int i=0;i<pages.length;i++) {			
			p_container.add(pages[i]);
		}
		scroll = new JScrollPane(p_container);
		p_south = new JPanel();
		
		createNavi(); //네비게이션 만들기
		
		p_south.setLayout(new GridLayout(1,4));
		
		scroll.setPreferredSize(new Dimension(400, 500));
		
		add(scroll);
		
		setVisible(true);
		setSize(400,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		showHide(0);
	}
	
	public void createNavi() {
		String[] images = {"friend.png","chat.png","shop.png","more.png"};
		for(int i=0;i<images.length;i++) {
			JLabel la = new JLabel(new ImageIcon(ImageUtil.getImage(images[i], 50, 50)));
			p_south.add(la);
		}
	}
	
	public void showHide(int n) {
		for(int i=0;i<pages.length;i++) {
			if(i==n) {
				pages[i].setVisible(true);
			}else {
				pages[i].setVisible(false);
			}
		}
	}

	
	public static void main(String[] args) {
		new ChatMain();
	}
}
