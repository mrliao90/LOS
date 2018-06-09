package cn.los.entity;

import java.util.function.Predicate;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import cn.los.base.BaseEntity;

@Entity
@Table(name = "sys_user")
@JsonIgnoreProperties(ignoreUnknown = true)
@Proxy(lazy = false)
public class UserEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;
    private String username;
    private String realname;
    private String password;
    private String salt;
    private String email;
    private String mobile;
    private String status;
    private String headimg;

    public UserEntity() {
        super();
    }

    public UserEntity(String username) {
        super();
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    private boolean isNameValid(Predicate op) {
        return false;

    }

}
