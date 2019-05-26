package SP_DIJKSTRA;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) {

		LinkedList<Integer>[] graph = new LinkedList[7];
		for (int i = 0; i < 7; i++)
			graph[i] = new LinkedList<>();

		graph[0].add(1);
		graph[0].add(2);

		graph[1].add(0);
		graph[1].add(2);
		graph[1].add(3);

		graph[2].add(0);
		graph[2].add(1);
		graph[2].add(3);
		graph[2].add(4);

		graph[3].add(1);
		graph[3].add(2);
		graph[3].add(4);

		graph[4].add(2);
		graph[4].add(3);

		int weights[][] = new int[7][7];

		weights[0][1] = 4;
		weights[1][0] = 4;
		weights[0][2] = 5;
		weights[2][0] = 5;
		weights[2][1] = 2;
		weights[1][2] = 2;
		weights[3][1] = 6;
		weights[1][3] = 6;
		weights[2][3] = 3;
		weights[3][2] = 3;
		weights[2][4] = 9;
		weights[4][2] = 9;
		weights[4][3] = 5;
		weights[3][4] = 5;

		PriorityQueue<Edge> heap = new PriorityQueue<Edge>(new Comparator<Edge>() {
			@Override
			public int compare(Edge o1, Edge o2) {
				return o1.w < o2.w ? -1 : 1;
			}
		});

		HashSet<Integer> isVisited = new HashSet<>();
		Edge source = new Edge(0, 0, 0);
		heap.add(source);
		while (!heap.isEmpty()) {
			Edge now = heap.poll();
			if (isVisited.contains(now.s) && isVisited.contains(now.d))
				continue;
			isVisited.add(now.s);
			isVisited.add(now.d);
			System.out.println(now.s + " " + now.d + " " + now.w);
			for (int i = 0; i < graph[now.d].size(); i++) {
				int src = now.d;
				int dest = graph[src].get(i);
				if (!(isVisited.contains(src) && isVisited.contains(dest))) {
					Edge e = new Edge(src, dest, weights[src][dest] + now.w);
					heap.add(e);
				}
			}

		}

	}
}

class Edge {
	int s, d, w;

	public Edge(int s, int d, int w) {
		this.s = s;
		this.d = d;
		this.w = w;
	}
}