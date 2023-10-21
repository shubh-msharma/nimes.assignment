package com.nimesa.assignment.models.dtos;

import lombok.*;
import org.springframework.http.HttpStatus;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse <T>{
    private HttpStatus status;
    private String message;
    private T data;
}
