module de.ld {
    requires javafx.controls;
    requires javafx.fxml;

    opens de.ld to javafx.fxml;
    exports de.ld;
}