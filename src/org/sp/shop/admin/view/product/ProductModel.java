package org.sp.shop.admin.view.product;

import javax.swing.table.AbstractTableModel;

//JTable에 정보를 제공해주는  Product 전용 컨트롤러
public class ProductModel extends AbstractTableModel{

	@Override
	public int getRowCount() {
		return 5;
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return "사과";
	}
	
}









