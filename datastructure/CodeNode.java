package datastructure;

public class CodeNode {
    String line;
    int lineNumber; // Número de línea
    CodeNode next;
    CodeNode prev;

    public CodeNode(String line, int lineNumber) {
        this.line = line;
        this.lineNumber = lineNumber;
        this.next = null;
        this.prev = null;
    }
}
