package filemanager;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import datastructure.CodeLinkedList;

public class FileManager {

  public void guardarArchivo(File archivo, String contenido) throws IOException {

    try(BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo))){
      escritor.write(contenido);
    } catch(IOException ex){
      System.out.println("Error al guardar el archivo '" + archivo.getName() + "': " + ex.getMessage());
      showErrorDialog("No se pudo guardar el archivo","Ocurrió un error al guardar el archivo: " + archivo.getName(), ex.getMessage());
    }

  }

  public String abrirArchivo(File archivo) throws IOException{

    if(archivo == null || !archivo.exists() || !archivo.canRead()){
      throw new IOException("El archivo no existe o no se puede leer: "+ archivo.getName());
    }

    try(BufferedReader br = new BufferedReader(new FileReader(archivo))){
      StringBuilder sb = new StringBuilder();
      String line;
      while((line = br.readLine())!=null){
        sb.append(line).append("\n");
      }
      return sb.toString();
    }
    catch(IOException ex){
      System.out.println("Error al leer archivo: " + ex.getMessage());
      showErrorDialog("No se pudo abrir el archivo","Ocurrió un error al abrir el archivo: " + archivo.getName(), ex.getMessage());
      return null;
    }

  }

  public void leerArchivo(File archivo, CodeLinkedList codeLines) throws IOException {
    if(archivo == null || !archivo.exist() || !archivo.canRead()){
      throw new IOException("El archivo no existe o no se puede leer: "+ archivo.getName());
    }

    try(BufferedReader br = new BufferedReader(new FileReader(archivo))){
      StringBuilder sb = new StringBuilder();
      String line;
      while((line = br.readLine())!=null){
        codeLines.addLine(line);
      }
      return;
    }
    catch(IOException ex){
      System.out.printl("Error al leer archivo: "+ ex.getMessage());
      showErrorDialog("No se pudo abrir el archivo","Ocurrio un error al abrir el archivo: " + archivo.getName(), ex.getMessage());
      return;
    }
  }

  public CodeLinkedList  abrirArchivoLinked(File archivo) throws IOException{
    

    if(archivo == null || !archivo.exists() || !archivo.canRead()){
      throw new IOException("El archivo no existe o no se puede leer: "+ archivo.getName());
    }

    try(BufferedReader br = new BufferedReader(new FileReader(archivo))){
      CodeLinkedList codeLines = new CodeLinkedList();
      String line;
      while((line = br.readLine())!=null){
        codeLines.addLine(line);
      }

      return codeLines;
    }
    catch(IOException ex){
      System.out.println("Error al leer archivo: " + ex.getMessage());
      showErrorDialog("No se pudo abrir el archivo","Ocurrió un error al abrir el archivo: " + archivo.getName(), ex.getMessage());
      return null;
    }

  }

  private void showErrorDialog(String title, String name, String message){
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle(title);
    alert.setHeaderText(name);
    alert.setContentText(message);
    alert.showAndWait();
  }

}
