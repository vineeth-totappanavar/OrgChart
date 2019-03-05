package com.orgemployees.repository.file;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

import com.orgemployees.model.Employee;
import com.orgemployees.search.bst.EmployeeTree;
import com.orgemployees.util.OrgEmployeeUtil;

/**
 * File Repository Stub implementation for Employee
 * 
 * @author Vineeth
 *
 */
public class FileEmployeeRepositoryStub extends FileRepository<Employee> {

	public static Log LOGGER = LogFactory.getLog(FileEmployeeRepositoryStub.class);

	private final String DB_FILE_NAME;
	private final String INDEX_FILE_NAME;
	private final int orgID;
	private final EmployeeTree tree;

	private static Map<Integer, FileEmployeeRepositoryStub> instances = null;

	private FileEmployeeRepositoryStub(int id) {
		this.DB_FILE_NAME = id + "_employee";
		this.INDEX_FILE_NAME = id + "_index";
		this.orgID = id;
		this.tree = new EmployeeTree();
		initEmployeeSearchTree();
	}

	/**
	 * Init Method to load all Employee Names of a Organisation to Search Engine
	 */
	private void initEmployeeSearchTree() {
		LOGGER.info("Started initlization of data for Organisation with file : " + DB_FILE_NAME);
		final List<String> orgData = readAllLines(DB_FILE_NAME);
		if (orgData.isEmpty()) {
			LOGGER.warn("No Employee data found for Organisation!!!");
		} else {
			for (String data : orgData) {
				this.tree.insert(OrgEmployeeUtil.splitData(data));
			}
			LOGGER.info("Completed initlization!!!");
		}
	}

	/**
	 * Get Instance of File Employee Repository Stub for a Organization. Here an
	 * instance would be created per Organization to manage all its Employee details
	 * 
	 * @param ids ID of the Organization
	 * @return Instance of File Employee Repository Stub
	 */
	public static FileEmployeeRepositoryStub getInstance(Integer... ids) {
		if (null == instances) {
			instances = new HashMap<Integer, FileEmployeeRepositoryStub>();
		}
		FileEmployeeRepositoryStub instance = instances.get(ids[0]);
		if (null == instance) {
			instance = new FileEmployeeRepositoryStub(ids[0]);
			instances.put(ids[0], instance);
		}
		return instance;
	}

	private List<Employee> constructOrganisationEmployees(List<Integer> empIDs) {
		List<Employee> employeeList = new ArrayList<Employee>();

		final List<String> empIdxDataList = readLines(INDEX_FILE_NAME, empIDs);
		if (!empIdxDataList.isEmpty()) {
			Map<Integer, Integer[]> manHierarchy = new HashMap<Integer, Integer[]>();
			Map<Integer, Integer[]> reportees = new HashMap<Integer, Integer[]>();
			Set<Integer> empIDList = new HashSet<Integer>();

			for (String empIdxData : empIdxDataList) {
				String[] idxSplitData = OrgEmployeeUtil.splitData(empIdxData);
				final int empID = Integer.parseInt(idxSplitData[0]);
				empIDList.add(empID);

				Integer[] ids = null;
				if (idxSplitData.length > 1) {
					ids = OrgEmployeeUtil.convert(OrgEmployeeUtil.splitPipe(idxSplitData[1]));
					if (null != ids) {
						manHierarchy.put(empID, ids);
						empIDList.addAll(Arrays.asList(ids));
					}
				}

				if (idxSplitData.length > 2) {
					ids = OrgEmployeeUtil.convert(OrgEmployeeUtil.splitPipe(idxSplitData[2]));
					if (null != ids) {
						reportees.put(empID, ids);
						empIDList.addAll(Arrays.asList(ids));
					}
				}
			}

			final List<String> empDataList = readLines(DB_FILE_NAME, empIDList);
			Map<Integer, Employee> empDataMap = new HashMap<Integer, Employee>();

			for (String empData : empDataList) {
				Employee emp = new Employee(this.orgID, OrgEmployeeUtil.splitData(empData));
				empDataMap.put(emp.getID(), emp);
			}

			for (Integer empID : empIDs) {
				Employee employee = empDataMap.get(empID);
				employeeList.add(employee);

				Employee tempEmployee = employee;
				Integer[] manHierarchyForEmp = manHierarchy.get(empID);
				if (null != manHierarchyForEmp) {
					for (int i = manHierarchyForEmp.length; i > 0; i--) {
						Employee manager = empDataMap.get(manHierarchyForEmp[i - 1]);
						tempEmployee.setManagedBy(manager);
						tempEmployee = manager;
					}
				}

				Integer[] reporteesForEmp = reportees.get(empID);
				if (null != reporteesForEmp) {
					for (int i = 0; i < reporteesForEmp.length; i++) {
						Employee reportee = empDataMap.get(reporteesForEmp[i]);
						employee.addReportees(reportee);
					}
				}
			}
		}
		return employeeList;
	}

	@Override
	public List<Employee> findByID(int id) {
		List<Employee> employeeList = new ArrayList<Employee>();
		if (id > 0) {
			employeeList.addAll(constructOrganisationEmployees(Arrays.asList(id)));
		}
		return employeeList;
	}

	@Override
	public List<Employee> findByID(List<Integer> idList) {
		return null;
	}

	@Override
	public List<Employee> findAll() {
		List<Employee> empList = new ArrayList<Employee>();
		final List<String> orgData = readAllLines(DB_FILE_NAME);

		for (String data : orgData) {
			empList.add(new Employee(this.orgID, OrgEmployeeUtil.splitData(data)));
		}
		return empList;
	}

	@Override
	public List<Employee> findByPage(int pageNo) {
		List<Integer> empIDList = new ArrayList<Integer>();
		List<Employee> empList = new ArrayList<Employee>();

		if (pageNo > 0) {
			pageNo--;
		}

		int empID = (pageNo * 10) + 1;
		for (int i = 0; i < 10; i++) {
			empIDList.add(empID++);
		}
		final List<String> orgData = readLines(DB_FILE_NAME, empIDList);
		final int empCount = orgData.size();
		for (String data : orgData) {
			Employee emp = new Employee(this.orgID, OrgEmployeeUtil.splitData(data));
			if (empCount == 10) {
				emp.getLinks().addReflinks("/organisation/" + orgID + "/employee?page=" + (pageNo + 2));
			}

			empList.add(emp);
		}
		return empList;
	}

	@Override
	public List<Employee> search(String name) {
		return findByID(this.tree.search(name).getID());
	}
}
