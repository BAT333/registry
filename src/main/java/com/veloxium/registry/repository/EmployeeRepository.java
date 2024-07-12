package com.veloxium.registry.repository;
import com.veloxium.registry.domain.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    Page<Employee> findAllByActiveTrue(Pageable pageable);


    Optional<Employee> findByIdAndActiveTrue(Long id);

    boolean existsByCpf(String cpf);
}
