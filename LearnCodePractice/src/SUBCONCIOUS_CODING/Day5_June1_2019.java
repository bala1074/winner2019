package SUBCONCIOUS_CODING;

//38Mins to 36.10Mins

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

public class Day5_June1_2019 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		int t = getInt(br.readLine());
		while (t-- > 0) {
			pw.println();
		}
		pw.flush();
		pw.close();
	}

	public static int getInt(String str) {
		return Integer.parseInt(str.trim());
	}

	public static long getLong(String str) {
		return Long.parseLong(str.trim());
	}

	public static double getDouble(String str) {
		return Double.parseDouble(str.trim());
	}

	public static boolean union(int arr[], int u, int v) {
		int s1 = find(arr, u);
		int s2 = find(arr, v);
		if (s1 != s2) {
			arr[s1] = s2;
			find(arr, u);
			return true;
		}
		return false;
	}

	public static int find(int arr[], int val) {
		if (arr[val] == val)
			return val;
		return arr[val] = find(arr, arr[val]);
	}

	public static LinkedList<Edge> kruskals(Edge[] edges, int n) {
		Arrays.sort(edges, new Comparator<Edge>() {
			@Override
			public int compare(Edge o1, Edge o2) {
				return o1.w < o2.w ? -1 : 1;
			}
		});

		int arr[] = new int[n + 1];
		for (int i = 0; i <= n; i++)
			arr[i] = i;
		LinkedList<Edge> mst = new LinkedList<>();
		for (int i = 0; i < edges.length; i++) {
			if (union(arr, edges[i].s, edges[i].d))
				mst.add(edges[i]);
		}
		return mst;
	}

	public static void updateFT(int ind, int val, int[] BIT, int n) {
		for (; ind <= n; ind += ind & -ind)
			BIT[ind] += val;
	}

	public static int queryFT(int ind, int[] BIT) {
		int sum = 0;
		for (; ind > 0; ind -= ind & -ind)
			sum += BIT[ind];
		return sum;
	}

	public static void build(int arr[], int tree[], int s, int e, int treeNode) {
		if (s == e) {
			tree[treeNode] = arr[s];
			return;
		}
		int m = (s + e) / 2;
		build(arr, tree, s, m, treeNode * 2);
		build(arr, tree, m + 1, e, treeNode * 2 + 1);
		tree[treeNode] = tree[treeNode * 2] + tree[treeNode * 2 + 1];
	}

	public static void update(int arr[], int tree[], int s, int e, int treeNode, int ind, int val) {
		if (s == e) {
			tree[treeNode] = arr[s];
			return;
		}
		int m = (s + e) / 2;
		if (ind > m)
			update(arr, tree, m + 1, e, treeNode * 2 + 1, ind, val);
		else
			update(arr, tree, s, m, treeNode * 2, ind, val);
		tree[treeNode] = tree[treeNode * 2] + tree[treeNode * 2 + 1];
	}

	public static int query(int tree[], int treeNode, int s, int e, int l, int r) {
		if (e < l || r < s)
			return 0;
		if (s >= l && e <= r) {
			return tree[treeNode];
		}
		int m = (s + e) / 2;
		int left = query(tree, treeNode * 2, s, m, l, r);
		int right = query(tree, treeNode * 2 + 1, m + 1, e, l, r);
		return left + right;
	}

	public static void updateLazy(int tree[], int lazy[], int treeNode, int s, int e, int l, int r, int val) {
		if (e < l || r < s)
			return;
		if (lazy[treeNode] != 0) {
			tree[treeNode] += lazy[treeNode];
			if (s != e) {
				lazy[treeNode * 2] += lazy[treeNode];
				lazy[treeNode * 2 + 1] += lazy[treeNode];
			}
			lazy[treeNode] = 0;
		}
		if (s >= l && e <= r) {
			tree[treeNode] += val;
			if (s != e) {
				lazy[treeNode * 2] += val;
				lazy[treeNode * 2 + 1] += val;
			}
			return;
		}
		int m = (s + e) / 2;
		updateLazy(tree, lazy, treeNode * 2, s, m, l, r, val);
		updateLazy(tree, lazy, treeNode * 2 + 1, m + 1, e, l, r, val);
	}

	public static int[][] fw(int arr[][], int n) {
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
				}
			}
		}
		return arr;
	}

	public static int bf(Edge[] edges, int n, int src, int dest) {
		int res[] = new int[n + 1];
		Arrays.fill(res, 10000);
		res[src] = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < edges.length; i++) {
				res[edges[j].d] = Math.min(res[edges[j].d], res[edges[j].s] + edges[j].w);
			}
		}
		return res[dest];
	}

	public static LinkedList<Integer> seive(int n) {
		boolean[] isPrime = new boolean[n + 1];
		Arrays.fill(isPrime, true);
		isPrime[0] = isPrime[1] = false;
		LinkedList<Integer> primes = new LinkedList<>();
		for (int i = 2; i <= n; i++) {
			if (isPrime[i]) {
				primes.add(i);
				for (int j = i * i; j <= n; j += i) {
					isPrime[j] = false;
				}
			}
		}
		return primes;
	}

	public static int factors(int n) {
		LinkedList<Integer> primes = seive(n);
		int res = 1, pi = 0;
		while (n > 1) {
			int c = 1;
			while (c % primes.get(pi) == 0) {
				c = c / primes.get(pi);
			}
			res *= c;
			pi++;
		}
		return res;
	}

	public static void dfs(LinkedList<Integer>[] graph, int src, HashSet<Integer> isv) {
		isv.add(src);
		for (int i = 0; i < graph[src].size(); i++) {
			if (!isv.contains(graph[src].get(i))) {
				dfs(graph, graph[src].get(i), isv);
			}
		}
	}

	public static void dfscc(LinkedList<Integer>[] graph, int src, HashSet<Integer> isv, HashSet<Integer> cc) {
		isv.add(src);
		cc.add(src);
		for (int i = 0; i < graph[src].size(); i++) {
			if (!isv.contains(graph[src].get(i))) {
				dfs(graph, graph[src].get(i), isv);
			}
		}
	}

	public static void topologicalSort(LinkedList<Integer>[] graph, int src, HashSet<Integer> isv, Stack<Integer> stk) {
		isv.add(src);
		for (int i = 0; i < graph[src].size(); i++) {
			if (!isv.contains(graph[src].get(i))) {
				dfs(graph, graph[src].get(i), isv);
			}
		}
		stk.push(src);// store as per finish time
	}

	public static boolean isConnected(LinkedList<Integer>[] graph, int src, int dest) {
		HashSet<Integer> isv = new HashSet<>();
		dfs(graph, src, isv);
		return isv.contains(dest);
	}

	public static LinkedList<HashSet<Integer>> cc(LinkedList<Integer>[] graph, int n) {
		LinkedList<HashSet<Integer>> cc = new LinkedList<>();
		HashSet<Integer> isv = new HashSet<>();
		HashSet<Integer> com = new HashSet<>();
		for (int i = 1; i <= n; i++) {
			if (!isv.contains(i)) {
				dfscc(graph, i, isv, com);
				cc.add(com);
				com = new HashSet<>();
			}
		}
		return cc;
	}

	public static LinkedList<HashSet<Integer>> scc(LinkedList<Integer>[] graph, LinkedList<Integer>[] tgraph, int n) {
		Stack<Integer> stk = new Stack<>();
		HashSet<Integer> isv = new HashSet<>();
		for (int i = 1; i <= n; i++) {
			if (!isv.contains(i)) {
				topologicalSort(tgraph, i, isv, stk);
			}
		}

		LinkedList<HashSet<Integer>> cc = new LinkedList<>();
		isv = new HashSet<>();
		HashSet<Integer> com = new HashSet<>();
		while (!stk.isEmpty()) {
			int i = stk.pop();
			if (!isv.contains(i)) {
				dfscc(graph, i, isv, com);
				cc.add(com);
				com = new HashSet<>();
			}
		}
		return cc;
	}

	public static void bfs(LinkedList<Integer>[] graph, int src) {
		HashSet<Integer> isv = new HashSet<>();
		isv.add(src);
		LinkedList<Integer> q = new LinkedList<>();
		while (!q.isEmpty()) {
			int now = q.poll();
			for (int i = 0; i < graph[now].size(); i++) {
				if (!isv.contains(graph[now].get(i))) {
					isv.add(graph[now].get(i));
					q.add(graph[now].get(i));
				}
			}
		}
	}

	public static void pq() {
		PriorityQueue<Integer> heap = new PriorityQueue<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o1 < o2 ? -1 : 1;
			}
		});
		heap.add(1);
		heap.contains(1);
		heap.remove();
		heap.poll();
		heap.toArray();

	}

	public static int gcd(int a, int b) {
		return b == 0 ? a : gcd(b, a % b);
	}

	public static int exp(int base, int exp) {
		int ans = exp(base, exp / 2);
		return exp % 2 == 0 ? ans * ans : ans * ans * base;
	}

	public static Node LCA(Node node, int a, int b) {
		if (node == null)
			return null;
		if (node.w == a || node.w == b)
			return node;
		Node left = LCA(node.l, a, b);
		Node right = LCA(node.r, a, b);
		if (left != null && right != null)
			return node;
		return left != null ? left : right;
	}

}
