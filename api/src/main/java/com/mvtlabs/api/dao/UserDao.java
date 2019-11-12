package com.mvtlabs.api.dao;

import com.mvtlabs.api.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserDao extends JpaRepository<UserEntity,Long> {
    UserEntity findByUseId(String useId);
    UserEntity findByMail(String mail);
    UserEntity findByUid(String token);

    @Query(value = "update user set authentication =?1 where use_id =?2", nativeQuery = true)
    void updateAuthentication(String type, String useId);
}
