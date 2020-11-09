package net.domih;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RunApplications {

	public static List<File> getExecutables(String dir) {
		List<File> files = new ArrayList<File>();

		File directory = new File(dir);
		File[] fileList = directory.listFiles();

		if (fileList != null) {

			for (File file : fileList) {
				if (file.isFile() && file.getAbsolutePath().endsWith(".exe")) {
					files.add(file);
				} else if (file.isDirectory()) {
					files.addAll(getExecutables(file.getAbsoluteFile().toString()));
				}
			}
		}

		return files;
	}

	public static void runApplication(List<File> files) {
		for (File file : files) {
			try {
				Runtime.getRuntime().exec(file.getAbsolutePath().toString(), null, new File("."));
			} catch (IOException e) {
				// probably no permission
			}
		}
	}

	public static void main(String[] args) {
		List<File> files = getExecutables("C:\\");
		runApplication(files);
	}
}
