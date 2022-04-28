package vn.com.tma.training.ProjectTraining.mapper;

import org.springframework.stereotype.Component;
import vn.com.tma.training.ProjectTraining.entity.EmployeeEntity;
import vn.com.tma.training.ProjectTraining.entity.entityDeleted.EmployeeDeletedEntity;
import vn.com.tma.training.ProjectTraining.entity.entityUpdated.EmployeeUpdatedEntity;

import java.util.Date;

@Component
public class TransferEmployee {
    public EmployeeDeletedEntity entityToDeleted(EmployeeEntity entity) {
        EmployeeDeletedEntity deletedEntity = new EmployeeDeletedEntity();
        deletedEntity.setNo(entity.getNo());
        deletedEntity.setFullName(entity.getFullName());
        deletedEntity.setAge(entity.getAge());
        deletedEntity.setSex(entity.isSex());
        deletedEntity.setAddress(entity.getAddress());
        deletedEntity.setMoneyPerHour(entity.getMoneyPerHour());
        deletedEntity.setPhone(entity.getPhone());
        deletedEntity.setStartDay(entity.getStartDay());
        deletedEntity.setTeamID(entity.getTeam().getNo());
        deletedEntity.setDeletedDate(new Date());
        return deletedEntity;
    } 
    public EmployeeUpdatedEntity entityToUpdated(EmployeeEntity entity){
        EmployeeUpdatedEntity updatedEntity=new EmployeeUpdatedEntity();
        updatedEntity.setNo(entity.getNo());
        updatedEntity.setFullName(entity.getFullName());
        updatedEntity.setAge(entity.getAge());
        updatedEntity.setSex(entity.isSex());
        updatedEntity.setAddress(entity.getAddress());
        updatedEntity.setMoneyPerHour(entity.getMoneyPerHour());
        updatedEntity.setPhone(entity.getPhone());
        updatedEntity.setStartDay(entity.getStartDay());
        updatedEntity.setTeamID(entity.getTeam().getNo());
        updatedEntity.setUpdatedDate(new Date());
        return updatedEntity;
    }
}
