package com.veloxium.registry.repository;

import com.veloxium.registry.domain.Employee;
import com.veloxium.registry.domain.RegisterUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<RegisterUser,Long> {
    Page<RegisterUser> findAllByActiveTrue(Pageable pageable);


    Optional<RegisterUser> findByIdAndActiveTrue(Long id);

    boolean existsByEmail(String email);
}
