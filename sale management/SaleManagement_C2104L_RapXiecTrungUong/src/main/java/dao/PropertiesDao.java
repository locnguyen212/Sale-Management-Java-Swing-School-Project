package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import common.SqlConnection;
import entity.Properties;

public class PropertiesDao {
	public List<Properties> getDb(){
		List<Properties> list = new ArrayList();
		try (
			Connection con = DriverManager.getConnection(SqlConnection.getConnectionString());
			PreparedStatement ps = con.prepareStatement("SELECT * FROM [properties]");
			ResultSet result = ps.executeQuery();
		) {
			while(result.next()) {
				if(result.getTimestamp("deleted_at")==null) {
					list.add(new Properties(result.getInt("id"), 
						result.getString("name"), 
						result.getInt("parent_id"), 
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
	
	public void insert(Properties pro) {
		try (
			Connection con = DriverManager.getConnection(SqlConnection.getConnectionString());
			CallableStatement ps = con.prepareCall("{call insertProperties(?, ?)}");
		) {
			ps.setString(1, pro.getName());
			ps.setInt(2, pro.getParentId());
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(Properties pro) {
		try (
			Connection con = DriverManager.getConnection(SqlConnection.getConnectionString());
			CallableStatement ps = con.prepareCall("{call updateProperties(?, ?, ?)}");
		) {
			ps.setString(1, pro.getName());
			ps.setInt(2, pro.getParentId());
			ps.setInt(3, pro.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(String id, int parentId) {	
		try (
			Connection con = DriverManager.getConnection(SqlConnection.getConnectionString());
			PreparedStatement ps = con.prepareStatement("UPDATE [properties] SET parent_id=?, updated_at=GETDATE() WHERE id IN (" + id + ")");
		) {
			ps.setInt(1, parentId);
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
			PreparedStatement ps = con.prepareStatement("UPDATE [properties] SET deleted_at=GETDATE() WHERE id IN (" + id + ")");
		) {
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
