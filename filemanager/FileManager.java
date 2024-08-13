package filemanager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {

  public void guardarArchivo(File archivo, String contenido){
    try(BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo))){
      escritor.write(contenido);
    } catch(IOException ex){
      System.out.println("Error al guardar archivo: " + ex.getMessage());
    }
  }
  
}
