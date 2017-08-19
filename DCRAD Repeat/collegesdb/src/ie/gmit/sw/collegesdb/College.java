package ie.gmit.sw.collegesdb;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class College {
	private String colID;
	private String colName;
	private String colAddr;
	
	public College(){
		
	}
	
	public College(String colID){
		this.colID = colID;
	}
	
	public College(String id, String name, String addr){
		this.colID = id;
		this.colName = name;
		this.colAddr = addr;
	}
	
	public String getColID() {
		return colID;
	}
	public void setColID(String colID) {
		this.colID = colID;
	}
	public String getColName() {
		return colName;
	}
	public void setColName(String colName) {
		this.colName = colName;
	}
	public String getColAddr() {
		return colAddr;
	}
	public void setColAddr(String colAddr) {
		this.colAddr = colAddr;
	}
}
