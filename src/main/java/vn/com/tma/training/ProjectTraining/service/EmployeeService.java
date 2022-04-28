package vn.com.tma.training.ProjectTraining.service;

import vn.com.tma.training.ProjectTraining.dto.EmployeeDTO;

import java.util.Set;

public interface EmployeeService {
    Set<EmployeeDTO> listEmployee();

    EmployeeDTO newEmployee(EmployeeDTO employeeDTO);

    EmployeeDTO findEmployee(Integer id);

    void deleteEmployee(Integer id);

    Set<EmployeeDTO> findByName(String name);

    EmployeeDTO updateEmployee(Integer id, EmployeeDTO employeeDTO);

    Set<EmployeeDTO> listEmployeeByTeam(Integer teamID);
}
