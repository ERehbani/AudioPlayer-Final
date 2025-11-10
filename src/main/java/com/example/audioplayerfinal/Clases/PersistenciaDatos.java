package com.example.audioplayerfinal.Clases;

import com.example.audioplayerfinal.Gestores.GestorJSON;
import com.example.audioplayerfinal.Gestores.GestorMusic;

import java.util.HashSet;

public class PersistenciaDatos {

    public static void guardarTodo(GestorMusic servicio) {
        try {
            GestorJSON.grabarArchivo(servicio.getCanciones(), "canciones.json");
            GestorJSON.grabarArchivo(servicio.getArtista(), "artistas.json");
            GestorJSON.grabarArchivo(servicio.getAlbums(), "albumes.json");
            GestorJSON.grabarArchivo(servicio.getPlaylist(), "playlists.json");
            System.out.println("💾 Datos guardados correctamente.");
        }catch (Exception e) {
            System.out.println("Error al guardar el archivo." + e.getMessage());
        }

    }

    public static void cargarTodo(GestorMusic servicio) {
        try {
            HashSet<Cancion> canciones = GestorJSON.leerArchivo("canciones", Cancion.class);
            for (Cancion c : canciones) {
                servicio.agregarCancion(c);
            }

            HashSet<Artista> artistas = GestorJSON.leerArchivo("artistas", Artista.class);
            for (Artista a : artistas) {
                servicio.agregarAritsta(a);
            }

            HashSet<Album> albumes = GestorJSON.leerArchivo("albumes", Album.class);
            for (Album a : albumes) {
                servicio.agregarAlbum(a);
            }

            HashSet<Playlist> playlists = GestorJSON.leerArchivo("playlists", Playlist.class);
            for (Playlist p : playlists) {
                servicio.agregarPlaylist(p);
            }

            System.out.println("✅ Datos cargados correctamente.");

        } catch (Exception e) {
            System.out.println("⚠️ No se pudieron cargar algunos datos: " + e.getMessage());
        }
    }
}