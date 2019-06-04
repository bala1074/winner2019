package SUBCONCIOUS_CODING;

//32.29Mins to 38.16Mins

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

public class Day8_June4_2019 {
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

	public static LinkedList<Edge> kruskals(Edge edges[], int n) {
		Arrays.sort(edges, new Comparator<Edge>() {
			@Override
			public int compare(Edge o1, Edge o2) {
				return o1.w < o2.w ? -1 : 1;
			}
		});
		int uf[] = new int[n + 1];
		for (int i = 0; i <= n; i++)
			uf[i] = i;
		LinkedList<Edge> mst = new LinkedList<Edge>();
		for (int i = 0; i < edges.length; i++) {
			if (union(uf, edges[i].s, edges[i].d))
				mst.add(edges[i]);
		}
		return mst;
	}

	public static int bf(Edge edges[], int n, int src, int dest) {
		int res[] = new int[n + 1];
		Arrays.fill(res, 100000);
		res[src] = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < edges.length; j++) {
				res[edges[j].d] = Math.min(res[edges[j].d], edges[j].w + res[edges[j].s]);
			}
		}
		return res[dest];
	}

	public static int[][] fw(int arr[][], int n) {
		for (int k = 0; k < n; k++)
			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++) {
					arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
				}
		return arr;
	}

	public static void updateFT(int ind, int val, int[] BIT, int n) {
		for (; ind <= n; ind += ind & -ind)
			BIT[ind] += val;
	}

	public static int queryFT(int ind, int BIT[]) {
		int sum = 0;
		for (; ind >= 0; ind -= ind & -ind)
			sum += BIT[ind];
		return sum;
	}

	public static void build(int arr[], int tree[], int treeNode, int s, int e) {
		if (s == e) {
			tree[treeNode] = arr[s];
			return;
		}
		int m = (s + e) / 2;
		build(arr, tree, treeNode * 2 + 1, m + 1, e);
		build(arr, tree, treeNode * 2, s, m);
		tree[treeNode] = tree[treeNode * 2] + tree[treeNode * 2 + 1];
	}

	public static void update(int arr[], int tree[], int treeNode, int s, int e, int ind, int val) {
		if (s == e) {
			tree[treeNode] = arr[s];
			return;
		}
		int m = (s + e) / 2;
		if (ind > m)
			build(arr, tree, treeNode * 2 + 1, m + 1, e);
		else
			build(arr, tree, treeNode * 2, s, m);
		tree[treeNode] = tree[treeNode * 2] + tree[treeNode * 2 + 1];
	}

	public static int query(int tree[], int treeNode, int s, int e, int l, int r) {
		if (e < l || r < s)
			return 0;
		if (s >= l && e <= r)
			return tree[treeNode];
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
		tree[treeNode] = tree[treeNode * 2] + tree[treeNode * 2 + 1];
	}

	public static LinkedList<Integer> seive(int n) {
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

	public static int fact(int n) {
		LinkedList<Integer> primes = seive(n);
		int res = 1, pi = 0;
		while (n > 1) {
			int c = 1;
			while (n % primes.get(pi) == 0) {
				n = n / primes.get(pi);
				c++;
			}
			pi++;
			res *= c;
		}
		return res;
	}

	public static int gcd(int a, int b) {
		return b == 0 ? a : gcd(b, a % b);
	}

	public static int exp(int base, int pow) {
		int ans = exp(base, pow / 2);
		return pow % 2 == 0 ? ans * ans : ans * ans * base;
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

	public static void pq() {
		PriorityQueue<Integer> heap = new PriorityQueue<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 < o2 ? -1 : 1;
			}
		});

		heap.add(1);
		heap.add(2);
		heap.contains(2);
		heap.remove(1);
		heap.poll();
		heap.toArray();

	}

	public static void dfs(LinkedList<Integer>[] graph, int src, HashSet<Integer> isv) {
		isv.add(src);
		for (int i = 0; i < graph[src].size(); i++) {
			if (!isv.contains(graph[src].get(i)))
				dfs(graph, graph[src].get(i), isv);
		}
	}

	public static void dfs2(LinkedList<Integer>[] graph, int src, HashSet<Integer> isv, HashSet<Integer> com) {
		isv.add(src);
		com.add(src);
		for (int i = 0; i < graph[src].size(); i++) {
			if (!isv.contains(graph[src].get(i)))
				dfs(graph, graph[src].get(i), isv);
		}
	}

	public static void dfs3(LinkedList<Integer>[] graph, int src, HashSet<Integer> isv, Stack<Integer> stk) {
		isv.add(src);
		for (int i = 0; i < graph[src].size(); i++) {
			if (!isv.contains(graph[src].get(i)))
				dfs(graph, graph[src].get(i), isv);
		}
		stk.add(src);
	}

	public static void bfs(LinkedList<Integer>[] graph, int src) {
		HashSet<Integer> isv = new HashSet<>();
		LinkedList<Integer> q = new LinkedList<>();
		isv.add(src);
		q.add(src);
		while (!q.isEmpty()) {
			src = q.poll();
			for (int i = 0; i < graph[src].size(); i++) {
				if (!isv.contains(graph[src].get(i))) {
					isv.add(graph[src].get(i));
					q.add(graph[src].get(i));
				}
			}
		}
	}

	public static void topologicalSort(LinkedList<Integer>[] graph, int n) {
		HashSet<Integer> isv = new HashSet<>();
		Stack<Integer> stk = new Stack<>();
		for (int i = 1; i <= n; i++)
			if (!isv.contains(i))
				dfs3(graph, i, isv, stk);

		while (!stk.isEmpty()) {
			System.out.println(stk.pop());
		}
	}

	public static boolean isConnected(LinkedList<Integer>[] graph, int src, int dest) {
		HashSet<Integer> isv = new HashSet<>();
		dfs(graph, src, isv);
		return isv.contains(dest);
	}

	public static LinkedList<HashSet<Integer>> cc(LinkedList<Integer>[] graph, int n) {
		LinkedList<HashSet<Integer>> cc = new LinkedList<>();
		HashSet<Integer> isv = new HashSet<>(), com = new HashSet<Integer>();
		for (int i = 1; i <= n; i++) {
			if (!isv.contains(i)) {
				dfs2(graph, i, isv, com);
				cc.add(com);
				com = new HashSet<Integer>();
			}
		}
		return cc;
	}

	public static LinkedList<HashSet<Integer>> scc(LinkedList<Integer>[] graph, LinkedList<Integer>[] tgraph, int n) {
		LinkedList<HashSet<Integer>> scc = new LinkedList<>();
		HashSet<Integer> isv = new HashSet<>(), com = new HashSet<Integer>();
		Stack<Integer> stk = new Stack();
		for (int i = 1; i <= n; i++) {
			if (!isv.contains(i)) {
				dfs3(graph, i, isv, stk);
			}
		}
		isv = new HashSet<>();
		while (!stk.isEmpty()) {
			int i = stk.pop();
			if (!isv.contains(i)) {
				dfs2(tgraph, i, isv, com);
				scc.add(com);
				com = new HashSet<Integer>();
			}
		}
		return scc;
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
