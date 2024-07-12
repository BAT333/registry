package com.veloxium.registry.model;


import com.veloxium.registry.domain.Employee;
import com.veloxium.registry.domain.Workspace;

public record DataEmployee(
        Long id,
        String name,
        String City,
        String State,
        Workspace workspace
) {
    public DataEmployee(Employee employee) {
        this(employee.getId(), employee.getName(), employee.getAddress().getCity(),employee.getAddress().getState(),employee.getFunctionEmployee().getWorkspace());
    }
}
