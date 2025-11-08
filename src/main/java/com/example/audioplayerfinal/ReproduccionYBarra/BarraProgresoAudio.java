package com.example.audioplayerfinal.ReproduccionYBarra;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

public class BarraProgresoAudio extends Application {

    @Override
    public void start(Stage stage) {
        //audio path se le envia la ruta del archivo de sonido
        String audioPath = getClass().getResource("/musiquita.wav").toExternalForm();
        Media media = new Media(audioPath);
        MediaPlayer player = new MediaPlayer(media);

        //botones de play y pase
        Button playBtn = new Button("▶ Play");
        Button pauseBtn = new Button("⏸ Pause");

        //barrita de progreso
        Slider barra = new Slider();
        barra.setMin(0);
        barra.setMax(100);
        barra.setValue(0);

        Slider volumen = new Slider(0, 1, 0.5); // rango 0 (mudo) a 1 (máximo)
        volumen.setShowTickMarks(true);
        volumen.setShowTickLabels(true);
        volumen.setBlockIncrement(0.1);
        volumen.setOrientation(javafx.geometry.Orientation.VERTICAL);
        volumen.setPrefHeight(100);  // altura del control
        volumen.setPrefWidth(20);    // ancho más angosto


        player.setVolume(0.5); // volumen inicial

        //vinculo la barra de volumen con slider
        volumen.valueProperty().addListener((obs, oldVal, newVal) -> {
            player.setVolume(newVal.doubleValue());
        });


        //actualiza la barra
        player.currentTimeProperty().addListener((obs, oldTime, newTime) -> {
            if (!barra.isValueChanging()) {
                Duration total = player.getTotalDuration();
                if (total != null && total.greaterThan(Duration.ZERO)) {
                    barra.setValue(newTime.toMillis() / total.toMillis() * 100);
                }
            }
        });

        // retroceder o adelantar
        barra.valueChangingProperty().addListener((obs, wasChanging, isChanging) -> {
            if (!isChanging) {
                player.seek(player.getTotalDuration().multiply(barra.getValue() / 100.0));
            }
        });

        // con esto se mueve la barrita
        barra.setOnMousePressed(e -> player.seek(player.getTotalDuration().multiply(barra.getValue() / 100.0)));
        barra.setOnMouseReleased(e -> player.seek(player.getTotalDuration().multiply(barra.getValue() / 100.0)));

        // acciones de los botones
        playBtn.setOnAction(e -> player.play());
        pauseBtn.setOnAction(e -> player.pause());

        HBox root = new HBox(10, playBtn, pauseBtn, barra, new Label("Volumen:"), volumen);
        root.setStyle("-fx-padding: 20; -fx-alignment: center;");


        stage.setScene(new Scene(root, 400, 150));
        stage.setTitle("Reproductor con barra de progreso");
        stage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}
