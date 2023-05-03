package com.proyecto.b.s.dto.request;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class SeniorityRequestDTO {

    private Long id;

    @Pattern(regexp = "^[a-zA-Z0-9+\\-*/!@#$%^&()_+=?.,<>{}|;'\":`~\\[\\]\\\\]*$", message = "El cuil solo puede contener letras, números y los siguientes caracteres especiales: + - * / ! @ # $ % ^ & ( ) _ + = ? . , < > { } | ; ' \" : ` ~ [ ] \\")
    private String name;
}
