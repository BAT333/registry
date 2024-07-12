package com.veloxium.registry.service;


import com.veloxium.registry.domain.Employee;
import com.veloxium.registry.domain.FunctionEmployee;
import com.veloxium.registry.domain.RegisterUser;
import com.veloxium.registry.infra.http.LoginFeing;
import com.veloxium.registry.model.*;
import com.veloxium.registry.repository.EmployeeRepository;
import com.veloxium.registry.repository.FunctionEmployeeRepository;
import com.veloxium.registry.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private LoginFeing feing;

    public DataUser registerUser(DataRegisterUser dataRester) {
        if (repository.existsByEmail(dataRester.email())) throw new RuntimeException("ERRO REGISTER USER");
        var user = repository.save(new RegisterUser(dataRester,null));
        var login =  feing.register(new DataRegisterLogin(dataRester.email(), dataRester.birthDate().toString(),"USER"));
        user.setLogin(login.id());
        return new DataUser(user);
    }


    public DataUser userID(Long id) {
        Optional<RegisterUser> registerUser = repository.findByIdAndActiveTrue(id);
        return registerUser.map(DataUser::new).orElse(null);
    }

    public DataUser updateUser(Long id, DataRegisterUser update) {
        Optional<RegisterUser> registerUser = repository.findByIdAndActiveTrue(id);
        registerUser.ifPresent(em-> em.updateData(update));
        this.feing.update(new DataRegisterLogin(update.email(), update.birthDate().toString(),"USER"),null);
        return registerUser.map(DataUser::new).orElse(null);
    }

    public void deleteUser(Long id) {
        this.feing.delete(id);
        repository.findByIdAndActiveTrue(id).ifPresent(RegisterUser::delete);

    }

    public Page<DataUser> list(Pageable pageable) {
        return this.repository.findAllByActiveTrue(pageable).map(DataUser::new);
    }}
