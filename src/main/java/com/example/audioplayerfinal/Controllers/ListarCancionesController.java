package com.example.audioplayerfinal.Controllers;

import com.example.audioplayerfinal.Clases.Cancion;
import com.example.audioplayerfinal.ENums.EGenero;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.List;

public class ListarCancionesController {

    @FXML
    private TableView<Cancion> tablaCanciones;

    @FXML
    private TableColumn<Cancion, String> colNombre;

    @FXML
    private TableColumn<Cancion, EGenero> colGenero;

    @FXML
    private TableColumn<Cancion, Integer> colDuracion;

    @FXML
    private TableColumn<Cancion, Integer> colReproducciones;

    private static List<Cancion> canciones;

    public static void setCanciones(List<Cancion> lista) {
        canciones = lista;
    }

    @FXML
    public void initialize() {
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
        colDuracion.setCellValueFactory(new PropertyValueFactory<>("duracion"));
        colReproducciones.setCellValueFactory(new PropertyValueFactory<>("cantidadReproducciones"));

        if (canciones != null) {
            ObservableList<Cancion> observableList = FXCollections.observableArrayList(canciones);
            tablaCanciones.setItems(observableList);
        }
    }

    @FXML
    private void onVolver() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/audioplayerfinal/formulario-cancion.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) tablaCanciones.getScene().getWindow();
        stage.setScene(scene);
    }
}
