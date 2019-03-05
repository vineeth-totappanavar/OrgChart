package com.orgemployees.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Employee Model to hold details about specific Employee along with the
 * reporting Manager and list of Reportees It would also hold the Links that
 * needs to be generated for each of the Employee
 * 
 * @author Vineeth
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
//@XmlType(name = "", propOrder = { "id", "name", "designation", "location", "links", "managedby", "reportees" })
@XmlRootElement(name = "employee")
public class Employee implements Instance {

	@XmlElement(name = "id")
	private int id;
	@XmlElement
	private String name;
	@XmlElement
	private String designation;
	@XmlElement
	private String location;
	@XmlElement
	private Links links;
	@XmlElement(name = "managedby")
	private Employee managedBy;
	@XmlElement(name = "employee")
	@XmlElementWrapper(name = "reportees")
	private List<Employee> reportees;
	
	public Employee() {
	}

	public Employee(int orgID, String... orgData) {
		this.id = Integer.parseInt(orgData[0]);
		this.name = orgData[1];
		this.designation = orgData[2];
		this.location = orgData[3];
		this.reportees = new ArrayList<Employee>();
		final String parInstRef = "/organisation/" + orgID;
		this.links = new Links(parInstRef + "/employee/" + this.getID());
		links.addReflinks(parInstRef);
	}

	public int getID() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Employee getManagedBy() {
		return managedBy;
	}

	public void setManagedBy(Employee managedBy) {
		this.managedBy = managedBy;
	}

	public void addReportees(Employee reportee) {
		reportees.add(reportee);
	}

	public Links getLinks() {
		return this.links;
	}
}
