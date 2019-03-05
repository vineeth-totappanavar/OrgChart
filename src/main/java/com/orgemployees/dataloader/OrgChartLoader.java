package com.orgemployees.dataloader;

import java.util.List;

import javax.servlet.http.HttpServlet;

import com.orgemployees.model.Employee;
import com.orgemployees.model.Organisation;
import com.orgemployees.repository.Repository;
import com.orgemployees.repository.RepositoryFactory;

/**
 * This class would load all the Employee information from different
 * Organizations on to Binary Search Tree within the Repository implementation
 * specific to Organization
 * 
 * @author Vineeth
 *
 */
public class OrgChartLoader extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * Called on Server startup
	 */
	public void init() {
		Repository<Organisation> orgRepo = RepositoryFactory.getRepository(Organisation.class);
		List<Organisation> allOrg = orgRepo.findAll();
		for (Organisation org : allOrg) {
			RepositoryFactory.getRepository(Employee.class, org.getID());
		}
	}
}
