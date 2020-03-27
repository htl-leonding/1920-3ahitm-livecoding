package at.htl.iterabledemo;

public class Node {

    private Node next;
    private String data;

    public Node(Node next, String data) {
        this.setNext(next);
        this.setData(data);
    }


    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Node{" +
                "next=" + next +
                ", data='" + data + '\'' +
                '}';
    }
}
