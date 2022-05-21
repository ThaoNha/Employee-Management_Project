package vn.com.tma.training.ProjectTraining.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import vn.com.tma.training.ProjectTraining.entity.EmployeeEntity;
import vn.com.tma.training.ProjectTraining.entity.TeamEntity;

import java.util.Set;

public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Integer> {
    Set<EmployeeEntity> findAllByFullNameContains(String name);

    @Query(value =
            "SELECT e.no,e.full_name,e.age,e.is_male,e.money_per_hour,e.address,e.phone,e.start_day,e.team_id from employee e where e.full_name ilike '%:name%'", nativeQuery = true)
    Page<EmployeeEntity> findByFullNameContainingIgnoreCase(@Param("name") String name, Pageable of);
    @Query(value =
            "SELECT e.no,e.full_name,e.age,e.is_male,e.money_per_hour,e.address,e.phone,e.start_day,e.team_id from employee e where e.team_id = :team_id", nativeQuery = true)
    Page<EmployeeEntity> findAllByTeam(@Param("team_id") Integer teamID, Pageable of);


    Page<EmployeeEntity> findAll(Pageable pageable);

    @Query(value = "select e.money_per_hour from Employee e where e.no=:id", nativeQuery = true)
    double findMoneyPerHourByNo(Integer id);
}
