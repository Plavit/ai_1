public class Node<S, A> implements Comparable<Node<S,A>>{
    S state = null;
    private double cost = Double.POSITIVE_INFINITY;
    private Node<S,A> parent = null;
    private A action = null;

    public Node() {
    }

    Node(S state) {
        this.state = state;
    }


    @Override
    public int hashCode(){
        return state.hashCode();
    }

    @Override
    public int compareTo(Node<S,A> node) {
        return ((Double) getCost()).compareTo(node.getCost());
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Node<S, A> getParent() {
        return parent;
    }

    public void setParent(Node<S, A> parent) {
        this.parent = parent;
    }

    public A getAction() {
        return action;
    }

    public void setAction(A action) {
        this.action = action;
    }
}
