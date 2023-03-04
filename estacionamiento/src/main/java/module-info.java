module proj.estacionamiento {
    requires javafx.controls;
    requires javafx.fxml;


    opens proj.estacionamiento to javafx.fxml;
    exports proj.estacionamiento;
    exports proj.estacionamiento.Clases;
    opens proj.estacionamiento.Clases to javafx.fxml;
}