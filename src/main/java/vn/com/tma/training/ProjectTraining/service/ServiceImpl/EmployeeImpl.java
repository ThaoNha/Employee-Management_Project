package vn.com.tma.training.ProjectTraining.service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.tma.training.ProjectTraining.dto.EmployeeDTO;
import vn.com.tma.training.ProjectTraining.entity.EmployeeEntity;
import vn.com.tma.training.ProjectTraining.entity.TeamEntity;
import vn.com.tma.training.ProjectTraining.entity.WorkingEntity;
import vn.com.tma.training.ProjectTraining.entity.entityDeleted.EmployeeDeletedEntity;
import vn.com.tma.training.ProjectTraining.entity.entityDeleted.WorkingDeletedEntity;
import vn.com.tma.training.ProjectTraining.entity.entityUpdated.EmployeeUpdatedEntity;
import vn.com.tma.training.ProjectTraining.mapper.EmployeeMapper;
import vn.com.tma.training.ProjectTraining.mapper.TransferEmployee;
import vn.com.tma.training.ProjectTraining.mapper.TransferWorking;
import vn.com.tma.training.ProjectTraining.repository.EmployeeRepository;
import vn.com.tma.training.ProjectTraining.repository.TeamRepository;
import vn.com.tma.training.ProjectTraining.repository.WorkingRepository;
import vn.com.tma.training.ProjectTraining.repository.deleted.EmployeeDeletedRepository;
import vn.com.tma.training.ProjectTraining.repository.deleted.WorkingDeletedRepository;
import vn.com.tma.training.ProjectTraining.repository.updated.EmployeeUpdatedRepository;
import vn.com.tma.training.ProjectTraining.service.EmployeeService;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class EmployeeImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRep;
    @Autowired
    private EmployeeMapper mapper;
    @Autowired
    private TeamRepository teamRep;
    @Autowired
    private TransferEmployee transferEmployee;
    @Autowired
    private TransferWorking transferWorking;
    @Autowired
    private EmployeeDeletedRepository employeeDeletedRepository;
    @Autowired
    private EmployeeUpdatedRepository employeeUpdatedRepository;
    @Autowired
    private WorkingDeletedRepository workingDeletedRepository;
    @Autowired
    private WorkingRepository workingRepository;

    @Override
    public Set<EmployeeDTO> listEmployee() {
        Set<EmployeeDTO> employeeDTOSet = new HashSet<>();
//      Set<EmployeeEntity> entitySet=employeeRep.findAll();
//      for (EmployeeEntity entity : entitySet) {
//          employeeDTOSet.add(mapper.toDTO(entity));
//       }
        employeeRep.findAll().forEach(employee -> {
            employeeDTOSet.add(mapper.toDTO(employee));
        });
        return employeeDTOSet;
    }

    @Override
    public EmployeeDTO newEmployee(EmployeeDTO employeeDTO) {
        TeamEntity teamEntity = teamRep.findByName(employeeDTO.getTeam());
        EmployeeEntity entity = employeeRep.save(mapper.toEntity(employeeDTO, teamEntity));
        return mapper.toDTO(entity);
    }

    @Override
    public EmployeeDTO findEmployee(Integer id) {

        Optional<EmployeeEntity> entityOptional = employeeRep.findById(id);
        if (entityOptional.isPresent()) {
            EmployeeEntity entity = entityOptional.get();
            return mapper.toDTO(entity);
        }
        return null;
    }

    @Override
    public void deleteEmployee(Integer id) {
        EmployeeEntity entity = employeeRep.findById(id).get();
        EmployeeDeletedEntity deletedEntity = transferEmployee.entityToDeleted(entity);
        Iterable<WorkingEntity> workingEntitySet = workingRepository.findAllById(id);
        if (workingEntitySet != null) {
            workingEntitySet.forEach(workingEntity -> {
                WorkingDeletedEntity workingDeletedEntity = transferWorking.entityToDeleted(workingEntity);
                workingDeletedRepository.save(workingDeletedEntity);
                workingRepository.delete(workingEntity);
            });
        }
        employeeDeletedRepository.save(deletedEntity);
        employeeRep.delete(entity);
    }

    @Override
    public Set<EmployeeDTO> findByName(String name) {
        Set<EmployeeDTO> employeeDTOSet = new HashSet<>();
        Set<EmployeeEntity> employeeEntitySet = employeeRep.findByName(name);
        if (employeeEntitySet != null) {
            employeeEntitySet.forEach(employeeEntity -> {
                employeeDTOSet.add(mapper.toDTO(employeeEntity));
            });
            return employeeDTOSet;
        }
        return null;
    }

    @Override
    public EmployeeDTO updateEmployee(Integer id, EmployeeDTO employeeDTO) {
        Optional<EmployeeEntity> entityOptional = employeeRep.findById(id);
        if (entityOptional.isPresent()) {
            EmployeeEntity entity = entityOptional.get();
            EmployeeUpdatedEntity updatedEntity = transferEmployee.entityToUpdated(entity);
            employeeUpdatedRepository.save(updatedEntity);
            if (!employeeDTO.getFullName().equals("") && employeeDTO.getFullName() != null) {
                entity.setFullName(employeeDTO.getFullName());
            }
            if (employeeDTO.getAge() > 0 && employeeDTO.getAge() < 70) {
                entity.setAge(employeeDTO.getAge());
            }
            if (!employeeDTO.getStartDay().after(new Date())) {
                entity.setStartDay(employeeDTO.getStartDay());
            }
            if (!employeeDTO.getTeam().equals(entity.getTeam().getName())) {
                TeamEntity teamEntity = teamRep.findByName(employeeDTO.getTeam());
                entity.setTeam(teamEntity);
            }
            if (!employeeDTO.getAddress().equals(entity.getAddress())) {
                entity.setAddress(employeeDTO.getAddress());
            }
            if (employeeDTO.getMoneyPerHour() != entity.getMoneyPerHour() & employeeDTO.getMoneyPerHour() > 0) {
                entity.setMoneyPerHour(employeeDTO.getMoneyPerHour());
            }
            entity.setSex(employeeDTO.isSex());

            return mapper.toDTO(employeeRep.save(entity));
        }
        return null;

    }

    @Override
    public Set<EmployeeDTO> listEmployeeByTeam(Integer teamID) {
        Set<EmployeeDTO> employeeDTOSet = new HashSet<>();
        Optional<TeamEntity> team = teamRep.findById(teamID);
        if (team.isPresent()) {
            TeamEntity teamEntity = team.get();
            employeeRep.findAllByTeam(teamEntity).forEach(employeeEntity -> {
                employeeDTOSet.add(mapper.toDTO(employeeEntity));
            });
            return employeeDTOSet;
        }

        return null;
    }
}
