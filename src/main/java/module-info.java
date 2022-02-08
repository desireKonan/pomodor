module com.project2.pomodoro {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.project2.pomodoro to javafx.fxml;
    exports com.project2.pomodoro;
}