package com.example.audioplayerfinal.Interfaces;

import com.example.audioplayerfinal.Clases.Cancion;
import com.example.audioplayerfinal.Exceptions.CancionNoExistenteException;

public interface IMetodosCancion {


    void agregarCancion(Cancion cancion) throws CancionNoExistenteException;

    void eliminarCancion(Cancion cancion) throws CancionNoExistenteException;

    String mostrarCancion();
}