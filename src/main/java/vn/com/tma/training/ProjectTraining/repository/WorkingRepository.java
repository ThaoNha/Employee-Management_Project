package vn.com.tma.training.ProjectTraining.repository;

import org.springframework.data.repository.CrudRepository;
import vn.com.tma.training.ProjectTraining.entity.WorkingEntity;

public interface WorkingRepository extends CrudRepository<WorkingEntity,Integer> {
    Iterable<WorkingEntity> findAllById(Integer id);

}
