module com.example.q2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.q2 to javafx.fxml;
    exports com.example.q2;
}