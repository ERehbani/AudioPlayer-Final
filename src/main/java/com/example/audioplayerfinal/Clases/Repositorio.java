package com.example.audioplayerfinal.Clases;

import com.example.audioplayerfinal.Exceptions.CancionNoExistenteException;
import com.example.audioplayerfinal.Exceptions.ElementoDuplicadoException;
import com.example.audioplayerfinal.Exceptions.ElementoNoExisteException;
import com.example.audioplayerfinal.Exceptions.RepositorioNoExisteException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
///// Falta buscar x clave
public class Repositorio<T, K>  {

    private HashMap<T, K> repositorio;

    public Repositorio() {
        this.repositorio = new HashMap<T, K>();
    }

    public void agregar(T t, K k) throws RepositorioNoExisteException, ElementoDuplicadoException {
        if(repositorio == null) throw new RepositorioNoExisteException("El repositorio no fue inicializado");
        if(repositorio.containsKey(t)) throw new ElementoDuplicadoException("El elemento ya existe en el repositorio");
        repositorio.put(t, k);
    }

    public void eliminar(T t) throws RepositorioNoExisteException, ElementoNoExisteException {
        if(repositorio == null) throw new RepositorioNoExisteException("El repositorio no fue inicializado");
        if(!repositorio.containsKey(t)) throw new ElementoNoExisteException("El elemento ya existe en el repositorio");
        repositorio.remove(t);
    }

    public String mostrar() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<T, K> entry: repositorio.entrySet()){
            sb.append(entry.getKey()).append(" - ").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }

    public K buscar(String nombre) throws ElementoNoExisteException {
        for (K value : repositorio.values()) {
            try {
                String nombreValue = (String) value.getClass().getMethod("getNombre").invoke(value);
                if (nombreValue.equalsIgnoreCase(nombre)) {
                    return value;
                }
            } catch (Exception e) {
                throw new RuntimeException("El tipo de objeto no tiene un método getNombre()", e);
            }
        }
        throw new ElementoNoExisteException("No se encontró ningún elemento con el nombre: " + nombre);
    }

}
