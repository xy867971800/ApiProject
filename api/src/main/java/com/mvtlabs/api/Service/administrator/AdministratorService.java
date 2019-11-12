package com.mvtlabs.api.Service.administrator;

import com.mvtlabs.api.core.http.Response;
import com.mvtlabs.api.core.http.adminitrator.AdministratorRegisterRequest;

import com.mvtlabs.api.core.http.adminitrator.AuditingRequest;
import com.mvtlabs.api.core.model.AdministratorHttpModel;
import com.mvtlabs.api.entity.ApplicationFormEntity;

import java.util.List;


public interface AdministratorService {
    public Response<AdministratorHttpModel> administratorRegister(AdministratorRegisterRequest administratorRegisterRequest);

    public Response<AdministratorHttpModel> administratorLogin(AdministratorRegisterRequest request);

    public Response<List<ApplicationFormEntity>> getAudit();

    public Response<String> doAudit(AuditingRequest request);
}
