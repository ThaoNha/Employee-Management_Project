package vn.com.tma.training.ProjectTraining.entity.entityDeleted;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "working_deleted")
@Data
@NoArgsConstructor
public class AdvanceDeletedEntity {
    @Id
    private int id;
    private Date date;
    private int money;
    @Column(name = "employee_id")
    private int employeeID;
}
