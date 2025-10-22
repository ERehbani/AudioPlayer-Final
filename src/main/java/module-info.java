module com.example.audioplayerfinal {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.audioplayerfinal to javafx.fxml;
    exports com.example.audioplayerfinal;
    exports Interfaces;
    opens Interfaces to javafx.fxml;
}