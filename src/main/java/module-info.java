module com.example.jeopardywordgame {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.jeopardywordgame to javafx.fxml;
    exports com.example.jeopardywordgame;
}