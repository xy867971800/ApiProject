package com.mvtlabs.api.entity;

import javax.persistence.*;

@Entity
@Table(name = "application")
public class ApplicationFormEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "use_id", unique = true, nullable = false, length = 36)
    private String useId;//申请人用户名

    @Column(name = "type", nullable = false, length = 36)
    private String applicationType;//申请类型

    @Column(name = "audit_status", nullable = false)
    private boolean status;//提交状态，0代表未审核，1代表已审核

    @Column(name = "info1",length = 255)
    private String info1;//申请信息

    @Column(name = "info2",length = 255)
    private String info2;

    @Column(name = "info3",length = 255)
    private String info3;

    @Column(name = "info4",length = 255)
    private String info4;

    @Column(name = "info5",length = 255)
    private String info5;

    @Column(name = "info6",length = 255)
    private String info6;

    @Column(name = "info7",length = 255)
    private String info7;

    @Column(name = "info8",length = 255)
    private String info8;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUseId() {
        return useId;
    }

    public void setUseId(String useId) {
        this.useId = useId;
    }

    public String getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(String applicationType) {
        this.applicationType = applicationType;
    }

    public String getInfo1() {
        return info1;
    }

    public void setInfo1(String info1) {
        this.info1 = info1;
    }

    public String getInfo2() {
        return info2;
    }

    public void setInfo2(String info2) {
        this.info2 = info2;
    }

    public String getInfo3() {
        return info3;
    }

    public void setInfo3(String info3) {
        this.info3 = info3;
    }

    public String getInfo4() {
        return info4;
    }

    public void setInfo4(String info4) {
        this.info4 = info4;
    }

    public String getInfo5() {
        return info5;
    }

    public void setInfo5(String info5) {
        this.info5 = info5;
    }

    public String getInfo6() {
        return info6;
    }

    public void setInfo6(String info6) {
        this.info6 = info6;
    }

    public String getInfo7() {
        return info7;
    }

    public void setInfo7(String info7) {
        this.info7 = info7;
    }

    public String getInfo8() {
        return info8;
    }

    public void setInfo8(String info8) {
        this.info8 = info8;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString(){
        return "ApplicationFormEntity{" +
                "id=" + id +
                ", useId='" + useId + '\'' +
                ", applicationType='" + applicationType + '\'' +
                ", status='" + status + '\'' +
                ", info1='" + info1 + '\'' +
                ", info2='" + info2 + '\'' +
                ", info3='" + info3 +  '\'' +
                ", info4='" + info4 +  '\'' +
                ", info5='" + info5 +  '\'' +
                ", info6='" + info6 + '\'' +
                ", info7='" + info7 + '\'' +
                ", info8='" + info8 + '\'' +
                '}';
    }
}
