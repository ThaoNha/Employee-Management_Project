package vn.com.tma.training.ProjectTraining.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.com.tma.training.ProjectTraining.dto.WorkingDTO;
import vn.com.tma.training.ProjectTraining.service.WorkingService;

import java.util.Set;

@RestController
public class WorkingController {
    @Autowired
    private WorkingService workingService;

    @GetMapping("/list-working/{id}")
    public Set<WorkingDTO> listWorking(@PathVariable Integer id) {
        return workingService.listWorking(id);
    }

    @PostMapping("/add-working/{id}")
    public WorkingDTO addWorking(@RequestBody WorkingDTO workingDTO, @PathVariable Integer id) {
        return workingService.addWorking(workingDTO, id);
    }

    @DeleteMapping("/delete-working/{id}")
    public void deleteWorking(@PathVariable Integer id) {
        workingService.deleteWorking(id);
    }

}
