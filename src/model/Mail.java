package model;

import java.util.Date;

public class Mail {
	static public enum Type {
		SENT, RECV, DRAFT
	};

	private int id;
	private int mailAddressId;
	private String subject;
	private String content;
	private String sender;
	private String[] recver;
	private Date sendTime;
	private Date recvTime;
	private Type type;

	public Mail(int id, int mailAddressId, String subject, String content, String sender, String[] recver, Date sendTime,
			Date recvTime, Type type) {
		super();
		this.id = id;
		this.mailAddressId = mailAddressId;
		this.subject = subject;
		this.content = content;
		this.sender = sender;
		this.recver = recver;
		this.sendTime = sendTime;
		this.recvTime = recvTime;
		this.type = type;
	}

	public Mail(int mailAddressId, String subject, String content, String sender, String[] recver, Date sendTime,
			Date recvTime, Type type) {
		this(-1, mailAddressId, subject, content, sender, recver, sendTime, recvTime, type);
	}

	public Mail(MailAddress mailAddress, String subject, String content, String sender, String[] recver, Date sendTime,
			Date recvTime, Type type) {
		this(mailAddress.getId(), subject, content, sender, recver, sendTime, recvTime, type);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMailAddressId() {
		return mailAddressId;
	}

	public void setMailAddressId(int mailAddressId) {
		this.mailAddressId = mailAddressId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String[] getRecver() {
		return recver;
	}

	public void setRecver(String[] recver) {
		this.recver = recver;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public Date getRecvTime() {
		return recvTime;
	}

	public void setRecvTime(Date recvTime) {
		this.recvTime = recvTime;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Mail [id=" + id + ", mailAddressId=" + mailAddressId + ", subject=" + subject + ", sender=" + sender
				+ ", recver=" + recver + ", sendTime=" + sendTime + ", recvTime=" + recvTime + ", type=" + type + "]";
	}

}
