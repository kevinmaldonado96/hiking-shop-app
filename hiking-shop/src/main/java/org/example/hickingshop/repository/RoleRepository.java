package org.example.hickingshop.repository;

import org.example.hickingshop.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> getRoleByRole(String string);
}
