package org.example.hickingshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDetailsDto {
    private String message;
    private String title;
}
