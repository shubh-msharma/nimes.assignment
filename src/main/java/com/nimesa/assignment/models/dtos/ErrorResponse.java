package com.nimesa.assignment.models.dtos;

import lombok.*;
import org.springframework.http.HttpStatus;

@Data
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private HttpStatus status;
    private String message;
}
