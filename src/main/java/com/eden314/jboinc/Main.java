package com.eden314.jboinc;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;

public class Main {
	public static void main(String[] args) throws Exception {
		CommandLine cmd = Main.parseArguments(args);
		
		String password = cmd.getOptionValue("password");
		String preferenceFile = cmd.getOptionValue("preferenceFile");
		Boinc boinc = new Boinc(password, preferenceFile);
		
		String command = cmd.getOptionValue("command");
		switch (command) {
		case "start":
			boinc.start();
			break;
		case "stop":
			boinc.stop();
			break;
		case "setPreference":
			String preference = cmd.getOptionValue("preference");
			String value = cmd.getOptionValue("value");
			boinc.setPreference(preference, value);
			break;
		default:
			System.out.println("Unkown command " + command);
		}
	}

	protected static CommandLine parseArguments(String[] args) {
		CommandLine result = null;

		try {
			Options options = new Options();
			options.addOption("p", "password", true, "Password for Boinc");
			options.addOption("c", "command", true, "Command to be executed");
			options.addOption("P", "preference", true, "Preference to be changed");
			options.addOption("v", "value", true, "New value of preference to be set");
			options.addOption("f", "preferenceFile", true, "Boinc preference file location");

			CommandLineParser parser = new DefaultParser();
			result = parser.parse(options, args);
		} catch (Exception e) {
			System.out.print(e);
			System.exit(1);
		}

		return result;
	}
}
