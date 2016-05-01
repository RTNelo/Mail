package util;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Util {
	public static String encodeSHA512(String src) {
		try {
			return new HexBinaryAdapter().marshal(MessageDigest.getInstance("SHA-512").digest(src.getBytes()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return src;
		}
	}
}
