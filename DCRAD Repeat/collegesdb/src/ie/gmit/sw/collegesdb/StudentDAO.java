package ie.gmit.sw.collegesdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class StudentDAO{
	private DataSource mysqlDS;

	public StudentDAO() throws Exception {
		Context context = new InitialContext();
		String db = "java:comp/env/jdbc/collegedb";
		mysqlDS = (DataSource) context.lookup(db);
	}
	
	// The getStudents method queries all three tables in order for the All Details to work seamlessly
	// and therefore has to remove the duplicate entries that arise from doing this using a for loop and if statement seen at the bottom of the while(rs.next())
	public ArrayList<Student> getStudents() throws Exception {
		ArrayList<Student> students = new ArrayList<Student>();
		
		Connection conn = mysqlDS.getConnection();
		PreparedStatement query = conn.prepareStatement("SELECT * FROM student s INNER JOIN course c ON s.cID = c.cID INNER JOIN college col ON s.colID = col.colID;");
		
		ResultSet rs = query.executeQuery();
		
		while (rs.next()) {
			boolean duplicate = false;
			
			String sID = rs.getString("sID");
			String colID = rs.getString("colID");
			String colName = rs.getString("colName");
			String colAddr = rs.getString("colAddr");
			College college = new College(colID, colName, colAddr);
			String cID = rs.getString("cID");
			String cName = rs.getString("cName");
			Course course = new Course(college, cID, cName);
			String firstName = rs.getString("first_name");
			String lastName = rs.getString("last_name");
			String addr = rs.getString("address");
			Student s = new Student(sID, colID, course, firstName, lastName, addr);
			
			for(int i = 0; i < students.size(); i++){
				if(students.get(i).getsID().equals(s.getsID())){
					duplicate = true;
				}
			}
			
			if(!duplicate){
				students.add(s);
			}
		}
		
		query.close();
		rs.close();
		conn.close();
		
		return students;
	}
	
	// The searchStudents method queries every column of every table in the db and then filters out rows using multiple WHERE clauses (if they were filled out in the search_students page)
	public ArrayList<Student> searchStudents(Search search) throws Exception {
		ArrayList<Student> students = new ArrayList<Student>();
		
		Connection conn = mysqlDS.getConnection();
		
		String query = "SELECT * FROM college col " +
						"INNER JOIN course c " +
						"ON col.colID = c.colID " +
						"INNER JOIN student s " +
						"ON s.cID = c.cID " +
						"WHERE ";
		
		if (search.getFirst_name() != "") {
			query += "s.first_name LIKE '%" + search.getFirst_name() + "%' AND ";
		}
		
		if (search.getLast_name() != "") {
			query += "s.last_name LIKE '%" + search.getLast_name() + "%' AND ";
		}
		
		if (search.getcID() != "") {
			query += "s.cID LIKE '%" + search.getcID() + "%' AND ";
		}
		
		if (search.getColID() != "") {
			query += "s.colID LIKE '%" + search.getColID() + "%' AND ";
		}
		
		if (search.getColName() != "") {
			query += "col.colName LIKE '%" + search.getColName() + "%' ";
		}
		
		// This if is a failsafe to keep the query syntax correct if an AND was left at the end of it without a follow up
		if(query.endsWith("AND ")){
			query = query.substring(0, query.length() - 4);
		}
		
		// Ordering results by student id here
		query += " ORDER BY s.sid;";

		PreparedStatement myStmt = conn.prepareStatement(query);

		ResultSet rs = myStmt.executeQuery();
		
		while (rs.next()) {
			boolean duplicate = false;
			
			String sID = rs.getString("sID");
			String colID = rs.getString("colID");
			String colName = rs.getString("colName");
			String colAddr = rs.getString("colAddr");
			College college = new College(colID, colName, colAddr);
			String cID = rs.getString("cID");
			String cName = rs.getString("cName");
			Course course = new Course(college, cID, cName);
			String firstName = rs.getString("first_name");
			String lastName = rs.getString("last_name");
			String addr = rs.getString("address");
			Student s = new Student(sID, colID, course, firstName, lastName, addr);
			
			for(int i = 0; i < students.size(); i++){
				if(students.get(i).getsID().equals(s.getsID())){
					duplicate = true;
				}
			}
			
			if(!duplicate){
				students.add(s);
			}
		}
		
		myStmt.close();
		rs.close();
		conn.close();
		
		return students;
	}
	
	// Simply inserts a new row into the student table (granted the cID and colID are existent and correct)
	public void addStudent(Student student) throws Exception {
		Connection conn = mysqlDS.getConnection();
		PreparedStatement myStmt = conn.prepareStatement("INSERT INTO student " +
					 									"VALUES (?,?,?,?,?,?)");
		
		myStmt.setString(1, student.getsID());
		myStmt.setString(2, student.getCourse().getCollege().getColID());
		myStmt.setString(3, student.getCourse().getcID());
		myStmt.setString(4, student.getFirst_name());
		myStmt.setString(5, student.getLast_name());
		myStmt.setString(6, student.getAddress());
		myStmt.executeUpdate();
		
		myStmt.close();
		conn.close();
	}
}