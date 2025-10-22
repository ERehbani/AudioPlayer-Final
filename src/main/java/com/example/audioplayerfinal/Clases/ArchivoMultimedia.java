package com.example.audioplayerfinal.Clases;

import com.example.audioplayerfinal.Interfaces.IMultimedia;

public class ArchivoMultimedia implements IMultimedia {
    private String nombre;
    private int duracion;

    public ArchivoMultimedia() {
    }

    public ArchivoMultimedia(String nombre, int duracion) {
        this.nombre = nombre;
        this.duracion = duracion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }


    @Override
    public boolean reproducir() {
        return false;
    }

    @Override
    public boolean parar() {
        return false;
    }

    @Override
    public boolean siguienteCancion() {
        return false;
    }

    @Override
    public boolean anteriorCancion() {
        return false;
    }
}
