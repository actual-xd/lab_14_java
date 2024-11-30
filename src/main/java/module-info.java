module ru.guu.lab14 {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.guu.lab14 to javafx.fxml;
    exports ru.guu.lab14;
}