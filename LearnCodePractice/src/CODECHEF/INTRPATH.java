package CODECHEF;

import java.io.*;
import java.util.*;

class INTRPATH {

	public static int com = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		int t = getInt(br.readLine());
		while (t-- > 0) {
			String inp[] = br.readLine().trim().split(" ");
			int n = getInt(inp[0]), q = getInt(inp[1]);
			LinkedList<Integer>[] graph = new LinkedList[n + 1];
			for (int i = 1; i <= n; i++) {
				graph[i] = new LinkedList<Integer>();
			}
			for (int i = 0; i < n - 1; i++) {
				inp = br.readLine().trim().split(" ");
				int s = getInt(inp[0]), d = getInt(inp[1]);
				graph[s].add(d);
				graph[d].add(s);
			}
			HashMap<String, Integer> dpGraph = new HashMap<>();
			HashMap<String, Integer> dpDFS = new HashMap<>();
			HashMap<String, Integer> dpPATH = new HashMap<>();

			while (q-- > 0) {
				inp = br.readLine().trim().split(" ");
				int a1 = getInt(inp[0]), b1 = getInt(inp[1]);
				int a = Math.min(a1, b1), b = Math.max(a1, b1);
				if (dpGraph.containsKey(a + "-" + b)) {
					pw.println(dpGraph.get(a + "-" + b));
				} else {
					ArrayList<Integer> path = new ArrayList<>();
					HashSet<Integer> isv = new HashSet<>();
					spInMst(graph, a1, b1, n, path, isv);
					int ans = path.size();
					for (int ind = 0; ind < path.size(); ind++) {
						if (isv.size() == n)
							break;
						int current = path.get(ind);

						boolean ispathkey = true;
						String pathKey1 = "";
						if (ind - 1 >= 0)
							pathKey1 = path.get(ind - 1) + "-";
						else
							ispathkey = false;
						pathKey1 = pathKey1 + path.get(ind);
						if (ind + 1 < path.size())
							pathKey1 = pathKey1 + "-" + path.get(ind + 1);
						else
							ispathkey = false;
						String pathKey2 = "";
						if (ind + 1 < path.size())
							pathKey2 = path.get(ind + 1) + "-";
						pathKey2 = pathKey2 + path.get(ind);
						if (ind - 1 >= 0)
							pathKey2 = pathKey2 + "-" + path.get(ind - 1);
						int forNow = 0;
						if (ispathkey && dpPATH.containsKey(pathKey1)) {
							forNow = dpPATH.get(pathKey1);
						} else if (ispathkey && dpPATH.containsKey(pathKey2)) {
							forNow = dpPATH.get(pathKey2);
						} else {
							isv.add(current);
							ArrayList<Integer> al = new ArrayList<>();
							for (int i = 0; i < graph[current].size(); i++) {
								if (!isv.contains(graph[current].get(i))) {
									if (dpDFS.containsKey(current + "-" + graph[current].get(i))) {
										al.add(dpDFS.get(current + "-" + graph[current].get(i)));
									} else {
										isv.add(graph[current].get(i));
										dfs(graph, graph[current].get(i), isv);
										al.add(com);
										dpDFS.put(current + "-" + graph[current].get(i), com);
										com = 0;
									}
								}
							}
							for (int i = 0; i < al.size(); i++) {
								forNow += al.get(i);
								for (int j = i + 1; j < al.size(); j++)
									forNow += al.get(i) * al.get(j);
							}
							dpPATH.put(pathKey1, forNow);
							dpPATH.put(pathKey2, forNow);
						}
						ans += forNow;
					}
					dpGraph.put(a + "-" + b, ans);
					pw.println(ans);
					// System.out.println(dpPATH);
				}
			}
		}
		pw.flush();
		pw.close();
	}

	public static void dfs(LinkedList<Integer>[] graph, int src, HashSet<Integer> isv) {
		com++;
		for (int i = 0; i < graph[src].size(); i++) {
			if (!isv.contains(graph[src].get(i))) {
				isv.add(graph[src].get(i));
				dfs(graph, graph[src].get(i), isv);
			}
		}
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

	public static void spInMst(LinkedList<Integer>[] graph, int src, int dest, int n, ArrayList<Integer> path,
			HashSet<Integer> isVisited) {
		HashSet<Integer> isv = new HashSet<>();
		Stack<Node> stk = new Stack<Node>();
		isv.add(src);
		stk.add(new Node(src, 0));

		int res[] = new int[n], level = 0;
		res[0] = src; // start
		while (!stk.isEmpty()) {
			Node node = stk.peek();
			int val = node.val;
			level = node.level;
			res[level] = res[level] == 0 ? val : res[level];
			boolean isLeaf = true;
			for (int i = 0; i < graph[val].size(); i++) {
				if (!isv.contains(graph[val].get(i))) {
					stk.push(new Node(graph[val].get(i), level + 1));
					isv.add(graph[val].get(i));
					isLeaf = false;
				}
			}
			if (isLeaf) {
				res[stk.pop().level] = 0;
			} else
				level += 1;
			if (isv.contains(dest)) {
				break;
			}
		}
		res[level] = dest; // dest
		// System.out.println(Arrays.toString(res));
		for (int i = 0; i < n; i++)
			if (res[i] == 0)
				break;
			else {
				isVisited.add(res[i]);
				path.add(res[i]);
			}
	}
}

class Node {
	int val, level;

	public Node(int n, int l) {
		this.val = n;
		this.level = l;
	}
}