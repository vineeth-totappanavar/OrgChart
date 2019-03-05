package com.orgemployees.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.orgemployees.model.Organisation;
import com.orgemployees.repository.Repository;
import com.orgemployees.repository.RepositoryFactory;

/**
 * Service/Endpoint implementation for Organization
 * 
 * @author Vineeth
 *
 */
@Path("/organisation")
@Produces(MediaType.APPLICATION_XML)
public class OrganisationService {

	/**
	 * Gets all the Organizations as part of OrgChart
	 * 
	 * @return List of Organizations
	 */
	@GET
	public List<Organisation> getOrganisationList() {
		Repository<Organisation> orgRepo = RepositoryFactory.getRepository(Organisation.class);
		List<Organisation> allOrg = orgRepo.findAll();
		return allOrg;
	}

	/**
	 * Gets a specific Organization detail
	 * 
	 * @param orgid Organization ID
	 * @return Instance of Organization
	 */
	@Path("/{orgid}")
	@GET
	public List<Organisation> getOrganisationDetails(@PathParam("orgid") int orgid) {
		Repository<Organisation> orgRepo = RepositoryFactory.getRepository(Organisation.class);
		return orgRepo.findByID(orgid);
	}
}
