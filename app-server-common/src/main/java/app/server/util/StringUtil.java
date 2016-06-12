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
     * 邮政编码是否合法
     * @param code
     * @return
     */
    public static boolean isPostNum(String code) {
        if (code == null) {
            return false;
        } else {
            Pattern p = Pattern.compile("[1-9]\\d{5}");
            Matcher m = p.matcher(code);
            return m.matches();
        }
    }


    /**
     * 转义字符转换
     * @param str
     * @return
     */
    public static String transform(String str) {
        if (str == null) {
            return "";
        } else {
            return str.replaceAll("&", "&amp;")
                    .replaceAll("<", "&lt;")
                    .replaceAll(">", "&gt;")
                    .replaceAll("'", "&apos;")
                    .replaceAll("\"", "&quot;")
                    .replaceAll("\n", "<br/>");
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
     * 检查国际标准书号（International Standard Book Number）是否合法,简称ISBN
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