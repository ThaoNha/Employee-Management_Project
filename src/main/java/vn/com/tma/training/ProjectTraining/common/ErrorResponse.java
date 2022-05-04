package vn.com.tma.training.ProjectTraining.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {
    private String message;
}
