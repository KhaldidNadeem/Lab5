module com.example.colorchooser {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.colorchooser to javafx.fxml;
    exports com.example.colorchooser;
}