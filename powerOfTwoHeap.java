import java.util.ArrayList;
import java.util.List;

public class powerOfTwoHeap {
    int curr_parent;
    List<Node> nodes;
    public powerOfTwoHeap(){
        curr_parent = 0;
        nodes = new ArrayList<>();
    }
    public void add(int val) {
        if (nodes.size() == 0) {
            nodes.add(new Node(val, 0, null));
            return;
        }
        Node curr_node = nodes.get(curr_parent);
        Node n = new Node(val, curr_node.height + 1, curr_node);
        curr_node.children.add(n);
        nodes.add(n);
        while (curr_node != null && n.value < curr_node.value) {
            int temp = curr_node.value;
            curr_node.value = n.value;
            n.value = temp;
            n = curr_node;
            curr_node = curr_node.parent;

        }
        update_curr_parent();
    }
    public void deleteMin() {
        if (nodes.size() == 0) {
            return;
        }
        Node n = nodes.get(0); // deleted minimum node
        Node bottom_node = nodes.get(nodes.size() - 1);
        bottom_node.parent.children.remove(bottom_node);
        nodes.remove(bottom_node);
        bottom_node.parent = null;
        n.value = bottom_node.value;
        update_curr_parent();
        // replacing values of the minimum node with the bottom-most node and then deleting it

        while (n.children.size() > 0 && n.value > getMaxChild(n).value) {
            Node child = getMinChild(n, n.value);
            if (child != null) {
                int temp = n.value;
                n.value = child.value;
                child.value = temp;
                n = child;
            }
        }

    }
    private void update_curr_parent() {
        Node n = nodes.get(curr_parent);
        if (n.children.size() == (int) Math.pow(2, n.height + 1)) {
            curr_parent++;
        }
        else if (n.children.size() == 0 && nodes.get(curr_parent - 1).children.size() < (int) Math.pow(2, n.height)) {
            curr_parent--;
        }
    }
    private Node getMaxChild(Node n) {
        Node max = null;
        for (Node child: n.children) {
            if (max == null || child.value > max.value) {
                max = child;
            }
        }
        return max;
    }
    private Node getMinChild(Node n, int smallest_val) {
        Node min = null;
        for (Node child: n.children) {
            if (min == null && child.value < smallest_val || child.value < min.value) {
                min = child;
            }
        }
        return min;
    }
    public void print(Node n, String indentation) {
        System.out.println(indentation + n.value);
        for (Node child: n.children) {
            print(child, indentation + " ");
        }
    }
    public static void main(String[] args) {
        powerOfTwoHeap POTH = new powerOfTwoHeap();
        for (int i = 1; i < 15; i++) {
            POTH.add(i);
        }
        for (int i = 0; i < 12; i++) {
            POTH.deleteMin();
        }
        POTH.print(POTH.nodes.get(0),"");
    }

}