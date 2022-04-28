package vn.com.tma.training.ProjectTraining.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.com.tma.training.ProjectTraining.dto.TeamDTO;
import vn.com.tma.training.ProjectTraining.service.TeamService;

import java.util.List;
import java.util.Set;

@RestController
public class TeamController {
    @Autowired
    private TeamService teamService;

    @GetMapping("/list-team")
    public Set<TeamDTO> listTeam() {
        return teamService.listTeam();
    }

    @GetMapping("/find-team-by-name/{name}")
    public Set<TeamDTO> findTeamByName(@PathVariable String name) {
        return teamService.findTeamByName(name);
    }

    @PostMapping("/add-team")
    public TeamDTO addTeam(@RequestBody TeamDTO teamDTO) {
        return teamService.addTeam(teamDTO);
    }

    @PutMapping("/update-team/{id}")
    public TeamDTO updateTeam(@PathVariable Integer id, @RequestBody TeamDTO teamDTO) {
        return teamService.updateTeam(id, teamDTO);
    }

}
