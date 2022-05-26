package vn.com.tma.training.ProjectTraining.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "working")
@Data
@NoArgsConstructor
public class WorkingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int no;
    @Temporal(TemporalType.DATE)
    private Date date;
    private int hour;
    @ManyToOne
    @JoinColumn(name = "employee_no")
    private EmployeeEntity employee;

}
