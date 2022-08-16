package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import common.SqlConnection;

import entity.ProductName;

public class ProductNameDao {
	public List<ProductName> getDb(){
		List<ProductName> list = new ArrayList<ProductName>();
		try (
			Connection con = DriverManager.getConnection(SqlConnection.getConnectionString());
			PreparedStatement ps = con.prepareStatement("SELECT * FROM product_name");
			ResultSet result = ps.executeQuery();
		) {
			while(result.next()) {
				if(result.getTimestamp("deleted_at")==null) {
					list.add(new ProductName(result.getInt("id"), 
							result.getString("name"), 
							result.getTimestamp("created_at") != null? result.getTimestamp("created_at").toLocalDateTime() : null, 
							result.getTimestamp("updated_at") != null? result.getTimestamp("updated_at").toLocalDateTime() : null, 
							result.getTimestamp("deleted_at") != null? result.getTimestamp("deleted_at").toLocalDateTime() : null));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void insert(ProductName pro) {
		try (
			Connection con = DriverManager.getConnection(SqlConnection.getConnectionString());
			CallableStatement ps = con.prepareCall("{call insertProductName(?)}");
		) {
			ps.setString(1, pro.getName());
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(ProductName pro) {
		try (
			Connection con = DriverManager.getConnection(SqlConnection.getConnectionString());
			CallableStatement ps = con.prepareCall("{call updateProductName(?, ?)}");
		) {
			ps.setString(1, pro.getName());
			ps.setInt(2, pro.getId());
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
	
	// categlory func with name 
	
	public CallableStatement createPrepareStatement(Connection con, int num,String SQL) throws Exception {
		  CallableStatement cs = con.prepareCall(SQL);
		  	cs.setInt(1, num);
		  	return cs;		
}
	public List<ProductName> selectProductNameWithIdCategory(int id) {
		List<ProductName> list = new ArrayList<ProductName>() ;
		try (Connection con = SqlConnection.connect();
				CallableStatement call = createPrepareStatement(con, id,"{call selectProductNameWithIdCategory(?)}");
				 ResultSet rs = call.executeQuery();
					){				
		 		 while(rs.next()) {
					 ProductName prn = new ProductName();
					 prn.setId(rs.getInt("id"));
					 prn.setName(rs.getString("name"));
					 list.add(prn);
				 }
			}catch(Exception e) {
				e.printStackTrace();
			}
		return  list;
	}
	

}
