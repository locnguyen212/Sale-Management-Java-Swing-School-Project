package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import common.SqlConnection;
import entity.Image;

public class ImageDao {
	public List<Image> getDb(){
		List<Image> list = new ArrayList<Image>();
		try (
			Connection con = DriverManager.getConnection(SqlConnection.getConnectionString());
			PreparedStatement ps = con.prepareStatement("SELECT * FROM image");
			ResultSet result = ps.executeQuery();
		) {
			while(result.next()) {
				if(result.getTimestamp("deleted_at")==null) {
					list.add(new Image(result.getInt("id"), 
							result.getInt("product_name_id"), 
							result.getString("name"), 
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
	
	public void insert(Image pro) {
		try (
			Connection con = DriverManager.getConnection(SqlConnection.getConnectionString());
			CallableStatement ps = con.prepareCall("{call insertImage(?, ?)}");
		) {
			ps.setInt(1, pro.getProductNameId());
			ps.setString(2, pro.getName());
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(Image pro) {
		try (
			Connection con = DriverManager.getConnection(SqlConnection.getConnectionString());
			CallableStatement ps = con.prepareCall("{call updateImage(?, ?, ?)}");
		) {
			ps.setInt(1, pro.getProductNameId());
			ps.setString(2, pro.getName());
			ps.setInt(3, pro.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(String id, int productNameId) {	
		try (
			Connection con = DriverManager.getConnection(SqlConnection.getConnectionString());
			PreparedStatement ps = con.prepareStatement("UPDATE [image] SET product_name_id=?, updated_at=GETDATE() WHERE id IN (" + id + ")");
		) {
			ps.setInt(1, productNameId);
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
			PreparedStatement ps = con.prepareStatement("UPDATE image SET deleted_at=GETDATE() WHERE id IN("+ id +")");
		) {
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
