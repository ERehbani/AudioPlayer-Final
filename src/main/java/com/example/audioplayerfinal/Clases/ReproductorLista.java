package com.example.audioplayerfinal.Clases;

import java.util.List;

public class ReproductorLista {

    private List<Cancion> lista;
    private int indiceActual = 0;
    private boolean enReproduccion = false;
    private boolean pausado = false;

    public void setLista(List<Cancion> canciones) {
        this.lista = canciones;
        this.indiceActual = 0;
    }

    public void reproducirActual() {
        if (lista == null || lista.isEmpty()) {
            System.out.println("❌ No hay canciones para reproducir.");
            return;
        }

        Cancion c = lista.get(indiceActual);
        enReproduccion = true;
        pausado = false;
        System.out.println("Reproduciendo (" + (indiceActual + 1) + "/" + lista.size() + "): " + c.getNombre());
    }

    public void siguiente() {
        if (lista == null || lista.isEmpty()) {
            System.out.println("No hay canciones en la lista.");
            return;
        }

        if (indiceActual < lista.size() - 1) {
            indiceActual++;
            reproducirActual();
        } else {
            System.out.println("Fin de la lista.");
        }
    }

    public void anterior() {
        if (lista == null || lista.isEmpty()) {
            System.out.println("No hay canciones en la lista.");
            return;
        }

        if (indiceActual > 0) {
            indiceActual--;
            reproducirActual();
        } else {
            System.out.println("Ya estás en la primera canción.");
        }
    }

    public void pausar() {
        if (!enReproduccion) {
            System.out.println("No hay ninguna canción reproduciéndose.");
            return;
        }

        if (!pausado) {
            pausado = true;
            System.out.println("Reproducción pausada: " + lista.get(indiceActual).getNombre());
        } else {
            System.out.println("Ya está pausada.");
        }
    }

    public void continuar() {
        if (!enReproduccion) {
            System.out.println("No hay canción en reproducción.");
            return;
        }

        if (pausado) {
            pausado = false;
            System.out.println("Reproducción reanudada: " + lista.get(indiceActual).getNombre());
        } else {
            System.out.println("La canción ya está reproduciéndose.");
        }
    }

    public void detener() {
        if (!enReproduccion) {
            System.out.println("No hay canción en reproducción.");
            return;
        }

        enReproduccion = false;
        pausado = false;
        System.out.println(" Reproducción detenida.");
    }

    public Cancion getCancionActual() {
        if (lista == null || lista.isEmpty()) return null;
        return lista.get(indiceActual);
    }
}
