package com.proyecto.b.s.dto.request.searchRequestDTO;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class SeniorityForSearchRequestDTO {
    @NotEmpty(message = "Seniority cannot be null")
    private String name;
}
