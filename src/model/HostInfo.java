package model;

public class HostInfo {
	private int id;
	private String suffix;
	private String smtpHost;
	private int smtpPort;
	private String pop3Host;
	private int pop3Port;

	public HostInfo(int id, String suffix, String smtpHost, int smtpPort, String pop3Host, int pop3Port) {
		super();
		this.id = id;
		this.suffix = suffix;
		this.smtpHost = smtpHost;
		this.smtpPort = smtpPort;
		this.pop3Host = pop3Host;
		this.pop3Port = pop3Port;
	}

	public HostInfo(String suffix, String smtpHost, int smtpPort, String pop3Host, int pop3Port) {
		this(-1, suffix, smtpHost, smtpPort, pop3Host, pop3Port);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getSmtpHost() {
		return smtpHost;
	}

	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}

	public int getSmtpPort() {
		return smtpPort;
	}

	public void setSmtpPort(int smtpPort) {
		this.smtpPort = smtpPort;
	}

	public String getPop3Host() {
		return pop3Host;
	}

	public void setPop3Host(String pop3Host) {
		this.pop3Host = pop3Host;
	}

	public int getPop3Port() {
		return pop3Port;
	}

	public void setPop3Port(int pop3Port) {
		this.pop3Port = pop3Port;
	}
}
