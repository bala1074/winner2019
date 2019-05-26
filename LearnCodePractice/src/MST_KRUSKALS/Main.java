package MST_KRUSKALS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
	public static void main(String[] args) {
		Edge[] edges = new Edge[8];
		edges[0] = new Edge(3, 1, 2);
		edges[1] = new Edge(1, 1, 3);
		edges[2] = new Edge(2, 2, 5);
		edges[3] = new Edge(4, 3, 5);
		edges[4] = new Edge(3, 3, 4);
		edges[5] = new Edge(1, 5, 6);
		edges[6] = new Edge(2, 4, 6);
		edges[7] = new Edge(4, 0, 3);

		System.out.println(Math.signum(1));

		Arrays.sort(edges, new Comparator<Edge>() {
			@Override
			public int compare(Edge o1, Edge o2) {
				return o1.v < o2.v ? -1 : 1;
			}
		});


		int arr[] = new int[9];
		for (int i = 1; i < 9; i++)
			arr[i] = i;

		int count = 0;
		ArrayList<Edge> graph = new ArrayList<>();
		for (int i = 0; i < 8; i++) {
			if (union(arr, edges[i].s, edges[i].d)) {
				count++;
				graph.add(edges[i]);
			}
			
		}

		for (int i = 0; i < graph.size(); i++)
			System.out.println(graph.get(i).s + " " + graph.get(i).d + " " + graph.get(i).v);

	}

	public static boolean union(int arr[], int u, int v) {
		int s1 = find(arr, u);
		int s2 = find(arr, v);
		if (s1 != s2) {
			arr[s1] = s2;
			find(arr, u);
			return true;
		}
		return false;
	}

	public static int find(int arr[], int val) {
		if (arr[val] == val)
			return val;
		return arr[val] = find(arr, arr[val]);
	}
}

class Edge {
	int v, s, d;

	public Edge(int v, int s, int d) {
		this.v = v;
		this.s = s;
		this.d = d;
	}
}
