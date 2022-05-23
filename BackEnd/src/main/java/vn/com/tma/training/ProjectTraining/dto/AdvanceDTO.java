package vn.com.tma.training.ProjectTraining.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.util.Date;

@Component
@Data
@NoArgsConstructor
public class AdvanceDTO {
    private int no;
    @PastOrPresent(message = "Date is Past or Present today")
    @JsonFormat(pattern = "MM-dd-yyyy")
    private Date date;
    @Positive(message = "Money is greater than 0")
    private int money;
    private int employee_id;
}
