package com.example.audioplayerfinal.Clases;

import com.example.audioplayerfinal.ENums.EGenero;
import com.example.audioplayerfinal.Exceptions.*;
import com.example.audioplayerfinal.Interfaces.IIdentificador;
import com.example.audioplayerfinal.Interfaces.IMetodosCancion;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

public class Album implements IMetodosCancion, IIdentificador {
    private int id;
    private String nombre;
    private Map <String, Cancion> listaDeCanciones;
    private Set<EGenero> generos;
    private Map<String,Artista> artistas;
    private String fechaDePublicacion;
    private String discografica;
    private static int contador = 0;

    public Album(String nombreAlbum, String fechaDePublicacion, String discografica) {
        this.id = contador;
        contador++;
        this.nombre = nombreAlbum;
        this.listaDeCanciones = new HashMap<>();
        this.generos = new HashSet<>();
        this.artistas = new HashMap<>();
        this.fechaDePublicacion = fechaDePublicacion;
        this.discografica = discografica;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Map<String, Cancion> getListaDeCanciones() {
        return listaDeCanciones;
    }

    public Set<EGenero> getGeneros() {
        return generos;
    }

    public Map<String,Artista> getArtistas() {
        return artistas;
    }

    public String getFechaDePublicacion() {
        return fechaDePublicacion;
    }

    public String getDiscografica() {
        return discografica;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setGeneros(Set<EGenero> generos) {
        this.generos = generos;
    }

    public void setArtistas(Map<String, Artista> artistas) {
        this.artistas = artistas;
    }

    public void setFechaDePublicacion(String fechaDePublicacion) {
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

    ///Contar cantidad de canciones

    public int cantidadDeCanciones() {
        return listaDeCanciones.size();
    }


    ///Mostrar datos del album

    public String mostrarDatosAlbum() {
        StringBuilder sb = new StringBuilder();
        sb.append("----------------------------------------");
        sb.append("Album ").append(nombre).append("\n");
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
            for (Map.Entry< String,Artista> entry : artistas.entrySet()) {
                sb.append(entry.getKey()).append(" - ").append(entry.getValue()).append("\n");
            }
        }

        sb.append("Lista de canciones:\n");
        if (listaDeCanciones.isEmpty()) {
            sb.append("No existen canciones en el album");
        }else{
            for (Map.Entry<String, Cancion> entry : listaDeCanciones.entrySet()) {
                sb.append(entry.getKey()).append(" - ");
            }
        }

        return sb.toString();

    }

    ///Agregar canciones

    @Override
    public void agregarCancion(Cancion cancion) throws
            CancionNoExistenteException {
        if (listaDeCanciones.containsKey(cancion.getNombre())) {
            throw new CancionNoExistenteException("La cacnion ya esta en el album");
        }
        listaDeCanciones.put(cancion.getNombre(), cancion);
    }

    ///Eliminar canciones

    @Override
    public void eliminarCancion(Cancion cancion) throws CancionNoExistenteException{
        if (!listaDeCanciones.containsKey(cancion.getNombre())) {
            throw new CancionNoExistenteException("La cacnion no esta en el album");
        }
        listaDeCanciones.remove(cancion.getNombre());
    }

    ///Mostrar canciones

    @Override
    public String mostrarCancion() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Cancion> entry : listaDeCanciones.entrySet()) {
            sb.append(entry.getKey() + " - ");
        }

        return sb.toString();
    }

    public void AgregarArtista(Artista artista) throws ArtistaIncluidoException {
        if (artista == null || artistas.containsValue(artista)) {
            throw new ArtistaIncluidoException("La artista esta en el album");
        }
        artistas.put(artista.getNombre(), artista);
    }
    public void eliminarArtista(Artista artista) throws ArtistaNoIncluidoException {
        if (artista == null || !artistas.containsValue(artista)) {
            throw new ArtistaNoIncluidoException("La artista no esta en el album");
        }
        artistas.remove(artista.getNombre());
    }

    public void AgregarGenero(EGenero gen) throws GeneroNoExistenteException {
        if (generos.contains(gen)) {
            throw new GeneroNoExistenteException("La genero esta en el album");
        }
        generos.add(gen);
    }

    public void eliminarGenero(EGenero gen) throws GeneroNoExistenteException {
        if (!generos.contains(gen)) {
            throw new GeneroNoExistenteException("La genero no esta en el album");
        }
        generos.remove(gen);
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("nombre", nombre);
        json.put("fechaDePublicacion", fechaDePublicacion);
        json.put("discografica", discografica);

        // 🔹 Generos
        JSONArray generosArray = new JSONArray();
        for (EGenero g : generos) {
            generosArray.put(g.getGenero()); // Guarda el nombre legible del género
        }
        json.put("generos", generosArray);

        // 🔹 Canciones
        JSONArray cancionesArray = new JSONArray();
        for (Cancion c : listaDeCanciones.values()) {
            cancionesArray.put(c.toJSON()); // se asume que Cancion tiene su propio toJSON()
        }
        json.put("canciones", cancionesArray);

        // 🔹 Artistas
        JSONArray artistasArray = new JSONArray();
        for (Artista a : artistas.values()) {
            artistasArray.put(a.toJSON()); // se asume que Artista tiene su propio toJSON()
        }
        json.put("artistas", artistasArray);

        return json;
    }


    public static Album fromJSON(JSONObject json) {
        String nombre = json.getString("nombre");
        String fecha = json.getString("fechaDePublicacion");
        String discografica = json.getString("discografica");

        Album album = new Album(nombre, fecha, discografica);
        album.id = json.getInt("id"); // asignación directa (no hay setter público)

        // 🔹 Generos
        JSONArray generosArray = json.optJSONArray("generos");
        if (generosArray != null) {
            for (int i = 0; i < generosArray.length(); i++) {
                String generoStr = generosArray.getString(i);
                for (EGenero g : EGenero.values()) {
                    if (g.getGenero().equalsIgnoreCase(generoStr)) {
                        try {
                            album.AgregarGenero(g);
                        } catch (GeneroNoExistenteException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
            }
        }

        // 🔹 Canciones
        JSONArray cancionesArray = json.optJSONArray("canciones");
        if (cancionesArray != null) {
            for (int i = 0; i < cancionesArray.length(); i++) {
                JSONObject jsonCancion = cancionesArray.getJSONObject(i);
                Cancion c = Cancion.fromJSON(jsonCancion);
                try {
                    album.agregarCancion(c);
                } catch (CancionNoExistenteException e) {
                    e.printStackTrace();
                }
            }
        }

        JSONArray artistasArray = json.optJSONArray("artistas");
        if (artistasArray != null) {
            for (int i = 0; i < artistasArray.length(); i++) {
                JSONObject jsonArtista = artistasArray.getJSONObject(i);
                Artista a = Artista.fromJSON(jsonArtista);
                try {
                    album.AgregarArtista(a);
                } catch (ArtistaIncluidoException e) {
                    e.printStackTrace();
                }
            }
        }

        return album;
    }
}
