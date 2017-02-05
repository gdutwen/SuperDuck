package sjkj.wen.superduck.activity.bean;

public class Data {
    private int id;
    private String username;
    private String password;
    private String access_token;
    @Override
	public String toString() {
		return "Data id=" + id + ", username=" + username + ", password=" + password + ", access_token=" + access_token
				;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	
    
	
    
}
