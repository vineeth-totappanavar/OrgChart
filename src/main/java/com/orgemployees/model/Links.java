package com.orgemployees.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Model to for generation of Links for a Instance.
 * 
 * @author Vineeth
 *
 */
@XmlRootElement(name = "links")
public class Links {

	private String selflink;
	private List<String> reflinks;

	public Links() {
	}

	public Links(final String link) {
		this.selflink = link;
		this.reflinks = new ArrayList<String>();
	}

	@XmlAttribute(name = "href")
	public String getSelflink() {
		return selflink;
	}

	@XmlElement(name = "ref-link")
	public List<String> getReflinks() {
		return reflinks;
	}

	public void addReflinks(String reflink) {
		this.reflinks.add(reflink);
	}
}
