package model;

public class MailAddress {
	private int id;
	private int userId;
	private String account;
	private String password;
	
	public MailAddress(int id, int userId, String account, String password) {
		super();
		this.id = id;
		this.userId = userId;
		this.account = account;
		this.password = password;
	}
	
	public MailAddress(int userId, String account, String password) {
		this(-1, userId, account, password);
	}
	
	public MailAddress(User user, String account, String password) {
		this(user.getId(), account, password);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "MailAddress [id=" + id + ", userId=" + userId + ", account=" + account + ", password=" + password + "]";
	}
}
