package vn.com.tma.training.ProjectTraining.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import vn.com.tma.training.ProjectTraining.entity.AdvanceEntity;

public interface AdvanceRepository extends CrudRepository<AdvanceEntity, Integer> {
    @Query(value = "select w.* from advance w where w.employee_id=:employee_id", nativeQuery = true)
    Iterable<AdvanceEntity> findAllById(@Param("employee_id") Integer employee_id);

}
