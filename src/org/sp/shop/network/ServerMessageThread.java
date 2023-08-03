package org.sp.shop.network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

//접속자 마다 1:1 대응하는 대화전용 쓰레드 
//각 접속자는 서로 독립적으로 대화를 진행해야 하므로 쓰레드로 처리
public class ServerMessageThread extends Thread{
	GUIServer guiServer;
	Socket socket; //대화용 소켓 (스트림을 뽑기 위해)
	BufferedReader buffr; //문자기반의 버퍼처리된 입력스트림
	BufferedWriter buffw; //문자기반의 버퍼처리된 출력스트림
	boolean flag=true; //이 쓰레드를 죽일지 말지 결정하는 논리값
	
	public ServerMessageThread(GUIServer guiServer, Socket socket) {
		this.guiServer=guiServer;
		this.socket=socket;
		try {
			buffr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			buffw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	//듣기 (입력스트림 이용)
	public void listen() {
		String msg=null;
		try {
			msg=buffr.readLine(); 
			guiServer.area.append(msg+"\n");//로그 남기기
			
			//나의 메서드만 호출하지 말고, 현재 접속한 모든 유저들이 보유한
			//sendMsg()도 함께 호출하자 
			for(int i=0;i<guiServer.vec.size();i++) {
				ServerMessageThread smt=guiServer.vec.get(i);
				smt.sendMsg(msg);//클라이언트에 보내기
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//말하기(출력스트림 이용)
	public void sendMsg(String msg) {
		try {
			buffw.write(msg+"\n"); //버처처리된 스트림은 \n으로 끝을 알려함
			buffw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//서버측의 쓰레드는 끊임없이 클라이언트의 메시지를 청취하고, 
	//다시 보내야 한다..따라서 무한루프로 처리...
	public void run() {
		while(flag) {
			listen();
		}
	}
}












