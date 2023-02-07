package vn.com.tma.training.ProjectTraining.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.com.tma.training.ProjectTraining.dto.WorkingDTO;
import vn.com.tma.training.ProjectTraining.entity.EmployeeEntity;
import vn.com.tma.training.ProjectTraining.entity.WorkingEntity;

@Component
public class WorkingMapper {
    @Autowired
    private ModelMapper mapper;


    public WorkingDTO toDTO(WorkingEntity entity) {
        WorkingDTO workingDTO = new WorkingDTO();
        workingDTO.setNo(entity.getNo());
        workingDTO.setDate(entity.getDate());
        workingDTO.setHour(entity.getHour());
        workingDTO.setEmployee_id(entity.getEmployee().getNo());
        return workingDTO;
    }

    public WorkingEntity toEntity(WorkingDTO dto, EmployeeEntity employeeEntity) {
        WorkingEntity workingEntity = new WorkingEntity();
        workingEntity.setNo(dto.getNo());
        workingEntity.setDate(dto.getDate());
        workingEntity.setHour(dto.getHour());
        workingEntity.setEmployee(employeeEntity);
        return workingEntity;
    }
}
