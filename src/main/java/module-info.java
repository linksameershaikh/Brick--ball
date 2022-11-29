module com.example.bb {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.bb to javafx.fxml;
    exports com.example.bb;
}