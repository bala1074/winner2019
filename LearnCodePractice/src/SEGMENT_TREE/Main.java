package SEGMENT_TREE;

public class Main {
	public static void main(String[] args) {

	}

	public static void build(int arr[], int s, int e, int tree[], int treeNode) {
		if (s > e)
			return;
		if (s == e) {
			tree[treeNode] = arr[s];
			return;
		}
		int mid = (s + e) / 2;
		build(arr, s, mid, tree, 2 * treeNode);
		build(arr, mid + 1, e, tree, 2 * treeNode + 1);
		tree[treeNode] = tree[2 * treeNode] + tree[2 * treeNode + 1];
	}

	public static void update(int arr[], int s, int e, int tree[], int treeNode, int uval, int uinx) {
		if (s > e)
			return;
		if (s == e) {
			arr[s] = uval;
			tree[treeNode] = arr[s];
			return;
		}
		int mid = (s + e) / 2;
		if (uinx > mid)
			update(arr, mid + 1, e, tree, 2 * treeNode + 1, uval, uinx);
		else
			update(arr, s, mid, tree, 2 * treeNode, uval, uinx);
		tree[treeNode] = tree[2 * treeNode] + tree[2 * treeNode + 1];
	}

	public static int query(int tree[], int s, int e, int treeNode, int l, int r) {
		if (s > r || e < l)
			return 0;
		if (s >= l && e <= r)
			return tree[treeNode];
		int mid = (s + e) / 2;
		int ans1 = query(tree, s, mid, 2 * treeNode, l, r);
		int ans2 = query(tree, mid + 1, e, 2 * treeNode + 1, l, r);
		return ans1 + ans2;
	}
}
