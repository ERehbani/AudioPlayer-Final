package com.example.audioplayerfinal.Interfaces;

import com.example.audioplayerfinal.Clases.Cancion;
import com.example.audioplayerfinal.Exceptions.CancionNoexistente;

public interface IMetodoC {

    void agregarCancion(Cancion cancion) throws CancionNoexistente;
    void eliminarCancion(Cancion cancion) throws CancionNoexistente;
    String mostrarCancion();

}
