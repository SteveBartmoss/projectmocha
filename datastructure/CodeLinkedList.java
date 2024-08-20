
public class CodeLinkedList{

    CodeNode head;
    CodeNode tail;

    public CodLinkedList(){
        this.head = null;
        this.tail = null;
    }

    public void addLine(String line){
        CodeNode newNode = new CodeNode(line);
        if(head == null){
            head= newNode;
            tail= newNode;
        }
        else{
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }
}