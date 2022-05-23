package vn.com.tma.training.ProjectTraining.mapper;

import org.springframework.stereotype.Component;
import vn.com.tma.training.ProjectTraining.entity.WorkingEntity;
import vn.com.tma.training.ProjectTraining.entity.entityDeleted.WorkingDeletedEntity;

@Component
public class TransferWorking {
    public WorkingDeletedEntity entityToDeleted(WorkingEntity entity) {
        WorkingDeletedEntity deletedEntity = new WorkingDeletedEntity();
        deletedEntity.setId(entity.getNo());
        deletedEntity.setDate(entity.getDate());
        deletedEntity.setHour(entity.getHour());
        deletedEntity.setEmployeeID(entity.getEmployee().getNo());
        return deletedEntity;
    }
}
