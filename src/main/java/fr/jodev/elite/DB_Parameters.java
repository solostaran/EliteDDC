package fr.jodev.elite;

public class DB_Parameters {

	private String driverClassName = "org.h2.Driver";
	private String url = "jdbc:h2:./elite";
	private String username = "elite";
	private String password = "";
	private String dialect = "org.hibernate.dialect.H2Dialect";
	
	public String getDriverClassName() {
		return driverClassName;
	}
	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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
	public String getDialect() {
		return dialect;
	}
	public void setDialect(String dialect) {
		this.dialect = dialect;
	}
}
