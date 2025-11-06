package com.example.audioplayerfinal.Clases;

import com.example.audioplayerfinal.ENums.EGenero;
import com.example.audioplayerfinal.Exceptions.AlbumNoEncontradoExcepcion;
import com.example.audioplayerfinal.Exceptions.ColeccionVaciaException;
import com.example.audioplayerfinal.Exceptions.EGeneroExistenteExcepcion;
import com.example.audioplayerfinal.Exceptions.EGeneroNoEncontradoExcepcion;
import com.example.audioplayerfinal.Interfaces.IIdentificador;

import java.util.*;

public class Artista implements IIdentificador {
    private static int contador = 0;
    private int id;
    private String nombre;
    private Set<EGenero> generos;
    private Map<String, Album> albums;

    public Artista(int ID, String nombreArtista, int edad) {
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

    public Set<EGenero> getGeneros() {
        return generos;
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
        sb.append("Nombre: ").append(nombre);
        sb.append("Generos: ").append(generos);
        sb.append("Canciones: ");

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
            for (Map.Entry<String, Album> entry : albums.entrySet()) {
                sb.append(entry.getKey());
            }
        }
        return sb.toString();
    }

    public void AgregarAlbum(Album album) throws AlbumNoEncontradoExcepcion {
        if (album == null || albums.containsKey(album.getNombre())) {
            throw new AlbumNoEncontradoExcepcion("Album ya esta registrado");
        }
        albums.put(album.getNombre(), album);
    }

    public void EliminarAlbum(Album album) throws AlbumNoEncontradoExcepcion {
        if (album == null || !albums.containsKey(album.getNombre())) {
            throw new AlbumNoEncontradoExcepcion("Album no esta registrado");
        }
        albums.remove(album.getNombre());
    }

    public void AgregarGenero(EGenero Gen)throws EGeneroExistenteExcepcion {
        if (Gen == null || generos.contains(Gen)) {
            throw new EGeneroExistenteExcepcion("Genero ya esta registrado");
        }
        generos.add(Gen);
    }

    public void EliminarGenero(EGenero Gen)throws EGeneroNoEncontradoExcepcion {
        if (Gen == null || !generos.contains(Gen)) {
            throw new EGeneroNoEncontradoExcepcion("Genero no esta registrado");
        }
        generos.remove(Gen);
    }

}
