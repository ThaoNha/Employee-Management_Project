package vn.com.tma.training.ProjectTraining.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class ImageDTO {
    private int no;
    private byte[] data;
    private String contentType;
    private int employeeID;
}
