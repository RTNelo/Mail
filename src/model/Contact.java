package model;

public class Contact {
	private int id;
	private int userId;
	private String nickname;
	private String mailAddress;

	public Contact(int id, int userId, String nickname, String mailAddress) {
		super();
		this.id = id;
		this.userId = userId;
		this.nickname = nickname;
		this.mailAddress = mailAddress;
	}

	public Contact(int userId, String nickname, String mailAddress) {
		this(-1, userId, nickname, mailAddress);
	}

	public Contact(User user, String nickname, String mailAddress) {
		this(user.getId(), nickname, mailAddress);
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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	@Override
	public String toString() {
		return "Contact [id=" + id + ", userId=" + userId + ", nickname=" + nickname + ", mailAddress=" + mailAddress
				+ "]";
	}
}
