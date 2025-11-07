package com.example.audioplayerfinal.Clases;
import com.example.audioplayerfinal.ENums.EGenero;
import com.example.audioplayerfinal.Exceptions.ArtistaIncluidoException;
import com.example.audioplayerfinal.Interfaces.IIdentificador;
import com.example.audioplayerfinal.Interfaces.IMultimedia;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Cancion extends ArchivoMultimedia implements IMultimedia, IIdentificador {

    private static int contador = 0;

    private int id;
    private EGenero genero;
    private Set<Artista> colaboradores;
    private int cantidadReproducciones;
    private String fechaPublicacion;

    public Cancion(String nombre, int duracion, int idCancion,  EGenero genero, int cantidadReproducciones, String fechaPublicacion) {
        super(nombre, duracion);
        this.id = idCancion;
        this.genero = genero;
        this.colaboradores = new HashSet<Artista>();
        this.cantidadReproducciones = cantidadReproducciones;
        this.fechaPublicacion = fechaPublicacion;
    }

    public void grabar(){

    };

    public int getId() {
        return id;
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

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
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
    public void agregarArtista(Artista artista) throws ArtistaIncluidoException {
        if (colaboradores.contains(artista)) {
            throw  new ArtistaIncluidoException("Artista ya existente");
        }
        this.colaboradores.add(artista);
    }

    //// Eliminar Artista
    public void eliminarArtista(Artista artista) throws ArtistaIncluidoException {
        if (!colaboradores.contains(artista)) {
            throw new ArtistaIncluidoException("Artista no existente");
        }
        colaboradores.remove(artista);
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("nombre", getNombre());
        json.put("duracion", getDuracion());
        json.put("genero", genero.getGenero());
        json.put("cantidadReproducciones", cantidadReproducciones);
        json.put("fechaPublicacion", fechaPublicacion);

        JSONArray colabs = new JSONArray();
        for (Artista a : colaboradores) {
            colabs.put(a.toJSON());
        }
        json.put("colaboradores", colabs);

        return json;
    }

    public static Cancion fromJSON(JSONObject json) {
        int id = json.getInt("id");
        String nombre = json.getString("nombre");
        int duracion = json.getInt("duracion");

        String generoStr = json.getString("genero");
        EGenero genero = null;
        for (EGenero g : EGenero.values()) {
            if (g.getGenero().equalsIgnoreCase(generoStr)) {
                genero = g;
                break;
            }
        }

        int cantidadReproducciones = json.getInt("cantidadReproducciones");
        String fechaPublicacion = json.getString("fechaPublicacion");

        Cancion c = new Cancion(nombre, duracion, id, genero, cantidadReproducciones, fechaPublicacion);

        JSONArray colabs = json.optJSONArray("colaboradores");
        if (colabs != null) {
            for (int i = 0; i < colabs.length(); i++) {
                JSONObject jsonArtista = colabs.getJSONObject(i);
                Artista a = Artista.fromJSON(jsonArtista);
                c.agregarArtista(a);;
            }
        }
        return c;
    }



}
