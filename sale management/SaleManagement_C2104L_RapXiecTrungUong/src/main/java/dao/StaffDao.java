package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import common.SqlConnection;
import entity.Order;
import entity.Staff;


public class StaffDao {
	 public List<Staff> getListStaff(){
		   List<Staff> list = new ArrayList<Staff>();
			try(Connection con = SqlConnection.connect();
					CallableStatement call = con.prepareCall("{call selectAllStaff}");
					ResultSet rs = call.executeQuery();)
				{
					while(rs.next()){
						Staff staff = new Staff();
						staff.setId(rs.getInt("id"));
						staff.setName(rs.getString("name"));
						staff.setLevel(rs.getInt("level"));
						staff.setPassword(rs.getString("password"));
						staff.setCreateAt(rs.getObject(5,LocalDateTime.class));
						staff.setUpdateAt(rs.getObject(6,LocalDateTime.class));
						staff.setDeleteAt(rs.getObject(7,LocalDateTime.class));
						list.add(staff);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
		   
		return list;
	   }
	 public void insertStaff(Staff staff) {
		   try(Connection con = SqlConnection.connect();
					CallableStatement call = con.prepareCall("{call insertStaff(?,?,?)}");)
				{
						call.setString(1,staff.getName());
						call.setInt(2,staff.getLevel());
						call.setString(3,staff.getPassword());
						call.executeUpdate();
				} catch (Exception e) {
					e.printStackTrace();
				}
	   }
	 
	 public void updateStaff(Staff staff) {
		 
		 try (Connection con = SqlConnection.connect();
				 CallableStatement call = con.prepareCall("{call updateStaff(?,?,?,?)}");){
				call.setString(1,staff.getName());
				call.setInt(2, staff.getLevel());
				call.setString(3,staff.getPassword());
				call.setInt(4,staff.getId());
				
				call.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
	 }
	 
	 public void deleteStaff(List<Integer> listId) {
		 
		 try (Connection con = SqlConnection.connect();
				 CallableStatement call = con.prepareCall("{call deleteStaff(?)}");){
			 listId.forEach(id->{
				try {
					call.setInt(1,id);
					call.addBatch();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 });
			 call.executeBatch();
			} catch (Exception e) {
				e.printStackTrace();
			}
	 }
}
