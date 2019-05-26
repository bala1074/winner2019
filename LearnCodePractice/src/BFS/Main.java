package BFS;

import java.util.HashSet;
import java.util.LinkedList;

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

		bfs(graph, 1);

	}

	public static void bfs(LinkedList<Integer>[] graph, int node) {
		HashSet<Integer> isVisited = new HashSet<>();
		LinkedList<Integer> queue = new LinkedList<Integer>();
		queue.add(node);
		while (!queue.isEmpty()) {
			int now = queue.poll();
			for (int i = 0; i < graph[now].size(); i++) {
				if (!isVisited.contains(graph[now].get(i))) {
					queue.add(graph[now].get(i));
					isVisited.add(graph[now].get(i));
				}
			}
			System.out.println(now);
		}
	}

}
