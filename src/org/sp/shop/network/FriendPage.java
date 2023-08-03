package org.sp.shop.network;

import java.awt.Dimension;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.plaf.DimensionUIResource;

import org.sp.shop.admin.domain.Emp;
import org.sp.shop.admin.model.EmpDAO;

import util.DBManager;

public class FriendPage extends JPanel{
	ChatMain chatMain;
	JPanel p_list; //리스트를 담을 패널 
	
	DBManager dbManager;
	EmpDAO empDAO;
	List<Emp> empList;
	
	public FriendPage(ChatMain chatMain) {
		this.chatMain =chatMain;
		p_list = new JPanel();
		
		//스크롤내의 패널을 y축 세로 방향으로 부착하기
		p_list.setLayout(new BoxLayout(p_list, BoxLayout.Y_AXIS));
		dbManager = new DBManager();
		empDAO = new EmpDAO(dbManager);
		empList = empDAO.selectAll();
		
		for(int i=0;i<empList.size();i++) {
			Emp emp=empList.get(i);
			EmpCell empCell = new EmpCell(chatMain, emp);
			p_list.add(empCell);
		}
		
		add(p_list);
		
		this.setPreferredSize(new Dimension(350, 500));
	}
	
}
