package com.veloxium.registry.domain;


import com.veloxium.registry.model.DataFunction;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "funcaofuncionario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class FunctionEmployee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "area")
    @Enumerated(EnumType.STRING)
    private Workspace workspace;
    @Column(name = "descricao")
    private String description;
    @Column(name = "salario")
    private BigDecimal salary;
    @OneToOne(mappedBy = "functionEmployee",cascade = CascadeType.ALL)
    @JoinColumn(name = "employee")
    private Employee employee;


    public FunctionEmployee(DataFunction function) {
        this.workspace = function.workspaceName();
        if(function.description()!=null){
            this.description =function.description();
        }else{
            this.description=workspace.description;
        }
        if(function.salary()!=null){
            this.salary =function.salary();
        }else{
            this.salary=workspace.salary;
        }
    }

    public void update(DataFunction function) {
        if(function.salary() != null){
            this.salary = function.salary();

        }
        if(function.workspaceName()!= null){
            this.workspace = function.workspaceName();
            if(function.description() != null){
                this.description = function.description();
            }else{
                this.description = function.workspaceName().description;
            }

        }

    }
}
