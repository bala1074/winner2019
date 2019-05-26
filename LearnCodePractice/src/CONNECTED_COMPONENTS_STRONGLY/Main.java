package CONNECTED_COMPONENTS_STRONGLY;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;

public class Main {
	public static void main(String[] args) {
		LinkedList<Integer>[] graph = new LinkedList[10];
		for (int i = 0; i < 10; i++)
			graph[i] = new LinkedList<Integer>();

		graph[0].add(1);
		graph[1].add(2);
		graph[2].add(3);
		graph[3].add(1);
		graph[2].add(4);
		graph[4].add(5);
		graph[5].add(6);
		graph[6].add(4);
		graph[7].add(6);
		graph[7].add(8);
		graph[8].add(7);
		graph[8].add(9);

		Stack<Integer> stk = new Stack<Integer>(); // store vertices in stack as per therir finish time
		HashSet<Integer> isVisited = new HashSet<Integer>();
		for (int i = 0; i < 10; i++) {
			if (!isVisited.contains(i))
				dfs(graph, i, stk, isVisited);
		}
		System.out.println(stk);

		// transpose the given graph
		for (int i = 0; i < 10; i++)
			graph[i] = new LinkedList<Integer>();

		graph[1].add(0);
		graph[2].add(1);
		graph[3].add(2);
		graph[0].add(3);
		graph[4].add(2);
		graph[5].add(4);
		graph[6].add(5);
		graph[4].add(6);
		graph[6].add(7);
		graph[8].add(7);
		graph[7].add(8);
		graph[9].add(8);

		isVisited = new HashSet<Integer>();
		while (!stk.isEmpty()) {
			int current = stk.pop();
			if (!isVisited.contains(current)) {
				dfs2(graph, current, isVisited);
				System.out.println();
			}
		}

	}

	public static void dfs(LinkedList<Integer>[] graph, int current, Stack<Integer> stk, HashSet<Integer> isVisited) {
		isVisited.add(current);
		for (int i = 0; i < graph[current].size(); i++) {
			if (!isVisited.contains(graph[current].get(i)))
				dfs(graph, graph[current].get(i), stk, isVisited);
		}
		stk.push(current);
	}

	public static void dfs2(LinkedList<Integer>[] graph, int current, HashSet<Integer> isVisited) {
		System.out.println(current);
		isVisited.add(current);
		for (int i = 0; i < graph[current].size(); i++) {
			if (!isVisited.contains(graph[current].get(i))) {
				dfs2(graph, graph[current].get(i), isVisited);
			}
		}
	}
}
