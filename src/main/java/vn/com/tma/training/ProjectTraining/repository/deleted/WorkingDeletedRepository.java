package vn.com.tma.training.ProjectTraining.repository.deleted;

import org.springframework.data.repository.CrudRepository;
import vn.com.tma.training.ProjectTraining.entity.entityDeleted.WorkingDeletedEntity;

public interface WorkingDeletedRepository extends CrudRepository<WorkingDeletedEntity, Integer> {
}
