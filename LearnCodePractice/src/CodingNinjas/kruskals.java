package CodingNinjas;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

public class kruskals {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		String str[] = br.readLine().trim().split(" ");
		int V = Integer.parseInt(str[0].trim());
		int E = Integer.parseInt(str[1].trim());

		Edge edges[] = new Edge[E];
		for (int i = 0; i < E; i++) {
			str = br.readLine().trim().split(" ");
			edges[i] = new Edge(Integer.parseInt(str[0].trim()), Integer.parseInt(str[1].trim()),
					Integer.parseInt(str[2].trim()));
		}

		LinkedList<Edge> mst = kruskals(edges, V);
		for (int i = 0; i < mst.size(); i++)
			pw.println(mst.get(i).s + " " + mst.get(i).d + " " + mst.get(i).w);
		pw.flush();
		pw.close();
	}

	public static LinkedList<Edge> kruskals(Edge edges[], int n) {
		Arrays.sort(edges, new Comparator<Edge>() {
			@Override
			public int compare(Edge o1, Edge o2) {
				return o1.w < o2.w ? -1 : 1;
			}
		});
		int uf[] = new int[n];
		for (int i = 0; i < n; i++)
			uf[i] = i;
		LinkedList<Edge> mst = new LinkedList<>();
		for (int i = 0; i < edges.length; i++) {
			if (union(uf, edges[i].s, edges[i].d))
				mst.add(edges[i]);
		}
		return mst;
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
	int s, d, w;

	public Edge(int s, int d, int w) {
		this.s = s;
		this.d = d;
		this.w = w;
	}
}