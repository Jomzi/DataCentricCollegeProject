package ie.gmit.sw.collegesdb;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class StudentsController {
	private ArrayList<Student> students;
	private StudentDAO dao;
	
	public StudentsController(){
		try{
			dao = new StudentDAO();
		}catch (Exception e){
			System.out.println("Cannot connect to db");
		}
	}
	
	// getStudents is used to return the students member variable (specifically needed by search_details as we don't want to get all the students.
	public ArrayList<Student> getStudents() {
		return students;
	}
	
	// loadStudents operates just like the getCourses and getColleges
	public void loadStudents(){
		try {
			students = dao.getStudents();
		} catch (SQLException se) {
			FacesMessage message = new FacesMessage("ERROR: " + se.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Simlar to getUpdateCollege and setUpdateCollege, the methods allDetails and getStudent put a student object in the external context and return it from the external context respectively
	public String allDetails(Student student) {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

		Map<String, Object> sessionMap = externalContext.getSessionMap();
		sessionMap.put("student", student);
		
		return "student_details";
	}
	
	public Student getStudent() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

		Map<String, Object> sessionMap = externalContext.getSessionMap();
		Student student = (Student)sessionMap.get("student");
		
		return student;
	}
	
	// Search students uses the DAO to apply WHERE clauses to the SELECT * query and then sends the user to the search_results page
	public String searchStudents(Search search) {
		try {
			students = dao.searchStudents(search);
			return "search_results";
		} catch (SQLException se) {
			String m = se.getMessage();
			
			switch(se.getErrorCode()) {
			case 0:
				m = "Cannot connect to database";
				break;
			}
			
			FacesMessage message = new FacesMessage("ERROR: " + m);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	// AddStudent uses the DAO to add a student to the db and redirects the user to the list_students page to see the new addition
	public String addStudent(Student student) {
		try {
			dao.addStudent(student);
			return "list_students";
		} catch (SQLException se) {
			String m = se.getMessage();
			
			switch(se.getErrorCode()) {
			case 0:
				m = "Cannot connect to database";
				break;
			}
			
			FacesMessage message = new FacesMessage("ERROR: " + m);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}