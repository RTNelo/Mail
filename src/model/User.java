package model;

import util.Util;

public class User {
	private int id;
	private String account;
	private String passwordHash;
	private String nickname;

	public User(int id, String account, String passwordHash, String nickname) {
		super();
		this.id = id;
		this.account = account;
		this.passwordHash = passwordHash;
		this.nickname = nickname;
	}
	
	public User(String account, String password, String nickname) {
		this(-1, account, Util.encodeSHA512(password), nickname);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", account=" + account + ", nickname=" + nickname + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPasswordHash() {
		return passwordHash;
	}
	
	public void setPassword(String password) {
		this.setPasswordHash(Util.encodeSHA512(password));
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
}
