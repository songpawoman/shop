package org.sp.shop.admin.view.product;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.sp.shop.admin.domain.SubCategory;
import org.sp.shop.admin.domain.TopCategory;
import org.sp.shop.admin.model.SubCategoryDAO;
import org.sp.shop.admin.model.TopCategoryDAO;

//등록화면..
public class RegistPage extends ProductSubPage{
	JComboBox box_top; //상위 카테고리
	JComboBox box_sub; //하위 카테고리 
	
	JTextField t_product_name;
	JTextField t_brand;
	JTextField t_price;
	//대표이미지는 파일 탐색기로 대체...
	JLabel la_path; //선택한 이미지경로 출력 
	JButton bt_file;//파일 탐색기 띄우는 버튼 
	JPanel p_preview; //선택한 이미지 미리보기 
	JTextArea area;//상세내용 입력
	JScrollPane scroll;
	
	JPanel p_content; // 너비 700x500 짜리..
	TopCategoryDAO topCategoryDAO;
	SubCategoryDAO subCategoryDAO;
	List<TopCategory> topList; //콤보박스에 채워넣을 원본 데이터 
											//DTO들이 들어있음
			
	
	public RegistPage() {
		box_top = new JComboBox();
		box_sub = new JComboBox();
		t_product_name = new JTextField();
		t_brand = new JTextField();
		t_price = new JTextField();
		la_path = new JLabel("이미지를 선택하세요");
		bt_file = new JButton("파일찾기");
		p_preview = new JPanel();//추후 그림을 직접 그리겠슴..
		area = new JTextArea();
		scroll = new JScrollPane(area);
		p_content = new JPanel();
		topCategoryDAO = new TopCategoryDAO();
		subCategoryDAO  = new SubCategoryDAO();
		
		//스타일
		p_content.setPreferredSize(new Dimension(700, 550));
		
		Dimension d = new Dimension(700, 50);
		
		box_top.setPreferredSize(d);
		box_sub.setPreferredSize(d);
		t_product_name.setPreferredSize(d);
		t_brand.setPreferredSize(d);
		t_price.setPreferredSize(d);
		la_path.setPreferredSize(new Dimension(600, 50));
		p_preview.setPreferredSize(new Dimension(130, 100));
		scroll.setPreferredSize(new Dimension(700, 100));
		
		//조립 
		p_content.add(box_top);
		p_content.add(box_sub);
		p_content.add(t_product_name);
		p_content.add(t_brand);
		p_content.add(t_price);
		p_content.add(la_path);
		p_content.add(bt_file);
		p_content.add(p_preview);
		p_content.add(scroll);
		
		add(p_content);
		
		//콤보박스에 상위 카테고리 채우기 
		getTopList();
		
		//첫번째 콤보박스에 이벤트 연결 
		box_top.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index=box_top.getSelectedIndex();
				//index를 이용하여 ArrayList 의 DTO를 추출하자 
				TopCategory dto=topList.get(index);
				int topcategory_idx=dto.getTopcategory_idx(); //pk
				System.out.println("pk 값은 "+topcategory_idx);
				
				subList(topcategory_idx);
			}
		});
	}
	
	public void getTopList() {
		topList = topCategoryDAO.selectAll();
		
		//topList에 들어있는 요소들을 꺼내여(DTO들을 꺼내어)
		//콤보박스의 요소로 채워넣자
		for(int i=0;i<topList.size();i++) {
			TopCategory topCategory=topList.get(i); //DTO 꺼내기
			
			box_top.addItem(topCategory.getTopname());			
		}
	}
	
	//서브 카테고리 가져오기 
	public void subList(int topcateogory_idx) {
		List<SubCategory> subList=subCategoryDAO.selectAllByFkey(topcateogory_idx);
		
		//기존에 이미 등록된 아이템들이 존재한다면 싹 비우고~~
		box_sub.removeAllItems();
		
		//하위 카테고리를 두번째 콤보박스에 채워넣기
		for(int i=0;i<subList.size();i++) {
			SubCategory dto=subList.get(i); //DTO 꺼내기
			box_sub.addItem(dto.getSubname());
		}
		
	}
	
}











