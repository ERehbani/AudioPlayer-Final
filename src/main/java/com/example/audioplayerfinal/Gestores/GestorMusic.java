package com.example.audioplayerfinal.Gestores;

import com.example.audioplayerfinal.Clases.*;
import com.example.audioplayerfinal.ENums.EGenero;
import com.example.audioplayerfinal.Exceptions.*;
import com.example.audioplayerfinal.Utils.TextoUtils;

import java.util.*;

public class GestorMusic {

    // repositorios para manejar las entidades principales
    private Repositorio<Integer, Cancion> repoCanciones;
    private Repositorio<Integer, Album> repoAlbumes;
    private Repositorio<Integer, Artista> repoArtistas;
    private Repositorio<Integer, Playlist> repoPlaylists;

    // mapas auxiliares para busquedas mas rapidas
    private Map<String, Cancion> cancionesPorNombre;
    private Map<EGenero, Set<Cancion>> cancionesPorGenero;
    private Map<String, Album> albumesPorNombre;
    private Map<String, Artista> artistasPorNombre;
    private Map<String, Playlist> playlistsPorNombre;

    // constructor: inicializa todos los repositorios y mapas vacios
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

    // crea un nuevo artista y lo agrega al repositorio si no existe otro con el mismo nombre
    public Artista crearArtista(String nombre) throws ElementoDuplicadoException, RepositorioNoExisteException {
        // normaliza el nombre
        String clave = TextoUtils.normalizarTexto(nombre);
        // verifica si ya existe
        if (artistasPorNombre.containsKey(clave))
            throw new ElementoDuplicadoException("ya existe un artista con el nombre: " + nombre);
        // crea y registra el artista
        Artista nuevo = new Artista(nombre);
        repoArtistas.agregar(nuevo.getId(), nuevo);
        artistasPorNombre.put(clave, nuevo);
        return nuevo;
    }

    // crea un nuevo album con su fecha y discografica y lo guarda en el repositorio
    public Album crearAlbum(String nombre, String fecha, String discografica)
            throws ElementoDuplicadoException, RepositorioNoExisteException {
        String clave = TextoUtils.normalizarTexto(nombre);
        if (albumesPorNombre.containsKey(clave))
            throw new ElementoDuplicadoException("ya existe un album con el nombre: " + nombre);

        // crea el album vacio con un mapa de artistas
        Album nuevo = new Album(nombre, fecha, discografica);
        Map<String, Artista> map = new HashMap<>();
        nuevo.setArtistas(map);

        // lo guarda en el repositorio
        repoAlbumes.agregar(nuevo.getId(), nuevo);
        albumesPorNombre.put(clave, nuevo);
        return nuevo;
    }

    // crea una nueva cancion y la registra segun nombre, genero y demas datos
    public Cancion crearCancion(String nombre, int duracionSeg, EGenero genero, String ruta, int cantReproducciones, String fecha)
            throws ElementoDuplicadoException, RepositorioNoExisteException {
        String clave = TextoUtils.normalizarTexto(nombre);
        if (cancionesPorNombre.containsKey(clave))
            throw new ElementoDuplicadoException("ya existe una cancion con el nombre: " + nombre);

        // instancia la cancion
        Cancion nueva = new Cancion(nombre, duracionSeg, genero, ruta, cantReproducciones, fecha);

        // la agrega al repositorio y a los mapas auxiliares
        repoCanciones.agregar(nueva.getId(), nueva);
        cancionesPorNombre.put(clave, nueva);
        cancionesPorGenero.computeIfAbsent(genero, k -> new HashSet<>()).add(nueva);

        return nueva;
    }

    // crea una nueva playlist y la agrega al repositorio
    public Playlist crearPlaylist(String nombre) throws ElementoDuplicadoException, RepositorioNoExisteException {
        String clave = TextoUtils.normalizarTexto(nombre);
        if (playlistsPorNombre.containsKey(clave))
            throw new ElementoDuplicadoException("ya existe una playlist con el nombre: " + nombre);

        // instancia la playlist y la guarda
        Playlist nueva = new Playlist(nombre);
        repoPlaylists.agregar(nueva.getId(), nueva);
        playlistsPorNombre.put(clave, nueva);
        return nueva;
    }

