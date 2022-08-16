package common;

import java.sql.Connection;
import java.sql.DriverManager;

public class SqlConnection {
	private static String url = "jdbc:sqlserver://";
	private static String serverName = "THIENLOC";
	private static String port = "1433";
	private static String DbName = "saleManagementDb";
	private static String user = "sa1";
	private static String password = "123456";
	
	public static String getConnectionString() {
		return url + serverName + ":" + port + ";databaseName=" + DbName + ";user=" + user + ";password=" + password + ";encrypt=true;trustServerCertificate=true";
	}
	
	public static Connection connect() {
		Connection con = null;
		try{
			con = DriverManager.getConnection(getConnectionString());
		} catch (Exception e) {
			e.printStackTrace();
			con = null;
		}
		return con;
	}
}
