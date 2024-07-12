package com.veloxium.registry.repository;

import com.veloxium.registry.domain.FunctionEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FunctionEmployeeRepository extends JpaRepository<FunctionEmployee,Long> {
}
