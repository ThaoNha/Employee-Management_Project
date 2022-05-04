package vn.com.tma.training.ProjectTraining.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
@NoArgsConstructor
public class EmployeeDTO {
    private int no;
    private String fullName;
    private int age;
    private boolean sex;
    private String address;
    private double moneyPerHour;
    private String phone;
    private Date startDay;
    private int teamID;


}
