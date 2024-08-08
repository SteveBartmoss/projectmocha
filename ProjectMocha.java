import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ProjectMocha extends Application{

    public void start(Stage primaryStage){
        primaryStage.setTitle("mochaEditor");

        StackPane root = new StackPane();

        root.getChildren().add(new Label("hola"));

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
