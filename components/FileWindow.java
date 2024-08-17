package components;

import java.io.File;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.stage.FileChooser;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tab;
import javafx.stage.Stage;

import filemanager.FileManager;

public class FileWindow{

    File localFile = null;
    int initialContentLength = 0;
    FileManager fileManager = new FileManager();
    Tab tabFile = new Tab("");
    TextArea textArea = new TextArea();

    public Tab setNewFileWindo(File archivo){

        localFile = archivo;

        tabFile.setText(archivo.getName());
        tabFile.setContent(textArea);

        textArea.setText(fileManager.abrirArchivo(localFile));
        initialContentLength = fileManager.abrirArchivo(localFile).length();

        textArea.textProperty().addListener(new ChangeListener<String>(){
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue){
                if(localFile != null){
                    if(newValue.length() != initialContentLength){
                        tabFile.setText(archivo.getName() + " (Modificado)");
                    }
                    else{
                        tabFile.setText(archivo.getName());
                    }
                }
            }
        });

        return tabFile;
    }

    public void saveWindowChanges(){
        
        if(localFile != null){
            fileManager.guardarArchivo(localFile, textArea.getText());
            initialContentLength = textArea.getText().length();
        }
        else{
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Guardar Archivo");
            Stage tabStage = (Stage) tabFile.getTabPane().getScene().getWindow();
            localFile = fileChooser.showSaveDialog(tabStage);
            fileManager.guardarArchivo(localFile, textArea.getText());
            initialContentLength = textArea.getText().length();
        }
        
    }
}