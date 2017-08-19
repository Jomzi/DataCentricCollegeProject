package ie.gmit.sw.collegesdb;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Student {
	private String sID;
	private String colID;
	private Course course;
	private String first_name;
	private String last_name;
	private String address;
	
	public Student(){
		course = new Course();
	}

	public Student(String sID, String colID, Course course, String first_name, String last_name, String address) {
		super();
		this.sID = sID;
		this.colID = colID;
		this.course = course;
		this.first_name = first_name;
		this.last_name = last_name;
		this.address = address;
	}
	
	public String getsID() {
		return sID;
	}
	public void setsID(String sID) {
		this.sID = sID;
	}
	public String getColID() {
		return colID;
	}
	public void setColID(String colID) {
		this.colID = colID;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	
	
}
