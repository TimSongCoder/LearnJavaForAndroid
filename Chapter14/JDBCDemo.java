import java.io.IOException;
import java.io.FileNotFoundException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCDemo{
	
	private static final String URL_JAVADB = "jdbc:derby:employee;create=true";
	private static final String URL_SQLITE = "jdbc:sqlite:employee";
	
	public static void main(String[] args){
		if(args.length !=1){
			System.err.println("usage 1: java JDBCDemo javadb");
			System.err.println("usage 2: java JDBCDemo sqlite");
			return;
		}
		String url = null;
		switch(args[0]){
			case "javadb":
				url = URL_JAVADB;
				break;
			case "sqlite":
				url = URL_SQLITE;
				break;
			default:
				System.err.println("invalid command-line argument");
				return;
		}
		Connection con = null;
		try{
			if("sqlite".equals(args[0])){
				Class.forName("org.sqlite.JDBC"); // load the Driver class file explicitly, regarding non-JAVADB
			}
			con = DriverManager.getConnection(url);
			// ... perform useful work
			// simulates a JDBC method throwing SQLException
			throw new SQLException("Unable to access database table", new IOException("File I/O problem")); // specify an IOException as cause.
		}catch(ClassNotFoundException cnfe){
			cnfe.printStackTrace();
		}catch(SQLException sqle){
			while(sqle!=null){
				System.err.println("SQL error : " + sqle.getMessage());
				System.err.println("SQL state : " + sqle.getSQLState());
				System.err.println("Error code : " + sqle.getErrorCode());
				System.err.println("Cause : " + sqle.getCause());
				sqle = sqle.getNextException();
			}
		}finally{
			if(con != null){
				try{
					con.close();
				}catch(SQLException sqle){
					sqle.printStackTrace();
				}
			}
		}
	}
}