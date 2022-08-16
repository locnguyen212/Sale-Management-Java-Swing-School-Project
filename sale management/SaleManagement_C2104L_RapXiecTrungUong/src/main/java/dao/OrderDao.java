package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import common.SqlConnection;
import entity.Order;

public class OrderDao {

public List<Order> getListOrder(){
	   List<Order> list = new ArrayList<Order>();
		try(Connection con = SqlConnection.connect();
				CallableStatement call = con.prepareCall("{call selectOrderWithNameStaff}");
				ResultSet rs = call.executeQuery();)
			{
				while(rs.next()){
					Order ord = new Order();
					ord.setId(rs.getInt("id"));
					ord.setNameStaff(rs.getString("name"));
					ord.setOrderNumber(rs.getInt("order_number"));
					ord.setCreatedAt(rs.getObject(4,LocalDateTime.class));
					ord.setUpdateAt(rs.getObject(5,LocalDateTime.class));
					ord.setTolTalCash(rs.getString("total"));
					list.add(ord);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
	   
	return list;
   }
   
   public void insertOrder(Order order) {
	   try(
	   		   Connection con = SqlConnection.connect();
			   CallableStatement call = con.prepareCall("{call insertOrder(?,?)}");)
			{
		   call.setInt(1,order.getStaffId());
		   call.setInt(2,order.getOrderNumber());
		   call.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
	        }
   }
   public int getNewlyInsertedId() {
	   int id = 0 ;
	   try(
	   		Connection con = SqlConnection.connect();
			   CallableStatement call = con.prepareCall("{call getLastId}");
			   ResultSet rs = call.executeQuery();)
			{	
		   while(rs.next()) {
		   	    id = rs.getInt("lastId");
		   }
		   	} catch (Exception e) {
				e.printStackTrace();
	        }
	   return id;
   }
   public void deleteOrder(List<Integer> list) {
	   try(
	   		   Connection con = SqlConnection.connect();
			   CallableStatement call = con.prepareCall("{call deleteOrder(?)}");)
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
   public List getListOrderNumber(){
	   List list = new ArrayList();
		try(Connection con = SqlConnection.connect();
				CallableStatement call = con.prepareCall("{call SelectOrderNumber}");
				ResultSet rs = call.executeQuery();)
			{
				while(rs.next()){
					list.add(rs.getInt("order_number"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
	   
	return list;
   }
}
