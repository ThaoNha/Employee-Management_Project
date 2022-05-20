package vn.com.tma.training.ProjectTraining.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import vn.com.tma.training.ProjectTraining.entity.EmployeeEntity;
import vn.com.tma.training.ProjectTraining.entity.TeamEntity;

import java.util.Set;

public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Integer> {
    Set<EmployeeEntity> findAllByFullNameContains(String name);

    Page<EmployeeEntity> findByFullNameContainingIgnoreCase(String name, Pageable of);

    Page<EmployeeEntity> findAllByTeam(TeamEntity team, Pageable of);

    @Query(value = "select e.no, e.full_name, e.age, e.male, e.address, e.money_per_hour,e.phone,e.start-day from employee e,", nativeQuery = true)
    Page<EmployeeEntity> findAll(Pageable pageable);

    @Query(value = "select e.money_per_hour from Employee e where e.no=:id", nativeQuery = true)
    double findMoneyPerHourByNo(Integer id);
}
