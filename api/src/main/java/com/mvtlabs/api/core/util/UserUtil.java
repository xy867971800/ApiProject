package com.mvtlabs.api.core.util;

import com.mvtlabs.api.core.model.ProjectModel;
import com.mvtlabs.api.core.model.UserHttpModel;
import com.mvtlabs.api.entity.ProjectEntity;
import com.mvtlabs.api.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class UserUtil {
    public static List<UserHttpModel> toUserHttpModel(Iterable<UserEntity> entities) {
        List<UserHttpModel> httpModels = new ArrayList<>();

        for (UserEntity entity : entities) {
            httpModels.add(toUserHttpModel(entity));
        }

        return httpModels;
    }

    public static UserHttpModel toUserHttpModel(UserEntity entity) {
        UserHttpModel httpModel = new UserHttpModel();
        httpModel.id = entity.getId();
        httpModel.useId = entity.getUseId();
        httpModel.name = entity.getName();
        httpModel.token = entity.getUid();
        return httpModel;
    }

    public static ProjectModel toProjectModel(ProjectEntity projectEntity){
        ProjectModel projectModel = new ProjectModel();
        projectModel.appId = projectEntity.getAppId();
        projectModel.projectName = projectEntity.getProjectName();
        projectModel.useId = projectEntity.getUseId();
        projectModel.usageQuality = projectEntity.getUsageQuantity();
        return projectModel;
    }
}
