package app.service;

import app.domain.models.service.EmployeeServiceModel;

public interface EmployeeService {

    boolean saveEmployee(EmployeeServiceModel employeeServiceModel);
}
