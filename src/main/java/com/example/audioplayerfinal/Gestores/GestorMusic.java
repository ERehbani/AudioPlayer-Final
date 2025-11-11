package com.example.audioplayerfinal.Gestores;

import com.example.audioplayerfinal.Clases.*;
import com.example.audioplayerfinal.ENums.EGenero;
import com.example.audioplayerfinal.Exceptions.*;
import com.example.audioplayerfinal.Utils.TextoUtils;

import java.text.Normalizer;
import java.util.*;

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
        String clave = TextoUtils.normalizarTexto(nombre);
        if (artistasPorNombre.containsKey(clave))
            throw new ElementoDuplicadoException("Ya existe un artista con el nombre: " + nombre);

        Artista nuevo = new Artista(nombre);
        repoArtistas.agregar(nuevo.getId(), nuevo);
        artistasPorNombre.put(clave, nuevo);
        return nuevo;
    }


    public Album crearAlbum(String nombre, String fecha, String discografica)
            throws ElementoDuplicadoException, RepositorioNoExisteException {
        String clave = TextoUtils.normalizarTexto(nombre);
        if (albumesPorNombre.containsKey(clave))
            throw new ElementoDuplicadoException("Ya existe un álbum con el nombre: " + nombre);

        Album nuevo = new Album(nombre, fecha, discografica);
        Map<String,Artista> map = new HashMap<>();
        nuevo.setArtistas(map);
        repoAlbumes.agregar(nuevo.getId(), nuevo);
        albumesPorNombre.put(clave, nuevo);
        return nuevo;
    }

    public Cancion crearCancion(String nombre, int duracionSeg, EGenero genero, String ruta, int cantReproducciones, String fecha)
            throws ElementoDuplicadoException, RepositorioNoExisteException {

        String clave = TextoUtils.normalizarTexto(nombre);
        if (cancionesPorNombre.containsKey(clave))
            throw new ElementoDuplicadoException("Ya existe una canción con el nombre: " + nombre);

        Cancion nueva = new Cancion(nombre, duracionSeg, genero, ruta, cantReproducciones, fecha);

        repoCanciones.agregar(nueva.getId(), nueva);
        cancionesPorNombre.put(clave, nueva);
        cancionesPorGenero.computeIfAbsent(genero, k -> new HashSet<>()).add(nueva);

        return nueva;
    }


    public Playlist crearPlaylist(String nombre) throws ElementoDuplicadoException, RepositorioNoExisteException {
        String clave = TextoUtils.normalizarTexto(nombre);
        if (playlistsPorNombre.containsKey(clave))
            throw new ElementoDuplicadoException("Ya existe una playlist con el nombre: " + nombre);

        Playlist nueva = new Playlist(nombre);
        repoPlaylists.agregar(nueva.getId(), nueva);
        playlistsPorNombre.put(clave, nueva);
        return nueva;
    }


    // Buscar canciones
    public Cancion buscarCancionPorNombre(String nombre) throws ElementoNoExisteException {
        String clave = TextoUtils.normalizarTexto(nombre);
        if (!cancionesPorNombre.containsKey(clave)) {
            throw new ElementoNoExisteException("No se encontró la cancion " + nombre);
        }
        return cancionesPorNombre.get(clave);
    }

    public Cancion buscarCancionPorId(int id) throws ElementoNoExisteException {
        for (Cancion c : cancionesPorNombre.values()) {
            if (c.getId() == id) {
                return c;
            }
        }
        throw new ElementoNoExisteException("No se encontró la cancion con ID: " + id);
    }

    public List<Cancion> getCanciones() {
        return new ArrayList<>(cancionesPorNombre.values());
    }

    public List<Artista> getArtista() {
        return new ArrayList<>(artistasPorNombre.values());
    }

    public List<Album> getAlbums() {
        return new ArrayList<>(albumesPorNombre.values());
    }

    public List<Playlist> getPlaylist() {
        return new ArrayList<>(playlistsPorNombre.values());
    }

    public boolean agregarCancion(Cancion cancion) throws ElementoDuplicadoException, RepositorioNoExisteException {
        if (cancion == null)
            throw new IllegalArgumentException("Se debe enviar una cancion");
        String clave = TextoUtils.normalizarTexto(cancion.getNombre());

        if (cancionesPorNombre.containsKey(clave))
            throw new ElementoDuplicadoException("La cancion ya fue agregada");
        cancionesPorNombre.put(clave, cancion);
        return true;
    }

    public boolean agregarAritsta(Artista artista) throws ElementoDuplicadoException {
        if (artista == null)
            throw new IllegalArgumentException("Se debe enviar un artista");
        String clave = TextoUtils.normalizarTexto(artista.getNombre());
        if (artistasPorNombre.containsKey(clave))
            throw new ElementoDuplicadoException("El artista ya se encuentra en el repositorio");

        artistasPorNombre.put(clave, artista);
        return true;
    }

    public boolean agregarAlbum(Album album) throws ElementoDuplicadoException {
        if (album == null)
            throw new IllegalArgumentException("Se debe enviar un artista");
        String clave = TextoUtils.normalizarTexto(album.getNombre());
        if (albumesPorNombre.containsKey(clave))
            throw new ElementoDuplicadoException("El álbum ya se encuentra en el repositorio");

        albumesPorNombre.put(clave, album);
        return true;
    }

    public boolean agregarPlaylist(Playlist playlist) throws ElementoDuplicadoException {
        if (playlist == null)
            throw new IllegalArgumentException("Se debe enviar un artista");
        String clave = TextoUtils.normalizarTexto(playlist.getNombre());
        if (playlistsPorNombre.containsKey(clave))
            throw new ElementoDuplicadoException("La playlist ya se encuentra en el repositorio");

        playlistsPorNombre.put(clave, playlist);
        return true;
    }


    // Buscar albumes
    public Album buscarAlbumPorNombre(String nombre) throws ElementoNoExisteException {
        String clave = TextoUtils.normalizarTexto(nombre);
        if (!albumesPorNombre.containsKey(clave)) {
            throw new ElementoNoExisteException("No se encontró el album con nombre: " + nombre);
        }
        return albumesPorNombre.get(clave);
    }

    public Artista buscarArtistaPorNombre(String nombre) throws ElementoNoExisteException {
        String clave = TextoUtils.normalizarTexto(nombre);
        if (!artistasPorNombre.containsKey(clave)) {
            throw new ElementoNoExisteException("No se encontró el artista con nombre: " + nombre);
        }
        return artistasPorNombre.get(clave);
    }

    public Playlist buscarPlaylistPorNombre(String nombre) throws ElementoNoExisteException {
        String clave = TextoUtils.normalizarTexto(nombre);
        if (!playlistsPorNombre.containsKey(clave)) {
            throw new ElementoNoExisteException("No se encontró la playlist con nombre: " + nombre);
        }
        return playlistsPorNombre.get(clave);
    }

    public void eliminarCancion(String nombre) throws ElementoNoExisteException {
        String clave = TextoUtils.normalizarTexto(nombre);
        Cancion c = buscarCancionPorNombre(nombre);

        Album album = c.getAlbum();
        if (album != null) {
            try {
                album.eliminarCancion(c);
            } catch (CancionNoExistenteException ignored) {}
        }

        Artista artista = c.getArtista();
        if (artista != null) {
            try {
                for (Album a : artista.getCanciones().values()) {
                    if (a.contieneCancion(c)) {
                        try {
                            a.eliminarCancion(c);
                        } catch (CancionNoExistenteException ignored) {}
                    }
                }
            } catch (Exception ignored) {}
        }

        for (Playlist p : getPlaylist()) {
            p.eliminarCancionPorNombre(nombre);
        }

        cancionesPorNombre.remove(clave);
        try {
            repoCanciones.eliminar(c.getId());
        } catch (RepositorioNoExisteException | ElementoNoExisteException ignored) {}

        System.out.println(" Canción '" + nombre + "' eliminada correctamente.");
    }

    public void eliminarArtista(String nombre) throws ElementoNoExisteException, ArtistaNoIncluidoException {
        String clave = TextoUtils.normalizarTexto(nombre);
        Artista artista = buscarArtistaPorNombre(nombre);

        List<String> cancionesAEliminar = new ArrayList<>();
        for (Cancion c : new ArrayList<>(cancionesPorNombre.values())) {
            if (c.getArtista() != null && c.getArtista().equals(artista)) {
                cancionesAEliminar.add(c.getNombre());
            }
        }
        for (String n : cancionesAEliminar) {
            eliminarCancion(n);
        }

        for (Album album : new ArrayList<>(albumesPorNombre.values())) {
            if (album.getArtistas().containsKey(artista.getNombre())) {
                album.eliminarArtista(artista);
            }
        }

        artistasPorNombre.remove(clave);
        try {
            repoArtistas.eliminar(artista.getId());
        } catch (RepositorioNoExisteException | ElementoNoExisteException ignored) {}

        System.out.println("Artista '" + nombre + "' y sus canciones fueron eliminados.");
    }

    public void eliminarAlbum(String nombre) throws ElementoNoExisteException {
        String clave = TextoUtils.normalizarTexto(nombre);
        Album album = buscarAlbumPorNombre(nombre);

        List<String> cancionesDeEsteAlbum = new ArrayList<>();
        for (Cancion c : new ArrayList<>(cancionesPorNombre.values())) {
            if (c.getAlbum() != null && c.getAlbum().equals(album)) {
                cancionesDeEsteAlbum.add(c.getNombre());
            }
        }
        for (String nc : cancionesDeEsteAlbum) {
            eliminarCancion(nc);
        }

        for (Artista a : new ArrayList<>(artistasPorNombre.values())) {
            try {
                a.eliminarAlbum(album);
            } catch (Exception ignored) {}
        }

        albumesPorNombre.remove(clave);
        try {
            repoAlbumes.eliminar(album.getId());
        } catch (RepositorioNoExisteException | ElementoNoExisteException ignored) {}

        System.out.println(" Álbum '" + nombre + "' y sus canciones fueron eliminados.");
    }

    public void eliminarPlaylist(String nombre) throws ElementoNoExisteException {
        String clave = TextoUtils.normalizarTexto(nombre);
        Playlist p = buscarPlaylistPorNombre(nombre);
        playlistsPorNombre.remove(clave);
        try {
            repoPlaylists.eliminar(p.getId());
        } catch (RepositorioNoExisteException | ElementoNoExisteException ignored) {}
        System.out.println("✅ Playlist '" + nombre + "' eliminada correctamente.");
    }

}
