package com.example.audioplayerfinal.Clases;

import com.example.audioplayerfinal.ENums.EGenero;
import com.example.audioplayerfinal.Exceptions.ArtistaIncluidoException;
import com.example.audioplayerfinal.Interfaces.IMultimedia;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Cancion extends ArchivoMultimedia implements IMultimedia {

    private static int contador = 0;

    private int idCancion;
    private EGenero genero;
    private Set<Artista> colaboradores;
    private int cantidadReproducciones;
    private String fechaPublicacion;

    public Cancion(String nombre, int duracion, int idCancion,  EGenero genero, int cantidadReproducciones, String fechaPublicacion) {
        super(nombre, duracion);
        this.idCancion = idCancion;
        this.genero = genero;
        this.colaboradores = new HashSet<Artista>();
        this.cantidadReproducciones = cantidadReproducciones;
        this.fechaPublicacion = fechaPublicacion;
    }

    public void grabar(){

    };

    public int getIdCancion() {
        return idCancion;
    }

    public EGenero getGenero() {
        return genero;
    }

    public void setGenero(EGenero genero) {
        this.genero = genero;
    }

    public Set<Artista> getColaboradores() {
        return colaboradores;
    }

    public void setColaboradores(Set<Artista> colaboradores) {
        this.colaboradores = colaboradores;
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

    public static void setContador(int contador) {
        Cancion.contador = contador;
    }

    public void setIdCancion(int idCancion) {
        this.idCancion = idCancion;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idCancion);
    }

    public String DatosArtista() {
        StringBuilder sb = new StringBuilder();

        sb.append("Nombre: ").append(getNombre());
        sb.append("\nDuracion: ").append(getDuracion());
        sb.append("\nGenero: ").append(genero);
        sb.append("\nColaboradores: ").append(colaboradores);
        sb.append("\nCantidad Reproducciones: ").append(cantidadReproducciones);
        sb.append("\nFecha de Publicacion: ").append(fechaPublicacion);

        return sb.toString();
    }

    public int cantidadDeReproducciones() {
        return cantidadReproducciones;
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

    /// /Agregar Artista
    public void AgregarArtista(Artista artista) throws ArtistaIncluido {
        if (colaboradores.contains(artista)) {
            throw  new ArtistaIncluido("Artista ya existente");
        }
        this.colaboradores.add(artista);
    }

    //// Eliminar Artista
    public void EliminarArtista(Artista artista) throws ArtistaIncluido {
        if (!colaboradores.contains(artista)) {
            throw new ArtistaIncluido("Artista no existente");
        }
        colaboradores.remove(artista);
    }
}
