package app.server.ifs.utils;

/**
 * 参数封装类
 *
 * @author huangxiaofeng 2011-08-06
 */
public class XmlParams {

    /**
     * 参数名称
     */
    private String name;
    /**
     * 最大长度
     */
    private int maxLength;

    /**
     * 最小长度
     */
    private int minLength;
    /**
     * 是否可以为空
     */
    private String isNull;
    /**
     * 名称类型
     */
    private String type;
    /**
     * 验证tokenId标识码
     */
    private String isValid;
    /**
     * 正则表达式
     */
    private String regular;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public String getIsNull() {
        return isNull;
    }

    public void setIsNull(String isNull) {
        this.isNull = isNull;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return name + ":" + maxLength + ":" + isNull + ":" + type;
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }

    public String getRegular() {
        return regular;
    }

    public void setRegular(String regular) {
        this.regular = regular;
    }

    public int getMinLength() {
        return minLength;
    }

    public void setMinLength(int minLength) {
        this.minLength = minLength;
    }

}
