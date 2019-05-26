package TREES;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

class TALCA {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine().trim());
		ArrayList<Integer>[] graph = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++)
			graph[i] = new ArrayList<>();
		for (int i = 1; i < n; i++) {
			String inp[] = br.readLine().trim().split(" ");
			int a = Integer.parseInt(inp[0].trim());
			int b = Integer.parseInt(inp[1].trim());
			graph[a].add(b);
			graph[b].add(a);
		}
		int t = Integer.parseInt(br.readLine().trim());
		while (t-- > 0) {
			String inp[] = br.readLine().trim().split(" ");
			int s = Integer.parseInt(inp[0].trim());
			int l = Integer.parseInt(inp[1].trim());
			int r = Integer.parseInt(inp[2].trim());

			int ans[] = new int[4], ansI = 0;

			// dfs 1 direciton
			Stack<Integer> stk = new Stack<>();
			stk.add(graph[s].get(0));
			HashSet<Integer> isv = new HashSet<>();
			isv.add(s);
			isv.add(graph[s].get(0));
			while (!stk.isEmpty()) {
				int now = stk.pop();
				if (now == l || now == r) {
					ans[ansI++] = now;
				}
				for (int i = 0; i < graph[now].size(); i++) {
					int nw = graph[now].get(i);
					if (!isv.contains(nw)) {
						stk.push(nw);
						isv.add(nw);
					}
				}
				if (ansI >= 2)
					break;
			}
			if (ansI == 2)
				pw.println(ans[0]);
			else if (ansI == 1)
				pw.println(s);
			else {
				// dfs 1 direciton
				stk = new Stack<>();
				stk.add(graph[s].get(1));
				isv = new HashSet<>();
				isv.add(s);
				isv.add(graph[s].get(1));
				while (!stk.isEmpty()) {
					int now = stk.pop();
					if (now == l || now == r) {
						ans[ansI++] = now;
					}
					for (int i = 0; i < graph[now].size(); i++) {
						int nw = graph[now].get(i);
						if (!isv.contains(nw)) {
							stk.push(nw);
							isv.add(nw);
						}
					}
					if (ansI > 0)
						break;
				}
				pw.println(ans[0]);
			}
		}
		pw.flush();
		pw.close();
	}
}