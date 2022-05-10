package vn.com.tma.training.ProjectTraining.service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.tma.training.ProjectTraining.entity.AdvanceEntity;
import vn.com.tma.training.ProjectTraining.entity.EmployeeEntity;
import vn.com.tma.training.ProjectTraining.entity.WorkingEntity;
import vn.com.tma.training.ProjectTraining.repository.AdvanceRepository;
import vn.com.tma.training.ProjectTraining.repository.EmployeeRepository;
import vn.com.tma.training.ProjectTraining.repository.WorkingRepository;
import vn.com.tma.training.ProjectTraining.response.StatisticResponse;
import vn.com.tma.training.ProjectTraining.service.StatisticService;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class StatisticImpl implements StatisticService {

    @Autowired
    private WorkingRepository workingRepository;
    @Autowired
    private AdvanceRepository advanceRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public StatisticResponse getStatistic(Integer id) {
        double moneyPerHour = employeeRepository.findMoneyPerHourByNo(id);
        int month = LocalDate.now().getMonthValue();
        List<WorkingEntity> workingList = workingRepository.findAllByEmployeeNoAndMonth(id, month);
        List<AdvanceEntity> advanceList = advanceRepository.findAllByEmployeeNoAndMonth(id, month);


        StatisticResponse statisticResponse = new StatisticResponse();
        double totalWorking = workingList.stream().mapToDouble(working -> (working.getHour() * moneyPerHour)).sum();
        double totalAdvance = advanceList.stream().mapToDouble(AdvanceEntity::getMoney).sum();

        statisticResponse.setNumberOfWorkingDay(workingList.size());
        statisticResponse.setTotalGet(totalWorking);
        statisticResponse.setTotalAdvances(totalAdvance);
        statisticResponse.setSummary(totalWorking - totalAdvance);
        return statisticResponse;
    }
}
