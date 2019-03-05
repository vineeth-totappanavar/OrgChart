package com.orgemployees.repository.mysql;

import java.util.List;

import com.orgemployees.model.Organisation;
import com.orgemployees.repository.Repository;

/**
 * MySQL Repository Stub implementation for Organization. 
 * 
 * @author Vineeth
 *
 */
public class MysqlOrganisationRepositoryStub implements Repository<Organisation>{

	@Override
	public List<Organisation> findByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Organisation> findByID(List<Integer> idList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Organisation> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Organisation> findByPage(int pageNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Organisation> search(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
