package com.example.audioplayerfinal.Clases;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class Catalogo<T> {
    private Map<Integer, T> elementos;

    public Catalogo() {
        this.elementos = new HashMap<Integer, T>();
    }

    private boolean agregar(int id, T item){
        if (item == null) {
            throw new IllegalArgumentException("El item a agregar no puede ser null.");
        }
        try {
            T newItem = elementos.put(id, item);
            return newItem != null;
        } catch (NullPointerException error){
            throw new IllegalArgumentException("La coleccion 'elementos' no está inicializado");
        } catch (Exception error) {
            throw new RuntimeException("Error al agregar elemento a la colleción");
        }
    }

    private T obtenerPorId(int id) throws NoSuchElementException, IllegalStateException {
        if (elementos == null) {
            throw new IllegalStateException("El mapa 'elementos' no está inicializado.");
        }

        if (elementos.isEmpty()) {
            throw new NoSuchElementException("El catalogo está vacío. No hay elementos para buscar.");
        }

        for (Map.Entry<Integer, T> entry : elementos.entrySet()) {
            if (entry.getKey() == id) {
                return entry.getValue();
            }
        }
        throw new NoSuchElementException("No se encontró un elemento con el id: " + id);
    }


    private void eliminarPorId(int id) throws NoSuchElementException, IllegalStateException {
        if (elementos == null) {
            throw new IllegalStateException("El mapa 'elementos' no está inicializado.");
        }

        if (elementos.isEmpty()) {
            throw new NoSuchElementException("El mapa está vacío. No hay elementos para eliminar.");
        }

        if (!elementos.containsKey(id)) {
            throw new NoSuchElementException("No se encontró un elemento con el id: " + id);
        }

        elementos.remove(id);
        System.out.println("Elemento con id " + id + " eliminado correctamente.");
    }

    private String obtenerTodos() throws NoSuchElementException, IllegalStateException{
        StringBuilder sb = new StringBuilder();
        try {
            for (Map.Entry<Integer, T> entry: elementos.entrySet()){
                sb.append(entry.getKey()).append(" - ").append(entry.getValue());
            }
            return sb.toString();
        }catch (NoSuchElementException error) {
            throw new NoSuchElementException("El mapa está vacío. No hay elementos a mostrar");
        }
        catch(IllegalArgumentException error) {
            throw new IllegalArgumentException("El mapa 'elementos' no está incializado");
        }
    }

}
