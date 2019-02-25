
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;



/**
 * 
 * 
 * 
 * 
 * @author 
 * @version 1.0.0
 */
public class DalUtils {
	
	private static final int NUM0 = 0;
	private static final int NUM1 = 1;
	private static final int NUM2 = 2;
	private static final int NUM3 = 3;
	private static final int NUM4 = 4;
	private static final int NUM8 = 8;
	private static final int NUM16 = 16;
	private static final int NUM24 = 24;
	private static final int NUM_0XFF = 0xFF;

	
	/**
	 * 
	 * 
	 * @author 
	 */
	public static enum HashAlgorithm {
		/**
		 * MD5-based hash algorithm used by ketama.
		 */
		KETAMA_HASH;
		public long hash(byte[] digest, int nTime) {
			long rv = ((long) (digest[NUM3 + nTime * NUM4] & NUM_0XFF) << NUM24)
					| ((long) (digest[NUM2 + nTime * NUM4] & NUM_0XFF) << NUM16)
					| ((long) (digest[NUM1 + nTime * NUM4] & NUM_0XFF) << NUM8)
					| (digest[NUM0 + nTime * NUM4] & NUM_0XFF);

			return rv & 0xffffffffL; /* Truncate to 32-bits */
		}
	}
	
	public static byte[] computeMd5(String k) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.reset();
			md5.update(k.getBytes("UTF-8"));
			return md5.digest();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("MD5 not supported", e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Unknown string :" + k, e);
		}
	}

}
