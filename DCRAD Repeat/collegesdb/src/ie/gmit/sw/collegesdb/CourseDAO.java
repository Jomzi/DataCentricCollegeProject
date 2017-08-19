package ie.gmit.sw.collegesdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CourseDAO{
	private DataSource mysqlDS;

	public CourseDAO() throws Exception {
		Context context = new InitialContext();
		String db = "java:comp/env/jdbc/collegedb";
		mysqlDS = (DataSource) context.lookup(db);
	}
	
	// See CollegeDAO to see what these methods do, as they are almost identical in how they work
	
	public ArrayList<Course> getCourses() throws Exception {
		ArrayList<Course> courses = new ArrayList<Course>();
		
		Connection conn = mysqlDS.getConnection();
		PreparedStatement query = conn.prepareStatement("SELECT * " +
					 									"FROM course c INNER JOIN college col ON c.colID = col.colID;");
		
		ResultSet rs = query.executeQuery();
		
		while (rs.next()) {
			String colID = rs.getString("colID");
			String colName = rs.getString("colName");
			String colAddr = rs.getString("colAddr");
			College college = new College(colID, colName, colAddr);
			String cID = rs.getString("cID");
			String cName = rs.getString("cName");
			courses.add(new Course(college, cID, cName));
		}
		
		query.close();
		rs.close();
		conn.close();
		
		return courses;
	}
	
	public void addCourse(Course course) throws Exception {
		Connection conn = mysqlDS.getConnection();
		PreparedStatement myStmt = conn.prepareStatement("INSERT INTO course " +
					 									"VALUES (?,?,?)");
		
		myStmt.setString(1, course.getCollege().getColID());
		myStmt.setString(2, course.getcID());
		myStmt.setString(3, course.getcName());
		myStmt.executeUpdate();
		
		myStmt.close();
		conn.close();
	}
}