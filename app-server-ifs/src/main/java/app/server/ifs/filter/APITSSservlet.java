package app.server.ifs.filter;


import app.server.ifs.init.HttpUtil;
import app.server.ifs.init.URLMapConfig;
import app.server.ifs.utils.PropertiesConfig;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Enumeration;


public class APITSSservlet extends HttpServlet {

    //private static Logger logger = Logger.getLogger(APITSSservlet.class);

    private static final long serialVersionUID = 4607002214515050501L;

    public APITSSservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // resp.sendRedirect ("");
        Enumeration enumeration = req.getHeaderNames(); // 通过枚举类型获取请求文件的头部信息集
        String headder = req.getHeader("Content-Type");
        /*
         * // req.getHeader ("content-type;value=multipart/form-data"); //遍历头部信息集 while(enumeration.hasMoreElements()){ //取出信息名 String name=(String)enumeration.nextElement(); //取出信息值 String
         * value=req.getHeader(name); System.out.println ("key="+name+";value="+value); }
         */
        JSONObject json = new JSONObject();
        String reqURL = req.getRequestURL().toString();
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");

        if (reqURL.contains("gettime")) { // 取服务器时间
            long time = System.currentTimeMillis();
            json.put("code", "0");
            json.put("msg", "success");
            JSONObject res_data = new JSONObject();
            res_data.put("time", time);
            json.put("res_data", res_data);
            /**
             *
             { "code":"0", "msg":"message", "res_data": { "time":”XXXXXX” } }
             */
            try {
                resp.getWriter().write(json.toJSONString());
                resp.getWriter().flush();
                resp.getWriter().close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {

            }

        } else if (reqURL.contains("get_client_version")) {
            JSONArray version = new JSONArray();
            JSONObject androidJsonVer = new JSONObject();
            JSONObject iOSJsonVer = new JSONObject();
            JSONObject res_data = new JSONObject();
            iOSJsonVer.put("ctype", 1);
            iOSJsonVer.put("vno", iOSVersion);
            androidJsonVer.put("ctype", 2);
            androidJsonVer.put("vno", androidVersion);
            version.add(iOSJsonVer);
            version.add(androidJsonVer);
            JSONObject vJSON = new JSONObject();
            vJSON.put("version", version);
            json.put("code", "0");
            json.put("msg", "success");
            json.put("res_data", vJSON);
            /**
             *
             { "code":"0", "msg":"message", "res_data": { "time":”XXXXXX” } }
             */
            try {
                resp.getWriter().write(json.toJSONString());
                resp.getWriter().flush();
                resp.getWriter().close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {

            int port = req.getServerPort();
            String path = req.getContextPath();
            String contextPath = port + path;
            int index = reqURL.indexOf(apiVersion);
            String ifsURL = reqURL.substring(0, index) + contextPath;
            String apiServerName = reqURL.substring(index + 2);
            String realURL = URLMapConfig.getValueByKey(apiServerName);
            String validaToken = URLMapConfig.getValidateTokenFlagByUrl(apiServerName);
            if (headder.contains("multipart/form-data")) {
                String result = HttpUtil.doPostFormData(realURL, req);
                resp.getWriter().write(result);
                resp.getWriter().flush();
                resp.getWriter().close();
                return;
            } else {
                MonitorHttpUrlConnection urlConn = null;
                InputStream is = null;
                ByteArrayOutputStream os = null;
                OutputStream respOs = null;
                net.sf.json.JSONObject reqJSON = null;
                net.sf.json.JSONObject para_data_json = null;
                try {
                    is = req.getInputStream();
                    // 验证

                    /**
                     * { "timestamp":"1453798193112", "request_id":"1453798193112", "sign":"CF9207297368C32B599304D1BCECCC1A", "para_data":{ "user_id":"123", "para1":"value1", "para2":"value2",
                     * "para3":"value3", } } 0 处理成功 1 失败 100000 验签失败 100001 数据格式异常 100002 请求超时失效，校验时间戳超时，默认2分钟超时 100003 request_id无效 100004 请求太频繁 100005 必填参数为空
                     */
                    BufferedReader streamReader;
                    StringBuilder responseStrBuilder = new StringBuilder();
                    try {
                        streamReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                        String inputStr;
                        while ((inputStr = streamReader.readLine()) != null)
                            responseStrBuilder.append(inputStr);
                        reqJSON = net.sf.json.JSONObject.fromObject(responseStrBuilder.toString());
                        String type = reqJSON.optString("type");
                        para_data_json = reqJSON.getJSONObject("para_data");
                        long timestamp = reqJSON.optLong("timestamp");
                        // 验证是否超时
                        long currentTimestamp = System.currentTimeMillis();
                        // 请求超过 5分钟 验证不通过
                        if (currentTimestamp - timestamp >= 3000000) {
                            json.put("code", "100002");
                            json.put("msg", "超时访问");
                            resp.getWriter().write(json.toJSONString());
                            resp.getWriter().flush();
                            resp.getWriter().close();
                            return;
                        }
                        String request_id = reqJSON.optString("request_id");
                        // 验证request_id
                        // 验证签名
                        // 是否需要token validaToken = 0 不需验证token
                        StringBuffer str = new StringBuffer();
                        // Md5(json.get(“para_data”).toString+timestamp+request_id+ gcw)
                        if (null != para_data_json) {
                            str.append(para_data_json.toString());
                        }
                        str.append(timestamp).append(request_id).append(apiSignKeyWord);
                        String userId = null;
                        String token = null;

                        if ("1".equals(validaToken) || reqURL.contains("get_download_token")) {
                            userId = para_data_json.getString("user_id");
                            if (StringUtils.isEmpty(token)) {
                                json.put("code", "100006");
                                json.put("msg", "token失效");
                                resp.getWriter().write(json.toJSONString());
                                resp.getWriter().flush();
                                resp.getWriter().close();
                                return;
                            }
                            str.append(token);
                        }
                        String sign = reqJSON.optString("sign");
                        String serviceSign = "";//MD5Util.getMD5(str.toString());
                        if (sign.toUpperCase().equals(serviceSign)) {// = 通过

                        } else {
                            json.put("code", "100000");
                            json.put("msg", "验签失败");
                            resp.getWriter().write(json.toJSONString());
                            resp.getWriter().flush();
                            resp.getWriter().close();
                            return;
                        }


                    } catch (Exception e) {
                        json.put("code", "100001");
                        json.put("msg", "数据格式异常");
                        resp.getWriter().write(json.toJSONString());
                        resp.getWriter().flush();
                        resp.getWriter().close();
                        return;
                    }
                    if (reqURL.contains("get_upload_token")) {
                        String cover_upload = para_data_json.optString("cover_upload");
                        String file_name = para_data_json.optString("file_name");
                        String type = para_data_json.optString("type");
                        StringBuffer token = new StringBuffer();
                        StringBuffer url = new StringBuffer();
                        String space = null;
                        if ("1".equals(type)) {
                            url.append(PropertiesConfig.getValueByKey("images_space_domain_name")).append("/");
                            space = PropertiesConfig.getValueByKey("images_space");
                        } else if ("2".equals(type)) {
                            url.append(PropertiesConfig.getValueByKey("video_space_domain_name")).append("/");
                            space = PropertiesConfig.getValueByKey("video_space");
                        }
                        token.append(getUploadToken(space, file_name, cover_upload, type));

                        json.put("code", "0");
                        json.put("msg", "success");
                        net.sf.json.JSONObject resData = new net.sf.json.JSONObject();
                        resData.put("upload_token", token.toString());
                        resData.put("access_prefix_url", url.toString());
                        json.put("res_data", resData);
                        resp.getWriter().write(json.toJSONString());
                        resp.getWriter().flush();
                        resp.getWriter().close();
                        return;
                    }
                    if (reqURL.contains("get_download_token")) {
                        String url = para_data_json.optString("url");
                        //指定时长
                        long download_expired = 3600 * download_token_expired;

                        json.put("code", "0");
                        json.put("msg", "success");
                        net.sf.json.JSONObject resData = new net.sf.json.JSONObject();
                        resData.put("access_url", "");//urlSigned);
                        json.put("res_data", resData);
                        resp.getWriter().write(json.toJSONString());
                        resp.getWriter().flush();
                        resp.getWriter().close();
                        return;
                    }


                    try {
                        String result = HttpUtil.doPost(realURL, responseStrBuilder.toString());
                        resp.getWriter().write(result);
                        resp.getWriter().flush();
                        resp.getWriter().close();
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                        net.sf.json.JSONObject retrunJSON = new net.sf.json.JSONObject();
                        retrunJSON.put("code", "100099");
                        retrunJSON.put("msg", "异常");
                        resp.getWriter().write(retrunJSON.toString());
                        resp.getWriter().flush();
                        resp.getWriter().close();
                        return;
                    }


                } catch (Throwable e) {
                    e.printStackTrace();
                } finally {
                    if (urlConn != null) {
                        try {
                            urlConn.disconnect();
                            urlConn = null;
                        } catch (Exception e) {
                        }
                    }
                }
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private String getUploadToken(String space, String fileName, String converUpload, String type) {
        long expiredTime = 3600 * upload_token_expired;
        if ("1".equals(converUpload)) { // 覆盖
            if ("1".equals(type)) { //图片
            }
            return null;
        } else {
            if ("1".equals(type)) { //图片
            }
            return null;
        }
    }

    final long upload_token_expired = StringUtils.isEmpty(PropertiesConfig.getValueByKey("upload_token_expired")) ? 24 : Long.valueOf(PropertiesConfig.getValueByKey("upload_token_expired"));
    final long download_token_expired = StringUtils.isEmpty(PropertiesConfig.getValueByKey("download_token_expired")) ? 24 : Long.valueOf(PropertiesConfig.getValueByKey("download_token_expired"));
    final String ACCESS_KEY = PropertiesConfig.getValueByKey("AK");
    final String SECRET_KEY = PropertiesConfig.getValueByKey("SK");
    final String apiSignKeyWord = PropertiesConfig.getValueByKey("api_sign_keyword");
    final String apiVersion = PropertiesConfig.getValueByKey("api_version");
    final String iOSVersion = StringUtils.isEmpty(PropertiesConfig.getValueByKey("iOS_version")) ? "1.0" : PropertiesConfig.getValueByKey("iOS_version");
    final String androidVersion = StringUtils.isEmpty(PropertiesConfig.getValueByKey("android_version")) ? "1.0" : PropertiesConfig.getValueByKey("android_version");

}
