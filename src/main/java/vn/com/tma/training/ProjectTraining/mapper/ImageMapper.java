package vn.com.tma.training.ProjectTraining.mapper;


import org.springframework.stereotype.Component;
import vn.com.tma.training.ProjectTraining.dto.ImageDTO;
import vn.com.tma.training.ProjectTraining.entity.EmployeeEntity;
import vn.com.tma.training.ProjectTraining.entity.ImageEntity;

@Component
public class ImageMapper {


    public ImageEntity toEntity(ImageDTO dto, EmployeeEntity employee) {
        ImageEntity entity=new ImageEntity();
        entity.setEmployee(employee);
        entity.setContentType(dto.getContentType());
        entity.setData(dto.getData());
        return entity;
    }

    public ImageDTO toDTO(ImageEntity entity) {
        ImageDTO dto=new ImageDTO();
        dto.setNo(entity.getNo());
        dto.setEmployeeID(entity.getNo());
        dto.setContentType(entity.getContentType());
        dto.setData(entity.getData());
        return dto;
    }
}
