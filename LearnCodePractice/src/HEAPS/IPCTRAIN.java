package HEAPS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class IPCTRAIN {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		int t = Integer.parseInt(br.readLine().trim());
		while (t-- > 0) {
			String inp[] = br.readLine().trim().split(" ");
			int n = Integer.parseInt(inp[0].trim());
			int d = Integer.parseInt(inp[1].trim());

		}
	}
}

class Node {
	int start, teach, sad;

	public Node(int s, int t, int sd) {
		this.start = s;
		this.teach = t;
		this.sad = sd;
	}
}