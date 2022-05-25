package vn.com.tma.training.ProjectTraining.service.ServiceImpl;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

import java.util.ArrayList;
import java.util.List;

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
    public Page<WorkingDTO> listWorking(Integer id, Integer page) {
        EmployeeEntity entity=employeeRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Employee is not found!"));
        Page<WorkingEntity> pageWorking = workingRepository.findAllByEmployee(entity, PageRequest.of(page - 1, 5, Sort.by("no")));
        return pageWorking.map(workingEntity -> workingMapper.toDTO(workingEntity));
    }
    @Override
    public List<WorkingDTO> getAll(Integer employee_id) {
        EmployeeEntity entity=employeeRepository.findById(employee_id).orElseThrow(()->new IllegalArgumentException("Employee is not found!"));
        List<WorkingEntity> pageWorking = workingRepository.findAllByEmployee(entity);
        List<WorkingDTO> dtos=new ArrayList<>();
        pageWorking.forEach(working->dtos.add(workingMapper.toDTO(working)));
        return dtos;
    }
    @SneakyThrows
    @Override
    public WorkingDTO addWorking(WorkingDTO workingDTO) {
        if (workingRepository.existsByEmployeeNoAndDate(workingDTO.getEmployee_id(), workingDTO.getDate()))
            throw new Exception("Working Date is existing!");

        EmployeeEntity employeeEntity = employeeRepository.findById(workingDTO.getEmployee_id()).orElseThrow(() -> new IllegalArgumentException("Employee is not found!"));
        WorkingEntity entity = workingMapper.toEntity(workingDTO, employeeEntity);
        return workingMapper.toDTO(workingRepository.save(entity));
    }

    @Override
    public void deleteWorking(Integer id) {
        WorkingEntity entity = workingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Working is not found!"));
        workingDeletedRepository.save(transferWorking.entityToDeleted(entity));
        workingRepository.delete(entity);

    }




}
