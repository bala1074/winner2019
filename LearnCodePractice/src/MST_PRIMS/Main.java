package MST_PRIMS;

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
		weights[0][2] = 1;
		weights[2][0] = 1;
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
				return o1.v < o2.v ? -1 : 1;
			}
		});
		
		Edge source = new Edge(0, 0, 0); // keep src,dest same and wight it self is 0
		heap.add(source);
		HashSet<Integer> isVisited = new HashSet<>();
		while (!heap.isEmpty()) {
			Edge current = heap.poll();

			if ((isVisited.contains(current.s) && isVisited.contains(current.d)))
				continue;

			isVisited.add(current.s);
			isVisited.add(current.d);

			System.out.println(current.s + " " + current.d + " " + current.v);

			for (int i = 0; i < graph[current.d].size(); i++) {
				int src = current.d;
				int dest = graph[current.d].get(i);

				if (!(isVisited.contains(src) && isVisited.contains(dest)))
					heap.add(new Edge(src, dest, weights[src][dest]));
			}
		}

	}
}

class Edge {
	int v, s, d;

	public Edge(int s, int d, int v) {
		this.v = v;
		this.s = s;
		this.d = d;
	}
}
