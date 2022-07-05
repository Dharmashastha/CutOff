package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public enum ConnectionUtility {

	CONNECTION;

	private final static String DBURL = "jdbc:mysql://localhost:3306/zoho";

	private final static String USER = "root";

	private final static String PASSWORD = "Root@123";
	
	private final static String loadDriver = "com.mysql.cj.jdbc.Driver";
	
	private static Connection connect = null;
	
	public static Connection getConnection() throws CustomException, ClassNotFoundException{
		try {
			if (connect == null) {
				
				HelperUtility.checkString(DBURL);
				HelperUtility.checkString(USER);
				HelperUtility.checkString(PASSWORD);
				HelperUtility.checkString(loadDriver);
				
				Class.forName(loadDriver);
				
				connect = DriverManager.getConnection(DBURL, USER, PASSWORD);
				
				System.out.println("Connected.");
				return connect;
			} else {
				return connect;
			}
		} catch (SQLException e) {
			 e.printStackTrace();
			throw new CustomException("Connection Error");
		}

	}
	
	public static void closeConnection()
	{
		if(connect != null)
		{
			try {
				connect.close();
			} catch (SQLException e) {}
		}
	}
}
