package vn.com.tma.training.ProjectTraining.service;

import vn.com.tma.training.ProjectTraining.dto.AdvanceDTO;

import java.util.Set;

public interface AdvanceService {
    Set<AdvanceDTO> listAdvance(Integer id);

    AdvanceDTO addAdvance(AdvanceDTO advanceDTO);

    void deleteAdvance(Integer id);

}