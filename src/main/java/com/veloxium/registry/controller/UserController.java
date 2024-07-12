package com.veloxium.registry.controller;


import com.veloxium.registry.model.*;
import com.veloxium.registry.service.EmployeeService;
import com.veloxium.registry.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/user")
@SecurityRequirement(name = "bearer-key")
public class UserController {
    @Autowired
    private UserService service;


    @PostMapping
    @Transactional
    public ResponseEntity<DataUser> register(@RequestBody @Valid DataRegisterUser dataRester, UriComponentsBuilder builder){
       var employee = this.service.registerUser(dataRester);
       URI uri = builder.path("/user/{id}").buildAndExpand(employee.id()).toUri();
       return ResponseEntity.created(uri).body(employee) ;
    }
    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DataUser> update(@PathVariable Long id, @RequestBody DataRegisterUser update){
        return ResponseEntity.ok(this.service.updateUser(id,update));
    }
    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id){
         service.deleteUser(id);
         return ResponseEntity.noContent().build();
    }
    @GetMapping
    public ResponseEntity<Page<DataUser>> list(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable){
        return ResponseEntity.ok(this.service.list(pageable));
    }
    @GetMapping("{id}")
    public ResponseEntity<DataUser> users(@PathVariable Long id){
        return ResponseEntity.ok(this.service.userID(id) );
    }



}
