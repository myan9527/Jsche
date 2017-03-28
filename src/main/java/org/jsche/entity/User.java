/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jsche.entity;

import org.jsche.common.ErrorMessage;
import org.jsche.common.validation.constraints.CustomPattern;
import org.jsche.common.validation.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
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
    @Column(unique = true, nullable = false, length = 100)
    @NotEmpty(message = ErrorMessage.PASSWORD_REQUIRED)
    private String password;
    private Date lastLogin;

    @Column(unique = true, nullable = false, length = 100)
    @CustomPattern(regex = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$",
            message = ErrorMessage.EMAIL_REQUIRED)
    private String email;
    @Column(nullable = false, length = 200)
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
