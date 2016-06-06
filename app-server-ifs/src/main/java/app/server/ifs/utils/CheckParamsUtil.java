package app.server.ifs.utils;

import net.sf.json.JSONObject;
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
                                if (!StringUtil.isEmpty(name)) {
                                    vo.setName(name.trim());
                                }
                                if (maxLength != null && maxLength.matches(reg)) {
                                    vo.setMaxLength(Integer.parseInt(maxLength));
                                }
                                if (minLnenth != null && minLnenth.matches(reg)) {
                                    vo.setMinLength(Integer.parseInt(minLnenth));
                                }
                                String isNull = paramElement.attributeValue("isNull");
                                if (!StringUtil.isEmpty(isNull)) {
                                    vo.setIsNull(isNull.trim());
                                }
                                String type = paramElement.attributeValue("type");
                                if (!StringUtil.isEmpty(type)) {
                                    vo.setType(type.trim());
                                }
                                String isValid = paramElement.attributeValue("isValid");
                                if (!StringUtil.isEmpty(isValid)) {
                                    vo.setIsValid(isValid.trim());
                                }
                                String regular = paramElement.attributeValue("regular");
                                if (!StringUtil.isEmpty(regular)) {
                                    vo.setRegular(regular.trim());
                                }
                                list.add(vo);
                            }
                        }
                        String method = paramMapElement.attributeValue("method");
                        if (!StringUtil.isEmpty(method)) {
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
                    if (StringUtil.isEmpty(nameValue)) {
                        msg.put("code", para_is_null_msg_code);
                        msg.put("msg", name + "[" + nameValue + "] is null");
                        log.error(method + ":" + msg);
                        return msg;
                    }
                } else {
                    if (StringUtil.isEmpty(nameValue)) {
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

                // 验证特殊字符,如：枚举类型等
                if (!StringUtil.isEmpty(regular)) {
//                    if ("phone".equals (name)) {
//                        String phoneReg = SysConfFactory.getValueByKey ("phone_interregional");
//                        nameValue = SecretKeyUtil.decode (nameValue);
//                        if (!phoneReg.matches (nameValue)) {
//                            msg.setRetCode (ConstantDefine.SYSTEM_PRARMS_ERROR);
//                            msg.setMessage (name + ConstantDefine.PARAM_ERROR_VALUE);
//                            log.error (method + ":" + msg);
//                            return msg;
//                        }
//                    }
//                    if (!nameValue.matches (regular)) {
//                        msg.setRetCode (ConstantDefine.SYSTEM_PRARMS_ERROR);
//                        msg.setMessage (name + ConstantDefine.PARAM_ERROR_VALUE);
//                        log.error (method + ":" + msg);
//                        return msg;
//                    }
                }

                // 非法字符验证
                /*if (warnValue (nameValue)) {
                    msg.setRetCode (ConstantDefine.SYSTEM_PRARMS_WARN);
                    msg.setMessage (name + " can not contain dangerous character: " + ConstantDefine.WARN_STRING);
                    log.error (method + ":" + msg);
                    return msg;
                }*/

                // tokenId有效性验证
         /*       if (ConstantDefine.IS_VALID.equals (isValid)) {
                    if ("tokenId".equals (name)) {
                        try {
                            String userId = tokenOperation.queryUserIdByToken (nameValue);
                            if (userId == null) {
                                msg.setRetCode (ConstantDefine.SYSTEM_TOKENID_EXPIRED);
                                msg.setMessage (ConstantDefine.TOKENID_EXPIRED_VALUE);
                                log.error (method + ":" + msg);
                                return msg;
                            }
                        } catch (Exception e) {
                            msg.setRetCode (ConstantDefine.ORTHERS_ERROR);
                            msg.setMessage ("validate token error :" + e.getMessage ());
                            log.error (method + ":" + msg);
                            return msg;
                        }
                    }
                }*/
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

    /*public static Future<?> checkSig(JSONObject request){
        String method = null;
        BaseDomain domain = null;
        try {
            method = request.getString ("method");
            String params = request.getString ("params");
            String signature = request.getString ("signature");

            checkParam ("signature", signature);
            JSONObject signatureJson = new JSONObject (signature);
            String api_key = signatureJson.getString ("api_key");
            String sig = signatureJson.getString ("sig");
            checkParam ("api_key", api_key);
            checkParam ("sig", sig);

            String secret_key = secretMap.get (api_key);
            if (null == secret_key) {
                // get from db
                secret_key = "SPDSS543543";
            }
            String localSig = com.whty.aam.common.util.StringUtil.toMD5 (params + secret_key);
            if (!sig.equals (localSig)) {
                domain = new BaseDomain ();
                domain.setRetCode (ConstantDefine.SYSTEM_PRARMS_SIG_ERROR);
                domain.setMessage ("sig illegal");
                return ImmediateFuture.newInstance (domain);
            }

        } catch (Exception e) {
            log.error ("method=" + method + " check sig error:" + e.getMessage (), e);
            domain = new BaseDomain ();
            domain.setRetCode (ConstantDefine.SYSTEM_PRARMS_NULL);
            domain.setMessage (e.getMessage ());
            return ImmediateFuture.newInstance (domain);
        }
        return null;
    }*/

    private static void checkParam(String key, String value) throws Exception {
        if (StringUtil.isEmpty(value)) {
            throw new Exception(key + " param must not be empty");
        }
    }

    /**
     * @Title: checkPhoneAttribution
     * @Description:查询手机号码归属地
     * @Author: wanq
     * @Since:
     * @param phone
     * @return
     */
   /* public static String checkPhoneAttribution(String phone){
        HttpURLConnection connection = null;
        try {
            String surl = SysConfFactory.getValueByKey ("check_phone_url");
            if (!StringUtil.isEmpty (surl)) {
                surl = surl.replaceFirst ("\\$\\{phone\\}", phone);
                URL url = new URL (surl);
                connection = (HttpURLConnection) url.openConnection ();
                connection.setRequestProperty ("content-type", "text/html; charset=utf-8");
                connection.setRequestMethod ("GET");
                BufferedReader rd = new BufferedReader (new InputStreamReader (connection.getInputStream (),"utf-8"));

                String temp = null;
                StringBuffer sb = new StringBuffer ();
                temp = rd.readLine ();
                while (temp != null) {
                    sb.append (temp);
                    temp = rd.readLine ();
                }
                rd.close ();
                connection.disconnect ();

                String content = sb.toString ();
                return content;
            }

        } catch (Exception e) {
            log.error ("验证手机号归属地出错！" + e);
        } finally {
            if (connection != null) {
                connection.disconnect ();
            }
        }
        return null;
    }*/
}
