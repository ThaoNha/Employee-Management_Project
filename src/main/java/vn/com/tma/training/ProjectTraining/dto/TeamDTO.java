package vn.com.tma.training.ProjectTraining.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;

@Component
@Data
@NoArgsConstructor
public class TeamDTO {
    private int no;
    @NotEmpty(message = "Team's name is not empty")
    private String name;


}
