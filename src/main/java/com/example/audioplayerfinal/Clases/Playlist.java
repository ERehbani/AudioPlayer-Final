package com.example.audioplayerfinal.Clases;
import com.example.audioplayerfinal.Exceptions.PlaylistVaciaException;
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
    private static int contador = 0;

    public Playlist(String nombre) {
        this.id = contador++;
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

    @Override
    public String mostrarCancion() throws PlaylistVaciaException {
        StringBuilder sb = new StringBuilder();
        if(canciones.isEmpty()){
            throw new PlaylistVaciaException("La playlist está vacía");
        }
        sb.append("Canciones de la playlist: ").append(nombre).append("\n");
        for (Cancion c: canciones){
            sb.append(c.getDuracion()).append(" - ").append(c.getNombre())
                    .append(" - ").append(c.getFechaPublicacion()).append("\n");
        }

        return sb.toString();
    }

    @Override
    public void agregarCancion(Cancion cancion) {
        this.canciones.add(cancion);
    }
    @Override
    public void eliminarCancion(Cancion cancion) {
        this.canciones.remove(cancion);
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
        Playlist playlist = new Playlist(nombre);

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
