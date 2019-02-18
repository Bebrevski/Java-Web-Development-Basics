package app.service;

import app.domain.models.service.EmployeeServiceModel;
import app.domain.models.view.EmployeeListViewModel;

import java.util.List;

public interface EmployeeService {

    boolean saveEmployee(EmployeeServiceModel employeeServiceModel);

    List<EmployeeServiceModel> findAllEmployees();

    boolean removeEmployee(String id);
}
