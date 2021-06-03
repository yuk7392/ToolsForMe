package ToolsForMe;

public class User {
	
	private String nickName;
	private String id;
	private String password;
	private String quick_password;
	
	public User(){
		
		this.nickName = "null";
		this.id = "null";
		this.password = "null";
		this.quick_password = "null";
		
	}

	public User(String _nickName,String _id,String _password) {
		
		this.nickName = _nickName;
		this.id = _id;
		this.password = _password;
		
	}
	
	public User(String _nickName,String _id,String _password,String _quick_password) {
		
		this.nickName = _nickName;
		this.id = _id;
		this.password = _password;
		this.quick_password = _quick_password;
		
	}

	public String getnickName() {
		return nickName;
	}

	public void setnickName(String nickName) {
		this.nickName = nickName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getQuick_password() {
		return quick_password;
	}

	public void setQuick_password(String quick_password) {
		this.quick_password = quick_password;
	}

	@Override
	public String toString() {
		return nickName+"[ID : "+id+", PW : "+password+", 빠른로그인 번호 : "+quick_password+"]";
	}
	
}
