package vn.com.tma.training.ProjectTraining.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import vn.com.tma.training.ProjectTraining.entity.WorkingEntity;

public interface WorkingRepository extends CrudRepository<WorkingEntity,Integer> {
    @Query(value = "select w.* from working w where w.employee_id=:employee_id",nativeQuery = true)
    Iterable<WorkingEntity> findAllById(@Param("employee_id") Integer employee_id);

}
