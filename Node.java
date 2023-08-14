import java.util.ArrayList;
import java.util.List;

public class Node {
    List<Node> children;
    Node parent;
    int value;
    int height;
    public Node(int value, int height, Node parent) {
        children = new ArrayList<>();
        this.value = value;
        this.height = height;
        this.parent = parent;

    }
}
