package org.sp.shop.network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

//무한루프로, 상대방의 메시지를 청취해야 하므로 쓰레드 이용 
public class ClientMessageThread extends Thread{
	ClientMain clientMain;
	Socket socket;
	BufferedReader buffr; //문자기반의 버퍼처리된 입력스트림
	BufferedWriter buffw; //문자기반의 버퍼처리된 출력스트림
	boolean flag=true; //이 쓰레드를 죽일지 말지 결정하는 논리값
	
	public ClientMessageThread(ClientMain clientMain) {
		this.clientMain=clientMain;
		this.socket=clientMain.socket;
		try {
			buffr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			buffw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}
	
	//듣고
	public void listen() {
		String msg=null;
		try {
			msg=buffr.readLine();
			clientMain.area.append(msg+"\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//말하고
	public void sendMsg(String msg) {
		try {
			buffw.write(msg+"\n");
			buffw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		while(flag) {
			listen();
		}
	}
}







