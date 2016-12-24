
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Fernando Andrauss
 */
public class LivrosFx extends Application {
    
 @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/livros.fxml"));
        Scene s = new Scene(loader.load());
        primaryStage.setScene(s);
        primaryStage.setTitle("LivrosFx");
        primaryStage.show();
    }
    
     public static void main(String[] args) {
        launch(args);
    }
    
}
