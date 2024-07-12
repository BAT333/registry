package com.veloxium.registry.domain;

import com.veloxium.registry.model.DataRegisterEmployee;
import com.veloxium.registry.model.DataUpdateEmployoo;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "Employee")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "cpf",unique = true)
    private String cpf;
    @Column(name = "birthDate")
    private LocalDate birthDate;
    @Column(name = "ativo")
    private Boolean active;
    @Column(name = "login",unique = true)
    private Long login;
    @Embedded
    private Address address;
    @OneToOne(cascade = CascadeType.ALL)
    private FunctionEmployee functionEmployee;


    public Employee(DataRegisterEmployee dataRester, FunctionEmployee employee,Long login) {
        this.name = dataRester.name();
        this.cpf = dataRester.cpf();
        this.birthDate = dataRester.birthDate();
        this.active = true;
        this.address = new Address(dataRester.address());
        this.functionEmployee = employee;
        this.login = login;
    }

    public void updateData(DataUpdateEmployoo updateEmployoo) {
        if(updateEmployoo.name() != null){
            this.name = updateEmployoo.name();
        }
        if(updateEmployoo.address()!= null){
            this.address.updateAddress(updateEmployoo.address());
        }
        if(updateEmployoo.function()!= null){
            this.functionEmployee.update(updateEmployoo.function());
        }
    }

    public void delete() {
        this.active =false;
    }
}
