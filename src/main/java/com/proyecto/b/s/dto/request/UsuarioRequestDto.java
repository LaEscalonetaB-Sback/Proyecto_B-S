package com.proyecto.b.s.dto.request;

public class UsuarioRequestDto {

    private Long id;
    private String email;
    private String clave;
    private String nombre;
    private String apellido;

    public UsuarioRequestDto() {
    }

    public UsuarioRequestDto(Long id, String email, String clave, String nombre, String apellido) {
        this.id = id;
        this.email = email;
        this.clave = clave;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
