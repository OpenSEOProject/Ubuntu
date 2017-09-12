package br.tatuapu.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.IllegalBlockSizeException;

/**
 *
 * @author tatuapu
 */
public class Encrypter {
    
    /**
     * encrypt hashIn to MD5 hash, using MessageDigest
     * @param hashIn
     * @return hashOut as hashIn in MD5, or null if error
     */
    public static String encryptMD5(String hashIn){
        String s;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            BigInteger hash = new BigInteger(1, md.digest(hashIn.getBytes()));
            s= hash.toString();
        } catch (NoSuchAlgorithmException ex) {
            return null;
        }
        return s;
    }
    public static String encrypt(String str) {
        try {
            // Encode the string into bytes using utf-8
            byte[] utf8 = str.getBytes("UTF8");

            // Encode bytes to base64 to get a string
            return new sun.misc.BASE64Encoder().encode(utf8);
        } catch (UnsupportedEncodingException e) {
        } catch (java.io.IOException e) {
        }
        return null;
    }

    public static String decrypt(String str) {
        try {
            // Decode base64 to get bytes
            byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str);

            // Decode using utf-8
            return new String(dec, "UTF8");
        } catch (UnsupportedEncodingException e) {
        } catch (java.io.IOException e) {
        }
        return null;
    }
}
