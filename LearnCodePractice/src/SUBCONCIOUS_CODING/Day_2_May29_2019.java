package SUBCONCIOUS_CODING;

//45 Mins challenge

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;

public class Day_2_May29_2019 {
	private static void mian() throws Exception {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

		int t = getInt(br.readLine());
		while (t-- > 0) {

			pw.println();
		}
		pw.flush();
		pw.close();
	}

	public static int find(int[] arr, int val) {
		if (arr[val] == val)
			return val;
		return arr[val] = find(arr, arr[val]);
	}

	public static boolean union(int[] arr, int u, int v) {
		int s1 = find(arr, u);
		int s2 = find(arr, v);
		if (s1 != s2) {
			arr[s1] = s2;
			find(arr, u);
			return true;
		} else
			return false;
	}

	public static LinkedList<Edge> kruskals(Edge[] edges, int n) {
		Arrays.sort(edges, new Comparator<Edge>() {

			@Override
			public int compare(Edge o1, Edge o2) {
				return o1.w < o2.w ? -1 : 1;
			}
		});

		LinkedList<Edge> mst = new LinkedList<>();
		int arr[] = new int[n + 1];
		for (int i = 0; i <= n; i++)
			arr[i] = i;
		for (int i = 0; i < edges.length; i++) {
			Edge current = edges[i];
			if (union(arr, current.s, current.d)) {
				mst.add(current);
			}
		}
		return mst;

	}

	public static void updateFT(int ind, int val, int[] BIT, int n) {
		for (; ind <= n; ind += ind & -ind)
			BIT[ind] += val;
	}

	public static int queryFT(int ind, int BIT[]) {
		int sum = 0;
		for (; ind > 0; ind -= ind & -ind)
			sum += BIT[ind];
		return sum;
	}

	public static void buildST(int arr[], int s, int e, int tree[], int treeNode) {
		if (s == e) {
			tree[treeNode] = arr[s];
			return;
		}
		int m = (s + e) / 2;
		buildST(arr, s, m, tree, 2 * treeNode);
		buildST(arr, m + 1, e, tree, 2 * treeNode + 1);
		tree[treeNode] = tree[treeNode * 2] + tree[treeNode * 2 + 1];
	}

	public static void updateST(int arr[], int s, int e, int tree[], int treeNode, int ind, int val) {
		if (s == e) {
			arr[s] = val;
			tree[treeNode] = arr[s];
			return;
		}
		int m = (s + e) / 2;
		if (ind > m)
			buildST(arr, m + 1, e, tree, 2 * treeNode + 1);
		else
			buildST(arr, s, m, tree, 2 * treeNode);
		tree[treeNode] = tree[treeNode * 2] + tree[treeNode * 2 + 1];
	}

	public static int queryST(int tree[], int treeNode, int s, int e, int l, int r) {
		if (e < l || r < s)
			return 0;
		if (s >= l && e <= r)
			return tree[treeNode];
		int m = (s + e) / 2;
		int left = queryST(tree, 2 * treeNode, s, m, l, r);
		int right = queryST(tree, 2 * treeNode + 1, m + 1, e, l, r);
		return left + right;
	}

	public static void updateLazyST(int arr[], int tree[], int lazy[], int treeNode, int s, int e, int l, int r,
			int val) {
		if (e < l || r < s)
			return;
		if (lazy[treeNode] != 0) {
			tree[treeNode] += lazy[treeNode];
			if (s != e) {
				lazy[2 * treeNode] += lazy[treeNode];
				lazy[2 * treeNode + 1] += lazy[treeNode];
			}
			lazy[treeNode] = 0;
		}
		if (s >= l && e <= r) {
			tree[treeNode] += val;
			if (s != e) {
				lazy[2 * treeNode] += val;
				lazy[2 * treeNode + 1] += val;
			}
			return;
		}
		int m = (s + e) / 2;
		updateLazyST(arr, tree, lazy, treeNode, s, m, l, r, val);
		updateLazyST(arr, tree, lazy, treeNode, m + 1, e, l, r, val);
		tree[treeNode] = tree[treeNode * 2] + tree[2 * treeNode + 1];
	}

