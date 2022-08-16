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
import entity.Order;
import entity.OrderDetail;

public class OrderDetailDao {
	
	public String Report_Me() {
		String report =  null;
//		List<OrderDetail> list = new ArrayList<OrderDetail>();
		try(Connection con = SqlConnection.connect();
				CallableStatement call = con.prepareCall("{call reportSale}");
				ResultSet rs = call.executeQuery();)
			{
				while(rs.next()){
					report = rs.getString("DT");

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		

		return report;
}
	// sửa phần ông trước đi
public List<OrderDetail> selectOrderDetail() {
	List<OrderDetail> list = new ArrayList<OrderDetail>();
	try(Connection con = SqlConnection.connect();
			CallableStatement call = con.prepareCall("{call selectOrderDetailWithPrice}");
			ResultSet rs = call.executeQuery();)
		{
			while(rs.next()){
				OrderDetail od = new OrderDetail();
				od.setId(rs.getInt("id"));
				od.setOrderId(rs.getInt("order_id"));
				od.setProductId(rs.getInt("product_id"));
				od.setProductName(rs.getString("productName"));
				od.setQuantity(rs.getInt("quantity"));
				od.setPrice(rs.getInt("price"));
				od.setCreatedAt(rs.getObject(7,LocalDateTime.class));
				od.setUpdatedAt(rs.getObject(8,LocalDateTime.class));
				list.add(od);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	return list;
}

public void insertOrderDetail(List<OrderDetail> list) {
	   try(
	   		  Connection con = SqlConnection.connect();
			   CallableStatement call = con.prepareCall("{call insertOrderDetail(?,?,?)}");
			   )
			{
		   for(OrderDetail orderdetail : list) {
			   call.setInt(1,orderdetail.getOrderId());
			   call.setInt(2,orderdetail.getProductId());
			   call.setInt(3,orderdetail.getQuantity());
		       call.addBatch();
		   }
		   call.executeBatch();
			} catch (Exception e) {
				e.printStackTrace();
	        }
}

public void deleteOrderDetailWithOrderId(List<Integer> list) {	
		try(
	   		   Connection con = SqlConnection.connect();
			   CallableStatement call = con.prepareCall("{call deleteOrderDetailWithOrderId(?)}");
			   )
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
			}
		catch (Exception e) {
				e.printStackTrace();
			}
	}
public CallableStatement createPrepareStatement(Connection con, int num,String SQL) throws Exception {
	  CallableStatement cs = con.prepareCall(SQL);
	  	cs.setInt(1, num);
	  	return cs;		
}

public List<OrderDetail> slectOrderDetailWithOrderID(int id) {
	List<OrderDetail> list = new ArrayList<OrderDetail>();
 try(
 		   Connection con = SqlConnection.connect();
		   CallableStatement call = createPrepareStatement(con,id,"{call selectOrderDetailWithOrderId(?)}");
			ResultSet rs = call.executeQuery();)
		{
		   while(rs.next()){
			   OrderDetail od = new OrderDetail();
				od.setId(rs.getInt("id"));
				od.setOrderId(rs.getInt("order_id"));
				od.setProductId(rs.getInt("product_id"));
				od.setProductName(rs.getString("productName"));
				od.setQuantity(rs.getInt("quantity"));
				od.setPrice(rs.getInt("price"));
	
				od.setCreatedAt(rs.getObject(7,LocalDateTime.class));;
				od.setUpdatedAt(rs.getObject(8,LocalDateTime.class));
				list.add(od);
			}
		} catch (Exception e) {
			e.printStackTrace();
      }
return list;
}

public void updateOrderDetail(OrderDetail od) {
	   try(
		   		  Connection con = SqlConnection.connect();
				   CallableStatement call = con.prepareCall("{call updateOrderDetail(?,?,?,?)}");
				   )
				{
				   call.setInt(1,od.getOrderId());
				   call.setInt(2,od.getProductId());
				   call.setInt(3,od.getQuantity());
				   call.setInt(4, od.getId());
			   call.executeUpdate();
				} catch (Exception e) {
					e.printStackTrace();
				}
	}
public void deleteOrderDetail(List<Integer> list) {
	   try(
	   		   Connection con = SqlConnection.connect();
			   CallableStatement call = con.prepareCall("{call deleteOrderDetail(?)}");)
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
public void InsertQuantitySoldUpdate(int quantity,int id) {
	try(
	   		   Connection con = SqlConnection.connect();
			   CallableStatement call = con.prepareCall("{call InsertQuantitySoldUpdate(?,?)}");)
			{
		  	call.setInt(1, quantity);
			call.setInt(2,id);
			call.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
	        }
}



}
