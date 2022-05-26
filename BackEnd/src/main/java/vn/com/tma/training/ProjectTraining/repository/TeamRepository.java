package vn.com.tma.training.ProjectTraining.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import vn.com.tma.training.ProjectTraining.entity.TeamEntity;

import java.util.List;

public interface TeamRepository extends CrudRepository<TeamEntity, Integer> {
    @Query(value = "Select * from Team team where team.name like %:name%", nativeQuery = true)
    Page<TeamEntity> findByTeamName(@Param("name") String name, Pageable of);

    List<TeamEntity> findByName(String team);

    Page<TeamEntity> findAll(Pageable of);

}
