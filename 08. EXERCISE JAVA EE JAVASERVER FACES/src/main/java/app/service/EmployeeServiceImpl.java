package app.service;

import app.domain.entities.Employee;
import app.domain.models.service.EmployeeServiceModel;
import app.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    @Inject
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean saveEmployee(EmployeeServiceModel employeeServiceModel) {
        try{
            this.employeeRepository.save(
                    this.modelMapper.map(employeeServiceModel, Employee.class)
            );
        } catch (Exception e){
            System.out.println("Employee repository did not saved entity!!!!!!!");
            return false;
        }
        return true;
    }
}
