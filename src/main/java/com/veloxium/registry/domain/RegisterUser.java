package com.veloxium.registry.domain;

import com.veloxium.registry.model.DataRegisterUser;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "User")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class RegisterUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "email",unique = true)
    private String email;
    @Column(name = "birthDate")
    private LocalDate birthDate;
    @Column(name = "login",unique = true)
    private Long login;
    @Column(name = "ativo")
    private Boolean active;

    public RegisterUser(DataRegisterUser dataRester,Long login) {
        this.name = dataRester.name();
        this.email =dataRester.email();
        this.birthDate = dataRester.birthDate();
        this.login = login;
        this.active = true;
    }

    public void delete() {
        this.active =false;
    }

    public void updateData(DataRegisterUser update) {
        if(update.name()!= null)this.name = update.name();
        if(update.email()!= null)this.email = update.email();

    }
}
