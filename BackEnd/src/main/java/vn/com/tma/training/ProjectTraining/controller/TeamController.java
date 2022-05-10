package vn.com.tma.training.ProjectTraining.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.com.tma.training.ProjectTraining.common.MessageResponse;
import vn.com.tma.training.ProjectTraining.dto.TeamDTO;
import vn.com.tma.training.ProjectTraining.service.TeamService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/team")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TeamController {
    @Autowired
    private TeamService teamService;

    @GetMapping("/get-all")
    public ResponseEntity<?> listTeam() {
        try {
            return ResponseEntity.ok(teamService.listTeam());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(MessageResponse.builder().message(e.getMessage()).build());
        }

    }

    @GetMapping("/find-by-name")
    public ResponseEntity<?> findTeamByName(@RequestParam String name) {

        try {
            return ResponseEntity.ok(teamService.findTeamByName(name));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(MessageResponse.builder().message(e.getMessage()).build());
        }


    }

    @PostMapping("/create")
    public ResponseEntity<?> addTeam(@RequestBody @Valid TeamDTO teamDTO) {
        try {
            return ResponseEntity.ok(teamService.addTeam(teamDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(MessageResponse.builder().message(e.getMessage()).build());

        }

    }

    @PutMapping("/update-team/{id}")
    public ResponseEntity<?> updateTeam(@PathVariable Integer id, @RequestBody @Valid TeamDTO teamDTO) {
        try {
            return ResponseEntity.ok(teamService.updateTeam(id, teamDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(MessageResponse.builder().message(e.getMessage()).build());

        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTeam(@PathVariable Integer id) {
        try {
            teamService.delete(id);
            return ResponseEntity.ok().body("Delete Team is  successful!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(MessageResponse.builder().message(e.getMessage()).build());

        }
    }

}
