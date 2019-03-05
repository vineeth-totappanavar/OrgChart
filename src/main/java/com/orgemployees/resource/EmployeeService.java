package com.orgemployees.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.orgemployees.model.Employee;
import com.orgemployees.repository.Repository;
import com.orgemployees.repository.RepositoryFactory;


/**
 * Service/Endpoint implementation for Employee
 * 
 * @author Vineeth
 *
 */
@Path("/organisation/{orgid}/employee")
@Produces(MediaType.APPLICATION_XML)
public class EmployeeService {

	/**
	 * Gets 10 employee details per page no specified. If no Page No specified, its
	 * assumed to be first page.
	 * 
	 * @param orgid  Organization ID for Employee
	 * @param pageNo Page No
	 * @return List of Employee
	 */
	@GET
	public List<Employee> getEmployeeListPerPage(@PathParam("orgid") int orgid, @QueryParam("page") int pageNo) {
		Repository<Employee> empRepo = RepositoryFactory.getRepository(Employee.class, orgid);
		List<Employee> empList = empRepo.findByPage(pageNo);
		return empList;
	}

	/**
	 * Gets datails for an Employee
	 * 
	 * @param orgid Organization ID for Employee
	 * @param empid Employee ID
	 * @return Employee details
	 */
	@Path("/{empid}")
	@GET
	public List<Employee> getEmployeeDetails(@PathParam("orgid") int orgid, @PathParam("empid") int empid) {
		Repository<Employee> empRepo = RepositoryFactory.getRepository(Employee.class, orgid);
		return empRepo.findByID(empid);
	}

	/**
	 * Search Employee by Name. Its assumed that we are performing a case sensitive
	 * search
	 * 
	 * @param orgid Organization ID for Employee
	 * @param name  Name of the Employee to be searched
	 * @return Employee details
	 */
	@Path("/search")
	@GET
	public List<Employee> getOrganisationDetails(@PathParam("orgid") int orgid, @QueryParam("name") String name) {
		Repository<Employee> empRepo = RepositoryFactory.getRepository(Employee.class, orgid);
		return empRepo.search(name);
	}
}
