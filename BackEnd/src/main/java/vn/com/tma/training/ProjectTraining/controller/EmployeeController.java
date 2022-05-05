package vn.com.tma.training.ProjectTraining.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.com.tma.training.ProjectTraining.common.ErrorResponse;
import vn.com.tma.training.ProjectTraining.dto.EmployeeDTO;
import vn.com.tma.training.ProjectTraining.service.EmployeeService;

import java.util.Set;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;


    @GetMapping("/get-all")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.ok(employeeService.listEmployee());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ErrorResponse.builder().message(e.getMessage()).build());
        }
    }
    @GetMapping("/get-all/{index}")
    public ResponseEntity<?> getPage(@PathVariable Integer pageIndex){
        try{
            return ResponseEntity.ok(employeeService.getPage(pageIndex-1));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(ErrorResponse.builder().message(e.getMessage()).build());

        }

    }
    @PostMapping("/create")
    public ResponseEntity<?> newEmployee(@RequestBody EmployeeDTO employeeDTO) {
        try {
            return ResponseEntity.ok(employeeService.newEmployee(employeeDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ErrorResponse.builder().message(e.getMessage()).build());
        }
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<?> findEmployee(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(employeeService.findEmployee(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ErrorResponse.builder().message(e.getMessage()).build());
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Integer id) {
        try {
            employeeService.deleteEmployee(id);
            return ResponseEntity.ok().body("Delete Employee is successful!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ErrorResponse.builder().message(e.getMessage()).build());
        }

    }

    @DeleteMapping("/delete-all")
    public ResponseEntity<?> deleteAll(@RequestBody Set<Integer> set){
        try{
            employeeService.deleteAll(set);
            return ResponseEntity.ok().body("Delete Employees is successful!");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(ErrorResponse.builder().message(e.getMessage()).build());

        }
    }

    @GetMapping("/find-by-name")
    public ResponseEntity<?> findByName(@RequestParam String name) {
        try {
            return ResponseEntity.ok(employeeService.findByName(name));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ErrorResponse.builder().message(e.getMessage()).build());

        }

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Integer id, @RequestBody EmployeeDTO employeeDTO) {
        try {
            return  ResponseEntity.ok(employeeService.updateEmployee(id, employeeDTO));
        }catch (Exception e ){
            return ResponseEntity.badRequest().body(ErrorResponse.builder().message(e.getMessage()).build());
        }
    }

    @GetMapping("/find-by-team/{teamID}")
    public ResponseEntity<?> listEmployeeByTeam(@PathVariable Integer teamID) {
        try{
            return ResponseEntity.ok(employeeService.listEmployeeByTeam(teamID));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(ErrorResponse.builder().message(e.getMessage()).build());

        }
    }

}
