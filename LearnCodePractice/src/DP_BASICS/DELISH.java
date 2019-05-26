package DP_BASICS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

class DELISH {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		long t = Integer.parseInt(br.readLine().trim());
		while (t-- > 0) {
			int n = Integer.parseInt(br.readLine().trim());
			long arr[] = new long[n];
			String inp[] = br.readLine().trim().split(" ");
			long r[] = new long[n];
			long sum = 0;
			for (int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(inp[i].trim());
				sum += arr[i];
				if (i == 0)
					r[i] = arr[i];
				else
					r[i] = r[i - 1] + arr[i];
			}
			long l[] = new long[n];
			for (int i = n - 1; i >= 0; i--) {
				if (i == n - 1)
					l[i] = arr[i];
				else
					l[i] = l[i + 1] + arr[i];
			}
			long max = 0, ssum = 0;
			for (int i = 0; i < n - 1; i++) {
				long pos1 = (int) Math.abs(l[i] - r[i] - arr[i]);
				long pos2 = (int) Math.abs(-l[i] + r[i] - arr[i]);
				// max = Math.max(max, );
				// System.out.println(max + " " + l[i] + " " + r[i] + " " + arr[i]);
			}
			pw.println(max);
		}
		pw.flush();
		pw.close();
	}
}
