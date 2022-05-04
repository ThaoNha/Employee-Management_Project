package vn.com.tma.training.ProjectTraining.service;

import vn.com.tma.training.ProjectTraining.dto.TeamDTO;

import java.util.Set;

public interface TeamService {
    Set<TeamDTO> listTeam();

    Set<TeamDTO> findTeamByName(String name);

    TeamDTO addTeam(TeamDTO teamDTO);

    TeamDTO updateTeam(Integer id, TeamDTO teamDTO);

    void delete(Integer id);
}
