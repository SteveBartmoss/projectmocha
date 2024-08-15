import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import filemanager.FileManager;

public class ProjectMocha extends Application{

    File archivo = null;
    int initialContentLength = 0;

    public void start(Stage primaryStage){

        primaryStage.setTitle("mochaEditor");

        BorderPane root = new BorderPane();

        /*VBox leftPane = new VBox();
        Label explorerLabel = new Label("Explorador");
        leftPane.getChildren().add(explorerLabel);*/

        VBox buttonPane = new VBox();
        Label buttonLabel = new Label("");
        buttonPane.getChildren().add(buttonLabel);

        TextArea textArea = new TextArea();
        //textArea.setPromptText("Escribe aqui...");
        VBox.setVgrow(textArea, Priority.ALWAYS);
        VBox centerPane = new VBox();
        centerPane.getChildren().add(textArea);

        MenuBar menuBar = new MenuBar();

        Menu fileMenu = new Menu("Archivo");
        Menu editMenu = new Menu("Editar");
        Menu helpMenu = new Menu("Ayuda");

        MenuItem newFile = new MenuItem("Nuevo");
        MenuItem openFile = new MenuItem("Abrir");
        MenuItem saveFile = new MenuItem("Guardar");
        MenuItem exit = new MenuItem("Salir");

        MenuItem about = new MenuItem("About");


        fileMenu.getItems().addAll(newFile, openFile, saveFile, exit);
        helpMenu.getItems().addAll(about);

        menuBar.getMenus().addAll(fileMenu, editMenu, helpMenu);

        openFile.setOnAction(e ->{
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Abrir Archivo");

            //fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

            //File file = fileChooser.showOpenDialog(primaryStage);

            archivo = fileChooser.showOpenDialog(primaryStage);

            FileManager fileManager = new FileManager();

            textArea.setText(fileManager.abrirArchivo(archivo));

            initialContentLength = fileManager.abrirArchivo(archivo).length();
            
            buttonLabel.setText(archivo.getName());

            /*try(BufferedReader br = new BufferedReader(new FileReader(file))){
                StringBuilder sb = new StringBuilder();
                String line;
                while((line = br.readLine()) != null){
                    sb.append(line).append("\n");
                }

                textArea.setText(sb.toString());
            }catch(IOException ex){
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("No se pudo leer el archivo");
                alert.setContentText("Ocurrio un error al leer archivo: " + ex.getMessage());
                alert.showAndWait();
            }*/ 
            
        });

        textArea.textProperty().addListener(new ChangeListener<String>(){
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue){
                if (archivo != null){
                    if(newValue.length() != initialContentLength){
                        buttonLabel.setText(archivo.getName() + " (Modificado)");
                    } else {
                        buttonLabel.setText(archivo.getName());
                    }
                }
            }
        });

        saveFile.setOnAction(e->{

            FileManager fileManager = new FileManager();

            if(archivo != null){
                fileManager.guardarArchivo(archivo, textArea.getText());
                initialContentLength = textArea.getText().length();
                buttonLabel.setText(archivo.getName());
            }else{
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Guardar Archivo");
                archivo = fileChooser.showSaveDialog(primaryStage);
                 if(archivo != null){
                    //guardarArchivo(file, textArea.getText());
                    fileManager.guardarArchivo(archivo, textArea.getText());
                    initialContentLength = textArea.getText().length();
                    buttonLabel.setText(archivo.getName());
                }
                String contenido = textArea.getText();
                System.out.println(contenido);
            }
            
            //fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files","*.txt"));

        });

        about.setOnAction(event->{

            Stage aboutStage = new Stage();
            aboutStage.setTitle("About MochaEditor");
            aboutStage.initModality(Modality.APPLICATION_MODAL);

            VBox aboutContent = new VBox();
            aboutContent.setPadding(new Insets(10));
            aboutContent.setSpacing(10);

            Label header = new Label("MochaEditor");
            header.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
            Label info = new Label("MochaEditor es un editor de texto desarrollado en JavaFX. \nVersión: 1.0\nDesarrollado por: Tu Nombre");
            

            Button closeButton = new Button("Cerrar");
            closeButton.setOnAction(e -> aboutStage.close());

            aboutContent.getChildren().addAll(header, info, closeButton);

            Scene aboutScene = new Scene(aboutContent, 300,150);
            aboutScene.getStylesheets().add(getClass().getResource("themes/darkTheme.css").toExternalForm());
            aboutStage.setScene(aboutScene);
            aboutStage.showAndWait();
            
            /*Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("About MochaEditor");
            alert.setHeaderText("Informacion sobre MochaEditor");
            alert.setContentText("MochaEditor es un editor de texto desarrollado en JavaFX.");
            alert.showAndWait();*/
        });

        HBox topPane = new HBox();
        topPane.getChildren().add(menuBar);

        //root.setLeft(leftPane);
        root.setCenter(centerPane);
        root.setTop(topPane);
        root.setBottom(buttonPane);
        
        Scene scene = new Scene(root, 800, 600);

        scene.getStylesheets().add(getClass().getResource("themes/darkTheme.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
