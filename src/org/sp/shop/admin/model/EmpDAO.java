package org.sp.shop.admin.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.sp.shop.admin.domain.Emp;

import util.DBManager;

public class EmpDAO {

	DBManager dbManager;
	
	
	public EmpDAO(DBManager dbManager) {
		this.dbManager =dbManager;
	}
	
	
	public List selectAll() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Emp> list = new ArrayList<Emp>();
		
		StringBuilder sb = new StringBuilder();
		sb.append("select * from emp order by emp_idx asc");
		
		con = dbManager.connect();
		try {
			pstmt = con.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Emp emp = new Emp();
				emp.setEmp_idx(rs.getInt("emp_idx"));
				emp.setId(rs.getString("id"));
				emp.setPass(rs.getString("pass"));
				emp.setName(rs.getString("name"));
				emp.setJob(rs.getString("job"));
				emp.setPhoto(rs.getString("photo"));
				
				list.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			 dbManager.release(con, pstmt, rs);
		}
		return list;
	}
	
	
}








