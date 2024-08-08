import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
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
        TexteArea textArea = new TextArea();
        textArea.setPromptText("Escribe aqui...");
        centerPane.getChildren().add(textArea);

        MenuBar menuBar = new MenuBar();

        Menu fileMenu = new Menu("Archivo");
        Menu editMenu = new Menu("Editar");
        Menu helpMenu = new Menu("Ayuda");

        MenuItem newFile = new MenuItem("Nuevo");
        MenuItem openFile = new MenuItem("Abrir");
        MenuItem saveFile = new MenuItem("Guardar");
        MenuItem exit = new MenuItem("Salir");


        fileMenu.getItems().addAll(newFile, openFile, saveFile, exit);

        menuBar.getMenus().addAll(fileMenu, editMenu, helpMenu);

        HBox topPane = new HBox();
        
        topPane.getChildren().add(menuBar);

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
