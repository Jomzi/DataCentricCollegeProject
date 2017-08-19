package ie.gmit.sw.collegesdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CollegeDAO {
	private DataSource mysqlDS;
	
	// Makes a connection to the DB
	public CollegeDAO() throws Exception {
		Context context = new InitialContext();
		String db = "java:comp/env/jdbc/collegedb";
		mysqlDS = (DataSource) context.lookup(db);
	}
	
	// Get colleges queries every row from college table in CollegeDB and returns it as an array list 
	public ArrayList<College> getColleges() throws Exception {
		ArrayList<College> colleges = new ArrayList<College>();
		
		Connection conn = mysqlDS.getConnection();
		PreparedStatement query = conn.prepareStatement("SELECT * " +
					 									"FROM college");
		
		ResultSet rs = query.executeQuery();
		
		while (rs.next()) {
			String id = rs.getString("colID");
			String name = rs.getString("colName");
			String addr = rs.getString("colAddr");
			colleges.add(new College(id, name, addr));
		}
		
		query.close();
		rs.close();
		conn.close();
		
		return colleges;
	}
	
	// Add college simply inserts a row into the college table
	public void addCollege(College college) throws Exception {
		Connection conn = mysqlDS.getConnection();
		PreparedStatement myStmt = conn.prepareStatement("INSERT INTO college " +
					 									"VALUES (?,?,?)");
		
		myStmt.setString(1, college.getColID());
		myStmt.setString(2, college.getColName());
		myStmt.setString(3, college.getColAddr());
		myStmt.executeUpdate();
		
		myStmt.close();
		conn.close();
	}
	
	// updateCollege executes an update query on a specified row in the college table
	public void updateCollege(College college) throws Exception {
		Connection conn = mysqlDS.getConnection();
		PreparedStatement myStmt = conn.prepareStatement("UPDATE college " +
					 									"SET colName=?, " +
					 									"colAddr=? " +
					 									"WHERE colID=?;");
		
		myStmt.setString(1, college.getColName());
		myStmt.setString(2, college.getColAddr());
		myStmt.setString(3, college.getColID());
		myStmt.executeUpdate();
		
		myStmt.close();
		conn.close();
	}
	
	// deleteCollege removes a row from the table if no constraints are returned from mysql
	public void deleteCollege(College college) throws Exception {
		Connection conn = mysqlDS.getConnection();
		PreparedStatement myStmt = conn.prepareStatement("DELETE FROM college " +
					 									"WHERE colID=?");
		
		myStmt.setString(1, college.getColID());
		myStmt.executeUpdate();
		
		myStmt.close();
		conn.close();
	}
}
