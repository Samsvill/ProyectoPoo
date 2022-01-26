module ec.edu.espol.proyectolinterfazabsamjo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens ec.edu.espol.proyectolinterfazabsamjo to javafx.fxml;
    exports ec.edu.espol.proyectolinterfazabsamjo;
    opens ec.edu.espol.model to javafx.fxml;
    exports ec.edu.espol.model;
    opens ec.edu.espol.controllers to javafx.fxml;
    exports ec.edu.espol.controllers;
    opens ec.edu.espol.utilitario to javafx.fxml;
    exports ec.edu.espol.utilitario;
}
