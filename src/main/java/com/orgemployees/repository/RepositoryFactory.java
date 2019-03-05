package com.orgemployees.repository;

import com.orgemployees.model.Instance;
import com.orgemployees.model.Organisation;
import com.orgemployees.repository.file.FileEmployeeRepositoryStub;
import com.orgemployees.repository.file.FileOrganisationRepositoryStub;
import com.orgemployees.repository.file.FileRepository;

/**
 * Factory Implementation to choose the Repository to be used for Data Access
 * 
 * @author Vineeth
 *
 */
public class RepositoryFactory {

	/**
	 * Gets Repository Stub for a Class specified
	 * 
	 * @param a   Model Class
	 * @param ids Parent ID if required for a Stub
	 * @return Repository Instance
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Instance> Repository<T> getRepository(Class<T> a, Integer... ids) {
		Repository<?> instance = null;
		String type = "FILE";
		switch (type) {
		case "FILE":
			instance = getFileRepoByInstanceType(a, ids);
			break;
		case "MYSQL":
			instance = getMySQLRepoByInstanceType(a, ids);
			break;
		}
		return (Repository<T>) instance;
	}

	/**
	 * Gets the stub implementation based on the Class for File based Data Access
	 * 
	 * @param a   stub for a Model class Class
	 * @param ids IDs of Organization for which dependent Instance needs to be
	 *            created
	 * @return File Repository Stub instance
	 */
	private static FileRepository<?> getFileRepoByInstanceType(Class<?> a, Integer... ids) {
		if (a.getCanonicalName().equals(Organisation.class.getCanonicalName())) {
			return FileOrganisationRepositoryStub.getInstance();
		} else {
			return FileEmployeeRepositoryStub.getInstance(ids);
		}
	}

	/**
	 * Gets the stub implementation based on the Class for MySQL based Data Access
	 * 
	 * @param a   stub for a Model class Class
	 * @param ids IDs of Organization for which dependent Instance needs to be
	 *            created
	 * @return MySQL Repository Stub instance
	 */
	private static Repository<?> getMySQLRepoByInstanceType(Class<?> a, Integer... ids) {
		// To be implemented
		return null;
	}
}
