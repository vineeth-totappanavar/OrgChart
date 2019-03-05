package com.orgemployees.search.bst;

/**
 * Implementation to hold Employee Name and ID to be used in Binary Search Tree
 * @author Vineeth
 *
 */
public class EmployeeTreeNode {
	protected String name; 
	protected int id;
	protected EmployeeTreeNode left, right;

	public EmployeeTreeNode(int id, String empName) { 
		this.id = id;
		this.name = empName;
		this.left = this.right = null;
	}
	
	public int getID() {
		return this.id;
	}
}
