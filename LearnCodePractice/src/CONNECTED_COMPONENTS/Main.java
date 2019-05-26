package CONNECTED_COMPONENTS;

import java.util.HashSet;
import java.util.LinkedList;

public class Main {
	public static void main(String[] args) {
		LinkedList<Integer>[] graph = new LinkedList[5];
		for (int i = 0; i < 5; i++)
			graph[i] = new LinkedList<>();
		graph[0].add(1);
		graph[1].add(2);
		graph[3].add(4);

		HashSet<Integer> isVisited = new HashSet<>();
		for (int i = 0; i < 5; i++) {
			if (!isVisited.contains(i)) {
				dfs(graph, i, isVisited);
				System.out.println();
			}
		}
	}

	public static void dfs(LinkedList<Integer>[] graph, int current, HashSet<Integer> isVisited) {
		System.out.println(current);
		isVisited.add(current);
		for (int i = 0; i < graph[current].size(); i++) {
			if (!isVisited.contains(graph[current].get(i))) {
				dfs(graph, graph[current].get(i), isVisited);
			}
		}
	}
}
