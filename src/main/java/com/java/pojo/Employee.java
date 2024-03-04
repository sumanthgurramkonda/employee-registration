package com.java.pojo;

import java.io.Serializable;
import java.util.Date;

public class Employee implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int employeeid;
	private String firstname;
	private String middlename;
	private String lastname;
	private Date dob;
	private String Country;
	private String Location;
	private String address;
	private int experience;
	private String education;
	
	public Employee() {
		
	}

	public Employee(int employeeid, String firstname, String middlename, String lastname, Date dob, String country,
			String location, String address, int experience, String education) {
		super();
		this.employeeid = employeeid;
		this.firstname = firstname;
		this.middlename = middlename;
		this.lastname = lastname;
		this.dob = dob;
		Country = country;
		Location = location;
		this.address = address;
		this.experience = experience;
		this.education = education;
	}

	public int getEmployeeid() {
		return employeeid;
	}

	public void setEmployeeid(int employeeid) {
		this.employeeid = employeeid;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getMiddlename() {
		return middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getCountry() {
		return Country;
	}

	public void setCountry(String country) {
		Country = country;
	}

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Employee [employeeid=" + employeeid + ", firstname=" + firstname + ", middlename=" + middlename
				+ ", lastname=" + lastname + ", dob=" + dob + ", Country=" + Country + ", Location=" + Location
				+ ", address=" + address + ", experience=" + experience + ", education=" + education + "]";
	}
	
	
	
	
}
