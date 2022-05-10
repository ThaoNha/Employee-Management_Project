package vn.com.tma.training.ProjectTraining.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "employee")
@Data
@NoArgsConstructor
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no")
    private int no;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "age")
    private int age;
    @Column(name = "isMale")
    private boolean isMale;
    @Column(name = "address")
    private String address;
    @Column(name = "money_per_hour")
    private double moneyPerHour;
    @Column(name = "phone")
    private String phone;
    @Column(name = "start_day")
    @Temporal(TemporalType.DATE)
    private Date startDay;

    @Lob
    private byte[] image;

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    private TeamEntity team;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private Set<WorkingEntity> workingSet;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private Set<AdvanceEntity> advanceSet;
}
