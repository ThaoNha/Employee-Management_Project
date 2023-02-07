package vn.com.tma.training.ProjectTraining.service;

import org.springframework.data.domain.Page;
import vn.com.tma.training.ProjectTraining.dto.WorkingDTO;

import java.util.List;
import java.util.Set;

public interface WorkingService {
    Page<WorkingDTO> listWorking(Integer id, Integer page);

    WorkingDTO addWorking(WorkingDTO workingDTO);

    void deleteWorking(Integer id);

    List<WorkingDTO> getAll(Integer employee_id);
}
