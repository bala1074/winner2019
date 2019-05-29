package SUBCONCIOUS_CODING;

// 1hr 5 Mins challenge

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;

public class Day1_May28_2019 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

		// System.out.println(primes());
		System.out.println(factorsBF(12));
		System.out.println(primeFactors(12));
		System.out.println(primeFactorsRem(12));

		int t = getInt(br.readLine());
		while (t-- > 0) {
			String inp[] = br.readLine().trim().split(" ");
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

	public static boolean union(int arr[], int u, int v) {
		int s1 = find(arr, u);
		int s2 = find(arr, v);
		if (s1 != s2) {
			arr[s1] = s2;
			find(arr, u);
			return true;
		} else
			return false;
	}

	public static int find(int[] arr, int ind) {
		if (arr[ind] == ind)
			return ind;
		return arr[ind] = find(arr, arr[ind]);
	}

	public static ArrayList<Integer> primes() {
		int n = 1000;
		boolean isPrime[] = new boolean[n];
		Arrays.fill(isPrime, true);
		isPrime[0] = isPrime[1] = false;
		int sqrtN = (int) Math.sqrt(n);
		ArrayList<Integer> primes = new ArrayList<>();
		for (int i = 2; i < n; i++) {
			if (isPrime[i]) {
				primes.add(i);
				for (int j = i * i; j < n; j += i) {
					isPrime[j] = false;
				}

			}
		}
		return primes;
	}

	public static int factorsBF(int n) {
		int fc = 0;
		int sqrtN = (int) Math.sqrt(n);
		for (int i = 1; i <= sqrtN; i++) {
			if (n % i == 0)
				fc += 2;
		}
		return fc + ((sqrtN * sqrtN == n) ? 1 : 0);
	}

	public static int primeFactors(int n) {
		ArrayList<Integer> primes = primes();
		int i = 0;
		ArrayList<Integer> fct = new ArrayList<>();
		while (n > 1) {
			int now = primes.get(i);
			int cnt = 0;
			while (n % now == 0) {
				n = n / now;
				cnt++;
			}
			if (cnt > 0)
				fct.add(cnt + 1);
			i++;
		}
		int res = 1;
		for (i = 0; i < fct.size(); i++)
			res *= fct.get(i);
		return res;
	}

	public static int primeFactorsRem(int n) {
		ArrayList<Integer> primes = primes();
		int i = 0;
		ArrayList<Integer> fct = new ArrayList<>();
		while (n > 1) {
			int now = primes.get(i);
			int cnt = 0;
			while (n % now == 0) {
				n = n / now;
				cnt++;
			}
			if (cnt > 0)
				fct.add(cnt + 1);
			i++;
		}
		int res = 1;
		int size = fct.size();
		for (i = 0; i < size; i++)
			if (i == size - 1)
				res *= fct.get(i) - 1;
			else
				res *= fct.get(i);
		return res;
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

	public static void build(int[] arr, int s, int e, int tree[], int treeNode) {
		if (s == e) {
			tree[treeNode] = arr[s];
			return;
		}
		int m = (s + e) / 2;
		build(arr, s, m, tree, 2 * treeNode);
		build(arr, m + 1, e, tree, 2 * treeNode + 1);
		tree[treeNode] = tree[treeNode * 2] + tree[treeNode * 2 + 1];
	}

	public static void update(int arr[], int s, int e, int tree[], int treeNode, int ind, int val) {
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
		tree[treeNode] = tree[treeNode * 2] + tree[treeNode * 2 + 1];
	}

	public static int query(int tree[], int treeNode, int s, int e, int l, int r) {
		if (e < l || r < s)
			return 0;
		if (s >= l && e <= r)
			return tree[treeNode];
		int m = (s + e) / 2;
		int leftTree = query(tree, 2 * treeNode, s, m, l, r);
		int rightTree = query(tree, 2 * treeNode + 1, m + 1, e, l, leftTree);
		return leftTree + rightTree;
	}

	public static void updateLazy(int arr[], int tree[], int lazy[], int treeNode, int s, int e, int l, int r,
			int val) {
		if (e < l || r < s)
			return;
		if (lazy[treeNode] != 0) {
			tree[treeNode] += lazy[treeNode];
			if (s != e) {
				lazy[2 * treeNode] += tree[treeNode];
				lazy[2 * treeNode + 1] += tree[treeNode];
			}
			lazy[treeNode] = 0;
		}

		if (s >= l && e <= r) {
			tree[treeNode] = val;
			if (s != e) {
				lazy[2 * treeNode] += val;
				lazy[2 * treeNode + 1] += val;
			}
		}
		int m = (s + e) / 2;
		updateLazy(arr, tree, lazy, 2 * treeNode, s, m, l, r, val);
		updateLazy(arr, tree, lazy, 2 * treeNode + 1, m + 1, e, l, r, val);
		tree[treeNode] = tree[treeNode * 2] + tree[treeNode * 2 + 1];
	}

	public static void dfs(LinkedList<Integer> graph[], int src, HashSet<Integer> isV) {
		isV.add(src);
		for (int i = 0; i < graph[src].size(); i++) {
			int current = graph[src].get(i);
			if (!isV.contains(current)) {
				dfs(graph, current, isV);
			}
		}
	}

	public static int[][] fw(int[][] arr, int n) {
		for (int k = 0; k < n; k++)
			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++)
					if (arr[i][j] > arr[i][k] + arr[k][j])
						arr[i][j] = arr[i][k] + arr[k][j];
		return arr;
	}

	public static int bf(Edge[] edges, int n, int src, int dest) {

		int res[] = new int[n + 1];
		Arrays.fill(res, 10000);// fill with max
		res[src] = 0;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < edges.length; j++) {
				Edge now = edges[j];
				if (res[now.s] + now.w < res[now.d])
					res[now.d] = res[now.s] + now.w;
			}
		}
		return res[dest];
	}

	public static LinkedList<Edge> kruskals(Edge[] edges, int n) {
		// STEP1 : SORT EDGE
		Arrays.sort(edges, new Comparator<Edge>() {

			@Override
			public int compare(Edge o1, Edge o2) {
				return o1.w < o2.w ? -1 : 1;
			}
		});
		// Step to pick each edge , if not forming cycle take it else skip
		LinkedList<Edge> selectedEdges = new LinkedList<>();
		int uf[] = new int[n];
		for (int i = 0; i < n; i++)
			uf[i] = i;

		for (int i = 0; i < edges.length; i++) {
			int cs = edges[i].s, cd = edges[i].d;
			if (union(uf, cs, cd)) {
				selectedEdges.add(edges[i]);
			}

		}
		return selectedEdges;
	}

	public static void topologocalSorting(LinkedList<Integer> graph[], int src, HashSet<Integer> isV,
			Stack<Integer> stk) {
		isV.add(src);
		for (int i = 0; i < graph[src].size(); i++) {
			int current = graph[src].get(i);
			if (!isV.contains(current)) {
				topologocalSorting(graph, current, isV, stk);
			}
		}
		stk.push(src);// store as per finish time;
	}

	public static void bfs(LinkedList<Integer> graph[], int src, HashSet<Integer> isV) {
		isV.add(src);
		LinkedList<Integer> queue = new LinkedList<>();
		queue.add(src);
		while (queue.isEmpty()) {
			int current = queue.poll();
			if (!isV.contains(current)) {
				isV.add(current);
				queue.add(current);
			}
		}
	}

	public static void PQ() {
		PriorityQueue<Integer> heap = new PriorityQueue<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 < o2 ? -1 : 1;
			}
		});
		heap.add(1);
		while (!heap.isEmpty()) {
			System.out.println(heap.poll());
		}
	}

}

class Edge {
	int s;
	int d;
	int w;

	public Edge(int s, int d, int w) {
		this.s = s;
		this.d = d;
		this.w = w;
	}
}