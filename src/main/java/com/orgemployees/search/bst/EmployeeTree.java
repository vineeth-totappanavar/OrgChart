package com.orgemployees.search.bst;

/**
 * Implementation for Binary Search Tree.
 * 
 * @author Vineeth
 *
 */
public class EmployeeTree {
	private EmployeeTreeNode root;

	public void insert(int id, String empName) {
		if (root == null) {
			root = new EmployeeTreeNode(id, empName);
			return;
		}
		EmployeeTreeNode loc = root;
		while (true) {
			if (empName.compareTo(loc.name) < 0) {
				if (loc.left != null) {
					loc = loc.left;
				} else {
					loc.left = new EmployeeTreeNode(id, empName);
					break;
				}
			} else if (empName.compareTo(loc.name) > 0) {
				if (loc.right != null) {
					loc = loc.right;
				} else {
					loc.right = new EmployeeTreeNode(id, empName);
					break;
				}
			} else {
				break;
			}
		}
	}

	public EmployeeTreeNode search(String v) {
		return search(root, v);
	}

	private EmployeeTreeNode search(EmployeeTreeNode t, String v) {
		EmployeeTreeNode result;
		if (t != null && !v.equals(t.name)) {
			System.out.println(t.name);
			if (v.compareTo(t.name) < 0) {
				result = search(t.left, v);
			} else {
				result = search(t.right, v);
			}
		} else {
			result = t;
		}
		return result;
	}

	public void insert(String... empData) {
		insert(Integer.parseInt(empData[0]), empData[1]);
	}
}
