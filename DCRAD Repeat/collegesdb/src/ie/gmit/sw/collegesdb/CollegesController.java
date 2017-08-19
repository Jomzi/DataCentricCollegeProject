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
public class CollegesController {
	private ArrayList<College> colleges;
	private CollegeDAO dao;
	
	// Initialise DAO on CollegesController object creation
	public CollegesController(){
		try{
			dao = new CollegeDAO();
		}catch (Exception e){
			System.out.println("Cannot connect to db");
		}
	}
	
	// Get colleges will return an ArrayList of all the colleges from the db and return them to where the request was made (e.g. list_colleges.xhtml on render)
	public ArrayList<College> getColleges(){
		try {
			colleges = dao.getColleges();
		} catch (SQLException se) {
			FacesMessage message = new FacesMessage("ERROR: " + se.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return colleges;
	}
	
	// Adds a college using DAO and returns a name to the xhtml page to show the new addition
	public String addCollege(College college) {
		try {
			dao.addCollege(college);
			return "list_colleges";
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
	
	// Updates the college details passed from the list_colleges.xhtml selection and updates them using DAO
	public String updateCollege(College college) {
		try {
			dao.updateCollege(college);
			return "list_colleges";
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
	
	// This method sets the current view to update_college.xhtml and passes a college variable in the external context to populate the table on the page
	public String setUpdateCollege(College college) {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

		Map<String, Object> sessionMap = externalContext.getSessionMap();
		sessionMap.put("college", college);
		
		return "update_college";
	}
	
	// This method gets the college object on update_college.xhtml from the external context
	public College getUpdateCollege() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

		Map<String, Object> sessionMap = externalContext.getSessionMap();
		College college = (College)sessionMap.get("college");
		
		return college;
	}
	
	// deleteCollege deletes the specified college if Yes is selected on the pop-up box (college removed from db and from local arraylist so no stray is displayed on list_colleges.xhtml)
	public String deleteCollege(College college) {
		try {
			dao.deleteCollege(college);
			colleges.remove(college);
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
