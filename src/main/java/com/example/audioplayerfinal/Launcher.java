package com.example.audioplayerfinal;

import com.example.audioplayerfinal.Clases.*;
import com.example.audioplayerfinal.ENums.EGenero;
import com.example.audioplayerfinal.Exceptions.AlbumNoEncontradoExcepcion;
import com.example.audioplayerfinal.Exceptions.ColeccionVaciaException;
import com.example.audioplayerfinal.Exceptions.ElementoDuplicadoException;
import com.example.audioplayerfinal.Exceptions.RepositorioNoExisteException;
import javafx.application.Application;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Collections;

public class Launcher {
    public static void main(String[] args) {
        Application.launch(HelloApplication.class, args);
        try {
            Artista ysya = new Artista("YSY A");
            Artista duki = new Artista("Duki");
            Album album = new Album("EL AFTER DEL AFTER", "11/11/2023", "Ysy A");
            Cancion nodamas = new Cancion("No da mas", 248, EGenero.TRAP, 50000, "11/11/2023");
            Playlist playlist = new Playlist(" 도우스케레레피올라");
            Repositorio repositorio = new Repositorio<>();

            album.agregarArtista(ysya);
            album.agregarArtista(duki);
            album.agregarGenero(EGenero.TRAP);
            album.agregarCancion(nodamas);
            nodamas.agregarArtista(ysya);
            nodamas.agregarArtista(duki);
            ysya.agregarAlbum(album);
            playlist.agregarCancion(nodamas);

            repositorio.agregar(nodamas.getId(), nodamas);
            System.out.println(nodamas.datosCancion());
            //System.out.println(repositorio.mostrar());
            //System.out.println(playlist.mostrarCancion());

        } catch (AlbumNoEncontradoExcepcion e) {
            throw new RuntimeException(e);
        } catch (ElementoDuplicadoException e) {
            throw new RuntimeException(e);
        } catch (RepositorioNoExisteException e) {
            throw new RuntimeException(e);
        } catch (ColeccionVaciaException e) {
            throw new RuntimeException(e);
        }
    }
}
