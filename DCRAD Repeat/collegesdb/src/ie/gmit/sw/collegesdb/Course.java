package ie.gmit.sw.collegesdb;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Course {
	private College college;
	private String cID;
	private String cName;
	
	public Course(){
		college = new College();
	}
	
	public Course(College college, String cID){
		this.college = college;
		this.cID = cID;
	}
	
	public Course(College college, String cID, String cName){
		this.college = college;
		this.cID = cID;
		this.cName = cName;
	}
	public College getCollege() {
		return college;
	}
	public void setCollege(College college) {
		this.college = college;
	}
	public String getcID() {
		return cID;
	}
	public void setcID(String cID) {
		this.cID = cID;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
}
