import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class CodeArea {

    private String codigo;
    private Text textCode;
    private TextFlow textFlow;

    public CodeArea(String code) {
        this.codigo = code;
        this.textCode = new Text(code);
        this.textFlow = new TextFlow(textCode);

        // Event listener for key presses
        textFlow.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            String character = event.getCharacter();
            // Update the code string
            codigo += character;
            // Update the text displayed
            textCode.setText(codigo);
        });
    }

    public void setCodigo(String code) {
        this.codigo = code;
        this.textCode.setText(code);
    }

    public String getCodigo() {
        return this.codigo;
    }

    public TextFlow getTextFlow() {
        return this.textFlow;
    }

    public static CodeArea createCodeArea(File archivo, FileManager fileManager) {
        String code = fileManager.abrirArchivo(archivo);
        return new CodeArea(code);
    }
}
