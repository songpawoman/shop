package org.sp.shop.network;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClientMain extends JFrame{
	JPanel p_north;
	JComboBox box;
	JTextField t_port;
	JButton bt;
	
	JTextArea area;
	JScrollPane scroll;
	
	JTextField t_input;
	Socket socket;
	ClientMessageThread cmt;
	
	public ClientMain() {
		p_north = new JPanel();
		box = new JComboBox();
		t_port = new JTextField("7777", 6);
		bt = new JButton("접속");
		
		area = new JTextArea();
		scroll = new JScrollPane(area);
		
		t_input = new JTextField();
		
		//아이피 채워넣기
		box.addItem("192.168.1.220");
		
		//조립 
		p_north.add(box);
		p_north.add(t_port);
		p_north.add(bt);
		add(p_north, BorderLayout.NORTH);
		
		add(scroll);
		add(t_input, BorderLayout.SOUTH);
		
		setSize(300,400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connect();
			}
		});
		
		t_input.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				int key=e.getKeyCode();
				if(key == KeyEvent.VK_ENTER) {//엔터치면..
					//서버에 메시지 전송
					send();
				}
			}
		});
	}
	
	//서버에 접속하기 
	public void connect() {
		String ip=(String)box.getSelectedItem();
		int port=Integer.parseInt(t_port.getText());
		
		//소켓의 인스턴스가 생성될때 접속이 시도됨...
		try {
			socket = new Socket(ip, port);
			
			//대화용쓰레드 생성 
			cmt = new ClientMessageThread(this);
			cmt.start();//대화시작
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	//서버에 메시지 보내기
	//회원제일 경우, 메시지뿐만 아니라, 각종 부가적 정보도 함께 보내야 
	//하기 때문에, 구조화된 텍스트 기반의 데이터를 표현할 수 있는 방법을
	//찾아야 한다...json (아이디, 이름, 이모티콘(smile.png), 메시지)
	//
	public void send() {
		StringBuffer sb=new StringBuffer();

		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("\"member_idx\":23,");
		sb.append("\"id\" :\"zino\", ");
		sb.append("\"name\" :\"지노\","); 
		sb.append("\"icon\" : \"smile.png\", ");
		sb.append("\"data\" : 변수 ");
		sb.append("}");
	
		
		cmt.sendMsg(t_input.getText());
		t_input.setText("");
	}
	
	public static void main(String[] args) {
		new ClientMain();
	}
}











