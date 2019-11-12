package com.mvtlabs.api.core.util;

import com.mvtlabs.api.core.model.AdministratorHttpModel;
import com.mvtlabs.api.core.model.UserHttpModel;
import com.mvtlabs.api.entity.AdministratorEntity;
import com.mvtlabs.api.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class AdministratorUtil {
    public static List<AdministratorHttpModel> toAdministratorHttpModel(Iterable<AdministratorEntity> entities) {
        List<AdministratorHttpModel> httpModels = new ArrayList<>();

        for (AdministratorEntity entity : entities) {
            httpModels.add(toAdministratorHttpModel(entity));
        }

        return httpModels;
    }

    public static AdministratorHttpModel toAdministratorHttpModel(AdministratorEntity entity) {
        AdministratorHttpModel httpModel = new AdministratorHttpModel();
        httpModel.id = entity.getId();
        httpModel.useId = entity.getUseId();
        httpModel.token = entity.getUid();
        return httpModel;
    }
}
