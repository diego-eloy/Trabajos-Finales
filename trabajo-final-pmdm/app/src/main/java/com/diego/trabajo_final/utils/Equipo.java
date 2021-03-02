package com.diego.trabajo_final.utils;

import java.io.Serializable;

public class Equipo implements Serializable {
    String imagen;
    String nombre,descripcion;

    public Equipo(String imagen, String nombre, String descripcion) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