    // busca una cancion por nombre dentro del mapa cancionesPorNombre
    public Cancion buscarCancionPorNombre(String nombre) throws ElementoNoExisteException {
        String clave = TextoUtils.normalizarTexto(nombre);
        if (!cancionesPorNombre.containsKey(clave))
            throw new ElementoNoExisteException("no se encontro la cancion " + nombre);
        return cancionesPorNombre.get(clave);
    }

    // busca una cancion por id recorriendo la coleccion de canciones
    public Cancion buscarCancionPorId(int id) throws ElementoNoExisteException {
        for (Cancion c : cancionesPorNombre.values()) {
            if (c.getId() == id) return c;
        }
        throw new ElementoNoExisteException("no se encontro la cancion con id: " + id);
    }

    // devuelve todas las canciones
    public List<Cancion> getCanciones() {
        return new ArrayList<>(cancionesPorNombre.values());
    }

    // devuelve todos los artistas
    public List<Artista> getArtista() {
        return new ArrayList<>(artistasPorNombre.values());
    }

    // devuelve todos los albums
    public List<Album> getAlbums() {
        return new ArrayList<>(albumesPorNombre.values());
    }

    // devuelve todas las playlists
    public List<Playlist> getPlaylist() {
        return new ArrayList<>(playlistsPorNombre.values());
    }

    // agrega una cancion nueva si no existe previamente
    public boolean agregarCancion(Cancion cancion) throws ElementoDuplicadoException, RepositorioNoExisteException {
        if (cancion == null)
            throw new IllegalArgumentException("se debe enviar una cancion");
        String clave = TextoUtils.normalizarTexto(cancion.getNombre());
        if (cancionesPorNombre.containsKey(clave))
            throw new ElementoDuplicadoException("la cancion ya fue agregada");
        cancionesPorNombre.put(clave, cancion);
        return true;
    }

    // agrega un artista al mapa si no existia
    public boolean agregarAritsta(Artista artista) throws ElementoDuplicadoException {
        if (artista == null)
            throw new IllegalArgumentException("se debe enviar un artista");
        String clave = TextoUtils.normalizarTexto(artista.getNombre());
        if (artistasPorNombre.containsKey(clave))
            throw new ElementoDuplicadoException("el artista ya se encuentra en el repositorio");

        artistasPorNombre.put(clave, artista);
        return true;
    }

    // agrega un album al mapa si no existia
    public boolean agregarAlbum(Album album) throws ElementoDuplicadoException {
        if (album == null)
            throw new IllegalArgumentException("se debe enviar un album");
        String clave = TextoUtils.normalizarTexto(album.getNombre());
        if (albumesPorNombre.containsKey(clave))
            throw new ElementoDuplicadoException("el album ya se encuentra en el repositorio");

        albumesPorNombre.put(clave, album);
        return true;
    }

    // agrega una playlist al mapa si no existia
    public boolean agregarPlaylist(Playlist playlist) throws ElementoDuplicadoException {
        if (playlist == null)
            throw new IllegalArgumentException("se debe enviar una playlist");
        String clave = TextoUtils.normalizarTexto(playlist.getNombre());
        if (playlistsPorNombre.containsKey(clave))
            throw new ElementoDuplicadoException("la playlist ya se encuentra en el repositorio");

        playlistsPorNombre.put(clave, playlist);
        return true;
    }

    // busca un album por nombre en el mapa
    public Album buscarAlbumPorNombre(String nombre) throws ElementoNoExisteException {
        String clave = TextoUtils.normalizarTexto(nombre);
        if (!albumesPorNombre.containsKey(clave))
            throw new ElementoNoExisteException("no se encontro el album con nombre: " + nombre);
        return albumesPorNombre.get(clave);
    }

    // busca un artista por nombre
    public Artista buscarArtistaPorNombre(String nombre) throws ElementoNoExisteException {
        String clave = TextoUtils.normalizarTexto(nombre);
        if (!artistasPorNombre.containsKey(clave))
            throw new ElementoNoExisteException("no se encontro el artista con nombre: " + nombre);
        return artistasPorNombre.get(clave);
    }

    // busca una playlist por nombre
    public Playlist buscarPlaylistPorNombre(String nombre) throws ElementoNoExisteException {
        String clave = TextoUtils.normalizarTexto(nombre);
        if (!playlistsPorNombre.containsKey(clave))
            throw new ElementoNoExisteException("no se encontro la playlist con nombre: " + nombre);
        return playlistsPorNombre.get(clave);
    }

