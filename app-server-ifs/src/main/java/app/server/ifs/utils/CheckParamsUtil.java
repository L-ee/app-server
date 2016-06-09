package app.server.ifs.utils;

import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 验证常用参数工具类
 */
public class CheckParamsUtil {

    /**
     * 配置信息
     */
    private static Map<String, List<XmlParams>> map = new HashMap<String, List<XmlParams>>();
    //参数必需验证
    private static final String para_is_not_null = "0";
    private static final String para_is_null = "1";
    private static final String para_is_null_msg_code = "100005";
    private static final Logger log = Logger.getLogger(CheckParamsUtil.class);
    public final static String WARN_STRING = "',<,>,";

    /**
     * 初始化 checkParams.xml配置
     */
    @SuppressWarnings("unchecked")
    public static void initXml(String dirConfig) throws Exception {
        InputStreamReader isr = null;
        XmlParams vo = null;
        List<XmlParams> list = null;
        try {
            File configFile = new File(dirConfig);
            SAXReader reader = new SAXReader();
            isr = new InputStreamReader(new FileInputStream(configFile), "UTF-8");
            Document document = reader.read(isr);

            Element root = document.getRootElement();
            if (root != null) {
                Iterator<Element> msg = root.elementIterator("msg");
                String reg = "^[0-9]+$";
                if (msg != null) {
                    while (msg.hasNext()) {
                        Element paramMapElement = (Element) msg.next();
                        list = new ArrayList<XmlParams>();
                        if (paramMapElement != null) {
                            Iterator<Element> iter = paramMapElement.elementIterator();
                            while (iter.hasNext()) {
                                Element paramElement = (Element) iter.next();
                                String maxLength = paramElement.attributeValue("maxLength");
                                String minLnenth = paramElement.attributeValue("minLength");
                                vo = new XmlParams();
                                String name = paramElement.attributeValue("name");
                                if (!StringUtils.isEmpty(name)) {
                                    vo.setName(name.trim());
                                }
                                if (maxLength != null && maxLength.matches(reg)) {
                                    vo.setMaxLength(Integer.parseInt(maxLength));
                                }
                                if (minLnenth != null && minLnenth.matches(reg)) {
                                    vo.setMinLength(Integer.parseInt(minLnenth));
                                }
                                String isNull = paramElement.attributeValue("isNull");
                                if (!StringUtils.isEmpty(isNull)) {
                                    vo.setIsNull(isNull.trim());
                                }
                                String type = paramElement.attributeValue("type");
                                if (!StringUtils.isEmpty(type)) {
                                    vo.setType(type.trim());
                                }
                                String isValid = paramElement.attributeValue("isValid");
                                if (!StringUtils.isEmpty(isValid)) {
                                    vo.setIsValid(isValid.trim());
                                }
                                String regular = paramElement.attributeValue("regular");
                                if (!StringUtils.isEmpty(regular)) {
                                    vo.setRegular(regular.trim());
                                }
                                list.add(vo);
                            }
                        }
                        String method = paramMapElement.attributeValue("method");
                        if (!StringUtils.isEmpty(method)) {
                            method = method.trim();
                            map.put(method, list);
                        }
                    }
                }
            }

        } catch (Exception ex) {
            log.error("parse XmlParams.xml Exception :", ex);
            throw new Exception("parse XmlParams.xml Exception :" + ex.getMessage());
        } finally {
            try {
                if (isr != null) {
                    isr.close();
                }
            } catch (IOException e) {
                throw e;
            }
        }
    }

    /**
     * 通过接口方法名获得参数对象集
     *
     * @param method
     * @return
     */
    private static List<XmlParams> getParamsList(String method) {
        return map.get(method);
    }

    /**
     * 验证参数是否为空、最大长度、数据格式、tokenId有效性、枚举类型验证、特殊字符验证
     * @return BaseDomain
     */
    public static JSONObject checkParams(JSONObject json, String method) {
        JSONObject msg = new JSONObject();
//        msg.setRetCode (ConstantDefine.OPERATING_SUCCESS);
        List<XmlParams> xmlParams = getParamsList(method);
        if (xmlParams != null && xmlParams.size() > 0) {
            for (XmlParams xmlParam : xmlParams) {
                int maxLength = xmlParam.getMaxLength();
                int minLength = xmlParam.getMinLength();
                String isNull = xmlParam.getIsNull();
                String type = xmlParam.getType();
                String isValid = xmlParam.getIsValid();
                String name = xmlParam.getName();
                String regular = xmlParam.getRegular();
                String nameValue = json.optString(name);

                // 验证参数是否为空
                if (para_is_not_null.equals(isNull)) {
                    if (StringUtils.isEmpty(nameValue)) {
                        msg.put("code", para_is_null_msg_code);
                        msg.put("msg", name + "[" + nameValue + "] is null");
                        log.error(method + ":" + msg);
                        return msg;
                    }
                } else {
                    if (StringUtils.isEmpty(nameValue)) {
                        continue;
                    }
                }
                // 验证最大长度
                if (maxLength > 0) {
                    if (nameValue.length() > maxLength) {
                        msg.put("code", para_is_null_msg_code);
                        msg.put("msg", "para [" + nameValue + "] too long ");

                        log.error(method + ":" + msg);
                        return msg;
                    }
                }
                // 验证最小长度
                if (minLength > 0) {
                    if (nameValue.length() < minLength) {
                        msg.put("code", para_is_null_msg_code);
                        msg.put("msg", "para [" + nameValue + "] the length is not enough ");

                        log.error(method + ":" + msg);
                        return msg;
                    }
                }
                // 数据格式验证
                if ("int".equals(type) || "long".equals(type)) {
                    String reg = "^[-]{0,1}[0-9]+$";
                    if (!nameValue.matches(reg)) {
                        msg.put("code", para_is_null_msg_code);
                        msg.put("msg", "para [" + nameValue + "] the data format validation error ");

                        log.error(method + ":" + msg);
                        return msg;
                    }
                }


            }
        }
        return null;
    }

    private static boolean warnValue(String value) {
        String[] warnStr = WARN_STRING.split(",");
        for (int i = 0; i < warnStr.length; i++) {
            if (value.indexOf(warnStr[i]) > -1) {
                return true;
            }
        }
        return false;
    }

    static Map<String, String> secretMap = new HashMap<String, String>();

    private static void checkParam(String key, String value) throws Exception {
        if (StringUtils.isEmpty(value)) {
            throw new Exception(key + " param must not be empty");
        }
    }

}
