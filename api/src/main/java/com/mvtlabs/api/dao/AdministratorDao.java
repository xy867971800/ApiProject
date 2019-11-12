package com.mvtlabs.api.dao;

import com.mvtlabs.api.entity.AdministratorEntity;
import com.mvtlabs.api.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministratorDao extends JpaRepository<AdministratorEntity,Long> {
    AdministratorEntity findByUseId(String useId);
}
