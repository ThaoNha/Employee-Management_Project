package vn.com.tma.training.ProjectTraining.service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.tma.training.ProjectTraining.dto.TeamDTO;
import vn.com.tma.training.ProjectTraining.entity.TeamEntity;
import vn.com.tma.training.ProjectTraining.mapper.TeamMapper;
import vn.com.tma.training.ProjectTraining.repository.TeamRepository;
import vn.com.tma.training.ProjectTraining.service.TeamService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service

public class TeamImpl implements TeamService {
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private TeamMapper mapper;

    @Override
    public Set<TeamDTO> listTeam() {
        Set<TeamDTO> dtoSet = new HashSet<>();
        teamRepository.findAll().forEach(team -> {
            dtoSet.add(mapper.toDTO(team));
        });
        return dtoSet;
    }

    @Override
    public Set<TeamDTO> findTeamByName(String name) {
        Set<TeamDTO> teamDTOSet = new HashSet<>();
        teamRepository.findByTeamName(name).forEach(teamEntity -> {
            teamDTOSet.add(mapper.toDTO(teamEntity));
        });
        return teamDTOSet;
    }

    @Override
    public TeamDTO addTeam(TeamDTO teamDTO) {
        TeamEntity teamEntity = teamRepository.save(mapper.toEntity(teamDTO));
        return mapper.toDTO(teamEntity);
    }

    @Override
    public TeamDTO updateTeam(Integer id, TeamDTO teamDTO) {

        TeamEntity entity = teamRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Team is not found!"));
        entity.setName(teamDTO.getName());
        return mapper.toDTO(teamRepository.save(entity));


    }

    @Override
    public void delete(Integer id) {
        TeamEntity entity=teamRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Team is not found!"));
        teamRepository.delete(entity);
    }
}
