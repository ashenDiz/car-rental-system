package model;

public class User {
	private String type;
	private String name;
	private String password;
	private String id_no;
	private int id;
	private boolean isLogged;
	public User(String type, String name, String password, String id_no) {
		super();
		this.type = type;
		this.name = name;
		this.password = password;
		this.id_no = id_no;
		
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getId_no() {
		return id_no;
	}
	public void setId_no(String id_no) {
		this.id_no = id_no;
	}
	public boolean isLogged() {
		return isLogged;
	}
	public void setLogged(boolean isLogged) {
		this.isLogged = isLogged;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "User [type=" + type + ", name=" + name + ", password=" + password + ", id_no=" + id_no + ", id=" + id
				+ ", isLogged=" + isLogged + "]";
	}
	
	
	
}
