package vn.com.tma.training.ProjectTraining.service;

import org.springframework.data.domain.Page;
import vn.com.tma.training.ProjectTraining.dto.EmployeeDTO;

import java.util.List;
import java.util.Set;

public interface EmployeeService {
    Set<EmployeeDTO> listEmployee();

    EmployeeDTO newEmployee(EmployeeDTO employeeDTO);

    EmployeeDTO findEmployee(Integer id);

    void deleteEmployee(Integer id);

    String deleteAll(List<Integer> ids);

    Page<EmployeeDTO> findByName(String name, Integer page);

    EmployeeDTO updateEmployee(Integer id, EmployeeDTO employeeDTO);

    Page<EmployeeDTO> listEmployeeByTeam(Integer teamID, Integer page);


    Page<EmployeeDTO> getPage(Integer pageIndex);

    List<EmployeeDTO> findByName(String name);

    List<EmployeeDTO> listEmployeeByTeam(Integer team_id);
}
