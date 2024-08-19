import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import java.io.File;

public class CodeArea {

    private String codigo;
    private TextFlow textFlow;

    public CodeArea(String codigo) {
        this.codigo = codigo;
        this.textFlow = new TextFlow(new Text(codigo));
    }

    // Método para establecer nuevo código y actualizar la visualización
    public void setCodigo(String codigo) {
        this.codigo = codigo;
        // Actualizar la visualización
        this.textFlow.getChildren().clear();
        this.textFlow.getChildren().add(new Text(codigo));
    }

    // Método para obtener el código actual
    public String getCodigo() {
        return this.codigo;
    }

    // Método para obtener el TextFlow
    public TextFlow getTextFlow() {
        return this.textFlow;
    }

    // Método estático para crear un CodeArea a partir de un archivo
    public static CodeArea createFromFile(File archivo, FileManager fileManager) {
        if (archivo != null) {
            String codigo = fileManager.abrirArchivo(archivo);
            return new CodeArea(codigo);
        } else {
            return new CodeArea(""); // Manejar caso de archivo nulo
        }
    }
}
