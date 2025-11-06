package com.example.audioplayerfinal.Clases;

import com.example.audioplayerfinal.Interfaces.IMetodoCancion;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Playlist implements IMetodoCancion {
    private int idPlaylist;
    private String nombre;
    private List<Cancion> canciones;
    public Playlist(int idPlaylist, String nombre) {
        this.idPlaylist = idPlaylist;
        this.nombre = nombre;
        this.canciones = new ArrayList<>();
    }

    public int getIdPlaylist() {
        return idPlaylist;
    }

    public void setIdPlaylist(int idPlaylist) {
        this.idPlaylist = idPlaylist;
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

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Playlist playlist)) return false;
        return idPlaylist == playlist.idPlaylist;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idPlaylist);
    }
}
