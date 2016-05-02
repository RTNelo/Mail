package util;

public class GetSuffix {
	public static String getSuffixOfMailAddress(String mailAddress){
		String suffix = mailAddress.split("@")[1];
		return suffix;
	}
}
