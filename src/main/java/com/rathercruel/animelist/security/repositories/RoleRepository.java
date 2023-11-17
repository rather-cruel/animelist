package com.rathercruel.animelist.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rathercruel.animelist.security.models.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByAuthority(String authority);
}
