package com.example.audioplayerfinal.Clases;

import com.example.audioplayerfinal.ENums.EGenero;
import com.example.audioplayerfinal.Exceptions.CancionNoExistenteException;
import com.example.audioplayerfinal.Exceptions.ElementoDuplicadoException;
import com.example.audioplayerfinal.Exceptions.ElementoNoExisteException;
import com.example.audioplayerfinal.Exceptions.RepositorioNoExisteException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GestorMusic {

    private Repositorio<Integer, Cancion> repoCanciones;
    private Repositorio<Integer, Album> repoAlbumes;
    private Repositorio<Integer, Artista> repoArtistas;
    private Repositorio<Integer, Playlist> repoPlaylists;
    private Map<String, Cancion> cancionesPorNombre;
    private Map<EGenero, Set<Cancion>> cancionesPorGenero;
    private Map<String, Album> albumesPorNombre;
    private Map<String, Artista> artistasPorNombre;
    private Map<String, Playlist> playlistsPorNombre;


    public GestorMusic() {
        this.repoCanciones = new Repositorio<>();
        this.repoAlbumes = new Repositorio<>();
        this.repoArtistas = new Repositorio<>();
        this.repoPlaylists = new Repositorio<>();

        this.cancionesPorNombre = new HashMap<>();

        this.cancionesPorGenero = new HashMap<>();
        this.albumesPorNombre = new HashMap<>();
        this.artistasPorNombre = new HashMap<>();
        this.playlistsPorNombre = new HashMap<>();
    }

    //Alan, axel cuando lean esto.
    //Estos metodos son para crear un artista, una playlist, una cancion o un artista
    //el mismo metodo los agrega al repositorio del mismo
    //La consola va a interactuar con esta clase y no con las demás.

    public Artista crearArtista(String nombre) throws ElementoDuplicadoException, RepositorioNoExisteException {
        String clave = normalizarTexto(nombre);
        if (artistasPorNombre.containsKey(clave)) {
            throw new ElementoDuplicadoException("Ya existe un artista con el nombre: " + nombre);
        }

        Artista nuevo = new Artista(nombre);
        repoArtistas.agregar(nuevo.getId(), nuevo);
        artistasPorNombre.put(clave, nuevo);
        return nuevo;
    }


    public Album crearAlbum(String nombre, String fecha, String discografica)
            throws ElementoDuplicadoException, RepositorioNoExisteException {
        String clave = normalizarTexto(nombre);
        if (albumesPorNombre.containsKey(clave)) {
            throw new ElementoDuplicadoException("Ya existe un álbum con el nombre: " + nombre);
        }

        Album nuevo = new Album(nombre, fecha, discografica);
        repoAlbumes.agregar(nuevo.getId(), nuevo);
        albumesPorNombre.put(clave, nuevo);
        return nuevo;
    }

    public Cancion crearCancion(String nombre, int duracionSeg, EGenero genero, String fecha) throws ElementoDuplicadoException, RepositorioNoExisteException, CancionNoExistenteException {
        String clave = normalizarTexto(nombre);

        if (cancionesPorNombre.containsKey(clave)) {
            throw new ElementoDuplicadoException("Ya existe una canción con el nombre: " + nombre);
        }

        Cancion nueva = new Cancion(nombre, duracionSeg, genero, 0, fecha);
        Album albumAsociado = albumesPorNombre.get(clave);

        if (albumAsociado == null) {
            albumAsociado = new Album(nombre, fecha, "Single");
            repoAlbumes.agregar(albumAsociado.getId(), albumAsociado);
            albumesPorNombre.put(clave, albumAsociado);
        }

        try {
            albumAsociado.agregarCancion(nueva);
        } catch (CancionNoExistenteException e) {
            throw new CancionNoExistenteException("Error al agregar la canción al álbum: " + e.getMessage());
        }

        nueva.setAlbum(albumAsociado);
        repoCanciones.agregar(nueva.getId(), nueva);
        cancionesPorNombre.put(clave, nueva);

        if (!cancionesPorGenero.containsKey(genero)) {
            cancionesPorGenero.put(genero, new HashSet<>());
        }
        cancionesPorGenero.get(genero).add(nueva);

        return nueva;
    }

    public Playlist crearPlaylist(String nombre) throws ElementoDuplicadoException, RepositorioNoExisteException {
        String clave = normalizarTexto(nombre);
        if (playlistsPorNombre.containsKey(clave)) {
            throw new ElementoDuplicadoException("Ya existe una playlist con el nombre: " + nombre);
        }

        Playlist nueva = new Playlist(nombre);
        repoPlaylists.agregar(nueva.getId(), nueva);
        playlistsPorNombre.put(clave, nueva);
        return nueva;
    }

    public String normalizarTexto(String texto) {
        return texto == null ? "" : texto.trim().toLowerCase();
    }

    // Buscar canciones
    public Cancion buscarCancionPorNombre(String nombre) throws ElementoNoExisteException {
        String clave = normalizarTexto(nombre);
        if(!cancionesPorNombre.containsKey(clave)){
            throw new ElementoNoExisteException("No se encontró la cancion " + nombre);
        }
        return cancionesPorNombre.get(clave);
    }

    public Cancion buscarCancionPorId(int id) throws ElementoNoExisteException {
        for (Cancion c: cancionesPorNombre.values()){
            if(c.getId() == id){
                return c;
            }
        }
        throw new ElementoNoExisteException("No se encontró la cancion con ID: " + id);
    }


    // Buscar albumes
    public Album buscarAlbumPorNombre(String nombre) throws ElementoNoExisteException{
        String clave = normalizarTexto(nombre);
        if(!albumesPorNombre.containsKey(clave)){
            throw new ElementoNoExisteException("No se encontró el álbum con nombre: " + nombre);
        }
        return albumesPorNombre.get(clave);
    }

    public Artista buscarArtistaPorNombre(String nombre) throws ElementoNoExisteException {
        String clave = normalizarTexto(nombre);
        if(!artistasPorNombre.containsKey(clave)){
            throw new ElementoNoExisteException("No se encontró el álbum con nombre: " + nombre);
        }
        return artistasPorNombre.get(clave);
    }

    public Playlist buscarPlaylistPorNombre(String nombre) throws ElementoNoExisteException{
        String clave = normalizarTexto(nombre);
        if(!playlistsPorNombre.containsKey(clave)){
            throw new ElementoNoExisteException("No se encontró el álbum con nombre: " + nombre);
        }
        return playlistsPorNombre.get(clave);
    }
}
