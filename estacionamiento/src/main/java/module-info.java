module com.example.estacionamiento {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.estacionamiento to javafx.fxml;
    exports com.example.estacionamiento;
}