package com.mvtlabs.api.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "project")
public class ProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "use_id", nullable = false,length = 40)
    private String useId;

    @Column(name = "project_name",nullable = false,length = 80)
    private String projectName;

    @Column(name = "app_id", unique = true ,nullable = false, length = 16)
    private String appId;

    @Column(name = "api_uid",unique = true, nullable = false, length = 36)
    private String apiUid;

    @Column(name = "api_secret", nullable = false ,length = 40)
    private String apiSecret;

    @Column(name = "create_time")
    private Date createTime = new Date( );

    @Column(name = "usage_quantity")
    private int usageQuantity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getUseId() {
        return useId;
    }

    public void setUseId(String projectId) {
        this.useId = projectId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getApiUid() {
        return apiUid;
    }

    public void setApiUid(String apiUid) {
        this.apiUid = apiUid;
    }

    public String getApiSecret() {
        return apiSecret;
    }

    public void setApiSecret(String apiSecret) {
        this.apiSecret = apiSecret;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getUsageQuantity() {
        return usageQuantity;
    }

    public void setUsageQuantity(int usageQuantity) {
        this.usageQuantity = usageQuantity;
    }

    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", useId='" + useId + '\'' +
                ", projectName='" + projectName +'\'' +
                ", appId='" + appId + '\'' +
                ", apiSecret=" + apiSecret + '\'' +
                ", apiUid=" + apiUid + '\'' +
                ", createTime=" + createTime +
                ", usageQuantity=" + usageQuantity +
                '}';
    }
}
