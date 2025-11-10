package com.example.audioplayerfinal.Reproductor;

import com.example.audioplayerfinal.Clases.Cancion;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.util.List;

public class ReproductorLista {

    private List<Cancion> lista;
    private int indiceActual = 0;
    private MediaPlayer player;

    public void setLista(List<Cancion> canciones) {
        this.lista = canciones;
        this.indiceActual = 0;
    }


    // reproducir cancion actual
    public void reproducirActual() {
        if (lista == null || lista.isEmpty()) {
            System.out.println("❌ No hay canciones para reproducir.");
            return;
        }

            Cancion c = lista.get(indiceActual);
            File archivo = new File(c.getRutaArchivo());

        if (!archivo.exists()) {
            System.out.println("⚠️ Archivo no encontrado: " + archivo.getAbsolutePath());
            siguiente();
            return;
        }

        try {
            detener(); // detener lo anterior
            Media media = new Media(archivo.toURI().toString());
            player = new MediaPlayer(media);
            player.play();
            System.out.println("▶ Reproduciendo (" + (indiceActual + 1) + "/" + lista.size() + "): " + c.getNombre());

            player.setOnEndOfMedia(this::siguiente);
        } catch (Exception e) {
            System.out.println("❌ Error al reproducir: " + e.getMessage());
        }
    }

    public void siguiente() {
        if (lista == null || lista.isEmpty()) return;

        indiceActual++;
        if (indiceActual >= lista.size()) {
            System.out.println("🏁 Fin de la lista.");
            indiceActual = lista.size() - 1;
            return;
        }
        reproducirActual();
    }

    public void anterior() {
        if (lista == null || lista.isEmpty()) return;

        indiceActual--;
        if (indiceActual < 0) {
            System.out.println("🚫 Ya estás en la primera canción.");
            indiceActual = 0;
            return;
        }
        reproducirActual();
    }

    public void pausar() {
        if (player != null) {
            player.pause();
            System.out.println("⏸ Reproducción pausada.");
        }
    }

    public void continuar() {
        if (player != null) {
            player.play();
            System.out.println("▶ Reproducción reanudada.");
        }
    }

    public void detener() {
        if (player != null) {
            player.stop();
            System.out.println("⏹ Reproducción detenida.");
        }
    }

    public Cancion getCancionActual() {
        if (lista == null || lista.isEmpty()) return null;
        return lista.get(indiceActual);
    }
}