package com.diego.trabajo_final.utils;

public class Dialogo {

    int imagen;
    String nombre, equipo;

    public Dialogo(int imagen, String nombre, String equipo) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.equipo = equipo;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }
}