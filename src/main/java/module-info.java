module com.scherbakov.ciphermaster {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.scherbakov.ciphermaster to javafx.fxml;
    exports com.scherbakov.ciphermaster;
}
