package vn.com.tma.training.ProjectTraining.service;

import org.springframework.data.domain.Page;
import vn.com.tma.training.ProjectTraining.dto.AdvanceDTO;

import java.util.Set;

public interface AdvanceService {
    Page<AdvanceDTO> listAdvance(Integer id, Integer page);

    AdvanceDTO addAdvance(AdvanceDTO advanceDTO);

    void deleteAdvance(Integer id);

}