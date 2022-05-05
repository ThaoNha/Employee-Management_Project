package vn.com.tma.training.ProjectTraining.service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.com.tma.training.ProjectTraining.common.ErrorResponse;
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
import java.util.List;
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
        TeamEntity teamEntity = teamRep.findById(employeeDTO.getTeamID()).orElseThrow(() -> new IllegalArgumentException("Team is not found!"));
        EmployeeEntity entity = employeeRep.save(mapper.toEntity(employeeDTO, teamEntity));
        return mapper.toDTO(entity);
    }

    @Override
    public EmployeeDTO findEmployee(Integer id) {
        EmployeeEntity entity = employeeRep.findById(id).orElseThrow(() -> new IllegalArgumentException("Employee is not found!"));
        return mapper.toDTO(entity);

    }

    @Override
    public void deleteEmployee(Integer id) {
        EmployeeEntity entity = employeeRep.findById(id).orElseThrow(() -> new IllegalArgumentException("Employee_id: " + id + " is not found!"));
        EmployeeDeletedEntity deletedEntity = transferEmployee.entityToDeleted(entity);
        Iterable<WorkingEntity> workingEntitySet = workingRepository.findAllById(id);
        if (workingEntitySet != null) {
            workingEntitySet.forEach(workingEntity -> {
                WorkingDeletedEntity workingDeletedEntity = transferWorking.entityToDeleted(workingEntity);
                workingDeletedRepository.save(workingDeletedEntity);
                workingRepository.delete(workingEntity);
            });
            employeeDeletedRepository.save(deletedEntity);
            employeeRep.delete(entity);

        } else {
            throw new IllegalArgumentException("Working set is not found!");
        }

    }

    @Override
    public String deleteAll(List<Integer> ids) {
        StringBuilder result = new StringBuilder();
        for (Integer id : ids) {
            try {
                deleteEmployee(id);

            } catch (Exception e) {
                result.append(e.getMessage()).append("-");
            }
        }
        return result.toString().toString();
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
        } else {
            throw new IllegalArgumentException("Working set is not found!");
        }

    }

    @Override
    public EmployeeDTO updateEmployee(Integer id, EmployeeDTO employeeDTO) {
        EmployeeEntity entity = employeeRep.findById(id).orElseThrow(() -> new IllegalArgumentException("Employee is not found!"));

        EmployeeUpdatedEntity updatedEntity = transferEmployee.entityToUpdated(entity);


        if (!employeeDTO.getFullName().equals("") && employeeDTO.getFullName() != null) {
            entity.setFullName(employeeDTO.getFullName());
        }
        if (employeeDTO.getAge() > 0 && employeeDTO.getAge() < 70) {
            entity.setAge(employeeDTO.getAge());
        }
        if (!employeeDTO.getStartDay().after(new Date())) {
            entity.setStartDay(employeeDTO.getStartDay());
        }
        if (employeeDTO.getTeamID() != entity.getTeam().getNo()) {
            TeamEntity teamEntity = teamRep.findById(employeeDTO.getTeamID()).orElseThrow(() -> new IllegalArgumentException("Team is not found!"));
            entity.setTeam(teamEntity);
        }
        if (!employeeDTO.getAddress().equals(entity.getAddress())) {
            entity.setAddress(employeeDTO.getAddress());
        }
        if (employeeDTO.getMoneyPerHour() != entity.getMoneyPerHour() & employeeDTO.getMoneyPerHour() > 0) {
            entity.setMoneyPerHour(employeeDTO.getMoneyPerHour());
        }
        entity.setSex(employeeDTO.isSex());

        employeeUpdatedRepository.save(updatedEntity);
        return mapper.toDTO(employeeRep.save(entity));

    }

    @Override
    public Set<EmployeeDTO> listEmployeeByTeam(Integer teamID) {
        Set<EmployeeDTO> employeeDTOSet = new HashSet<>();
        TeamEntity entity = teamRep.findById(teamID).orElseThrow(() -> new IllegalArgumentException("Employee is not found!"));

        employeeRep.findAllByTeam(entity).forEach(employeeEntity -> {
            employeeDTOSet.add(mapper.toDTO(employeeEntity));
        });
        return employeeDTOSet;

    }

    @Override
    public Page<EmployeeDTO> getPage(Integer pageIndex) {
        Page<EmployeeEntity> page = employeeRep.findAllWithPageIndex(PageRequest.of(pageIndex, 5));
        return page.map(entity -> mapper.toDTO(entity));
    }

}
