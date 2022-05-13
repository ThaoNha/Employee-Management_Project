package vn.com.tma.training.ProjectTraining.service;

import org.springframework.data.domain.Page;
import vn.com.tma.training.ProjectTraining.dto.TeamDTO;

public interface TeamService {
    Page<TeamDTO> listTeam(Integer page);

    Page<TeamDTO> findTeamByName(String name, Integer page);

    TeamDTO addTeam(TeamDTO teamDTO);

    TeamDTO updateTeam(Integer id, TeamDTO teamDTO);

    void delete(Integer id);
}
