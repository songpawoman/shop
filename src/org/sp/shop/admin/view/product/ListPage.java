package org.sp.shop.admin.view.product;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

//등록화면..
public class ListPage extends ProductSubPage{
	JTable table;
	JScrollPane scroll;
	JPanel p_south; //페이징 처리 번호가 얹혀질 패널 
	ProductModel model;
	
	public ListPage() {
		table = new JTable(model = new ProductModel());
		scroll = new JScrollPane(table);
		p_south = new JPanel();
		
		this.setLayout(new BorderLayout());
		
		
		//조립 
		add(scroll);//CENTER에 부착 
		add(p_south, BorderLayout.SOUTH);
	}
}








