package datastructure;

public class CodeLinkedList {
    public CodeNode head;
    public CodeNode tail;
    public int lineCount = 0; // Contador de líneas

    public CodeLinkedList() {
        this.head = null;
        this.tail = null;
    }

    public void addLine(String line) {
        lineCount++;
        CodeNode newNode = new CodeNode(line, lineCount);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    public int getLineCount() {
        return lineCount;
    }
}
