package com.example.audioplayerfinal.Clases;

import com.example.audioplayerfinal.ENums.EGenero;
import com.example.audioplayerfinal.Interfaces.IMultimedia;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.Objects;

public class Cancion extends ArchivoMultimedia implements IMultimedia {

    private static int contador = 0;

    private int idCancion;
    private int idAlbum;
    private int cantidadReproducciones;
    private String fechaPublicacion;
    private EGenero genero;
    private List<Integer> colaboradores;

    public Cancion(String nombre, int duracion,int idAlbum, int cantidadReproducciones, String fechaPublicacion, EGenero genero, List<Integer> colaboradores) {
        super(nombre, duracion);
        this.idCancion = contador++;
        this.idAlbum = idAlbum;
        this.cantidadReproducciones = cantidadReproducciones;
        this.fechaPublicacion = fechaPublicacion;
        this.genero = genero;
        this.colaboradores = colaboradores;
    }


    public int getIdCancion() {
        return idCancion;
    }

    public void setIdCancion(int idCancion) {
        this.idCancion = idCancion;
    }

    public int getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(int idAlbum) {
        this.idAlbum = idAlbum;
    }

    public int getCantidadReproducciones() {
        return cantidadReproducciones;
    }

    public void setCantidadReproducciones(int cantidadReproducciones) {
        this.cantidadReproducciones = cantidadReproducciones;
    }

    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public EGenero getGenero() {
        return genero;
    }

    public void setGenero(EGenero genero) {
        this.genero = genero;
    }

    public List<Integer> getColaboradores() {
        return colaboradores;
    }

    public void setColaboradores(List<Integer> colaboradores) {
        this.colaboradores = colaboradores;
    }



    ///  ??
    public void grabar(){};



     ///  Mostrar todos los datos de la cancion;
    public String DatosCancion() {
        StringBuilder sb = new StringBuilder();

        sb.append("Nombre: ").append(getNombre());
        sb.append("\nDuracion: ").append(getDuracion());
        sb.append("\nGenero: ").append(genero.toString());
        sb.append("\nColaboradores: ").append(colaboradores.toString());
        sb.append("\nCantidad Reproducciones: ").append(cantidadReproducciones);
        sb.append("\nFecha de Publicacion: ").append(fechaPublicacion);

        return sb.toString();
    }




    public boolean reproducir() {
        return false;
    }

    public boolean parar() {
        return false;
    }

    public boolean siguienteCancion() {
        return false;
    }

    public boolean anteriorCancion() {
        return false;
    }
}
