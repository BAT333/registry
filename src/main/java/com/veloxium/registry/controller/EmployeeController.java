package com.veloxium.registry.controller;


import com.veloxium.registry.model.DataEmployee;
import com.veloxium.registry.model.DataRegisterEmployee;
import com.veloxium.registry.model.DataUpdateEmployoo;
import com.veloxium.registry.service.EmployeeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/employee")
@SecurityRequirement(name = "bearer-key")
public class EmployeeController {
    @Autowired
    private EmployeeService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DataEmployee> register(@RequestBody @Valid DataRegisterEmployee dataRester, UriComponentsBuilder builder){
       var employee = this.service.registerEmployee(dataRester);
       URI uri = builder.path("/employee/{id}").buildAndExpand(employee.id()).toUri();
       return ResponseEntity.created(uri).body(employee) ;
    }
    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DataEmployee> update(@PathVariable Long id, @RequestBody DataUpdateEmployoo updateEmployoo){
        return ResponseEntity.ok(this.service.updateEmployee(id,updateEmployoo));
    }
    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id){
         service.deleteEmployee(id);
         return ResponseEntity.noContent().build();
    }
    @GetMapping
    public ResponseEntity<Page<DataEmployee>> list(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable){
        return ResponseEntity.ok(this.service.list(pageable));
    }
    @GetMapping("{id}")
    public ResponseEntity<DataEmployee> employee(@PathVariable Long id){
        return ResponseEntity.ok(this.service.employeeID(id) );
    }



}
