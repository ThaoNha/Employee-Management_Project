package vn.com.tma.training.ProjectTraining.entity.entityUpdated;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "employee_updated")
@Data
@NoArgsConstructor
public class EmployeeUpdatedEntity {
    @Id
    @Column(name = "no")
    private int no;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "age")
    private int age;
    @Column(name = "sex")
    private boolean sex;
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
    private Date updatedDate;
}
