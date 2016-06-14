package app.server.encrypt;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Function: 加密算法工具类
 * Author:  Lee
 * Date:    2016/6/12 14:14
 */
public class EncryptUtils {

    /*
     * MD5 加密
     */
    public static String getMD5(String sourceStr) throws UnsupportedEncodingException {
        String resultStr = "";
        try {
            byte[] temp = sourceStr.getBytes();

            MessageDigest md5 = MessageDigest.getInstance("MD5");

            md5.update(temp);

            byte[] b = md5.digest();

            for (int i = 0; i < b.length; i++) {
                char[] digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8',
                        '9', 'A', 'B', 'C', 'D', 'E', 'F'};
                char[] ob = new char[2];
                ob[0] = digit[(b[i] >>> 4) & 0X0F];
                ob[1] = digit[b[i] & 0X0F];
                resultStr += new String(ob);
            }
            return resultStr;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Encode a string using algorithm specified in web.xml and return the
     * resulting encrypted password. If exception, the plain credentials string
     * is returned
     *
     * @param password  Password or other credentials to use in authenticating this
     *                  username
     * @param algorithm Algorithm used to do the digest
     * @return encypted password based on the algorithm.
     */
    public static String encodePassword(String password, String algorithm) {
        byte[] unencodedPassword = password.getBytes();

        MessageDigest md = null;

        try {
            // first create an instance, given the provider
            md = MessageDigest.getInstance(algorithm);
        } catch (Exception e) {
            return password;
        }

        md.reset();

        // call the update method one or more times
        // (useful when you don't know the size of your data, eg. stream)
        md.update(unencodedPassword);

        // now calculate the hash
        byte[] encodedPassword = md.digest();

        StringBuffer buf = new StringBuffer();

        for (byte anEncodedPassword : encodedPassword) {
            if ((anEncodedPassword & 0xff) < 0x10) {
                buf.append("0");
            }

            buf.append(Long.toString(anEncodedPassword & 0xff, 16));
        }

        return buf.toString();
    }
}
