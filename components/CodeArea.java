import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class CodeArea {

    private String codigo;
    private CodeLinkedList codeLines = new CodeLinkedList();
    private TextFlow textFlow;

    public CodeArea(String code) {
        this.textFlow = new TextFlow();
        String swap = "";
        this.codigo = code;
        
        for (int i = 0; i < code.length(); i++) {
            char letra = code.charAt(i);
            if (letra != '\n' && letra != '\r') {
                swap += letra;
            } else {
                codeLines.addLine(swap);
                swap = ""; // Reset swap for the next line
            }
        }

        if (!swap.isEmpty()) {
            codeLines.addLine(swap); // Handle last line if no newline character at the end
        }

        CodeNode current = codeLines.head;
        while (current != null) {
            textFlow.getChildren().add(new Text(current.line + "\n"));
            current = current.next;
        }

        // Event listener for key presses
        textFlow.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            String character = event.getCharacter();
            if (!character.equals("\n") && !character.equals("\r")) {
                swap += character;
            } else {
                swap += character;
                codeLines.addLine(swap);
                textFlow.getChildren().add(new Text(swap));
                swap = ""; // Reset swap for the next line
            }
        });
    }

    public void setCodigo(String code) {
        this.codigo = code;
        this.textFlow.getChildren().clear();
        // Reinitialize the text flow with the new code
        // (This part can be optimized if necessary)
        String swap = "";
        for (int i = 0; i < code.length(); i++) {
            char letra = code.charAt(i);
            if (letra != '\n' && letra != '\r') {
                swap += letra;
            } else {
                textFlow.getChildren().add(new Text(swap + "\n"));
                swap = "";
            }
        }
        if (!swap.isEmpty()) {
            textFlow.getChildren().add(new Text(swap));
        }
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
