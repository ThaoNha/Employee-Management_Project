package vn.com.tma.training.ProjectTraining.repository;

import org.springframework.data.repository.CrudRepository;
import vn.com.tma.training.ProjectTraining.entity.EmployeeEntity;
import vn.com.tma.training.ProjectTraining.entity.ImageEntity;

public interface ImageRepository extends CrudRepository<ImageEntity, Integer> {
    ImageEntity findByEmployee(EmployeeEntity entity);
}
