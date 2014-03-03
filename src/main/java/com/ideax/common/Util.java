package com.ideax.common;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Util {

    public static ObjectMapper mapper = new ObjectMapper();
    static {
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, false);
    }

    public static String getClientIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    private static final char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
            'e', 'f' };

    public static String md5Encoding(String src) {
        try {
            byte[] strTemp = src.getBytes("UTF-8");
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);

            byte[] md = mdTemp.digest();
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;

            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }

            return new String(str);
        } catch (Exception e) {
            return src;
        }
    }

    public static final String AK = "XcMrzzpIgrVlXUOtAEzL3PFz";
    public static final String SK = "BOFBT0GSnoh0cnancoAAhhSTWFVonf8d";
    public static final String bucket = "fmstore";
    public static final String host = "http://bcs.duapp.com";

    /**
     * 
     * @param method
     * @param bucket
     * @param objName
     *            前面不要加 “/”
     * @param time
     * @return
     */
    public static String genPcsUrl(String method, String bucket, String objName, int time) {
        if (StringUtils.isBlank(method) && StringUtils.isBlank(bucket) && StringUtils.isBlank(objName)) {
            return null;
        }
        String flag;
        if (time > 0)
            flag = "MBOI";
        else
            flag = "MBO";
        StringBuilder sb = new StringBuilder();
        sb.append(flag).append('\n').append("Method=").append(method).append('\n').append("Bucket=").append(bucket)
                .append('\n').append("Object=/").append(objName).append('\n');

        if (time > 0)
            sb.append("Time=").append(time).append('\n');

        String hmac = doSign(sb.toString(), SK);
        StringBuilder sb2 = new StringBuilder();

        String encodeObjName = null;
        try {
            encodeObjName = URLEncoder.encode(objName, "UTF-8").replace("+", "%20");
        } catch (UnsupportedEncodingException e) {
            encodeObjName = objName.replace("+", "%20");
        }
        sb2.append(host).append("/").append(bucket).append('/').append(encodeObjName).append("?sign=").append(flag)
                .append(':').append(AK).append(':').append(hmac);
        return sb2.toString();
    }

    private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";

    @SuppressWarnings("deprecation")
    public static String doSign(String data, String key) {
        SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), HMAC_SHA1_ALGORITHM);
        Mac mac;
        byte[] bytes = null;
        try {
            mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
            mac.init(signingKey);
            try {
                bytes = mac.doFinal(data.getBytes("UTF-8"));
            } catch (Exception e1) {
                bytes = mac.doFinal(data.getBytes());
            }

            String base64b = Base64.encodeBase64String(bytes);
            try {
                return URLEncoder.encode(base64b, "ISO-8859-1");
            } catch (UnsupportedEncodingException e) {
                return URLEncoder.encode(base64b);
            }
        } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace(System.err);
        } catch (InvalidKeyException e) {
            e.printStackTrace(System.err);
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("MBO").append('\n').append("Method=").append("GET").append('\n').append("Bucket=").append(bucket)
                .append('\n').append("Object=").append("/断桥残雪 - 许嵩.mp3").append('\n');
        String hmac = doSign(sb.toString(), SK);
        System.out.println(sb.toString());
        System.out.println(hmac);

        System.out.println(Util.genPcsUrl("GET", "fmstore", "断桥残雪 - 许嵩.mp3", 0));
    }
}
