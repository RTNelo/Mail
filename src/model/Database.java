package model;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import util.Util;

public class Database {
	static final String defaultUser = "root";
	static final String defaultPass = "VNH7PWHap4k4x9zkZasqY5wWbddtR9Wz";
	static final String defaultDriver = "com.mysql.jdbc.Driver";
	static final String defaultUrl = "jdbc:mysql://localhost/mail";

	static Database defaultDatabase = null;

	private Connection conn;

	public Database(String url, String user, String pass, String driver) {
		try {
			Class.forName(driver);
			this.setConn(DriverManager.getConnection(url, user, pass));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Database() {
		this(defaultUrl, defaultUser, defaultPass, defaultDriver);
	}

	static public Database setDefaultDatabase(Database newDb) {
		Database oldDb = defaultDatabase;
		defaultDatabase = newDb;
		return oldDb;
	}

	static public Database getDefaultDatabase() {
		if (defaultDatabase == null) {
			setDefaultDatabase(new Database());
		}
		return defaultDatabase;
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public PreparedStatement getStatement(String sql, Object... args) throws SQLException {
		PreparedStatement stmt = this.getConn().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		for (int i = 0; i != args.length; ++i) {
			switch(args[i].getClass().getName()) {
			case "java.util.Date":
				stmt.setDate(i + 1, (Date)args[i]);
				break;
			case "java.lang.Integer":
				stmt.setInt(i + 1, (int)args[i]);
				break;
			default:
				stmt.setString(i + 1, args[i].toString());
			}
		}
		return stmt;
	}

	private User resultSetToUser(ResultSet rs) throws SQLException {
		int userId = rs.getInt("id");
		String account = rs.getString("account");
		String passwordHash = rs.getString("passwordhash");
		String nickname = rs.getString("nickname");
		return new User(userId, account, passwordHash, nickname);
	}

	public User getUserById(int id) throws SQLException {
		ResultSet rs = this.getStatement("SELECT * FROM users WHERE id = ?;", id).executeQuery();
		if (!rs.next()) {
			return null;
		} else {
			return this.resultSetToUser(rs);
		}
	}

	public User getUserByAccount(String account) throws SQLException {
		ResultSet rs = this.getStatement("SELECT * FROM users WHERE account = ?;", account).executeQuery();
		if (!rs.next()) {
			return null;
		} else {
			return this.resultSetToUser(rs);
		}
	}

	public User getUserByLogin(String account, String password) throws SQLException {
		ResultSet rs = this.getStatement("SELECT * FROM users WHERE account = ? and passwordhash = ?;", account,
				Util.encodeSHA512(password)).executeQuery();
		if (!rs.next()) {
			return null;
		} else {
			return this.resultSetToUser(rs);
		}
	}
	
	private MailAddress resultSetToMailAddress(ResultSet rs) throws SQLException {
		int id = rs.getInt("id");
		int userId = rs.getInt("userid");
		String account = rs.getString("account");
		String password = rs.getString("password");
		return new MailAddress(id, userId, account, password);
	}
	
	public MailAddress getMailAddressById(int id) throws SQLException {
		ResultSet rs = this.getStatement("SELECT * FROM mailaddresses WHERE id = ?", id).executeQuery();
		if (rs.next()) {
			return this.resultSetToMailAddress(rs);
		} else {
			return null;
		}
	}
	
	public List<MailAddress> getMailAddressByUserId(int userId) throws SQLException {
		List<MailAddress> results = new LinkedList<MailAddress>();
		ResultSet rs = this.getStatement("SELECT * FROM mailaddresses WHERE userid = ?", userId).executeQuery();
		while (rs.next()) {
			results.add(this.resultSetToMailAddress(rs));
		}
		return results;
	}
	
	public MailAddress getMailAddressByUserAndAccount(User user, String account) throws SQLException {
		ResultSet rs = this.getStatement("SELECT * FROM mailaddresses WHERE userid = ? and account = ?",
				user.getId(), account).executeQuery();
		if (rs.next()) {
			return this.resultSetToMailAddress(rs);
		} else {
			return null;
		}
	}
	
	public List<MailAddress> getMailAddressByUser(User user) throws SQLException {
		return this.getMailAddressByUserId(user.getId());
	}
	
	private Mail resultSetToMail(ResultSet rs) throws SQLException {
		int mailId = rs.getInt("id");
		int mailAddressId = rs.getInt("mailaddressid");
		String subject = rs.getString("subject");
		String content = rs.getString("content");
		String sender = rs.getString("sender");
		String recvers = rs.getString("recver");
		Date sendTime = rs.getDate("sendtime");
		Date recvTime = rs.getDate("recvtime");
		Mail.Type type = Mail.Type.values()[rs.getInt("type")];
		return new Mail(mailId, mailAddressId, subject, content,
				sender, recvers.split(";"), sendTime, recvTime, type);
	}
	
	public Mail getMailById(int id) throws SQLException {
		ResultSet rs = this.getStatement("SELECT * FROM mails WHERE id = ?", id).executeQuery();
		if (rs.next()) {
			return this.resultSetToMail(rs);
		} else {
			return null;
		}
	}
	
	public List<Mail> getMailByMailAddressId(int mailAddressId) throws SQLException {
		List<Mail> results = new LinkedList<Mail>();
		ResultSet rs = this.getStatement("SELECT * FROM mails WHERE mailaddressid = ?", mailAddressId).executeQuery();
		while (rs.next()) {
			results.add(this.resultSetToMail(rs));
		}
		return results;
	}
	
	public List<Mail> getMailByMailAddressIdAndType(int mailAddressId, Mail.Type type) throws SQLException {
		List<Mail> results = new LinkedList<Mail>();
		ResultSet rs = this.getStatement("SELECT * FROM mails WHERE mailaddressid = ? and type = ?",
				mailAddressId, type.ordinal()).executeQuery();
		while (rs.next()) {
			results.add(this.resultSetToMail(rs));
		}
		return results;
	}
	
	private Contact resultSetToContact(ResultSet rs) throws SQLException {
		int contactId = rs.getInt("id");
		int userId = rs.getInt("userid");
		String nickname = rs.getString("nickname");
		String mailAddress = rs.getString("mailAddress");
		return new Contact(contactId, userId, nickname, mailAddress);
	}
	
	public Contact getContactByUserAndMailAddress(User user, String mailAddress) throws SQLException {
		ResultSet rs = this.getStatement("SELECT * FROM contacts WHERE userid = ? and mailaddress = ?",
				user.getId(), mailAddress).executeQuery();
		if (rs.next()) {
			return this.resultSetToContact(rs);
		} else {
			return null;
		}
	}
	
	public List<Contact> getContactByUserId(int userId) throws SQLException {
		List<Contact> results = new LinkedList<Contact>();
		ResultSet rs = this.getStatement("SELECT * FROM contacts WHERE userid = ?", userId).executeQuery();
		while (rs.next()) {
			results.add(this.resultSetToContact(rs));
		}
		return results;
	}
	
	public List<Contact> getContactByUser(User user) throws SQLException {
		return this.getContactByUserId(user.getId());
	}
	
	private int getGeneratedKey(PreparedStatement stmt) throws SQLException { 
		ResultSet rs = stmt.getGeneratedKeys();
		if (rs != null && rs.next()) {
			return rs.getInt(1);
		} else {
			return -1;
		}
	}
	
	public int addUser(User user) throws SQLException {
		PreparedStatement stmt = this.getStatement("INSERT INTO users (account, passwordhash, nickname) VALUES (?, ?, ?)",
				user.getAccount(), user.getPasswordHash(), user.getNickname());
		stmt.executeUpdate();
		user.setId(this.getGeneratedKey(stmt));
		return user.getId();
	}
	
	public int addMailAddress(MailAddress mailAddress) throws SQLException {
		PreparedStatement stmt = this.getStatement("INSERT INTO mailaddresses (userid, account, password) VALUES (?, ?, ?)",
				mailAddress.getUserId(), mailAddress.getAccount(), mailAddress.getPassword());
		stmt.executeUpdate();
		mailAddress.setId(this.getGeneratedKey(stmt));
		return mailAddress.getId();
	}
	
	public int addMail(Mail mail) throws SQLException {
		PreparedStatement stmt = this.getStatement("INSERT INTO mails (mailaddressid, subject, content, sender, recver, sendTime, recvTime, type) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
				mail.getMailAddressId(), mail.getSubject(), mail.getContent(),
				mail.getSender(), String.join(";", mail.getRecver()),
				mail.getSendTime(), mail.getRecvTime(),
				mail.getType());
		stmt.executeUpdate();
		mail.setId(this.getGeneratedKey(stmt));
		return mail.getId();
	}
	
	public int addContact(Contact contact) throws SQLException {
		PreparedStatement stmt = this.getStatement("INSERT INTO contacts (userid, nickname, mailaddress) VALUES (?, ?, ?)",
				contact.getUserId(), contact.getNickname(), contact.getMailAddress());
		stmt.executeUpdate();
		contact.setId(this.getGeneratedKey(stmt));
		return contact.getId();
	}
	
	public void removeUserById(int id) throws SQLException {
		PreparedStatement stmt = this.getStatement("DELETE FROM users WHERE id = ?;", id);
		stmt.executeUpdate();
	}
	
	public void removeUser(User user) throws SQLException {
		this.removeUserById(user.getId());
	}
	
	public void removeMailAddressById(int id) throws SQLException {
		PreparedStatement stmt = this.getStatement("DELETE FROM mailaddresses WHERE id = ?;", id);
		stmt.executeUpdate();
	}

	public void removeMailAddress(MailAddress mailAddress) throws SQLException {
		this.removeMailAddressById(mailAddress.getId());
	}
	
	public void removeMailById(int id) throws SQLException {
		PreparedStatement stmt = this.getStatement("DELETE FROM mails WHERE id = ?;", id);
		stmt.executeUpdate();
	}
	
	public void removeMail(Mail mail) throws SQLException {
		this.removeMailById(mail.getId());
	}
	
	public void removeContactById(int id) throws SQLException {
		PreparedStatement stmt = this.getStatement("DELETE FROM contacts WHERE id = ?;", id);
		stmt.executeUpdate();
	}
	
	public void removeContact(Contact contact) throws SQLException {
		this.removeContactById(contact.getId());
	}
	
	static public void test() throws SQLException {
		Database db = Database.getDefaultDatabase();
		int id = db.addUser(new User("haha4445555666666777", "123", "nickname4455555456666777"));
		System.out.println(id);
		User user = db.getUserById(id);
		System.out.println(user);
		db.addMailAddress(new MailAddress(user, "test", "test@test.com"));
	}
}
