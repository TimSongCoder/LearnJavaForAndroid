import java.io.IOException;
import java.io.FileNotFoundException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class PreparedStatementDemo{
	
	private static final String URL_JAVADB = "jdbc:derby:employee3;create=true";
	private static final String URL_SQLITE = "jdbc:sqlite:employee3";
	
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
			Statement stmt = null;
			try{
				stmt = con.createStatement();
				String sql = "CREATE TABLE EMPLOYEES(ID INTEGER, NAME VARCHAR(30))"; // STATIC SQL STATEMENT
				stmt.executeUpdate(sql);
				PreparedStatement pstmt = null;
				try{
					pstmt = con.prepareStatement("INSERT INTO EMPLOYEES VALUES(?, ?)");
					String[] names = {"Jhon Doe", "Sally Smith"};
					for(int i=0; i<names.length;i++){
						pstmt.setInt(1, i+1);
						pstmt.setString(2, names[i]);
						pstmt.executeUpdate();
					}
					sql = "SELECT * FROM EMPLOYEES";
					ResultSet resultSet = stmt.executeQuery(sql);
					while(resultSet.next()){
						System.out.println(resultSet.getInt("ID") + ", " + resultSet.getString("NAME"));
					}
					// drop the table
					sql = "DROP TABLE EMPLOYEES";
					stmt.executeUpdate(sql);
				}catch(SQLException sqle){
					while(sqle!=null){
						System.err.println("SQL error : " + sqle.getMessage());
						System.err.println("SQL state : " + sqle.getSQLState());
						System.err.println("Error code : " + sqle.getErrorCode());
						System.err.println("Cause : " + sqle.getCause());
						sqle = sqle.getNextException();
					}
				}finally{
					if(pstmt!=null){
						try{
							pstmt.close();
						}catch(SQLException sqle){
							sqle.printStackTrace();
						}
					}
				}
			}catch(SQLException sqle){
				while(sqle!=null){
					System.err.println("SQL error : " + sqle.getMessage());
					System.err.println("SQL state : " + sqle.getSQLState());
					System.err.println("Error code : " + sqle.getErrorCode());
					System.err.println("Cause : " + sqle.getCause());
					sqle = sqle.getNextException();
				}
			}finally{
				if(stmt != null){
					try{
						stmt.close();
					}catch(SQLException sqle){
						sqle.printStackTrace();
					}
				}
			}
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