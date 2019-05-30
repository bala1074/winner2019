package SUBCONCIOUS_CODING;

// 45Min to 42Mins challange

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

public class Day3_May30_2019 {

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

	public static boolean union(int arr[], int u, int v) {
		int s1 = find(arr, u);
		int s2 = find(arr, v);
		if (s1 == s2)
			return false;
		else {
			arr[s1] = s2;
			find(arr, u);
			return true;
		}
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
				// TODO Auto-generated method stub
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

	public static int queryFT(int ind, int BIT[]) {
		int sum = 0;
		for (; ind > 0; ind -= ind & -ind)
			sum += BIT[ind];
		return sum;
	}

	public static void build(int arr[], int[] tree, int treeNode, int s, int e) {
		if (s == e) {
			tree[treeNode] = arr[s];
			return;
		}
		int m = (s + e) / 2;
		build(arr, tree, 2 * treeNode, s, m);
		build(arr, tree, 2 * treeNode + 1, m + 1, e);
		tree[treeNode] = tree[2 * treeNode] + tree[2 * treeNode + 1];
	}

	public static void update(int arr[], int[] tree, int treeNode, int s, int e, int ind, int val) {
		if (s == e) {
			tree[treeNode] = arr[s];
			return;
		}
		int m = (s + e) / 2;
		if (ind > m)
			update(arr, tree, 2 * treeNode + 1, m + 1, e, ind, val);
		else
			update(arr, tree, 2 * treeNode, s, m, ind, val);
		tree[treeNode] = tree[2 * treeNode] + tree[2 * treeNode + 1];
	}

	public static int query(int tree[], int treeNode, int s, int e, int l, int r) {
		if (e < l || r < s)
			return 0;
		if (s >= l && e <= r)
			return tree[treeNode];
		int m = (s + e) / 2;
		int left = query(tree, 2 * treeNode, s, m, l, r);
		int right = query(tree, 2 * treeNode + 1, m + 1, e, l, r);
		return left + right;
	}

	public static void updateLazy(int arr[], int[] tree, int[] lazy, int treeNode, int s, int e, int l, int r,
			int val) {
		if (s > e)
			return;
		if (e < l || r < s)
			return;
		if (lazy[treeNode] != 0) {
			tree[treeNode] += lazy[treeNode];
			if (s != e) {
				lazy[treeNode * 2] += tree[treeNode];
				lazy[treeNode * 2 + 1] += tree[treeNode];
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
		updateLazy(arr, tree, lazy, 2 * treeNode + 1, m + 1, e, l, r, val);
		updateLazy(arr, tree, lazy, 2 * treeNode, s, m, l, r, val);
		tree[treeNode] = tree[2 * treeNode] + tree[2 * treeNode + 1];
	}

	public static int[][] fw(int[][] dp, int n) {
		for (int k = 0; k < n; k++)
			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++)
					dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
		return dp;
	}

	public static int bf(Edge[] edges, int src, int dest, int n) {
		int arr[] = new int[n + 1];
		Arrays.fill(arr, 100000);
		arr[src] = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < edges.length; j++) {
				if (arr[edges[j].s] + edges[j].w < arr[edges[j].d])
					arr[edges[j].d] = arr[edges[j].s] + edges[j].w;
			}
		}
		return arr[dest];
	}

	public static LinkedList<Integer> primes(int n) {
		LinkedList<Integer> primes = new LinkedList<>();
		boolean[] isPrime = new boolean[n + 1];
		Arrays.fill(isPrime, true);
		isPrime[0] = isPrime[1] = false;
		for (int i = 2; i <= n; i++) {
			if (isPrime[i]) {
				primes.add(i);
				for (int j = i * i; j <= n; j += i)
					isPrime[j] = false;
			}
		}
		return primes;
	}

	public static int factors(int n) {
		LinkedList<Integer> primes = primes(n);
		int out = 1, pi = 0;
		while (n > 1) {
			int inc = 1;
			while (n % primes.get(pi) == 0) {
				n = n / primes.get(pi);
				inc++;
			}
			out *= inc;
		}
		return out;
	}

	public static boolean isConnected(LinkedList<Integer>[] graph, int src, int dest) {
		HashSet<Integer> isv = new HashSet<>();
		dfs(graph, src, isv);
		if (isv.contains(dest))
			return true;
		return false;
	}

	public static void scc(LinkedList<Integer>[] graph, LinkedList<Integer>[] tgraph, int n) {
		// step1: coneectivity and store int stack as per finish time
		Stack<Integer> stack = new Stack<>();
		HashSet<Integer> isv = new HashSet<>();
		for (int i = 1; i <= n; i++)
			if (!isv.contains(i))
				topologySort(graph, i, isv, stack);
		// step2: DFS on transpose graph by pick nodes from stack, as we store by finish
		// time
		isv = new HashSet<>();
		while (!stack.isEmpty()) {
			int now = stack.pop();
			if (!isv.contains(now))
				dfs(tgraph, now, isv);
		}
	}

	public static void topologySort(LinkedList<Integer>[] graph, int src, HashSet<Integer> isv, Stack<Integer> stack) {
		isv.add(src);
		for (int i = 0; i < graph[src].size(); i++) {
			if (!isv.contains(graph[src].get(i))) {
				dfs(graph, graph[src].get(i), isv);
			}
		}
		stack.push(src);// store as per finish time
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
		isv.add(src);
		while (!q.isEmpty()) {
			int poll = q.poll();
			for (int i = 0; i < graph[poll].size(); i++) {
				if (!isv.contains(graph[src].get(i))) {
					q.add(graph[src].get(i));
					isv.add(graph[src].get(i));
				}
			}
		}

	}

	public static int pow(int base, int exp) {
		int ans = pow(base, exp / 2);
		return exp % 2 == 0 ? ans * ans : ans * ans * base;
	}

	public static int gcd(int a, int b) {
		return b == 0 ? a : gcd(b, a % b);
	}

	public static void pq() {
		PriorityQueue<Integer> heap = new PriorityQueue<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 < o2 ? -1 : 1;
			}
		});
		heap.add(1);
		heap.add(2);
		heap.poll();
		heap.contains(2);
		heap.remove(1);
		heap.toArray();
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

	public static int getInt(String str) {
		return Integer.parseInt(str.trim());

	}

	public static long getLong(String str) {
		return Long.parseLong(str.trim());
	}

	public static double getDouble(String str) {
		return Double.parseDouble(str.trim());
	}

}
