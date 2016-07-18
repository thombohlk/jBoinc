package com.eden314.jboinc;

import java.io.File;

public class BoincPreferenceFileLocator {
	
	protected static String defaultFileName = "global_prefs_override.xml";
	
	protected static String[] defaultFileLocations = {
			"/var/lib/boinc/",
			"/etc/boinc-client/"
	};

	public static String locate() throws Exception {
		for (String location : defaultFileLocations) {
			String fileLocation = location + defaultFileName;
			File f = new File(fileLocation);
			if(f.exists() && ! f.isDirectory()) {
				return fileLocation;
			}
		}
		
		throw new Exception("Could not locate Boinc preference file.");
	}
}
