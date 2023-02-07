package vn.com.tma.training.ProjectTraining.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.com.tma.training.ProjectTraining.common.MessageResponse;
import vn.com.tma.training.ProjectTraining.dto.WorkingDTO;
import vn.com.tma.training.ProjectTraining.service.WorkingService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/working")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class WorkingController {
    @Autowired
    private WorkingService workingService;

    @GetMapping("/get-page/{employee_id}")
    public ResponseEntity<?> listWorking(@PathVariable Integer employee_id, @RequestParam(required = false, defaultValue = "1") Integer page) {
        if (page == 0)
            ResponseEntity.badRequest().body("Page must be greater than 0!");
        try {
            return ResponseEntity.ok(workingService.listWorking(employee_id, page));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(MessageResponse.builder().message(e.getMessage()).build());

        }
    }

    @GetMapping("/get-all/{employee_id}")
    public ResponseEntity<?> listWorking(@PathVariable Integer employee_id) {
        try {
            return ResponseEntity.ok(workingService.getAll(employee_id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(MessageResponse.builder().message(e.getMessage()).build());

        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> addWorking(@RequestBody @Valid WorkingDTO workingDTO) {
        try {

            return ResponseEntity.ok(workingService.addWorking(workingDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(MessageResponse.builder().message(e.getMessage()).build());

        }

    }

    @DeleteMapping("/delete/{working_id}")
    public ResponseEntity<?> deleteWorking(@PathVariable Integer working_id) {
        try {
            workingService.deleteWorking(working_id);
            return ResponseEntity.ok().body("Delete Working is successful!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(MessageResponse.builder().message(e.getMessage()).build());
        }


    }

}