	public static int[][] fw(int arr[][], int n) {
		for (int k = 0; k < n; k++)
			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++)
					arr[i][j] = arr[i][j] > arr[i][k] + arr[k][j] ? arr[i][k] + arr[k][j] : arr[i][j];
		return arr;
	}

	public static int bf(Edge edges[], int n, int src, int dest) {
		int[] res = new int[n + 1];
		Arrays.fill(res, 10000);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < edges.length; j++) {
				Edge current = edges[j];
				if (res[current.s] + current.w < res[current.d])
					res[current.d] = res[current.s] + current.w;
			}
		}
		return res[dest];
	}

	public static LinkedList<Integer> seive(int n) {
		LinkedList<Integer> primes = new LinkedList<>();
		boolean isP[] = new boolean[n + 1];
		Arrays.fill(isP, true);
		isP[0] = isP[1] = false;
		for (int i = 2; i <= n; i++) {
			if (isP[i]) {
				primes.add(i);
				for (int j = i * i; j <= n; j += i)
					isP[j] = false;
			}
		}
		return primes;
	}

	public static int factCount(int n) {
		LinkedList<Integer> primes = seive(n);
		int c = 0, pi = 0, ans = 1;
		while (n > 1) {
			int sc = 0;
			while (n % primes.get(pi) == 0) {
				n = n / primes.get(pi);
				sc++;
			}
			ans *= (sc + 1);
		}
		return ans;
	}

	public static void dfs(LinkedList<Integer>[] graph, int src, HashSet<Integer> isv) {
		isv.add(src);
		for (int i = 0; i < graph[src].size(); i++) {
			if (!isv.contains(graph[src].get(i))) {
				dfs(graph, graph[src].get(i), isv);
			}
		}
	}

	public static void bfs(LinkedList<Integer>[] graph, int src, HashSet<Integer> isv) {
		LinkedList<Integer> q = new LinkedList<>();
		q.add(src);
		while (!q.isEmpty()) {
			src = q.poll();
			isv.add(src);
			for (int i = 0; i < graph[src].size(); i++) {
				if (!isv.contains(graph[src].get(i))) {
					q.add(graph[src].get(i));
				}
			}
		}
	}

	public static boolean connected(LinkedList<Integer>[] graph, int src, int dest) {
		HashSet<Integer> isv = new HashSet<>();
		dfs(graph, src, isv);
		if (isv.contains(dest))
			return true;
		return false;
	}

	public static boolean sconnected(LinkedList<Integer>[] graph, LinkedList<Integer>[] tgraph, int n) {

		Stack<Integer> stk = new Stack<>();
		HashSet<Integer> isv = new HashSet<>();
		for (int i = 0; i < n; i++) {
			if (!isv.contains(i))
				topologicalSort(tgraph, i, isv, stk);// store as per finsh time in stack
		}

		isv = new HashSet<>();
		while (!stk.isEmpty()) {
			int pop = stk.pop();
			if (!isv.contains(pop)) {
				dfs(tgraph, pop, isv);
			}
			if (isv.size() == n) {
				// visited all vertices
				break;
			}
		}
		return false;
	}

	public static Node lca(Node r, int s, int d) {
		if (r == null)
			return null;
		if (r.w == s || r.r.w == d)
			return r;
		Node left = lca(r.l, s, d);
		Node right = lca(r.r, s, d);
		if (left != null && right != null)
			return r;
		return left != null ? left : right;
	}

	public static int gcd(int a, int b) {
		return b == 0 ? a : gcd(b, a % b);
	}

	public static int exp(int base, int exp) {
		if (exp == 0)
			return 1;
		int ans = exp(base, exp / 2);
		return (exp % 2 == 0) ? ans * ans : ans * ans * base;
	}

	public static void pq() {
		PriorityQueue<Integer> heap = new PriorityQueue<Integer>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 < o2 ? -1 : 1;
			}
		});
	}

	public static void topologicalSort(LinkedList<Integer>[] graph, int src, HashSet<Integer> isv, Stack<Integer> stk) {
		isv.add(src);
		for (int i = 0; i < graph[src].size(); i++) {
			if (!isv.contains(graph[src].get(i))) {
				dfs(graph, graph[src].get(i), isv);
			}
		}
		stk.add(src);
	}

	public static int getInt(String str) {
		return Integer.parseInt(str.trim());
	}

	public static long getLong(String str) {
		return Long.parseLong(str.trim());
	}

	public static Double getDouble(String str) {
		return Double.parseDouble(str.trim());
	}
}

class Node {
	Node l, r;
	int w;
}