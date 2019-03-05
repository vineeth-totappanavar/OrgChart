package com.orgemployees.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility implementation to perform operations on Data
 * 
 * @author Vineeth
 *
 */
public class OrgEmployeeUtil {

	private static String TAB = "\t";
	private static String PIPE = "\\|";

	public static String[] splitData(final String data) {
		if (null == data || data.isEmpty()) {
			return null;
		}
		String[] splitData = data.split(TAB);
		return splitData;
	}

	public static List<String[]> splitData(final List<String> dataList) {
		List<String[]> splitData = new ArrayList<String[]>();
		for (String data : dataList) {
			splitData.add(splitData(data));
		}
		return splitData;
	}

	public static String[] splitPipe(final String data) {
		if (null == data || data.isEmpty()) {
			return null;
		}
		String[] splitData = data.split(PIPE);
		return splitData;
	}

	public static Integer[] convert(final String[] data) {
		if (null == data) {
			return null;
		}

		Integer[] result = new Integer[data.length];
		for (int i = 0; i < data.length; i++) {
			result[i] = Integer.parseInt(data[i]);
		}
		return result;
	}

}
