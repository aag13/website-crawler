
import java.util.LinkedList;

public class Node {

    String name = "";
    LinkedList children;
    int level;
    public Node(String s,int l) {
        name = s;
        children = new LinkedList();
        level = l;
    }
}
