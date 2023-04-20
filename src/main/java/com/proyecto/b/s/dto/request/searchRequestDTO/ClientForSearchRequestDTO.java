package com.proyecto.b.s.dto.request.searchRequestDTO;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ClientForSearchRequestDTO {
    @NotEmpty(message = "CLient cannot be null")
    private String name;
}
