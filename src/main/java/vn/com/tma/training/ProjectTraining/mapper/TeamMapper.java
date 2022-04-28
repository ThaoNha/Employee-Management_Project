package vn.com.tma.training.ProjectTraining.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.com.tma.training.ProjectTraining.dto.TeamDTO;
import vn.com.tma.training.ProjectTraining.entity.TeamEntity;
@Component
public class TeamMapper {
    @Autowired
    private ModelMapper mapper;
    public TeamEntity toEntity(TeamDTO dto){
        return mapper.map(dto,TeamEntity.class);
    }
    public TeamDTO toDTO(TeamEntity entity){
        return mapper.map(entity,TeamDTO.class);
    }
}
