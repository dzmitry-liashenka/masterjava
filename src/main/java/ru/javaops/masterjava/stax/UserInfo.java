package ru.javaops.masterjava.stax;

public class UserInfo {

	private String name;
	private String email;
	
	public UserInfo(String name, String email) {
		super();
		this.name = name;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String toString() {
		return "UserInfo [name=" + name + ", email=" + email + "]";
	}
	
}
