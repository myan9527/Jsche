/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jsche.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.jsche.common.validation.constraints.CustomPattern;

/**
 *
 * @author myan
 */
@Entity
@Table(name = "users")
public class User implements Serializable {
	private static final long serialVersionUID = 2621563319327340685L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @Column(unique = true,nullable = false,updatable = true,length = 100)
    private String password;
    private Date lastLogin;
    
    @Column(unique = true,nullable = false,updatable = true,length = 100)
    @CustomPattern(regex="^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$")
    private String email;
    @Column(nullable = false,length = 200)
    private String avatar;
    private boolean customizedAvatar;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isCustomizedAvatar() {
        return customizedAvatar;
    }

    public void setCustomizedAvatar(boolean customizedAvatar) {
        this.customizedAvatar = customizedAvatar;
    }
    
}
