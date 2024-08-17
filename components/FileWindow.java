package components;

import java.io.File;

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

        return tabFile;
    }
}