package com.example.audioplayerfinal.Clases;

import com.example.audioplayerfinal.ENums.EGenero;

import java.util.*;

public class Album {
    private int id;
    private String nombreAlbum;
    private Map <Integer, String> listaDeCanciones;
    private Set<EGenero> generos;
    private Map<Integer, String> artistas;
    private Date fechaDePublicacion;
    private String discografica;
    private static int contador = 0;

    public Album(int id, String nombreAlbum, Date fechaDePublicacion, String discografica) {
        this.id = contador;
        contador++;
        this.nombreAlbum = nombreAlbum;
        this.listaDeCanciones = new HashMap<>();
        this.generos = new HashSet<>();
        this.artistas = new HashMap<>();
        this.fechaDePublicacion = fechaDePublicacion;
        this.discografica = discografica;
    }

    public int getId() {
        return id;
    }

    public String getNombreAlbum() {
        return nombreAlbum;
    }

    public Map<Integer, String> getListaDeCanciones() {
        return listaDeCanciones;
    }

    public Set<EGenero> getGeneros() {
        return generos;
    }

    public Map<Integer, String> getArtistas() {
        return artistas;
    }

    public Date getFechaDePublicacion() {
        return fechaDePublicacion;
    }

    public String getDiscografica() {
        return discografica;
    }

    public void setNombreAlbum(String nombreAlbum) {
        this.nombreAlbum = nombreAlbum;
    }

    public void setGeneros(Set<EGenero> generos) {
        this.generos = generos;
    }

    public void setArtistas(Map<Integer, String> artistas) {
        this.artistas = artistas;
    }

    public void setFechaDePublicacion(Date fechaDePublicacion) {
        this.fechaDePublicacion = fechaDePublicacion;
    }

    public void setDiscografica(String discografica) {
        this.discografica = discografica;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return id == album.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    //Contar cantidad de canciones
    public int cantidadDeCanciones() {
        return listaDeCanciones.size();
    }

    //Mostrar canciones
    public String mostrarCanciones() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, String> entry : listaDeCanciones.entrySet()) {
            sb.append(entry.getKey() + " - " + entry.getValue() + "\n");
        }

        return sb.toString();
    }

    //Mostrar datos del album
    public String mostrarDatosAlbum() {
        StringBuilder sb = new StringBuilder();
        sb.append("----------------------------------------");
        sb.append("Album ").append(nombreAlbum).append("\n");
        sb.append("Discografica: ").append(discografica).append("\n");
        sb.append("Fecha de publicacion: ").append(fechaDePublicacion).append("\n");

        sb.append("Generos:" );
        if (generos.isEmpty()) {
            sb.append("No existen generos en el album");
        }else  {
            for (EGenero genero : generos) {
                sb.append(genero).append("\n");
            }
        }

        sb.append("Artistas:\n");
        if (artistas.isEmpty()) {
            sb.append("No existen artistas en el album");
        }
        else {
            for (Map.Entry<Integer, String> entry : artistas.entrySet()) {
                sb.append(entry.getKey()).append(" - ").append(entry.getValue()).append("\n");
            }
        }

        sb.append("Lista de canciones:\n");
        if (listaDeCanciones.isEmpty()) {
            sb.append("No existen canciones en el album");
        }else{
            for (Map.Entry<Integer, String> entry : listaDeCanciones.entrySet()) {
                sb.append(entry.getKey()).append(" - ").append(entry.getValue()).append("\n");
            }
        }

        return sb.toString();
    }
}
