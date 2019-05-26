package TREES;

public class LCA {

	public static void main(String[] args) {

		Node r = new Node(1);
		r.l = new Node(2);
		r.r = new Node(3);
		r.r.r = new Node(9);
		r.r.l = new Node(6);
		r.r.l.l = new Node(7);
		r.l.l = new Node(4);
		r.l.r = new Node(5);
		r.l.r.l = new Node(8);
		System.out.println(LCA(r, 9, 7).val);
	}

	public static Node LCA(Node node, int a, int b) {
		if (node == null)
			return null;
		if (node.val == a || node.val == b)
			return node;
		Node l = LCA(node.l, a, b);
		Node r = LCA(node.r, a, b);
		if (l != null && r != null)
			return node;
		return l == null ? r : l;
	}
}

class Node {
	Node l, r;
	int val;

	public Node(int v) {
		this.val = v;
		this.r = this.l = null;
	}
}