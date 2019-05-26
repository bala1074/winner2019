package SEGMENT_TREE;

import java.util.Arrays;

public class test {

	public static void main(String[] args) {
		int arr[] = new int[] { 1, 2, 3, 4, 5 };
		int tree[] = new int[48];
		int lazy[] = new int[48];
		build(arr, 0, 4, tree, 1);
		// System.out.println(Arrays.toString(tree));
		// update(arr, 0, 4, tree, 1, 2, 10);
		// System.out.println(Arrays.toString(tree));
		// System.out.println(query(tree, 0, 4, 1, 2, 3));
		updateLazy(tree, lazy, 0, 4, 1, 0, 4, 1);
		System.out.println(Arrays.toString(tree));
		System.out.println(Arrays.toString(lazy));
	}

	public static void build(int[] arr, int s, int e, int tree[], int treeNode) {
		if (s == e) {
			tree[treeNode] = arr[s];
			return;
		}
		int m = (s + e) / 2;
		build(arr, s, m, tree, 2 * treeNode);
		build(arr, m + 1, e, tree, 2 * treeNode + 1);
		tree[treeNode] = tree[2 * treeNode] + tree[2 * treeNode + 1];
	}

	public static void update(int[] arr, int s, int e, int tree[], int treeNode, int ind, int val) {
		if (s == e) {
			arr[s] = val;
			tree[treeNode] = val;
			return;
		}
		int m = (s + e) / 2;
		if (m < ind)
			update(arr, m + 1, e, tree, 2 * treeNode + 1, ind, val);
		else
			update(arr, s, m, tree, 2 * treeNode, ind, val);
		tree[treeNode] = tree[2 * treeNode] + tree[2 * treeNode + 1];
	}

	public static int query(int[] tree, int s, int e, int treeNode, int l, int r) {
		if (e < l || r < s)
			return 0;
		if (s >= l && e <= r)
			return tree[treeNode];
		int m = (s + e) / 2;
		int lft = query(tree, s, m, 2 * treeNode, l, r);
		int rgt = query(tree, m + 1, e, 2 * treeNode + 1, l, r);
		return lft + rgt;
	}

	public static void updateLazy(int[] tree, int[] lazy, int s, int e, int treeNode, int l, int r, int val) {
		if (s > e)
			return;
		System.out.println(treeNode + " " + s + " " + e);
		if (lazy[treeNode] != 0) {
			tree[treeNode] += lazy[treeNode];
			if (s != e) {
				lazy[2 * treeNode] += lazy[treeNode];
				lazy[2 * treeNode + 1] += lazy[treeNode];
			}
			lazy[treeNode] = 0;
		}

		if (e < l || r < s)
			return;
		if (s >= l && e <= r) {
			tree[treeNode] += val;
			if (s != e) {
				lazy[2 * treeNode] += val;
				lazy[2 * treeNode + 1] += val;
			}
			return;
		}
		int m = (s + e) / 2;
		updateLazy(tree, lazy, s, m, 2 * treeNode, l, r, val);
		updateLazy(tree, lazy, m + 1, e, 2 * treeNode + 1, l, r, val);
		tree[treeNode] = tree[2 * treeNode] + tree[treeNode * 2 + 1];
	}
}
