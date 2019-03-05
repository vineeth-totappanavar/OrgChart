package com.orgemployees.repository.mysql;

import java.util.List;

import com.orgemployees.model.Employee;
import com.orgemployees.repository.Repository;

/**
 * MySQL Repository Stub implementation for Employee
 * 
 * @author Vineeth
 *
 */
public class MysqlEmployeeRepositoryStub implements Repository<Employee>{

	@Override
	public List<Employee> findByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> findByID(List<Integer> idList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> findByPage(int pageNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> search(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
