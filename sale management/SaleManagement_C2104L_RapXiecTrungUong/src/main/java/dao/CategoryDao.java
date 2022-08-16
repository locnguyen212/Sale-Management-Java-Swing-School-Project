package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import common.SqlConnection;
import entity.Category;
import entity.Order;


public class CategoryDao {
	
	 public List<Category> getlistCate(){
		   List<Category> list = new ArrayList<Category>();
			try(Connection con = SqlConnection.connect();
					CallableStatement call = con.prepareCall("{call slectCategory}");
					ResultSet rs = call.executeQuery();)
				{
					while(rs.next()){
						Category cate = new Category();
						cate.setId(rs.getInt("id"));
						cate.setName(rs.getString("name"));
						list.add(cate);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
		   
		return list;
	   }
	 
	 
	 public void insertCateglory(Category categlory) {
		   try(
		   		   Connection con = SqlConnection.connect();
				   CallableStatement call = con.prepareCall("{call insertCategory(?)}");)
				{
			   call.setString(1,categlory.getName());
			 
			   call.executeUpdate();
				} catch (Exception e) {
					e.printStackTrace();
		        }
	   }
	 
	 public void updateCateglory(Category categlory) {
		   try(
		   		   Connection con = SqlConnection.connect();
				   CallableStatement call = con.prepareCall("{call updateCategory(?,?)}");)
				{
			   call.setString(1,categlory.getName());
			   call.setInt(2, categlory.getId());
			   call.executeUpdate();
				} catch (Exception e) {
					e.printStackTrace();
		        }
	   }
	 


	public void deleteCat(List<Integer> list) {
		   try(
		   		   Connection con = SqlConnection.connect();
				   CallableStatement call = con.prepareCall("{call deleteCategory(?)}");)
				{
			   list.forEach(id->{
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
