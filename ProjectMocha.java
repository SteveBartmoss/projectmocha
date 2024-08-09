import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ProjectMocha extends Application{

    public void start(Stage primaryStage){
        primaryStage.setTitle("mochaEditor");

        BorderPane root = new BorderPane();

        VBox leftPane = new VBox();
        Label explorerLabel = new Label("Explorador");
        leftPane.getChildren().add(explorerLabel);

        TextArea textArea = new TextArea();
        textArea.setPromptText("Escribe aqui...");
        VBox.setVgrow(textArea, Priority.ALWAYS);
        VBox centerPane = new VBox();
        
        centerPane.getChildren().add(textArea);

        Button getTextButton = new Button("Save");
        getTextButton.setOnAction(e -> {

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Guardas Archivo");

            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files","*.txt"));

            File file = fileChooser.showSaveDialog(primaryStage);
            if(file != null){
                guardarArchivo(file, textArea.getText());
            }
            String contenido = textArea.getText();
            System.out.println(contenido);
        });

        //centerPane.getChildren().add(getTextButton);

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
        topPane.getChildren().add(getTextButton);

        root.setLeft(leftPane);
        root.setCenter(centerPane);
        root.setTop(topPane);

        Scene scene = new Scene(root, 800, 600);

        scene.getStylesheets().add(getClass().getResource("darkTheme.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void guardarArchivo(File file, String content){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
            writer.write(content);
        } catch(IOException ex){
            System.out.println("Error al guardar el archivo: " + ex.getMessage());
        }
    }

    public static void main(String[] args){
        launch(args);
    }
}
