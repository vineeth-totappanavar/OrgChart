package com.orgemployees.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 * Model to hold the Organization details.
 * 
 * @author Vineeth
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "id", "name", "address", "links" })
@XmlRootElement(name = "organisation")
public class Organisation implements Instance {

	@XmlTransient
	private final String INSTANCE_REFERENCE;

	@XmlElement(name = "id")
	private int id;
	@XmlElement
	private String name;
	@XmlElement
	private String address;

	public Organisation() {
		this.INSTANCE_REFERENCE = null;
	}

	public Organisation(String... orgData) {
		this.id = Integer.parseInt(orgData[0]);
		this.name = orgData[1];
		this.address = orgData[2];
		this.INSTANCE_REFERENCE = "/organisation/" + this.getID();
	}

	public int getID() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	@XmlElement(name = "links")
	public Links getLinks() {
		Links links = new Links(this.INSTANCE_REFERENCE);
		links.addReflinks(this.INSTANCE_REFERENCE + "/employee");
		links.addReflinks(this.INSTANCE_REFERENCE + "/employee/search");
		return links;
	}
}
