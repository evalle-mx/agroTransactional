package net.tce.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.log4j.Logger;


public class UtilsSeguridad {
	static Logger log4j = Logger.getLogger( UtilsSeguridad.class);
	 private final static String HEX = "0123456789ABCDEF";
	 private final static String KEY = "j9a1mn6d";
	 
	 /**
	  * Encripta cadenas
	  * @param cleartext es la cadena a encriptar
	  * @return
	  * @throws Exception
	  */
	 public static String encrypt(String cadena) throws Exception {
         return toHex(encrypt(getRawKey(KEY.getBytes()), cadena.getBytes()));
	 }
 
	 /**
	  * Desencripta una cadena
	  * @param encrypted
	  * @return
	  * @throws Exception
	  */
	 public static String decrypt( String cadena) throws Exception {
	         return new String(decrypt(getRawKey(KEY.getBytes()), toByte(cadena)));
	 }

	 /**
	  * Obtiene un arreglo de bytes de la llave. Se usa el protocolo AES
	  * @param key 
	  * @return
	  * @throws Exception
	  */
	 private static byte[] getRawKey(byte[] key) throws Exception {
         KeyGenerator kgen = KeyGenerator.getInstance("AES");
         SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
         sr.setSeed(key);
	     kgen.init(128, sr); // 192 and 256 bits may not be available
	     SecretKey skey = kgen.generateKey();
	     return skey.getEncoded();
	 }

	 /**
	  * Obtiene un arreglo de bytes cifrados de la cadena
	  * @param raw
	  * @param clear
	  * @return
	  * @throws Exception
	  */
	 private static byte[] encrypt(byte[] raw, byte[] clear) throws Exception {
	     Cipher cipher = Cipher.getInstance("AES");
	     cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(raw, "AES"));
	     return cipher.doFinal(clear);
	 }
	
	 /**
	  * Obtiene un arreglo de bytes decifrados de la cadena
	  * @param raw
	  * @param encrypted
	  * @return
	  * @throws Exception
	  */
	 private static byte[] decrypt(byte[] raw, byte[] encrypted) throws Exception {
	     SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
         Cipher cipher = Cipher.getInstance("AES");
	     cipher.init(Cipher.DECRYPT_MODE, skeySpec);
	     return cipher.doFinal(encrypted);
	 }
	 /**
	  * Convierte una cadena en un arreglo de bytes
	  * @param hexString
	  * @return
	  */
	 public static byte[] toByte(String hexString) {
         int len = hexString.length()/2;
         byte[] result = new byte[len];
         for (int i = 0; i < len; i++)
                 result[i] = Integer.valueOf(hexString.substring(2*i, 2*i+2), 16).byteValue();
         return result;
	 }
	 
	 /**
	  * 
	  * @param buf
	  * @return
	  */
	 public static String toHex(byte[] buf) {
         if (buf == null)
                 return "";
         StringBuffer result = new StringBuffer(2*buf.length);
         for (int i = 0; i < buf.length; i++) {
                 appendHex(result, buf[i]);
         }
         return result.toString();
	 }
	 
	 /**
	  * 
	  * @param sb
	  * @param b
	  */
	 private static void appendHex(StringBuffer sb, byte b) {
         sb.append(HEX.charAt((b>>4)&0x0f)).append(HEX.charAt(b&0x0f));
	 }
	
	 /**
	     * Encripta un String con el algoritmo MD5.
	     * @return String
		 * @throws NoSuchAlgorithmException 
	     * @throws Exception
	     */
	    public static String hashMD5(String clear) throws Exception {
	    	 StringBuffer h =null;
			try {
				 MessageDigest md= MessageDigest.getInstance("MD5");
				 byte[] b = md.digest(clear.getBytes());
		         int size = b.length;
		         h = new StringBuffer(size);
		         for (int i = 0; i < size; i++) {
		            int u = b[i]&255; // unsigned conversion
		            if (u<16) {
		                h.append("0"+Integer.toHexString(u));
		            } else {
		                h.append(Integer.toHexString(u));
		            }
		         }
			} catch (Exception e) {
				log4j.error(Mensaje.MSG_ERROR_ENCRIPT.concat(clear));
				throw new Exception(e);
			}
	        return h.toString();
	    }

/* public static void main(String[] args) {
		
		try {
			System.out.println("encripta: ".concat(hashMD5("123456789")) );
			System.out.println("encripta: ".concat(encrypt("1234")) );
			System.out.println("encripta: ".concat(encrypt("2")) );
			System.out.println("encripta: ".concat(encrypt("1234567891")) );
			
			System.out.println("desencripta: ".concat(decrypt("A376719D0EF3DA09DAD6001F38DCE8C6")) );
			System.out.println("desencripta: ".concat(decrypt("D34584B006282720D81DACAE60AEC1EE")) );
			System.out.println("desencripta: ".concat(decrypt("DF959F956F331D5525A0E4FCE93D8DFC")) );
			System.out.println("desencripta: ".concat(decrypt("671B9002F7FDA82F38145CFCA71CD5CA")) );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}*/

}
