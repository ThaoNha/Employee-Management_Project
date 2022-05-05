package vn.com.tma.training.ProjectTraining.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Min;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.util.Date;

@Component
@Data
@NoArgsConstructor
public class WorkingDTO {
    private int id;
    @PastOrPresent(message = "Date is Past or Present today")
    private Date date;
    @Positive(message = "Hour is greater than 0")
    private int hour;
    private int employee_id;
}
