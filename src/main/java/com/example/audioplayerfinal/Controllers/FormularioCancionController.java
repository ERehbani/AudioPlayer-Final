package com.example.audioplayerfinal.Controllers;

import com.example.audioplayerfinal.Clases.Cancion;
import com.example.audioplayerfinal.ENums.EGenero;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FormularioCancionController {

    private Button btnVerCanciones;

    public static final List<Cancion> listaCanciones = new ArrayList<>();

    @FXML
    private TextField txtNombre, txtDuracion, txtIdAlbum, txtColaboradores, txtRuta, txtReproducciones;

    @FXML
    private ComboBox<EGenero> comboGenero;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Label lblMensaje;

    @FXML
    public void initialize() {
        comboGenero.setItems(FXCollections.observableArrayList(EGenero.values()));
    }

    @FXML
    private void onGuardar() {
        try {
            String nombre = txtNombre.getText();
            int duracion = Integer.parseInt(txtDuracion.getText());
            int idAlbum = Integer.parseInt(txtIdAlbum.getText());
            EGenero genero = comboGenero.getValue();

            List<Integer> colaboradores = new ArrayList<>();
            if (!txtColaboradores.getText().isEmpty()) {
                for (String id : txtColaboradores.getText().split(",")) {
                    colaboradores.add(Integer.parseInt(id.trim()));
                }
            }

            int reproducciones = Integer.parseInt(txtReproducciones.getText());
            String fecha = datePicker.getValue().toString();

            String ruta = txtRuta.getText();

            Cancion nueva = new Cancion(nombre, duracion, genero, ruta, reproducciones, fecha);
            listaCanciones.add(nueva);

            lblMensaje.setText("✅ Canción creada: " + nueva.getNombre());
        } catch (Exception e) {
            lblMensaje.setText("❌ Error: " + e.getMessage());
        }
    }


    @FXML
    private void onVerCanciones() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/audioplayerfinal/lista-canciones.fxml"));
        ListarCancionesController.setCanciones(listaCanciones);
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) txtNombre.getScene().getWindow();
        stage.setScene(scene);
    }


}
