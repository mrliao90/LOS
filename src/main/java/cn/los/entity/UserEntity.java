package cn.los.entity;

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
    private String status;
    private String headimg;

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

    @Override
    public String toString() {
        return "UserEntity [id=" + getId() + ", username=" + username + ", realname=" + realname
                + ", password=" + password + ", status=" + status + ", headimg=" + headimg + "]";
    }

}
