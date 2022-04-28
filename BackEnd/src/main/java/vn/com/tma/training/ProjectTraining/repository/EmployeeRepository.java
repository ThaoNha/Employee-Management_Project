package vn.com.tma.training.ProjectTraining.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import vn.com.tma.training.ProjectTraining.entity.EmployeeEntity;
import vn.com.tma.training.ProjectTraining.entity.TeamEntity;

import java.util.Set;

public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Integer> {
    Set<EmployeeEntity> findAllByFullNameContains(String name);

    @Query(value = "Select * from Employee em where em.full_name like %:name%", nativeQuery = true)
    Set<EmployeeEntity> findByName(@Param("name") String name);

    Set<EmployeeEntity> findAllByTeam(TeamEntity team);
}
