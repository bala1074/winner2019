package SEGMENT_TREE_LAZY;

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

	public static void update(int arr[], int s, int e, int tree[], int lazy[], int treeNode, int ul, int ur, int val) {
		if (s > e)
			return;

		if (lazy[treeNode] != 0) {
			tree[treeNode] += lazy[treeNode];
			if (s != e) {
				lazy[2 * treeNode] += lazy[treeNode];
				lazy[2 * treeNode + 1] += lazy[treeNode];
			}
			lazy[treeNode] = 0;
		}

		// no overlap
		if (s > ur || e < ul)
			return;

		// complete overlap
		if (s >= ul && e <= ur) {
			tree[treeNode] += val;
			if (s != e) {
				lazy[2 * treeNode] += val;
				lazy[2 * treeNode + 1] += val;
			}
			return;
		}

		// partial
		int mid = (s + e) / 2;
		update(arr, mid + 1, e, tree, lazy, 2 * treeNode + 1, ul, ur, val);
		update(arr, s, mid, tree, lazy, 2 * treeNode, ul, ur, val);
		tree[treeNode] = Math.min(tree[2 * treeNode], tree[2 * treeNode + 1]);
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
