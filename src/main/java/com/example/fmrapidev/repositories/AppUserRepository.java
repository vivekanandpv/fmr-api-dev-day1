package com.example.fmrapidev.repositories;

import com.example.fmrapidev.models.AppRole;
import com.example.fmrapidev.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
    Optional<AppUser> findUserByUsername(String username);

    boolean existsByUsername(String username);

    @Query("SELECT user.appRoles FROM AppUser user WHERE user.username = ?1")
    Set<AppRole> findRolesByUsername(String username);
}