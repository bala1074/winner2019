package SP_FLOYD_WARSHALL;

import java.util.Arrays;

public class Main {
	public static void main(String[] args) {

		int INF = 100000;

		int graph[][] = { { 0, 5, INF, 10 }, { INF, 0, 3, INF }, { INF, INF, 0, 1 }, { INF, INF, INF, 0 } };

		for (int k = 0; k < 4; k++) {
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					if (graph[i][j] > graph[i][k] + graph[k][j])
						graph[i][j] = graph[i][k] + graph[k][j];
				}
			}
		}

		System.out.println(Arrays.deepToString(graph));

	}
}
