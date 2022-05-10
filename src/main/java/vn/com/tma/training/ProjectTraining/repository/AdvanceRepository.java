package vn.com.tma.training.ProjectTraining.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import vn.com.tma.training.ProjectTraining.entity.AdvanceEntity;

import java.util.List;

public interface AdvanceRepository extends CrudRepository<AdvanceEntity, Integer> {
    Iterable<AdvanceEntity> findAllByEmployeeNo(Integer employee_id);

    @Query(value = "select a.* from advance a where employee_id=:id and month(a.date)=:month", nativeQuery = true)
    List<AdvanceEntity> findAllByEmployeeNoAndMonth(@Param("id") Integer id, @Param("month") int month);
}
