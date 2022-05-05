package vn.com.tma.training.ProjectTraining.entity.entityDeleted;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "employee_deleted")
@Data
@NoArgsConstructor
public class EmployeeDeletedEntity {
    @Id
    @Column(name = "no")
    private int no;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "age")
    private int age;
    @Column(name = "isMale")
    private boolean isMale;;
    @Column(name = "address")
    private String address;
    @Column(name = "money_per_hour")
    private double moneyPerHour;
    @Column(name = "phone")
    private String phone;
    @Column(name = "start_day")
    private Date startDay;
    @Column(name = "team_id")
    private int teamID;
    @Column
    private Date deletedDate;
}
