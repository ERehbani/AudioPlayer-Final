package com.example.audioplayerfinal.Clases;

import com.example.audioplayerfinal.ENums.EGenero;

import java.net.PortUnreachableException;
import java.util.*;

public class Artista {
    private static int contador = 0;
    private String nombre;
    private int ID;
    private String nombreArtista;
    private int edad;
    private Set<EGenero> generos;
    private Map<String, Album> Canciones;

    public Artista(int ID, String nombreArtista, int edad) {
        this.ID = contador++;
        this.nombreArtista = nombreArtista;
        this.edad = edad;
        this.generos = new HashSet<>();
        Canciones = new HashMap<>();
    }

    public Artista() {
        this.generos = new HashSet<>();
        Canciones = new HashMap<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getID() {
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
        return Canciones;
    }

    public void setCanciones(Map<String, Album> canciones) {
        Canciones = canciones;
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

    public String DatosArtista() {
        StringBuilder sb = new StringBuilder();
        sb.append("-----------DATOS-----------");
        sb.append("Nombre: ").append(nombre);
        sb.append("edad: ").append(edad);
        sb.append("Generos: ").append(generos);
        sb.append("Canciones: ");

        if(getCanciones().isEmpty()){
            sb.append("No tiene canciones este artista");
        }else{
            for(Map.Entry<String, Album> entry : getCanciones().entrySet()){
                sb.append(entry.getKey()).append("\n");            }
        }

        return sb.toString();
    }

    public void GenerosDeArtista(Artista artista) {

    }
}
