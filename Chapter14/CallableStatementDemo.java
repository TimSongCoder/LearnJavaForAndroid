import java.io.IOException;
import java.io.FileNotFoundException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;

public class CallableStatementDemo{
	
	private static final String URL_JAVADB = "jdbc:derby:employee4;create=true";
	
	public static void main(String[] args){
		Connection con = null;
		try{
			con = DriverManager.getConnection(URL_JAVADB);
			Statement stmt = null;
			try{
				stmt = con.createStatement();
				String sql = "CREATE PROCEDURE FIRE(IN ID INTEGER)" + 
							"   PARAMETER STYLE JAVA" +
							"   LANGUAGE JAVA" +
							"   DYNAMIC RESULT SETS 0" +
							"   EXTERNAL NAME 'CallableStatementDemo.fire'";
				stmt.executeUpdate(sql);  // store the procedure, not supported by sqlite.
				sql = "CREATE TABLE EMPLOYEES(ID INTEGER, NAME VARCHAR(30), FIRED BOOLEAN)"; // STATIC SQL STATEMENT
				stmt.executeUpdate(sql);
				PreparedStatement pstmt = null;
				CallableStatement cstmt = null;
				try{
					pstmt = con.prepareStatement("INSERT INTO EMPLOYEES VALUES(?, ?, false)");
					String[] names = {"Jhon Doe", "Sally Smith"};
					for(int i=0; i<names.length;i++){
						pstmt.setInt(1, i+1);
						pstmt.setString(2, names[i]);
						pstmt.executeUpdate();
					}
					// output the tabular content
					sql = "SELECT * FROM EMPLOYEES";
					ResultSet resultSet = stmt.executeQuery(sql);
					while(resultSet.next()){
						System.out.println(resultSet.getInt("ID") + ", " + resultSet.getString("NAME") + ", " + resultSet.getBoolean("FIRED"));
					}
					// FIRE ONE EMPLOYEE
					cstmt = con.prepareCall("{call FIRE(?)}");  // CALL THE STORED PROCEDURE FIRE, WILL INVOKE THE SPECIFIED PUBLIC STATIC METHOD
					cstmt.setInt(1, 2); // set the parameter value to fire No.2 employee4
					cstmt.execute();
					//output the tabular content again.
					sql = "SELECT * FROM EMPLOYEES";
					resultSet = stmt.executeQuery(sql);
					while(resultSet.next()){
						System.out.println(resultSet.getInt("ID") + ", " + resultSet.getString("NAME") + ", " + resultSet.getBoolean("FIRED"));
					}
					// drop the table
					sql = "DROP TABLE EMPLOYEES";
					stmt.executeUpdate(sql);
					// drop procedure
					sql = "DROP PROCEDURE FIRE";
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
	
	public static void fire(int id) throws SQLException{
		Connection con = DriverManager.getConnection("jdbc:default:connection");
		String sql = "UPDATE EMPLOYEES SET FIRED=TRUE WHERE ID=" + id;
		Statement stmt = null;
		try{
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}finally{
			if(stmt != null){
				try{
					stmt.close();
				}catch(SQLException sqle){
					sqle.printStackTrace();
				}
			}
		}
	}
}