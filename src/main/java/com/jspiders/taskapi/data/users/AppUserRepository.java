package com.jspiders.taskapi.data.users;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser,Long> {
    boolean existsByEmailOrMobile(String email,String mobile);
    Optional<AppUser> findByEmail(String email);
    boolean existsByEmailAndPassword(String email ,String password);
    Optional<AppUser> findByEmailAndUserId(String email,Long userId);
    Optional<AppUser> findByNameAndUserId(String name,Long userId);
    Optional<AppUser> findByMobileAndUserId(String mobile,Long userId);

}
