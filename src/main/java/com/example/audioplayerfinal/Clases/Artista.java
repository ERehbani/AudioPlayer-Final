package com.example.audioplayerfinal.Clases;

import com.example.audioplayerfinal.ENums.EGenero;

import java.net.PortUnreachableException;
import java.util.*;

public class Artista {
    private static int Contador = 0;
    private String nombre;
    private int ID;
    private String nombreArtista;
    private int edad;
    private Set<EGenero> generos;
    private Map<Album, String> Canciones;

    public Artista(int ID, String nombreArtista, int edad) {
        this.ID = Contador++;
        this.nombreArtista = nombreArtista;
        this.edad = edad;
        this.generos = new HashSet<>();
        Canciones = new HashMap<>();
    }

    public Artista() {
        this.generos = new HashSet<>();
        Canciones = new HashMap<>();
    }

    public static int getContador() {
        return Contador;
    }

    public static void setContador(int contador) {
        Contador = contador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
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

    public Map<Album, String> getCanciones() {
        return Canciones;
    }

    public void setCanciones(Map<Album, String> canciones) {
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

    public void DatosArtista() {
        System.out.println("------------ Datos ------------");
        System.out.println("|| Nombre : " + nombre + "||");
        System.out.println("|| Edad : " + edad + " ||");
        System.out.println("|| Generos : " + generos);
        System.out.println("|| Canciones : " + Canciones);
    }

    public void GenerosDeArtista(Artista artista) {

    }
}
