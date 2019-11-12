package com.mvtlabs.api.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "administrator")
public class AdministratorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "uid", unique = true, nullable = false,length = 36)
    private String uid;

    @Column(name = "use_id", unique = true,length = 40,nullable = false)
    private String useId;

    @Column(name = "password", nullable = false, length = 60)
    private String password;

    @Column(name = "register_time")
    private Date registerTime = new Date();

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

    public String getUseId() {
        return useId;
    }

    public void setUseId(String useId) {
        this.useId = useId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", uid='" + uid + '\'' +
                ", useId='" + useId + '\'' +
                ", password=" + password + '\'' +
                ", register_time=" + registerTime +
                '}';
    }
}
