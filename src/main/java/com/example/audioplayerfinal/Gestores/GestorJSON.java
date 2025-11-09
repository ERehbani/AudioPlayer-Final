package com.example.audioplayerfinal.Gestores;

import com.example.audioplayerfinal.Clases.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Collection;
import java.util.HashSet;

public class GestorJSON {

    public static <T> void grabarArchivo(Collection<T> coleccion, String nombreArchivo) {
        JSONArray jsonArray = new JSONArray();

        for (T elemento : coleccion) {
            if (elemento instanceof Cancion) {
                jsonArray.put(((Cancion) elemento).toJSON());
            } else if (elemento instanceof Album) {
                jsonArray.put(((Album) elemento).toJSON());
            } else if (elemento instanceof Artista) {
                jsonArray.put(((Artista) elemento).toJSON());
            } else if (elemento instanceof Playlist) {
                jsonArray.put(((Playlist) elemento).toJSON());
            }
        }

        JsonUtiles.grabarUnJson(jsonArray, nombreArchivo);
    }
    /* esto cuando testeen se llama asi el cancion.class es para saber de qué tipo es
    la coleccion que estás enviando, si saben como optimizarlo joya, voy a pensar a ver si hay 
    otra forma más practica, hasta ahora se me ocurrio de esa forma
  
    HashSet<Cancion> canciones = GestorJSON.leerArchivo("canciones", Cancion.class);*/

    public static <T> HashSet<T> leerArchivo(String nombreArchivo, Class<T> tipo) {
        HashSet<T> coleccion = new HashSet<>();
        JSONArray jsonArray = new JSONArray(JsonUtiles.leer(nombreArchivo));

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);

            Object nuevo = null;
            if (tipo == Cancion.class) {
                nuevo = Cancion.fromJSON(obj);
            } else if (tipo == Album.class) {
                nuevo = Album.fromJSON(obj);
            } else if (tipo == Artista.class) {
                nuevo = Artista.fromJSON(obj);
            } else if (tipo == Playlist.class) {
                nuevo = Playlist.fromJSON(obj);
            }

            if (nuevo != null) {
                coleccion.add(tipo.cast(nuevo));
            }
        }
        return coleccion;
    }
}
