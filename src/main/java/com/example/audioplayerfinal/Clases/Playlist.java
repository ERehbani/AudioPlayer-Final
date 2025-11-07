package com.example.audioplayerfinal.Clases;
import com.example.audioplayerfinal.Interfaces.IIdentificador;
import com.example.audioplayerfinal.Interfaces.IMetodosCancion;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Playlist implements IMetodosCancion, IIdentificador {
    private int id;
    private String nombre;
    private List<Cancion> canciones;
    public Playlist(int idPlaylist, String nombre) {
        this.id = idPlaylist;
        this.nombre = nombre;
        this.canciones = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Cancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<Cancion> canciones) {
        this.canciones = canciones;
    }

    @Override
    public void agregarCancion(Cancion cancion) {
        this.canciones.add(cancion);
    }
    @Override
    public void eliminarCancion(Cancion cancion) {
        this.canciones.remove(cancion);
    }
    @Override
    public String mostrarCancion() {
        StringBuilder sb = new StringBuilder();
        for (Cancion cancion : this.canciones) {
            sb.append(cancion.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    public JSONObject toJSON() {
        JSONObject obj = new JSONObject();
        obj.put("id", this.id);
        obj.put("nombre", this.nombre);

        JSONArray jsonCanciones = new JSONArray();
        for (Cancion c : this.canciones) {
            jsonCanciones.put(c.toJSON());
        }
        obj.put("canciones", jsonCanciones);

        return obj;
    }

    // 🔽 MÉTODO FROM JSON
    public static Playlist fromJSON(JSONObject obj) {
        int id = obj.getInt("id");
        String nombre = obj.getString("nombre");
        Playlist playlist = new Playlist(id, nombre);

        JSONArray jsonCanciones = obj.getJSONArray("canciones");
        for (int i = 0; i < jsonCanciones.length(); i++) {
            JSONObject jsonCancion = jsonCanciones.getJSONObject(i);
            Cancion c = Cancion.fromJSON(jsonCancion);
            playlist.agregarCancion(c);
        }

        return playlist;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Playlist playlist)) return false;
        return id == playlist.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
