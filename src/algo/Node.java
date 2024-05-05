package algo;



public class Node implements Comparable<Node> {

    public String word;
    public Node parent;
    public int cost;

    Node(String word, Node parent, int cost) {
        this.word = word;
        this.parent = parent;
        this.cost = cost;
    }
    public String getWord() {
        return word;
    }
    public Node getParent() {
        return parent;
    }
    public int getCost() {
        return cost;
    }

    public int compareTo(Node node2) {
        return Integer.compare(this.getCost(), node2.getCost());
    }


}
