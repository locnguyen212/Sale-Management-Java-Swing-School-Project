package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import common.SqlConnection;
import entity.ProductProperties;

public class ProductPropertiesDao {
	public List<ProductProperties> getDb(){
		List<ProductProperties> list = new ArrayList();
		try (
			Connection con = DriverManager.getConnection(SqlConnection.getConnectionString());
			PreparedStatement ps = con.prepareStatement("SELECT * FROM product_properties");
			ResultSet result = ps.executeQuery();
		) {
			while(result.next()) {
				if(result.getTimestamp("deleted_at")==null) {
					list.add(new ProductProperties(result.getInt("id"), 
						result.getInt("product_id"), 
						result.getInt("properties_id"), 
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
	
	public void insert(ProductProperties pro) {
		try (
			Connection con = DriverManager.getConnection(SqlConnection.getConnectionString());
			CallableStatement ps = con.prepareCall("{call insertProductProperties(?, ?)}");
		) {
			ps.setInt(1, pro.getProductId());
			ps.setInt(2, pro.getPropertiesId());
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(ProductProperties pro) {
		try (
			Connection con = DriverManager.getConnection(SqlConnection.getConnectionString());
			CallableStatement ps = con.prepareCall("{call updateProductProperties(?, ?, ?)}");
		) {
			ps.setInt(1, pro.getProductId());
			ps.setInt(2, pro.getPropertiesId());
			ps.setInt(3, pro.getId());
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
			PreparedStatement ps = con.prepareStatement("UPDATE product_properties SET deleted_at=GETDATE() WHERE id IN("+ id +")");
		) {
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
