public class NodeDemo{
	public static void main(String[] args){
		Node first = new Node();
		first.name = "FirstNode";
		Node last = new Node();
		last.name = "LastNode";
		first.next = last;
		last.next = null; // Indicate last is the last node.
		
		Node temp = first;
		while(temp != null){
			System.out.println(temp.name);
			temp = temp.next;
		}
	}
}

class Node{
	String name;
	Node next;
}