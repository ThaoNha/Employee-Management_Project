package vn.com.tma.training.ProjectTraining.service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.com.tma.training.ProjectTraining.dto.AdvanceDTO;
import vn.com.tma.training.ProjectTraining.entity.AdvanceEntity;
import vn.com.tma.training.ProjectTraining.entity.EmployeeEntity;
import vn.com.tma.training.ProjectTraining.mapper.AdvanceMapper;
import vn.com.tma.training.ProjectTraining.mapper.TransferAdvance;
import vn.com.tma.training.ProjectTraining.repository.AdvanceRepository;
import vn.com.tma.training.ProjectTraining.repository.EmployeeRepository;
import vn.com.tma.training.ProjectTraining.repository.deleted.AdvanceDeletedRepository;
import vn.com.tma.training.ProjectTraining.service.AdvanceService;

@Service
public class AdvanceImpl implements AdvanceService {
    @Autowired
    private AdvanceMapper advanceMapper;
    @Autowired
    private AdvanceRepository advanceRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private TransferAdvance transferAdvance;
    @Autowired
    private AdvanceDeletedRepository advanceDeletedRepository;

    @Override
    public Page<AdvanceDTO> listAdvance(Integer id, Integer page) {
        EmployeeEntity entity=employeeRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Employee is not found!"));
        Page<AdvanceEntity> pageAdvance = advanceRepository.findAllByEmployee(entity, PageRequest.of(page - 1, 5, Sort.by("no")));
        return pageAdvance.map(advanceEntity -> advanceMapper.toDTO(advanceEntity));
    }

    @Override
    public AdvanceDTO addAdvance(AdvanceDTO advanceDTO) {
        EmployeeEntity employeeEntity = employeeRepository.findById(advanceDTO.getEmployee_id()).orElseThrow(() -> new IllegalArgumentException("Employee is not found!"));
        AdvanceEntity entity = advanceMapper.toEntity(advanceDTO, employeeEntity);
        return advanceMapper.toDTO(advanceRepository.save(entity));
    }

    @Override
    public void deleteAdvance(Integer id) {
        AdvanceEntity entity = advanceRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Advance is not found!"));
        advanceDeletedRepository.save(transferAdvance.entityToDeleted(entity));
        advanceRepository.delete(entity);

    }


}
