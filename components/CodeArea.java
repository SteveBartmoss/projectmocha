import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class CodeArea {

    private String codigo;
    private CodeLinkedList codeLines = new CodeLinkedList();
    private TextFlow textFlow;
    private int currentLineIndex = 0; // Puntero a la línea actual
    private String currentLineText = ""; // Texto de la línea actual

    public CodeArea(String code) {
        this.textFlow = new TextFlow();
        this.codigo = code;
        
        // Inicializar las líneas de código
        initializeCodeLines(code);
        
        // Renderizar el contenido inicial
        renderTextFlow();
        
        // Event listener para actualizar el contenido incrementando
        textFlow.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            String character = event.getCharacter();
            handleKeyPress(character);
        });
    }

    // Inicializa las líneas de código en la lista y el TextFlow
    private void initializeCodeLines(String code) {
        String swap = "";
        for (int i = 0; i < code.length(); i++) {
            char letra = code.charAt(i);
            if (letra != '\n' && letra != '\r') {
                swap += letra;
            } else {
                codeLines.addLine(swap);
                swap = ""; // Reiniciar swap para la siguiente línea
            }
        }

        if (!swap.isEmpty()) {
            codeLines.addLine(swap); // Manejar la última línea si no hay carácter de nueva línea al final
        }
    }

    // Renderiza el contenido inicial en el TextFlow
    private void renderTextFlow() {
        CodeNode current = codeLines.head;
        while (current != null) {
            textFlow.getChildren().add(new Text(current.line + "\n"));
            current = current.next;
        }
    }

    // Manejar las teclas presionadas para actualizar el contenido
    private void handleKeyPress(String character) {
        if (!character.equals("\n") && !character.equals("\r")) {
            currentLineText += character;
            updateCurrentLine();
        } else {
            codeLines.addLine(currentLineText);
            textFlow.getChildren().add(new Text(currentLineText + "\n"));
            currentLineText = ""; // Reiniciar el texto de la línea para la siguiente línea
            currentLineIndex++;
        }
    }

    // Actualiza la línea actual en el TextFlow
    private void updateCurrentLine() {
        if (currentLineIndex < textFlow.getChildren().size()) {
            Text currentTextNode = (Text) textFlow.getChildren().get(currentLineIndex);
            currentTextNode.setText(currentLineText);
        } else {
            textFlow.getChildren().add(new Text(currentLineText));
        }
    }

    public void setCodigo(String code) {
        this.codigo = code;
        this.textFlow.getChildren().clear();
        codeLines.clear();  // Limpiar la lista enlazada
        currentLineIndex = 0;  // Reiniciar el puntero de línea
        currentLineText = "";  // Reiniciar el texto de la línea
        initializeCodeLines(code);
        renderTextFlow();
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
