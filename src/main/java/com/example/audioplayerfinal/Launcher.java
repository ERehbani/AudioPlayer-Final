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
    }
}