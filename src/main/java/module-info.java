module com.project2.pomodoro {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.pomodoro to javafx.fxml, javafx.media;
    exports com.pomodoro;
}