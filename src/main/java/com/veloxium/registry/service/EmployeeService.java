package com.veloxium.registry.service;


import com.veloxium.registry.domain.Employee;
import com.veloxium.registry.domain.FunctionEmployee;
import com.veloxium.registry.infra.http.LoginFeing;
import com.veloxium.registry.model.DataEmployee;
import com.veloxium.registry.model.DataRegisterEmployee;
import com.veloxium.registry.model.DataRegisterLogin;
import com.veloxium.registry.model.DataUpdateEmployoo;
import com.veloxium.registry.repository.EmployeeRepository;
import com.veloxium.registry.repository.FunctionEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;


@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository repository;
    @Autowired
    private LoginFeing feign;
    @Autowired
    private FunctionEmployeeRepository repositoryFunction;
    public DataEmployee registerEmployee(DataRegisterEmployee dataRester) {
        if(repository.existsByCpf(dataRester.cpf())) throw new RuntimeException("ERRO REGISTER");
        var functionEmployee = repositoryFunction.save(new FunctionEmployee(dataRester.workspace()));
        var employee = repository.save(new Employee(dataRester,functionEmployee,null));
        var login =  feign.register(new DataRegisterLogin(dataRester.cpf(),dataRester.birthDate().toString(),"EMPLOYEE"));
        employee.setLogin(login.id());
        return new DataEmployee(employee);
    }


    public DataEmployee employeeID(Long id) {
        Optional<Employee> employee = repository.findByIdAndActiveTrue(id);
        return employee.map(DataEmployee::new).orElse(null);
    }

    public DataEmployee updateEmployee(Long id, DataUpdateEmployoo updateEmployoo) {
        Optional<Employee> employee = repository.findByIdAndActiveTrue(id);
        employee.ifPresent(em-> em.updateData(updateEmployoo));
        return employee.map(DataEmployee::new).orElse(null);
    }

    public void deleteEmployee(Long id) {

        var user = repository.findByIdAndActiveTrue(id);
       if(user.isPresent()) {
           user.get().delete();
           this.feign.delete(user.get().getLogin());
       }

    }

    public Page<DataEmployee> list(Pageable pageable) {
        return this.repository.findAllByActiveTrue(pageable).map(DataEmployee::new);
    }}
