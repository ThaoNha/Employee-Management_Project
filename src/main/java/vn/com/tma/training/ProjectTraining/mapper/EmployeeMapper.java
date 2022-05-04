package vn.com.tma.training.ProjectTraining.mapper;

import org.springframework.stereotype.Component;
import vn.com.tma.training.ProjectTraining.dto.EmployeeDTO;
import vn.com.tma.training.ProjectTraining.entity.EmployeeEntity;
import vn.com.tma.training.ProjectTraining.entity.TeamEntity;

@Component
public class EmployeeMapper {


    public EmployeeDTO toDTO(EmployeeEntity entity) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setNo(entity.getNo());
        dto.setFullName(entity.getFullName());
        dto.setAge(entity.getAge());
        dto.setSex(entity.isSex());
        dto.setAddress(entity.getAddress());
        dto.setMoneyPerHour(entity.getMoneyPerHour());
        dto.setPhone(entity.getPhone());
        dto.setStartDay(entity.getStartDay());
        dto.setTeamID(entity.getTeam().getNo());
        return dto;
    }

    public EmployeeEntity toEntity(EmployeeDTO dto, TeamEntity team) {
        EmployeeEntity entity = new EmployeeEntity();
        entity.setNo(dto.getNo());
        entity.setFullName(dto.getFullName());
        entity.setAge(dto.getAge());
        entity.setSex(dto.isSex());
        entity.setAddress(dto.getAddress());
        entity.setMoneyPerHour(dto.getMoneyPerHour());
        entity.setPhone(dto.getPhone());
        entity.setStartDay(dto.getStartDay());
        entity.setTeam(team);

        return entity;
    }


}
