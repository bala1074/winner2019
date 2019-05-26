package TOPOLOGICAL_SORTING;

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
		graph[2].add(4);
		graph[4].add(5);
		graph[5].add(6);
		graph[7].add(8);
		graph[8].add(9);

		Stack<Integer> stk = new Stack<Integer>();
		HashSet<Integer> isVisited = new HashSet<>();
		for (int i = 0; i < 10; i++) {
			if (!isVisited.contains(i)) {
				dfs(graph, i, isVisited, stk);
			}
		}

		while (!stk.isEmpty()) {
			System.out.println(stk.pop());
		}
	}

	public static void dfs(LinkedList<Integer>[] graph, int current, HashSet<Integer> isVisisted, Stack<Integer> stk) {

		isVisisted.add(current);
		for (int i = 0; i < graph[current].size(); i++) {
			if (!isVisisted.contains(graph[current].get(i))) {
				dfs(graph, graph[current].get(i), isVisisted, stk);
			}
		}

		stk.push(current);

	}
}
