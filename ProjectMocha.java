import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx,scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ProjectMocha extends Application{

    public void start(Stage primaryStage){
        primaryStage.setTitle("mochaEditor");

        BorderPane root = new BorderPane();

        VBox leftPane = new VBox();
        Label explorerLabel = new Label("Explorador");
        leftPane.getChildren().add(explorerLabel);

        VBox centerPane = new VBox();
        Label mainWindowLabel = new Label("Ventana principal");
        centerPane.getChildren().add(mainWindowLabel);

        root.setLeft(leftPane);
        root.setCenter(centerPane);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
