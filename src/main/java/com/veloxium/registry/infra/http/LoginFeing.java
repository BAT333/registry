package com.veloxium.registry.infra.http;

import com.veloxium.registry.infra.http.config.FeignConfig;
import com.veloxium.registry.model.DataLoginsDTO;
import com.veloxium.registry.model.DataRegisterLogin;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "userSeguranca",configuration = FeignConfig.class)
public interface LoginFeing{

    @RequestMapping(method = RequestMethod.POST, value = "/login/auth")
    @CircuitBreaker(name = "erroLogin")
    List<String> auth(String token);
    @RequestMapping(method = RequestMethod.POST, value = "/login/register")
    @CircuitBreaker(name = "erroLogin")
    DataLoginsDTO register(DataRegisterLogin login);
    @RequestMapping(method =RequestMethod.PUT,value = "login/update/{id}")
    @CircuitBreaker(name = "erroLogin")
    void update(DataRegisterLogin dto,@PathVariable Long id);
    @RequestMapping(method =RequestMethod.DELETE,value = "login/{id}")
    @CircuitBreaker(name = "erroLogin")
    void delete(@PathVariable Long id);



}


