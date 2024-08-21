package datastructure;

public class CodeNode {
    public String line;
    public int lineNumber; // Número de línea
    public CodeNode next;
    public CodeNode prev;

    public CodeNode(String line, int lineNumber) {
        this.line = line;
        this.lineNumber = lineNumber;
        this.next = null;
        this.prev = null;
    }
}
