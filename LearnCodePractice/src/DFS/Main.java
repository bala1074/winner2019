package DFS;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;

public class Main {
	public static void main(String[] args) {

		LinkedList<Integer>[] graph = new LinkedList[7];
		for (int i = 0; i < 7; i++)
			graph[i] = new LinkedList<Integer>();
		graph[0].add(3);

		graph[3].add(1);
		graph[3].add(4);
		graph[3].add(5);
		graph[3].add(0);

		graph[4].add(3);
		graph[1].add(3);
		graph[1].add(2);

		graph[5].add(3);
		graph[5].add(6);

		graph[2].add(1);

		HashSet<Integer> hs = new HashSet<Integer>();
		int s = 1;
		hs.add(s);
		dfs(graph, s, hs);
		System.out.println();
		dfs2(graph, s);

	}

	public static void dfs(LinkedList<Integer>[] graph, int node, HashSet<Integer> isv) {
		System.out.println(node);
		for (int i = 0; i < graph[node].size(); i++) {
			if (!isv.contains(graph[node].get(i))) {
				isv.add(graph[node].get(i));
				dfs(graph, graph[node].get(i), isv);
			}
		}
	}

	public static void dfs2(LinkedList<Integer>[] graph, int nd) {
		HashSet<Integer> isv = new HashSet<>();
		isv.add(nd);

		Stack<Integer> stk = new Stack<>();
		stk.add(nd);
		while (!stk.isEmpty()) {
			int node = stk.pop();
			System.out.println(node);
			for (int i = 0; i < graph[node].size(); i++) {
				if (!isv.contains(graph[node].get(i))) {
					isv.add(graph[node].get(i));
					stk.push(graph[node].get(i));
				}
			}
		}
	}

}