    // elimina una cancion de todos los lugares donde aparezca
    public void eliminarCancion(String nombre) throws ElementoNoExisteException {
        String clave = TextoUtils.normalizarTexto(nombre);
        Cancion c = buscarCancionPorNombre(nombre);

        // si tiene album, la saca del album
        Album album = c.getAlbum();
        if (album != null) {
            try {
                album.eliminarCancion(c);
            }
            catch (CancionNoExistenteException ignored) {}
        }

        // si tiene artista, la quita de sus albums
        Artista artista = c.getArtista();
        if (artista != null) {
            try {
                for (Album a : artista.getCanciones().values()) {
                    if (a.contieneCancion(c)) {
                        try { a.eliminarCancion(c); }
                        catch (CancionNoExistenteException ignored) {}
                    }
                }
            } catch (Exception ignored) {}
        }

        // la elimina de todas las playlists
        for (Playlist p : getPlaylist()) {
            p.eliminarCancionPorNombre(nombre);
        }

        // borra del mapa principal
        cancionesPorNombre.remove(clave);

        // intenta quitarla del repositorio
        try { repoCanciones.eliminar(c.getId()); }
        catch (RepositorioNoExisteException | ElementoNoExisteException ignored) {}

        System.out.println("cancion '" + nombre + "' eliminada correctamente.");
    }

    // elimina un artista y limpia todas sus relaciones
    public void eliminarArtista(String nombre) throws ElementoNoExisteException, ArtistaNoIncluidoException {
        String clave = TextoUtils.normalizarTexto(nombre);
        Artista artista = buscarArtistaPorNombre(nombre);

        // junta las canciones del artista para eliminarlas
        List<String> cancionesAEliminar = new ArrayList<>();
        for (Cancion c : new ArrayList<>(cancionesPorNombre.values())) {
            if (c.getArtista() != null && c.getArtista().equals(artista)) {
                cancionesAEliminar.add(c.getNombre());
            }
        }

        // elimina las canciones asociadas
        for (String n : cancionesAEliminar) eliminarCancion(n);

        // elimina el artista de los albums donde aparezca
        for (Album album : new ArrayList<>(albumesPorNombre.values())) {
            if (album.getArtistas().containsKey(artista.getNombre())) {
                album.eliminarArtista(artista);
            }
        }

        // lo saca del mapa
        artistasPorNombre.remove(clave);

        // lo borra del repositorio
        try { repoArtistas.eliminar(artista.getId()); }
        catch (RepositorioNoExisteException | ElementoNoExisteException ignored) {}

        System.out.println("artista '" + nombre + "' y sus canciones fueron eliminados.");
    }

    // elimina un album y todas sus relaciones
    public void eliminarAlbum(String nombre) throws ElementoNoExisteException {
        String clave = TextoUtils.normalizarTexto(nombre);
        Album album = buscarAlbumPorNombre(nombre);

        // busca las canciones del album
        List<String> cancionesDeEsteAlbum = new ArrayList<>();
        for (Cancion c : new ArrayList<>(cancionesPorNombre.values())) {
            if (c.getAlbum() != null && c.getAlbum().equals(album)) {
                cancionesDeEsteAlbum.add(c.getNombre());
            }
        }

        // las elimina una por una
        for (String nc : cancionesDeEsteAlbum) eliminarCancion(nc);

        // limpia referencias en artistas
        for (Artista a : new ArrayList<>(artistasPorNombre.values())) {
            try { a.eliminarAlbum(album); }
            catch (Exception ignored) {}
        }

        // lo remueve del mapa principal
        albumesPorNombre.remove(clave);

        // intenta quitarlo del repositorio
        try { repoAlbumes.eliminar(album.getId()); }
        catch (RepositorioNoExisteException | ElementoNoExisteException ignored) {}

        System.out.println("album '" + nombre + "' y sus canciones fueron eliminados.");
    }

    // elimina una playlist del repositorio
    public void eliminarPlaylist(String nombre) throws ElementoNoExisteException {
        String clave = TextoUtils.normalizarTexto(nombre);
        Playlist p = buscarPlaylistPorNombre(nombre);

        // la remueve del mapa
        playlistsPorNombre.remove(clave);

        // intenta quitarla del repositorio
        try {
            repoPlaylists.eliminar(p.getId());
        }
        catch (RepositorioNoExisteException | ElementoNoExisteException ignored) {}

        System.out.println("playlist '" + nombre + "' eliminada correctamente.");
    }
}
