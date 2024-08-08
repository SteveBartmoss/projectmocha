import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scena.layout.HBox;
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

        HBox topPane = new HBox();
        Label navigationLabel = new Label("Barra de navegacion");
        topPane.getChildren().add(navigationLabel);

        root.setLeft(leftPane);
        root.setCenter(centerPane);
        root.setTop(topPane);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
