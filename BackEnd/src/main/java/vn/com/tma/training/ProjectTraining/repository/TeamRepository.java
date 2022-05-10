package vn.com.tma.training.ProjectTraining.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import vn.com.tma.training.ProjectTraining.entity.TeamEntity;

import java.util.Set;

public interface TeamRepository extends CrudRepository<TeamEntity, Integer> {
    @Query(value = "Select * from Team team where team.name like %:name%", nativeQuery = true)
    Set<TeamEntity> findByTeamName(@Param("name") String name);

    TeamEntity findByName(String team);
}
