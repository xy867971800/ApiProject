package com.mvtlabs.api.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //逻辑主键
    @Column(name = "uid",unique = true,nullable = false,length = 36)
    private String uid;

    @Column(name = "phone",unique = true,length = 20)
    private String phone;

    @Column(name = "use_id",unique = true,length = 40)
    private String useId;

    @Column(name = "name",length = 20)
    private String name;

    @Column(name = "password",nullable = false,length = 60)
    private String password;

    @Column(name = "c_locked",nullable = false)
    private boolean locked;

    @Column(name = "mail",nullable = false,length = 255)
    private String mail;

    @Column(name = "register_time")
    private Date registerTime = new Date();

    //用户认证
    @Column(name = "authentication")
    private String authentication;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUseId() {
        return useId;
    }

    public void setUseId(String useId) {
        this.useId = useId;
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

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public String getAuthentication() {
        return authentication;
    }

    public void setAuthentication(String authentication) {
        this.authentication = authentication;
    }

    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", uid='" + uid + '\'' +
                ", phone='" + phone +'\'' +
                ", useId='" + useId + '\'' +
                ", name=" + name + '\'' +
                ", password=" + password + '\'' +
                ", locked=" + locked +
                ", mail=" + mail +
                ", register_time=" + registerTime +
                ", authentication" + authentication +
                '}';
    }

}
