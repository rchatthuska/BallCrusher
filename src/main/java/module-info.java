module com.example.ballcrusher {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ballcrusher to javafx.fxml;
    exports com.example.ballcrusher;
}