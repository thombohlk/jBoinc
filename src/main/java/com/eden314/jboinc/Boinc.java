package com.eden314.jboinc;

public class Boinc {

	protected static final String SERVICE_COMMAND_START = "always";
	protected static final String SERVICE_COMMAND_STOP = "never";

	private String password;

	private String preferenceFile;

	public Boinc(String password) {
		this.setPassword(password);
	}

	public Boinc(String password, String preferenceFile) {
		this.setPassword(password);
		this.setPreferenceFile(preferenceFile);
	}

	public void start() {
		this.instructService(SERVICE_COMMAND_START);
	}

	public void stop() {
		this.instructService(SERVICE_COMMAND_STOP);
	}

	public void setPreference(String setting, String value) throws Exception {
		BoincPreferenceEditor.insertOrUpdate(this.getPreferenceFile(), setting, value);
		this.reloadPreferences();
	}

	protected void instructService(String instruction) {
		String[] command = { "boinccmd", "--passwd", this.getPassword(), "--set_run_mode", instruction };
		CLI.execute(command);
	}

	protected void reloadPreferences() {
		String[] cmd = { "boinccmd", "--read_global_prefs_override" };
		CLI.execute(cmd);
	}

	public String getPassword() {
		return password;
	}

	public Boinc setPassword(String password) {
		this.password = password;
		return this;
	}

	public String getPreferenceFile() throws Exception {
		if (preferenceFile == null || preferenceFile == "") {
			this.setPreferenceFile(BoincPreferenceFileLocator.locate());
		}

		return preferenceFile;
	}

	public Boinc setPreferenceFile(String preferenceFile) {
		this.preferenceFile = preferenceFile;
		return this;
	}
}
