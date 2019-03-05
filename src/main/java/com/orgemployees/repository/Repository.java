package com.orgemployees.repository;

import java.util.List;

/**
 * Base Interface for any Class that would perform operations on Data
 * 
 * @author Vineeth
 *
 * @param <T> Data access layer (Ex File, MySQL, Oracle, NoSQL etc)
 */
public interface Repository<T> {

	/**
	 * Find instance by ID
	 * 
	 * @param id ID
	 * @return Instance
	 */
	public List<T> findByID(final int id);

	/**
	 * Find List of instances by list of IDs
	 * 
	 * @param idList List of IDs
	 * @return List of Instances
	 */
	public List<T> findByID(final List<Integer> idList);

	/**
	 * Find all Instances
	 * 
	 * @return List of Instances
	 */
	public List<T> findAll();

	/**
	 * Find Instances for a Page No
	 * 
	 * @param pageNo Page No
	 * @return List of Instances
	 */
	public List<T> findByPage(final int pageNo);

	/**
	 * Search Instance by Name
	 * 
	 * @param name Name to be searched
	 * @return Instance
	 */
	public List<T> search(final String name);
}
