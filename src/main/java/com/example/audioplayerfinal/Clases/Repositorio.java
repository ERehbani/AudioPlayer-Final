package com.example.audioplayerfinal.Clases;

import com.example.audioplayerfinal.Exceptions.CancionNoExistenteException;
import com.example.audioplayerfinal.Exceptions.ElementoDuplicadoException;
import com.example.audioplayerfinal.Exceptions.ElementoNoExisteException;
import com.example.audioplayerfinal.Exceptions.RepositorioNoExisteException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Repositorio<K, V>  {

    private HashMap<K, V> repositorio;

    public Repositorio() {
        this.repositorio = new HashMap<K, V>();
    }

    public void agregar(K k, V v) throws RepositorioNoExisteException, ElementoDuplicadoException {
        if(repositorio == null)
            throw new RepositorioNoExisteException("El repositorio no fue inicializado");
        if(repositorio.containsKey(k))
            throw new ElementoDuplicadoException("El elemento ya existe en el repositorio");
        repositorio.put(k, v);
    }

    public void eliminar(K k) throws RepositorioNoExisteException, ElementoNoExisteException {
        if(repositorio == null)
            throw new RepositorioNoExisteException("El repositorio no fue inicializado");
        if(!repositorio.containsKey(k))
            throw new ElementoNoExisteException("El elemento no existe en el repositorio");
        repositorio.remove(k);
    }

    public String mostrar() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<K, V> entry: repositorio.entrySet()){
            sb.append(entry.getKey()).append(" - ").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }

    public V buscarPorClave(K k) throws ElementoNoExisteException {
        if (!repositorio.containsKey(k))
            throw new ElementoNoExisteException("No se encontró el elemento con la clave: " + k);

        return repositorio.get(k);
    }

    public V buscar(String nombre) throws ElementoNoExisteException {
        for (V value : repositorio.values()) {
            try {
                String nombreValue = (String) value.getClass().getMethod("getNombre").invoke(value);
                if (nombreValue.equalsIgnoreCase(nombre)) {
                    return value;
                }
            } catch (Exception e) {
                throw new RuntimeException("El tipo de objeto no contiene un nombre:", e);
            }
        }
        throw new ElementoNoExisteException("No se encontró ningún elemento con el nombre: " + nombre);
    }

    public Collection<V> listarTodos() {
        return repositorio.values();
    }

}
