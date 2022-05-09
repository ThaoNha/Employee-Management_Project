package vn.com.tma.training.ProjectTraining.mapper;

import org.springframework.stereotype.Component;
import vn.com.tma.training.ProjectTraining.entity.AdvanceEntity;
import vn.com.tma.training.ProjectTraining.entity.entityDeleted.AdvanceDeletedEntity;

@Component
public class TransferAdvance {
    public AdvanceDeletedEntity entityToDeleted(AdvanceEntity entity) {
        AdvanceDeletedEntity deletedEntity = new AdvanceDeletedEntity();
        deletedEntity.setId(entity.getId());
        deletedEntity.setDate(entity.getDate());
        deletedEntity.setMoney(entity.getMoney());
        deletedEntity.setEmployeeID(entity.getEmployee().getNo());
        return deletedEntity;
    }
}
