package grizzly.dto;

public class ConnectionDTO {
	//DB입력 정보를 저장하는 클래스
	
	private String userName;
	private String password;
	private String host;
	private String port;
	private String sid;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	
	//DTO 객체 확인 
	@Override
	public String toString() {
		return "ConnectionDTO [userName=" + userName + ", password=" + password + ", host=" + host + ", port=" + port
				+ ", sid=" + sid + "]";
	}
	
	
	
	
	

}
