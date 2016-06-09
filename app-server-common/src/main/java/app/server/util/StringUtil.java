package app.server.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import java.security.MessageDigest;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串处理类
 *
 * @author t2w
 */
public class StringUtil {


    public static String toUnicode(String text) {
        if (text == null)
            return "";
        char chars[] = text.toCharArray();
        StringBuffer sb = new StringBuffer();
        int length = chars.length;
        for (int i = 0; i < length; i++) {
            int s = chars[i];
            sb.append("&#");
            sb.append(s);
            sb.append(";");
        }

        return sb.toString();
    }

    /**
     * 检测字符串里是否有中文字符
     *
     * @param str
     * @return
     */
    public static boolean chinese(String str) {
        if (str == null) {
            return false;
        }
        String regex = "[\u0391-\uFFE5]+";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        boolean validate = m.matches();
        return validate;
    }

    /**
     * 检测输入的邮政编码是否合法
     *
     * @param code
     * @return
     */
    public static boolean isPostCode(String code) {
        if (code == null) {
            return false;
        } else {
            Pattern p = Pattern.compile("[1-9]\\d{5}");
            Matcher m = p.matcher(code);
            return m.matches();
        }
    }


    /**
     * 屏掉WML不支持的代码
     *
     * @param str
     * @return
     */
    public static String wmlEncode(String str) {
        if (str == null) {
            return "";
        } else {
            str = str.replaceAll("&", "&amp;");
            str = str.replaceAll("<", "&lt;");
            str = str.replaceAll(">", "&gt;");
            str = str.replaceAll("'", "&apos;");
            str = str.replaceAll("\"", "&quot;");
            str = str.replaceAll("\n", "<br/>");
            str = str.replaceAll("<br>", "<br/>");
            return str;
        }
    }

    /**
     * 将字节转换成16进制
     *
     * @param b byte[]
     * @return String
     */
    public static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        return hs.toUpperCase();
    }


    /**
     * 检查书的ISBN号是否合法
     * @param isbn
     * @return
     */
    public static boolean isISBN(String isbn) {
        if (StringUtils.isEmpty(isbn)) {
            return false;
        }
        int len = isbn.length();
        if (len != 13) {
            return false;
        }
        String[] splits = isbn.split("-");
        len = splits.length;
        if (len != 4) {
            return false;
        }
        len = splits[0].length();
        if (len < 1 || len > 5) {
            return false;
        }
        len = splits[1].length();
        if (len < 2 || len > 5) {
            return false;
        }
        len = splits[2].length();
        if (len < 1 || len > 6) {
            return false;
        }
        len = splits[3].length();
        if (len != 1) {
            return false;
        }
        String realISBN = isbn.replaceAll("-", "");
        char[] numbers = realISBN.toCharArray();
        int sum = 0;
        for (int i = 10; i > 1; i--) {
            int index = 10 - i;
            int number = Integer.parseInt(String.valueOf(numbers[index]));
            sum = sum + number * i;
        }
        int code = 11 - (sum % 11);
        String codeStr = String.valueOf(code);
        if (code == 10) {
            codeStr = "X";
        }
        if (!splits[3].equals(codeStr)) {
            return false;
        }
        return true;
    }

    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replaceAll("-", "");
        return uuid;
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

    /**
     * Encode a string using Base64 encoding. Used when storing passwords as
     * cookies.
     * <p/>
     * This is weak encoding in that anyone can use the decodeString routine to
     * reverse the encoding.
     *
     * @param str
     * @return String
     */
    public static String encodeString(String str) {
        Base64 encoder = new Base64();
        return String.valueOf(encoder.encode(str.getBytes())).trim();
    }


    /**
     * <p>Checks if the String contains only unicode digits.
     * A decimal point is not a unicode digit and returns false.</p>
     * <p/>
     * <p><code>null</code> will return <code>false</code>.
     * An empty String ("") will return <code>true</code>.</p>
     * <p/>
     * <pre>
     * StringUtils.isNumeric(null)   = false
     * StringUtils.isNumeric("")     = true
     * StringUtils.isNumeric("  ")   = false
     * StringUtils.isNumeric("123")  = true
     * StringUtils.isNumeric("12 3") = false
     * StringUtils.isNumeric("ab2c") = false
     * StringUtils.isNumeric("12-3") = false
     * StringUtils.isNumeric("12.3") = false
     * </pre>
     *
     * @param str the String to check, may be null
     * @return <code>true</code> if only contains digits, and is non-null
     */
    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        int sz = str.length();
        for (int i = 0; i < sz; i++) {
            if (Character.isDigit(str.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }


    /**
     * 单引号(')，分号(;) 和 注释符号(--)的语句给替换掉
     *
     * @return
     */
    public static String TransactSQLInjection(String str) {
        return str.replaceAll(".*([';]+|(--)+).*", " ");
    }

    /**
     * 去除字符串中的空格、回车、换行符、制表符
     *
     * @param sourceStr
     * @return
     */
    public static String replaceString(String sourceStr) {
        Pattern p = Pattern.compile("\\s*|\t|\r|\n");
        Matcher m = p.matcher(sourceStr);
        String after = m.replaceAll("");
        return after;
    }


}