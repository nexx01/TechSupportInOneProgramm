module org.iresto {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.iresto to javafx.fxml;
    exports org.iresto;
}