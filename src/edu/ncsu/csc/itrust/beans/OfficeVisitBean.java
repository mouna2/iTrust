package edu.ncsu.csc.itrust.beans;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * A bean for storing data about an office visit at the hospital.
 * 
 * A bean's purpose is to store data. Period. Little or no functionality is to be added to a bean 
 * (with the exception of minor formatting such as concatenating phone numbers together). 
 * A bean must only have Getters and Setters (Eclipse Hint: Use Source > Generate Getters and Setters� 
 * to create these easily)
 */
public class OfficeVisitBean {
	private long visitID = 0;
	private long patientID = 0;
	private long hcpID = 0;
	private String notes = "";
	private String visitDateStr = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
	private String hospitalID = "";
	private boolean isERIncident = false;

	public OfficeVisitBean() {
	}

	/**
	 * For use ONLY by DAOs
	 * setters and getters method
	 * @param visitID
	 */
	public OfficeVisitBean(long visitID) {
		this.visitID = visitID;
	}

	public long getID() {
		return visitID;
	}

	public long getPatientID() {
		return this.patientID;
	}

	public long getHcpID() {
		return this.hcpID;
	}

	public String getNotes() {
		return this.notes;
	}

	public Date getVisitDate() {
		Date d = null; 
		try {
			d = new SimpleDateFormat("MM/dd/yyyy").parse(visitDateStr);
		} catch (ParseException e) {
			System.out.println(e.toString());
		}
		
		return d;
	}

	public String getVisitDateStr() {
		return visitDateStr;
	}

	public long getVisitID() {
		return visitID;
	}

	public void setHcpID(long hcpID) {
		this.hcpID = hcpID;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public void setPatientID(long patientID) {
		this.patientID = patientID;
	}

	public void setVisitDateStr(String visitDate) {
		this.visitDateStr = visitDate;
	}

	public String getHospitalID() {
		return hospitalID;
	}

	public void setHospitalID(String hospitalID) {
		this.hospitalID = hospitalID;
	}

	public boolean isERIncident() {
		return isERIncident;
	}

	public void setERIncident(boolean isERIncident) {
		this.isERIncident = isERIncident;
	}

}
