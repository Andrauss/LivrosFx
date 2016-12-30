
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Fernando Andrauss
 */
public class LivrosFx extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Carrega o FXML
        FXMLLoader splashLoader = new FXMLLoader(getClass().getResource("/splash.fxml"));
        StackPane splashPane = splashLoader.load();

        // Cria a Janela do Splash
        // Define como transparente para que não apareça decoração de janela (maximizar, minimizar)
        Stage splashStage = new Stage(StageStyle.TRANSPARENT);
        final Scene scene = new Scene(splashPane);
        scene.setFill(Color.TRANSPARENT); // Define que a cor do painel root seja transparente para que dê o efeito de sombra
        splashStage.setScene(scene);

        // Cria o serviço para rodar alguma tarefa em background enquanto o splash é mostrado (no caso somente um delay de 10s)
        Service<Boolean> splashService = new Service<Boolean>() {

            // Mostra o splash quando o serviço for iniciado
            @Override
            public void start() {
                super.start();
                splashStage.show(); // mostra a janela
            }

            // Simulação de uma tarefa pesada 
            @Override
            protected Task<Boolean> createTask() {
                return new Task<Boolean>() {
                    @Override
                    protected Boolean call() throws Exception {
                        Thread.sleep(10000); // Delay de 10s
                        return true;
                    }
                };
            }

            // Quando a tarefa for finalizada fecha o splash e mostra a tela principal
            @Override
            protected void succeeded() {
                splashStage.close();  // Fecha o splash
                try {
                    chamarTelaPrincipal(primaryStage); // Chama a tela principal
                } catch (Exception ex) {
                }
            }
        };

        splashService.start();

    }

    public static void main(String[] args) {
        launch(args);
    }

    private void chamarTelaPrincipal(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/livros.fxml"));
        Scene s = new Scene(loader.load());
        primaryStage.setScene(s);
        primaryStage.setTitle("LivrosFx");
        primaryStage.show();
    }

}
