package vn.com.tma.training.ProjectTraining.service;

import vn.com.tma.training.ProjectTraining.dto.WorkingDTO;

import java.util.Set;

public interface WorkingService {
    Set<WorkingDTO> listWorking(Integer id);

    WorkingDTO addWorking(WorkingDTO workingDTO);

    void deleteWorking(Integer id);

}
