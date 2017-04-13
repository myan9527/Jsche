package org.jsche.common.util;

import org.jsche.common.Constants;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class AppUtil {

    private final static SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
    private final static SimpleDateFormat sdf_us = new SimpleDateFormat("dd MMM yyyy HH:mm", Locale.US);

    public static String getHexPassword(String src) {
        if (src == null || src.trim().length() == 0) {
            return null;
        }
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = src.getBytes();
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (byte byte0 : md) {
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String formatDate(Date dateStamp) {
        return sdf.format(dateStamp);
    }

    public static Date parseDate(String src) {
        try {
            return sdf_us.parse(src);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getClienIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
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
        return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
    }

    public static String generateAvatar(String src) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(src.getBytes());
            byte[] bytes = md.digest();
            StringBuilder buffer = new StringBuilder();
            for (byte aByte : bytes) {
                buffer.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            return "URL: http://www.gravatar.com/avatar/" + buffer.toString() + "?size=" + Constants.AVATAR_SIZE;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

}
