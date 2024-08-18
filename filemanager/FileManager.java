package filemanager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
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
      return null;
    }
    
  }
}
