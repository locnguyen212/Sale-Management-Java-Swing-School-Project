package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import common.SqlConnection;
import entity.OrderDetail;
import entity.Product;

public class ProductDao {

	
	public List<Product> getDb(){
		List<Product> list = new ArrayList<Product>();
		try (
			Connection con = DriverManager.getConnection(SqlConnection.getConnectionString());
			PreparedStatement ps = con.prepareStatement("SELECT * FROM product");
			ResultSet result = ps.executeQuery();
		) {
			while(result.next()) {
				if(result.getTimestamp("deleted_at")==null) {
					list.add(new Product(result.getInt("id"), 
						result.getInt("category_id"), 
						result.getInt("product_name_id"), 
						result.getInt("quantity"), 
						result.getInt("quantity_sold"), 
						result.getBoolean("is_available"), 
						result.getInt("buy_price"), 
						result.getInt("price"), 
						result.getString("information"), 
						result.getTimestamp("created_at")!=null? result.getTimestamp("created_at").toLocalDateTime() : null, 
						result.getTimestamp("updated_at")!=null? result.getTimestamp("updated_at").toLocalDateTime() : null, 
						result.getTimestamp("deleted_at")!=null? result.getTimestamp("deleted_at").toLocalDateTime() : null));
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Product> getDbSearch(String ids, int count){
		List<Product> list = new ArrayList<Product>();
		String sql = "SELECT * "
				+ "FROM product p "
				+ "JOIN (SELECT product_id, COUNT(product_id) AS 'count' "
				+ "FROM product_properties "
				+ "WHERE properties_id IN ("+ids+") "
				+ "GROUP BY product_id) tempDb "
				+ "ON tempDb.product_id = p.id AND [count] = " + count;
		try (
			Connection con = DriverManager.getConnection(SqlConnection.getConnectionString());
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet result = ps.executeQuery();
		) {
			while(result.next()) {
				if(result.getTimestamp("deleted_at")==null) {
					list.add(new Product(result.getInt("id"), 
						result.getInt("category_id"), 
						result.getInt("product_name_id"), 
						result.getInt("quantity"), 
						result.getInt("quantity_sold"), 
						result.getBoolean("is_available"), 
						result.getInt("buy_price"), 
						result.getInt("price"), 
						result.getString("information"), 
						result.getTimestamp("created_at")!=null? result.getTimestamp("created_at").toLocalDateTime() : null, 
						result.getTimestamp("updated_at")!=null? result.getTimestamp("updated_at").toLocalDateTime() : null, 
						result.getTimestamp("deleted_at")!=null? result.getTimestamp("deleted_at").toLocalDateTime() : null));
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void insert(Product pro) {
		try (
			Connection con = DriverManager.getConnection(SqlConnection.getConnectionString());
			CallableStatement ps = con.prepareCall("{call insertProduct(? , ?, ?, ?, ?, ?, ?)}");
		) {
			ps.setInt(1, pro.getCategoryId());
			ps.setInt(2, pro.getProductNameId());
			ps.setInt(3, pro.getQuantity());
			ps.setBoolean(4, pro.isAvailable());
			ps.setInt(5, pro.getBuyPrice());
			ps.setInt(6, pro.getPrice());
			ps.setString(7, pro.getInformation());
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(Product pro) {
		try (
			Connection con = DriverManager.getConnection(SqlConnection.getConnectionString());
			CallableStatement ps = con.prepareCall("{call updateProduct(?, ?, ?, ?, ?, ?, ?, ?, ?)}");
		) {
			ps.setInt(1, pro.getCategoryId());
			ps.setInt(2, pro.getProductNameId());
			ps.setInt(3, pro.getQuantity());
			ps.setInt(4, pro.getQuantitySold());
			ps.setBoolean(5, pro.isAvailable());
			ps.setInt(6, pro.getBuyPrice());
			ps.setInt(7, pro.getPrice());
			ps.setString(8, pro.getInformation());
			ps.setInt(9, pro.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(String categoryId, String productNameId, String isAvailable, String ids) {
		String condition = "";
		String sql = "UPDATE product SET ";
		
		if(categoryId!=null) {
			condition += "category_id=" + categoryId + ",";;
		}
		
		if(productNameId!=null) {
			condition += "product_name_id=" + productNameId + ",";
		}
		
		if(isAvailable!=null) {
			condition += "is_available=" + (isAvailable.equals("True")? "1" : "0") + ",";
		}
		
		sql = sql + condition +" updated_at=GETDATE() WHERE id IN ("+ids+")";
		

		
		try (
			Connection con = DriverManager.getConnection(SqlConnection.getConnectionString());
				PreparedStatement ps = con.prepareStatement(sql);
		) {
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(int quantitySold, int id) {
		try (
			Connection con = DriverManager.getConnection(SqlConnection.getConnectionString());
			CallableStatement ps = con.prepareCall("{call updateProductQuantitySold(quantity_sold, id)}");
		) {
			ps.setInt(1, quantitySold);
			ps.setInt(2, id);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delete(String dbName, int id) {
		try (
			Connection con = DriverManager.getConnection(SqlConnection.getConnectionString());
			CallableStatement ps = con.prepareCall("{call deleteStatement(?, ?)}");
		) {
			ps.setString(1, dbName);
			ps.setInt(2, id);		
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delete(String id) {
		try (
			Connection con = DriverManager.getConnection(SqlConnection.getConnectionString());
			PreparedStatement ps = con.prepareStatement("UPDATE product SET deleted_at=GETDATE() WHERE id IN("+ id +")");
		) {
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String TopOneProduct() {
		String report =  null;
		try(Connection con = SqlConnection.connect();
				CallableStatement call = con.prepareCall("{call TopOneProduct}");
				ResultSet rs = call.executeQuery();)
			{
				while(rs.next()){
					report = rs.getString("report");

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return report;
	}

	
	
	
}
