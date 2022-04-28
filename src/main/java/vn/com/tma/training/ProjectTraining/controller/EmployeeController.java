package vn.com.tma.training.ProjectTraining.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.com.tma.training.ProjectTraining.dto.EmployeeDTO;
import vn.com.tma.training.ProjectTraining.service.EmployeeService;

import java.util.Set;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public String hello() {
        return "Hello";
    }

    @GetMapping("/employee")
    public Set<EmployeeDTO> listEmployee() {
        return employeeService.listEmployee();
    }

    @PostMapping("/new-employee")
    public EmployeeDTO newEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return employeeService.newEmployee(employeeDTO);
    }

    @GetMapping("/employee-id/{id}")
    public EmployeeDTO findEmployee(@PathVariable Integer id) {
        return employeeService.findEmployee(id);
    }

    @DeleteMapping("/delete-employee/{id}")
    public void deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
    }

    @GetMapping("/employee-name/{name}")
    public Set<EmployeeDTO> findByName(@PathVariable String name) {
        return employeeService.findByName(name);
    }

    @PutMapping("/update-employee/{id}")
    public EmployeeDTO updateEmployee(@PathVariable Integer id, @RequestBody EmployeeDTO employeeDTO) {
        return employeeService.updateEmployee(id, employeeDTO);
    }

    @GetMapping("/list-employee-by-team/{teamID}")
    public Set<EmployeeDTO> listEmployeeByTeam(@PathVariable Integer teamID) {
        return employeeService.listEmployeeByTeam(teamID);
    }

}
