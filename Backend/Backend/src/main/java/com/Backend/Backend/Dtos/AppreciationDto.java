package com.Backend.Backend.Dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AppreciationDto {
    @NotNull(message = "ID can't not be null")
    private Long blogId;

    private String operation;
}
