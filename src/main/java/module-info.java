module com.example.audioplayerfinal {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires org.json;
    requires javafx.media;

    // Paquetes que usás en FX y consola
    opens com.example.audioplayerfinal to javafx.fxml;
    opens com.example.audioplayerfinal.Clases to javafx.base;
    opens com.example.audioplayerfinal.Controllers to javafx.fxml;
    opens com.example.audioplayerfinal.ReproduccionYBarra to javafx.graphics;
    exports com.example.audioplayerfinal.ReproduccionYBarra;



    opens com.example.audioplayerfinal.UI to javafx.fxml;
    exports com.example.audioplayerfinal.UI;

    exports com.example.audioplayerfinal;
    exports com.example.audioplayerfinal.Interfaces;
    opens com.example.audioplayerfinal.Interfaces to javafx.fxml;
    opens com.example.audioplayerfinal.Gestores to javafx.base;

}
