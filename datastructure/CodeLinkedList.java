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

    public void insertAfter(CodeNode node, String line){
        lineCount++;
        CodeNode newNode = new CodeNode(line,lineCount);
        if(node == tail){
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }else{
            newNode.next = node.next;
            node.next.prev = newNode;
            node.next = newNode;
            newNode.prev = node;
        }
    }

    public void clear() {
        CodeNode current = head;
        
        // Desvincular todos los nodos
        while (current != null) {
            CodeNode nextNode = current.next;
            
            // Rompe las conexiones de los nodos
            current.next = null;
            current.prev = null;
            
            // Avanza al siguiente nodo
            current = nextNode;
        }
        
        // Restablecer la lista
        head = null;
        tail = null;
        lineCount = 0;
    }

    public int getLineCount() {
        return lineCount;
    }
}
