package com.orgemployees.repository.file;

import java.io.File;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import com.orgemployees.repository.Repository;

/**
 * Abstract Implementation of File Repository. All common implementations for
 * File based Data access can be added here
 * 
 * @author Vineeth
 *
 * @param <T> Type of File Repository Stub
 */
public abstract class FileRepository<T> implements Repository<T> {

	private static String PATH = System.getProperty("com.orgemployees.datadirectory");
	private static String FILE_PATH = "/data/{0}.dat";

	protected List<String> readLines(final String fileName, Collection<Integer> idList) {

		List<String> lineData = new ArrayList<String>();
		String path = getFilePath(fileName);
		if (null != path) {
			File file = new File(path);

			for (Integer id : idList) {
				try (Stream<String> lines = Files.lines(Paths.get(file.getPath()), StandardCharsets.UTF_8)) {
					final String line = lines.skip(id).findFirst().get();
					if (null != line && !line.isEmpty()) {
						lineData.add(line);
					}
				} catch (Exception e) {
					// Nothing to worry about
				}
			}
		}
		return lineData;
	}

	protected List<String> readAllLines(final String fileName) {

		List<String> lineData = new ArrayList<String>();
		String path = getFilePath(fileName);
		if (null != path) {
			File file = new File(path);

			try {
				lineData = Files.readAllLines(Paths.get(file.getPath()), StandardCharsets.UTF_8);
				lineData.remove(0);
				lineData.removeAll(Arrays.asList(new String()));
			} catch (Exception e) {
				// Nothing to worry about
			}
		}

		return lineData;
	}

	private String getFilePath(final String fileName) {
		String path = null;
		if (null != PATH) {
			path = PATH + MessageFormat.format(FILE_PATH, fileName);
		} else {
			URL url = getClass().getResource(MessageFormat.format(FILE_PATH, fileName));
			if (null != url) {
				path = url.getFile();
			}
		}
		return path;
	}

}
