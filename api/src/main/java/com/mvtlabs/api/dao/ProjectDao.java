package com.mvtlabs.api.dao;

import com.mvtlabs.api.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectDao extends JpaRepository<ProjectEntity,Long> {
    List<ProjectEntity> findByUseId(String useId);
}
