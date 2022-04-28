package vn.com.tma.training.ProjectTraining.service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.tma.training.ProjectTraining.dto.WorkingDTO;
import vn.com.tma.training.ProjectTraining.entity.EmployeeEntity;
import vn.com.tma.training.ProjectTraining.entity.WorkingEntity;
import vn.com.tma.training.ProjectTraining.mapper.TransferWorking;
import vn.com.tma.training.ProjectTraining.mapper.WorkingMapper;
import vn.com.tma.training.ProjectTraining.repository.EmployeeRepository;
import vn.com.tma.training.ProjectTraining.repository.WorkingRepository;
import vn.com.tma.training.ProjectTraining.repository.deleted.WorkingDeletedRepository;
import vn.com.tma.training.ProjectTraining.service.WorkingService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class WorkingImpl implements WorkingService {
    @Autowired
    private WorkingMapper workingMapper;
    @Autowired
    private WorkingRepository workingRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private TransferWorking transferWorking;
    @Autowired
    private WorkingDeletedRepository workingDeletedRepository;

    @Override
    public Set<WorkingDTO> listWorking(Integer id) {
        Set<WorkingDTO> workingDTOSet = new HashSet<>();
        workingRepository.findAllById(id).forEach(workingEntity -> {
            workingDTOSet.add(workingMapper.toDTO(workingEntity));
        });
        return workingDTOSet;
    }

    @Override
    public WorkingDTO addWorking(WorkingDTO workingDTO, Integer id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
        WorkingEntity entity = workingMapper.toEntity(workingDTO,employeeEntity);
        return workingMapper.toDTO(workingRepository.save(entity));
    }

    @Override
    public void deleteWorking(Integer id) {
        Optional<WorkingEntity> optionalWorking = workingRepository.findById(id);
        if (optionalWorking.isPresent()) {
            WorkingEntity entity = optionalWorking.get();
            workingDeletedRepository.save(transferWorking.entityToDeleted(entity));
            workingRepository.delete(entity);
        }
    }


}
