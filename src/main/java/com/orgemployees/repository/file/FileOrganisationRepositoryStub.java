package com.orgemployees.repository.file;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.orgemployees.model.Organisation;
import com.orgemployees.util.OrgEmployeeUtil;

/**
 * File Repository Stub implementation for Organization. This is a Singleton
 * implementation for all the Organizations in OrgChart Project
 * 
 * @author Vineeth
 *
 */
public class FileOrganisationRepositoryStub extends FileRepository<Organisation> {

	private static String DB_FILE_NAME = "organisation";

	private FileOrganisationRepositoryStub() {
	}

	private static FileOrganisationRepositoryStub instance;

	public static FileOrganisationRepositoryStub getInstance() {
		if (null == instance) {
			instance = new FileOrganisationRepositoryStub();
		}
		return instance;
	}

	@Override
	public List<Organisation> findByID(int id) {
		List<Organisation> orgList = new ArrayList<Organisation>();
		if (id > 0) {
			final List<String> orgData = readLines(DB_FILE_NAME, Arrays.asList(id));
			if (!orgData.isEmpty()) {
				Organisation organisation = new Organisation(OrgEmployeeUtil.splitData(orgData.get(0)));
				if (null != organisation) {
					orgList.add(organisation);
				}
			}
		}
		return orgList;
	}

	@Override
	public List<Organisation> findByID(List<Integer> idList) {
		return null;
	}

	@Override
	public List<Organisation> findAll() {
		List<Organisation> orgList = new ArrayList<Organisation>();
		final List<String> orgData = readAllLines(DB_FILE_NAME);
		for (String data : orgData) {
			orgList.add(new Organisation(OrgEmployeeUtil.splitData(data)));
		}
		return orgList;
	}

	@Override
	public List<Organisation> findByPage(int pageNo) {
		return null;
	}

	@Override
	public List<Organisation> search(String name) {
		return null;
	}
}
