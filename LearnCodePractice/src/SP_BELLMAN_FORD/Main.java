package SP_BELLMAN_FORD;

import java.util.Arrays;

public class Main {
	public static void main(String[] args) {

		Edge[] edges = new Edge[9];
		edges[0] = new Edge(1, 2, 6);
		edges[1] = new Edge(1, 4, 4);
		edges[2] = new Edge(1, 5, 5);
		edges[3] = new Edge(2, 3, -1);
		edges[4] = new Edge(4, 3, 3);
		edges[7] = new Edge(4, 2, -2);
		edges[8] = new Edge(4, 5, -2);
		edges[5] = new Edge(5, 6, -1);
		edges[6] = new Edge(3, 6, 3);

		int dist[] = new int[7];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[1] = 0;
		for (int i = 1; i < 6; i++) {
			for (int j = 0; j < 9; j++) {
				int u = edges[j].s, v = edges[j].d, uv = edges[j].w;
				if (dist[u] + uv < dist[v])
					dist[v] = dist[u] + uv;
			}
		}
		System.out.println(Arrays.toString(dist));
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