package com.Backend.Backend.Dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class BlogAppreciationDto {
    @NotNull
    @Pattern(regexp = "like|unlike|dislike|not_dislike")
    private String operation;
}
