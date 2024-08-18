import javafx.application.Platform;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
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

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import filemanager.FileManager;
import components.About;
import components.FileWindow;

public class ProjectMocha extends Application{

    File archivo = null;
    int initialContentLength = 0;
    boolean haveChanges = false;
    FileManager fileManager = new FileManager();
    //FileWindow fileWindowManager = new FileWindow();

    public void start(Stage primaryStage){

        primaryStage.setTitle("mochaEditor");

        BorderPane root = new BorderPane();

        KeyCombination keyCombCtrlS = new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN);
        KeyCombination keyCombCtrlN = new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN);
        KeyCombination keyCombCtrlO = new KeyCodeCombination(KeyCode.O, KeyCodeCombination.CONTROL_DOWN);
        KeyCombination keyCombCtrlQ = new KeyCodeCombination(KeyCode.Q, KeyCodeCombination.CONTROL_DOWN);

        VBox buttonPane = new VBox();
        Label buttonLabel = new Label("");
        buttonLabel.setPadding(new Insets(5, 0, 5, 5 )); // top, right, bottom, left
        buttonPane.getChildren().add(buttonLabel);

        TabPane tabPane = new TabPane();

        /*Tab tab1 = new Tab("Tab1");
        TextArea textArea = new TextArea();
        tab1.setContent(textArea);
        tabPane.getTabs().add(tab1);*/

        VBox.setVgrow(tabPane, Priority.ALWAYS);
        VBox centerPane = new VBox();
        centerPane.getChildren().add(tabPane);

        MenuBar menuBar = new MenuBar();

        Menu fileMenu = new Menu("Archivo");
        //Menu editMenu = new Menu("Editar");
        Menu helpMenu = new Menu("Ayuda");

        MenuItem newFile = new MenuItem("Nuevo");
        MenuItem openFile = new MenuItem("Abrir");
        MenuItem saveFile = new MenuItem("Guardar");
        MenuItem exit = new MenuItem("Salir");

        MenuItem about = new MenuItem("About");


        fileMenu.getItems().addAll(newFile, openFile, saveFile, exit);
        helpMenu.getItems().addAll(about);

        //menuBar.getMenus().addAll(fileMenu, editMenu, helpMenu);
        menuBar.getMenus().addAll(fileMenu, helpMenu);

        newFile.setOnAction(e ->{
            Tab newTab = FileWindow.createTab(archivo, fileManager);
            tabPane.getTabs().add(newTab);
        });

        openFile.setOnAction(e ->{

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Abrir Archivo");

            archivo = fileChooser.showOpenDialog(primaryStage);
            if (archivo != null) {
                Tab newTab = FileWindow.createTab(archivo, fileManager);
                tabPane.getTabs().add(newTab);
            }

            //tabPane.getTabs().add(fileWindowManager.setNewFileWindo(archivo));
            //textArea.setText(fileManager.abrirArchivo(archivo));

            //initialContentLength = fileManager.abrirArchivo(archivo).length();
            
            //buttonLabel.setText(archivo.getName());

        });

        /*textArea.textProperty().addListener(new ChangeListener<String>(){
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue){
                if (archivo != null){
                    if(newValue.length() != initialContentLength){
                        buttonLabel.setText(archivo.getName() + " (Modificado)");
                        haveChanges = true;
                    } else {
                        buttonLabel.setText(archivo.getName());
                        haveChanges = false;
                    }
                }
            }
        });*/

        saveFile.setOnAction(e->{
            Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();

            if(selectedTab != null){
                FileWindow fileWindow = (FileWindow) selectedTab.getUserData();
                if (fileWindow != null) {
                    // Llama al método para guardar cambios en el archivo asociado
                    fileWindow.saveWindowChanges();
                }
            }
            
            /*if(archivo != null){
                //fileManager.guardarArchivo(archivo, textArea.getText());
                //initialContentLength = textArea.getText().length();
                buttonLabel.setText(archivo.getName());
                //haveChanges = false;
            }else{
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Guardar Archivo");
                archivo = fileChooser.showSaveDialog(primaryStage);
                 if(archivo != null){
                    //fileManager.guardarArchivo(archivo, textArea.getText());
                    //initialContentLength = textArea.getText().length();
                    buttonLabel.setText(archivo.getName());
                    //haveChanges = false;
                }
                //String contenido = textArea.getText();
                //System.out.println(contenido);
            }*/
            
        });

        exit.setOnAction(e ->{
            Platform.exit();
        });

        about.setOnAction(event->{

            About windowAbout = new About();
            windowAbout.getAboutInfo();
        });

        HBox topPane = new HBox();
        topPane.getChildren().add(menuBar);

        //root.setLeft(leftPane);
        root.setCenter(centerPane);
        root.setTop(topPane);
        root.setBottom(buttonPane);
        
        Scene scene = new Scene(root, 800, 600);

        scene.addEventHandler(KeyEvent.KEY_PRESSED, event->{

            if(keyCombCtrlN.match(event)){
                Tab newTab = FileWindow.createTab(archivo, fileManager);
                tabPane.getTabs().add(newTab);
            }
            if(keyCombCtrlO.match(event)){
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Abrir Archivo");

                archivo = fileChooser.showOpenDialog(primaryStage);
                if (archivo != null) {
                    Tab newTab = FileWindow.createTab(archivo, fileManager);
                    tabPane.getTabs().add(newTab);
                }
            }
            if(keyCombCtrlS.match(event)){
                Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();

                if(selectedTab != null){
                    FileWindow fileWindow = (FileWindow) selectedTab.getUserData();
                    if (fileWindow != null) {
                        // Llama al método para guardar cambios en el archivo asociado
                        fileWindow.saveWindowChanges();
                    }
                }
            }
            if(keyCombCtrlQ.match(event)){
                Platform.exit();
            }
            
        });

        scene.getStylesheets().add(getClass().getResource("themes/darkTheme.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
