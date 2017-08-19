package ie.gmit.sw.collegesdb;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class CoursesController {
	private ArrayList<Course> courses;
	private CourseDAO dao;
	
	//The coursesController methods work very similarly to those of collegesController except for the fact that a different table is being queried
	
	public CoursesController(){
		try{
			dao = new CourseDAO();
		}catch (Exception e){
			System.out.println("Cannot connect to db");
		}
	}
	
	public ArrayList<Course> getCourses(){
		try {
			courses = dao.getCourses();
		} catch (SQLException se) {
			FacesMessage message = new FacesMessage("ERROR: " + se.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return courses;
	}
	
	public String addCourse(Course course) {
		try {
			dao.addCourse(course);
			return "list_courses";
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