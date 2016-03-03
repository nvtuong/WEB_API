package neo4j.bean;

public class User {
	private String id;
	private String name;
	private String avatar;
	
	public User(){
		
	}
	
	public User(String id, String name, String avatar) {
		this.id = id;
		this.name = name;
		this.avatar = avatar;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getAvatar() {
		return this.avatar;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
}
