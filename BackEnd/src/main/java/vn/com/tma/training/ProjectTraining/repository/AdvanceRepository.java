package vn.com.tma.training.ProjectTraining.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import vn.com.tma.training.ProjectTraining.entity.AdvanceEntity;
import vn.com.tma.training.ProjectTraining.entity.EmployeeEntity;

import java.time.LocalDate;
import java.util.List;

public interface AdvanceRepository extends CrudRepository<AdvanceEntity, Integer> {

    Page<AdvanceEntity> findAllByEmployee(EmployeeEntity entity, Pageable of);

    @Query(value = "select a.* from advance a where employee_no=:id and a.date between :startDate and :endDate", nativeQuery = true)
    List<AdvanceEntity> findAllByEmployeeNoAndMonth(@Param("id") Integer id, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    Iterable<AdvanceEntity> findAllByEmployeeNo(Integer id);
}
