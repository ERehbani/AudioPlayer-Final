package com.example.audioplayerfinal.Clases;

import com.example.audioplayerfinal.ENums.EGenero;
import com.example.audioplayerfinal.Exceptions.ColeccionVaciaException;

import java.net.PortUnreachableException;
import java.util.*;

public class Artista {
    private static int contador = 0;
    private int ID;
    private String nombreArtista;
    private int edad;
    private Set<EGenero> generos;
    private Map<String, Album> albums;

    public Artista(int ID, String nombreArtista, int edad) {
        this.ID = contador++;
        this.nombreArtista = nombreArtista;
        this.edad = edad;
        this.generos = new HashSet<>();
        albums = new HashMap<>();
    }


    public Artista() {
        this.generos = new HashSet<>();
        albums = new HashMap<>();
    }

    public int getID() {
        return ID;
    }


    public String getNombreArtista() {
        return nombreArtista;
    }

    public void setNombreArtista(String nombreArtista) {
        this.nombreArtista = nombreArtista;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Set<EGenero> getGeneros() {
        return generos;
    }

    public void setGeneros(Set<EGenero> generos) {
        this.generos = generos;
    }


    public Map<String, Album> getCanciones() {
        return albums;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Artista artista = (Artista) o;
        return Objects.equals(ID, artista.ID);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(ID);
    }

    public String datosArtista() throws ColeccionVaciaException {
        StringBuilder sb = new StringBuilder();
        sb.append("-----------DATOS DE ").append(nombreArtista).append("-----------");
        sb.append("Nombre: ").append(nombreArtista);
        sb.append("edad: ").append(edad);
        sb.append("Generos: ").append(generos);
        sb.append("Canciones: ");

        if (albums.isEmpty()) {
            throw new ColeccionVaciaException("No tiene canciones ");
        } else {
            for (Map.Entry<String, Album> entry : albums.entrySet()) {
                sb.append(entry.getValue().mostrarCanciones());
            }
        }

        return sb.toString();
    }

    public String mostrarCanciones() throws ColeccionVaciaException {
        StringBuilder sb = new StringBuilder();

        if (albums.isEmpty()) {
            throw new ColeccionVaciaException("No tiene canciones ");
        } else {
            for (Map.Entry<String, Album> entry : albums.entrySet()) {
                sb.append(entry.getValue().mostrarCanciones());
            }
        }

        return sb.toString();
    }

    public String mostrarAlbums() throws ColeccionVaciaException {
        StringBuilder sb = new StringBuilder();
        if (albums.isEmpty()) {
            throw new ColeccionVaciaException("No tiene Albums ");
        }else  {
            for (Map.Entry<String, Album> entry : albums.entrySet()) {
                sb.append(entry.getKey());
            }
        }

        return sb.toString();
    }

}
