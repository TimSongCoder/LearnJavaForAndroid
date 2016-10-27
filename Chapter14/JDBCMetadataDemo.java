import java.io.IOException;
import java.io.FileNotFoundException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DatabaseMetaData;

public class JDBCMetadataDemo{
	
	private static final String URL_JAVADB = "jdbc:derby:employee2;create=true";
	private static final String URL_SQLITE = "jdbc:sqlite:employee2";
	private static final String TB_EMPLOYEES = "EMPLOYEES";
	
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
			// EXPLORES THE METADATA
			dump(con.getMetaData());
			Statement stmt = null;
			try{
				stmt = con.createStatement();
				String sql = null;
				if(!isTbExist(con, TB_EMPLOYEES)){
					sql = "CREATE TABLE EMPLOYEES(ID INTEGER, NAME VARCHAR(30))"; // STATIC SQL STATEMENT
					stmt.executeUpdate(sql);
					sql = "INSERT INTO EMPLOYEES VALUES(1, 'Jhon Doe')";
					stmt.executeUpdate(sql);
					sql = "INSERT INTO EMPLOYEES VALUES(2, 'Sally Smith')";
					stmt.executeUpdate(sql);
				}
				
				sql = "SELECT * FROM EMPLOYEES";
				ResultSet resultSet = stmt.executeQuery(sql);
				while(resultSet.next()){
					System.out.println(resultSet.getInt("ID") + ", " + resultSet.getString("NAME"));
				}
				// drop the table
				sql = "DROP TABLE EMPLOYEES";
				// stmt.executeUpdate(sql);
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
	
	static boolean isTbExist(Connection con, String tbName) throws SQLException{
		DatabaseMetaData dbmd = con.getMetaData();
		ResultSet resultSet = dbmd.getTables(null, "APP", tbName, null);
		return resultSet.next();
	}
	
	static void dump(DatabaseMetaData dbmd) throws SQLException{
		System.out.println("DB Major version = " + dbmd.getDatabaseMajorVersion());
		System.out.println("DB Minor version = " + dbmd.getDatabaseMinorVersion());
		System.out.println("DB Product = " + dbmd.getDatabaseProductName());
		System.out.println("Driver name = " + dbmd.getDriverName());
		System.out.println("Numeric function names from escape clause = " + dbmd.getNumericFunctions());
		System.out.println("String function names from escape clause = " + dbmd.getStringFunctions());
		System.out.println("System function names from escape clause = " + dbmd.getSystemFunctions());
		System.out.println("Time/Date function names from escape clause = " + dbmd.getTimeDateFunctions());
		System.out.println("Catalog term = " + dbmd.getCatalogTerm());
		System.out.println("Schema term = " + dbmd.getSchemaTerm());
		System.out.println();
		System.out.println("Catalogs");
		System.out.println("--------");
		ResultSet resultSet = dbmd.getCatalogs();
		while(resultSet.next()){
			System.out.println(resultSet.getString("TABLE_CAT"));
		}
		System.out.println();
		System.out.println("Schemas");
		System.out.println("--------");
		resultSet = dbmd.getSchemas();
		while(resultSet.next()){
			System.out.println(resultSet.getString("TABLE_SCHEM"));
		}
		System.out.println();
		System.out.println("Schema/Table");
		System.out.println("--------");
		resultSet = dbmd.getSchemas();
		while(resultSet.next()){
			String schema = resultSet.getString("TABLE_SCHEM");
			ResultSet rs = dbmd.getTables(null, schema, "%", null); // use % as wildcard
			while(rs.next()){
				System.out.println(schema + " " + rs.getString("TABLE_NAME"));
			}
		}
	}
}