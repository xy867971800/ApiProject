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

    @Column(name = "app_id", nullable = false, length = 16)
    private String AppId;

    @Column(name = "api_uid",unique = true, nullable = false, length = 36)
    private String ApiUid;

    @Column(name = "api_secret", nullable = false ,length = 40)
    private String ApiSecret;

    @Column(name = "create_time")
    private Date createTime = new Date();

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
        return AppId;
    }

    public void setAppId(String appId) {
        AppId = appId;
    }

    public String getApiUid() {
        return ApiUid;
    }

    public void setApiUid(String apiUid) {
        ApiUid = apiUid;
    }

    public String getApiSecret() {
        return ApiSecret;
    }

    public void setApiSecret(String apiSecret) {
        ApiSecret = apiSecret;
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
}
