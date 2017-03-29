package org.jsche.entity;

import org.jsche.common.util.AppUtil;

import java.util.Date;

/**
 * Log system usage every time
 *
 * @author Administrator
 *
 */
public class SystemUsage {

    private static final long serialVersionUID = 2992017243303594610L;

    private int id;
    private String path;
    private String clientIp;
    private String dateStamp;
    private String method;
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getDateStamp() {
        return dateStamp;
    }

    public void setDateStamp(Date dateStamp) {
        this.dateStamp = AppUtil.formatDate(dateStamp);
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
