import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The main class for the DuckHunt game application.
 */
public class DuckHunt extends Application {

    /**
     * The entry point of the application.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Starts the DuckHunt game application.
     *
     * @param primaryStage the primary stage for the application
     */
    @Override
    public void start(Stage primaryStage) {
        Volume.setVolume(0.04);
        Scaler.SCALE = 3;
        CustomStage stage = new CustomStage();
        stage.show();
        stage.getCustomPane().requestFocus();
    }
}
