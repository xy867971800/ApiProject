package com.mvtlabs.api.dao;

import com.mvtlabs.api.entity.ApplicationFormEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApplicationFormDao extends JpaRepository<ApplicationFormEntity,Long> {

    ApplicationFormEntity findByUseId(String useId);

    @Query(value = "select * from application where audit_status = 0",nativeQuery = true)
    List findAllNotAudit();


}
