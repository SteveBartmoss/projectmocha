package components;

import java.io.File;
import java.io.IOException;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import filemanager.FileManager;
import datastructure.CodeLinkedList;
import datastructure.CodeNode;

public class CodeArea {

    //private FileManager fileManager = new FileManager();

    private String codigo;
    private CodeLinkedList codeLines = new CodeLinkedList();
    private TextFlow textFlow;
    private int currentLineIndex = 0; // Puntero a la línea actual
    private String currentLineText = ""; // Texto de la línea actual
    private CodeNode pointerCurrentRow;
    private int pointerCurrentCol;

    private Rectangle cursor;

    public CodeArea(String code, File archivo, FileManager fileManager) {
        this.textFlow = new TextFlow();
        //this.textFlow.getStyleClass().add("code-area-text");
        this.codigo = code;
        
        // Inicializar las líneas de código
        //initializeCodeLines(code);
        try{
            fileManager.leerArchivo(archivo, this.codeLines);
        } catch (IOException e){
            e.printStackTrace();
        }
        

        cursor = new Rectangle(1,15, Color.BLACK);
        textFlow.getChildren().add(cursor);

        this.pointerCurrentRow = codeLines.head;
        this.pointerCurrentCol = 0;
        
        // Renderizar el contenido inicial
        renderTextFlow();

        textFlow.setOnMouseClicked(event -> {
            textFlow.requestFocus();
        });

        textFlow.addEventFilter(KeyEvent.KEY_PRESSED, event ->{
            KeyCode codeKey = event.getCode();
            String character = event.getText();
            if(codeKey == KeyCode.BACK_SPACE){
                handleRemoveText();
            }
            else if(codeKey == KeyCode.DELETE){
                handleRemoveText();
            }
            else if(!character.isEmpty()){
                handleKeyPress(character);
            }else{
                moveCursor(codeKey);
                updateTextFlowAfterCursorMove();
            }

            event.consume();
            
        });
        
        // Event listener para actualizar el contenido incrementando
        /*textFlow.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            String character = event.getCharacter();
            handleKeyPress(character);
        });

        textFlow.addEventFilter(KeyEvent.KEY_PRESSED,event ->{
            KeyCode codeKey = event.getCode();
            moveCursor(codeKey);
            updateTextFlowAfterCursorMove();
        });*/
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
        
        pointerCurrentRow = codeLines.head; // Inicializar en la primera línea
    }

    // Renderiza el contenido inicial en el TextFlow
    private void renderTextFlow() {
        textFlow.getChildren().clear();
        CodeNode current = codeLines.head;
        while (current != null) {
            Text textNode = new Text(current.line + "\n");
            textNode.setFill(Color.WHITE);
            textFlow.getChildren().add(textNode);
            current = current.next;
        }
        updateCursorPosition();
    }

    private void moveCursor(KeyCode code) {

        textFlow.requestFocus();

        if (code == KeyCode.UP && pointerCurrentRow.prev != null) {
            pointerCurrentRow = pointerCurrentRow.prev;
            pointerCurrentCol = Math.min(pointerCurrentCol, pointerCurrentRow.line.length());
        } else if (code == KeyCode.DOWN && pointerCurrentRow.next != null) {
            pointerCurrentRow = pointerCurrentRow.next;
            pointerCurrentCol = Math.min(pointerCurrentCol, pointerCurrentRow.line.length());
        } else if (code == KeyCode.LEFT) {
            if (pointerCurrentCol > 0) {
                pointerCurrentCol--;
            } else if (pointerCurrentRow.prev != null) {
                pointerCurrentRow = pointerCurrentRow.prev;
                pointerCurrentCol = pointerCurrentRow.line.length();
            }
        } else if (code == KeyCode.RIGHT) {
            if (pointerCurrentCol < pointerCurrentRow.line.length()) {
                pointerCurrentCol++;
            } else if (pointerCurrentRow.next != null) {
                pointerCurrentRow = pointerCurrentRow.next;
                pointerCurrentCol = 0;
            }
        }

        //updateCursorPosition();
        System.out.println(pointerCurrentCol);
        System.out.println(pointerCurrentRow.line);
    }

    private void updateCursorPosition() {
        double x = 0;
        double y = 0;

        // Obtener la posición X e Y del cursor basado en la línea actual y la columna
        TextFlow tempFlow = new TextFlow(new Text(pointerCurrentRow.line.substring(0, pointerCurrentCol)));
        x = tempFlow.getBoundsInLocal().getWidth();
        y = textFlow.getBoundsInLocal().getHeight() - (pointerCurrentRow.line.length() - pointerCurrentCol) * 15;

        // Actualizar la posición del cursor visual
        cursor.setTranslateX(x);
        cursor.setTranslateY(y);
    }

    private void updateTextFlowAfterCursorMove(){
        String currentCode = pointerCurrentRow.line;
        char letra = currentCode.charAt(pointerCurrentCol);
        //pointerCurrentRow.line = currentCode + "<";
        updateCurrentLineInTextFlow();
        // Lógica para reflejar la posición del cursor en la interfaz (p. ej., selección de texto)
        // Aquí podrías agregar algún indicador visual en la posición actual del cursor si fuera necesario
        // Por ejemplo, podrías resaltar la línea actual o mover una barra vertical que represente el cursor
    }

    // Método para manejar las teclas presionadas y actualizar el contenido
    private void handleKeyPress(String character) {
        if (!character.equals("\n") && !character.equals("\r")) {
            pointerCurrentRow.line = pointerCurrentRow.line.substring(0, pointerCurrentCol)
                    + character
                    + pointerCurrentRow.line.substring(pointerCurrentCol);
            pointerCurrentCol++;
        } else {
            String newLine = pointerCurrentRow.line.substring(pointerCurrentCol);
            pointerCurrentRow.line = pointerCurrentRow.line.substring(0, pointerCurrentCol);
            codeLines.insertAfter(pointerCurrentRow, newLine);
            pointerCurrentRow = pointerCurrentRow.next;
            pointerCurrentCol = 0;
        }
        updateCurrentLineInTextFlow();
    }

    private void handleRemoveText(){
        if (pointerCurrentCol > 0) {
            // Elimina el carácter justo antes del cursor
            pointerCurrentRow.line = pointerCurrentRow.line.substring(0, pointerCurrentCol - 1) 
                                 + pointerCurrentRow.line.substring(pointerCurrentCol);
            pointerCurrentCol--;  // Mueve el cursor hacia atrás
        }
        updateCurrentLineInTextFlow();
    }

    // Método para actualizar la línea actual en el TextFlow
    private void updateCurrentLineInTextFlow() {
        int lineIndex = getLineIndex(pointerCurrentRow);
        Text currentTextNode = (Text) textFlow.getChildren().get(lineIndex);
        currentTextNode.setText(pointerCurrentRow.line);
    }

    // Método para obtener el índice de una línea en el TextFlow basado en el nodo
    private int getLineIndex(CodeNode node) {
        int index = 0;
        CodeNode current = codeLines.head;
        while (current != null) {
            if (current == node) {
                return index;
            }
            index++;
            current = current.next;
        }
        return -1;  // Si no se encuentra (lo que no debería suceder)
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
        try {
            String code = fileManager.abrirArchivo(archivo);
            //fileManager.leerArchivo(archivo,codeLines);
            return new CodeArea(code,archivo,fileManager);
        } catch (IOException e) {
            e.printStackTrace();
            // Manejar la excepción de manera apropiada, por ejemplo, devolviendo null o un objeto CodeArea vacío
            return null;
        }
    }

}
