package vn.com.tma.training.ProjectTraining.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.com.tma.training.ProjectTraining.common.MessageResponse;
import vn.com.tma.training.ProjectTraining.dto.EmployeeDTO;
import vn.com.tma.training.ProjectTraining.service.EmployeeService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/employee")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/get-all")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.ok(employeeService.listEmployee());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(MessageResponse.builder().message(e.getMessage()).build());
        }
    }

    @GetMapping("/get-page")
    public ResponseEntity<?> getPage(@RequestParam(required = false, defaultValue = "1") Integer page) {
        if (page == 0)
            ResponseEntity.badRequest().body("Page must be greater than 0!");
        try {
            return ResponseEntity.ok(employeeService.getPage(page - 1));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(MessageResponse.builder().message(e.getMessage()).build());

        }

    }

    @GetMapping("/find-by-name")
    public ResponseEntity<?> findByName(@RequestParam String name, @RequestParam(required = false, defaultValue = "1") Integer page) {
        if (page == 0)
            ResponseEntity.badRequest().body("Page must be greater than 0!");
        try {
            return ResponseEntity.ok(employeeService.findByName(name, page));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(MessageResponse.builder().message(e.getMessage()).build());
        }

    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<?> findEmployee(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(employeeService.findEmployee(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(MessageResponse.builder().message(e.getMessage()).build());
        }

    }

    @GetMapping("/find-by-team/{team_id}")
    public ResponseEntity<?> listEmployeeByTeam(@PathVariable Integer team_id, @RequestParam(required = false, defaultValue = "1") Integer page) {
        if (page == 0)
            ResponseEntity.badRequest().body("Page must be greater than 0!");
        try {
            return ResponseEntity.ok(employeeService.listEmployeeByTeam(team_id, page));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(MessageResponse.builder().message(e.getMessage()).build());

        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> newEmployee(@RequestBody @Valid EmployeeDTO employeeDTO) {
        try {
            return ResponseEntity.ok(employeeService.newEmployee(employeeDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(MessageResponse.builder().message(e.getMessage()).build());
        }
    }

    @PostMapping("/create-all")
    public ResponseEntity<?> createAll(@RequestBody @Valid List<EmployeeDTO> list) {
        try {
            list.forEach(employeeDTO -> employeeService.newEmployee(employeeDTO));
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(MessageResponse.builder().message(e.getMessage()).build());
        }
    }

    @PutMapping("/update/{employee_id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Integer employee_id, @RequestBody @Valid EmployeeDTO employeeDTO) {

        try {
            return ResponseEntity.ok(employeeService.updateEmployee(employee_id, employeeDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(MessageResponse.builder().message(e.getMessage()).build());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Integer id) {
        try {
            employeeService.deleteEmployee(id);
            return ResponseEntity.ok().body("Delete Employee is successful!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(MessageResponse.builder().message(e.getMessage()).build());
        }

    }

    @DeleteMapping("/delete-multi")
    public ResponseEntity<?> deleteAll(@RequestParam(value = "ids") List<Integer> ids) {
        String s = employeeService.deleteAll(ids);
        return ResponseEntity.ok().body(s.isEmpty() ? "Delete Employees is successful!" : s);

    }


}
