package components;

import java.io.File;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tab;

import filemanager.FileManager;

public class FileWindow{

    int initialContentLength = 0;
    FileManager fileManager = new FileManager();


    public Tab setNewFileWindo(File archivo){

        Tab tabFile = new Tab(archivo.getName());
        TextArea textArea = new TextArea();
        tabFile.setContent(textArea);

        textArea.setText(fileManager.abrirArchivo(archivo));
        initialContentLength = fileManager.abrirArchivo(archivo).length();

        textArea.textProperty().addListener(new ChangeListener<String>(){
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue){
                if(archivo != null){
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
}