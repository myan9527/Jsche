package org.jsche.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.jsche.common.util.AppUtil;
import org.springframework.web.bind.annotation.RequestMethod;

@Entity
@Table(name = "system_usages")
/**
 * Log system usage every time
 * @author Administrator
 *
 */
public class SystemUsage implements Serializable {
	private static final long serialVersionUID = 2992017243303594610L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String path;
	private String clientIp;
	private String dateStamp;
	private RequestMethod method;
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
	public RequestMethod getMethod() {
		return method;
	}
	public void setMethod(RequestMethod method) {
		this.method = method;
	}
	
}
