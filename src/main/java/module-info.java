module com.project2.pomodoro {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.project2.pomodoro to javafx.fxml, javafx.media;
    exports com.project2.pomodoro;
}