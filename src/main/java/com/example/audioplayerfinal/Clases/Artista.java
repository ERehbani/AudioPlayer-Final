package com.example.audioplayerfinal.Clases;

import com.example.audioplayerfinal.ENums.EGenero;
import com.example.audioplayerfinal.Exceptions.*;
import com.example.audioplayerfinal.Interfaces.IIdentificador;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

public class Artista implements IIdentificador {
    private static int contador = 0;
    private int id;
    private String nombre;
    private Set<EGenero> generos;
    private Map<String, Album> albums;

    public Artista(String nombreArtista) {
        this.id = contador++;
        this.nombre = nombreArtista;
        this.generos = new HashSet<>();
        albums = new HashMap<>();
    }

    public Artista() {
        this.generos = new HashSet<>();
        albums = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGeneros() {
        StringBuilder sb = new StringBuilder();
        sb.append("GENEROS DE ").append(nombre).append(":\n");
        for (EGenero genero: generos){
            sb.append(genero.getGenero()).append("\n");
        }
        return sb.toString();
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
        return Objects.equals(id, artista.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }


    ///Muestra todos los datos del artista

    public String datosArtista() throws ColeccionVaciaException {
        StringBuilder sb = new StringBuilder();
        sb.append("-----------DATOS DE ").append(nombre).append("-----------");
        sb.append("\nNombre: ").append(nombre);
        sb.append("\nGeneros: ").append(generos);
        sb.append("\nCanciones: ");

        if (albums.isEmpty()) {
            throw new ColeccionVaciaException("No tiene canciones ");
        } else {
            for (Map.Entry<String, Album> entry : albums.entrySet()) {
                sb.append(entry.getValue().mostrarCancion());
            }
        }

        return sb.toString();
    }


    ///Muestra los nombres de las canciones del artista
    public String mostrarCanciones() throws ColeccionVaciaException {
        StringBuilder sb = new StringBuilder();

        if (albums.isEmpty()) {
            throw new ColeccionVaciaException("No tiene canciones ");
        } else {
            for (Map.Entry<String, Album> entry : albums.entrySet()) {
                sb.append(entry.getValue().mostrarCancion());
            }
        }

        return sb.toString();
    }

    /// Muestra los nombres de los albumes del artista
    public String mostrarAlbums() throws ColeccionVaciaException {
        StringBuilder sb = new StringBuilder();
        if (albums.isEmpty()) {
            throw new ColeccionVaciaException("No tiene Albums ");
        }else  {
            sb.append("ALBUMS DE ").append(getNombre()).append(":\n");
            for (Map.Entry<String, Album> entry : albums.entrySet()) {
                sb.append(entry.getKey());
            }
        }
        return sb.toString();
    }

    public void agregarAlbum(Album album) throws AlbumNoEncontradoExcepcion {
        if (album == null || albums.containsKey(album.getNombre())) {
            throw new AlbumNoEncontradoExcepcion("Album ya esta registrado");
        }
        albums.put(album.getNombre(), album);
    }

    public void eliminarAlbum(Album album) throws AlbumNoEncontradoExcepcion {
        if (album == null || !albums.containsKey(album.getNombre())) {
            throw new AlbumNoEncontradoExcepcion("Album no esta registrado");
        }
        albums.remove(album.getNombre());
    }

    public void agregarGenero(EGenero Gen)throws EGeneroExistenteExcepcion {
        if (Gen == null || generos.contains(Gen)) {
            throw new EGeneroExistenteExcepcion("Genero ya esta registrado");
        }
        generos.add(Gen);
    }

    public void eliminarGenero(EGenero Gen)throws EGeneroNoEncontradoExcepcion {
        if (Gen == null || !generos.contains(Gen)) {
            throw new EGeneroNoEncontradoExcepcion("Genero no esta registrado");
        }
        generos.remove(Gen);
    }

    public void agregarCancion(Cancion cancion) throws ElementoDuplicadoException {
        if(cancion == null)
            throw new IllegalArgumentException("Se debe enviar una canción");

        for (Album a : albums.values()) {
            if (a.contieneCancion(cancion)) {
                throw new ElementoDuplicadoException("La canción ya pertenece al artista.");
            }
        }

        Album albumSingle = new Album(
                cancion.getNombre(),
                cancion.getFechaPublicacion(),
                "Independiente"
        );

        if (cancion.getGenero() != null) {
            generos.add(cancion.getGenero());
        }

        albumSingle.agregarCancion(cancion);
        albums.put(albumSingle.getNombre(), albumSingle);
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("nombre", nombre);

        JSONArray generosArray = new JSONArray();
        for (EGenero g : generos) {
            generosArray.put(g.getGenero());
        }
        json.put("generos", generosArray);

        JSONArray albumsArray = new JSONArray();
        for (Album a : albums.values()) {
            albumsArray.put(a.toJSON());
        }
        json.put("albums", albumsArray);

        return json;
    }

    public static Artista fromJSON(JSONObject json) {
        int id = json.getInt("id");
        String nombre = json.getString("nombre");

        Artista artista = new Artista();
        artista.id = id;
        artista.nombre = nombre;

        JSONArray generosArray = json.optJSONArray("generos");
        if (generosArray != null) {
            for (int i = 0; i < generosArray.length(); i++) {
                String generoStr = generosArray.getString(i);
                for (EGenero g : EGenero.values()) {
                    if (g.getGenero().equalsIgnoreCase(generoStr)) {
                        try {
                            artista.agregarGenero(g);
                        } catch (EGeneroExistenteExcepcion e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
            }
        }

        JSONArray albumsArray = json.optJSONArray("albums");
        if (albumsArray != null) {
            for (int i = 0; i < albumsArray.length(); i++) {
                JSONObject jsonAlbum = albumsArray.getJSONObject(i);
                Album album = Album.fromJSON(jsonAlbum);
                try {
                    artista.agregarAlbum(album);
                } catch (AlbumNoEncontradoExcepcion e) {
                    e.printStackTrace();
                }
            }
        }
        return artista;
    }

}
