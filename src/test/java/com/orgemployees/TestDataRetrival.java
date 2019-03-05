package com.orgemployees;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.orgemployees.model.Organisation;
import com.orgemployees.resource.OrganisationService;

/**
 * JUnit for OrgChart
 * 
 * @author Vineeth
 *
 */
public class TestDataRetrival {

	OrganisationService orgserv;

	@Before
	public void setup() {
		orgserv = new OrganisationService();
	}

	@Test
	public void testOrgData() {
		List<Organisation> orgData = this.orgserv.getOrganisationList();
		assertTrue(orgData.size() == 3);
	}

	@Test
	public void testOrgDetails() {
		List<Organisation> orgData = this.orgserv.getOrganisationDetails(1);
		assertTrue(orgData.size() == 1);
		assertTrue(orgData.get(0).getName().equals("DellEMC"));
	}

}
