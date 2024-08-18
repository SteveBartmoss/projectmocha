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

    private File archivo;
    private TextArea textArea;
    private Tab tabFile;
    private int initialContentLength = 0;
    private FileManager fileManager = new FileManager();

    public FileWindow(File archivo, TextArea textArea, Tab tabFile) {
        this.archivo = archivo;
        this.textArea = textArea;
        this.tabFile = tabFile;
        this.initialContentLength = textArea.getText().length();

        textArea.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (archivo != null) {
                    if (newValue.length() != initialContentLength) {
                        tabFile.setText(archivo.getName() + " (Modificado)");
                    } else {
                        tabFile.setText(archivo.getName());
                    }
                }
                else{
                    if (newValue.length() != initialContentLength) {
                        tabFile.setText("newFile (Modificado)");
                    } else {
                        tabFile.setText("newFile");
                    }
                }
            }
        });
    }

    public void saveWindowChanges() {
        if (archivo != null) {
            fileManager.guardarArchivo(archivo, textArea.getText());
            initialContentLength = textArea.getText().length();
            tabFile.setText(archivo.getName());
        }
        else{
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Guardar Archivo");
            Stage tabStage = (Stage) tabFile.getTabPane().getScene().getWindow();
            archivo = fileChooser.showSaveDialog(tabStage);
            fileManager.guardarArchivo(archivo, textArea.getText());
            initialContentLength = textArea.getText().length();
            tabFile.setText(archivo.getName());
        }
    }

    public static Tab createTab(File archivo, FileManager fileManager) {

        Tab tabFile;

        if(archivo != null){
            tabFile = new Tab(archivo.getName());
        }
        else{
            tabFile = new Tab("newFile");
        }
        TextArea textArea = new TextArea();
        if(archivo != null){
            textArea.setText(fileManager.abrirArchivo(archivo));
        }
        FileWindow fileWindow = new FileWindow(archivo, textArea, tabFile);
        tabFile.setContent(textArea);
        tabFile.setUserData(fileWindow);
        return tabFile;
    }

}