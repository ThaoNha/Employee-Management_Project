package vn.com.tma.training.ProjectTraining.entity.entityDeleted;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "working_deleted")
@Data
@NoArgsConstructor
public class WorkingDeletedEntity {
    @Id
    private int id;
    private Date date;
    private int hour;
    @Column(name = "employee_id")
    private int employeeID;
}
