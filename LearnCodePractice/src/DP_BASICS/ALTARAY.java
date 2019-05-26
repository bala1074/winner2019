package DP_BASICS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

class ALTARAY {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		int t = Integer.parseInt(br.readLine().trim());
		while (t-- > 0) {
			int n = Integer.parseInt(br.readLine().trim());
			int arr[] = new int[n];
			String inp[] = br.readLine().trim().split(" ");
			for (int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(inp[i].trim());
			}
			int count = 1;
			for (int i = 0; i < n; i++) {
				if (count > 1) {
					pw.print(count + " ");
				} else {
					int c = 1;
					for (int j = i; j < n - 1; j++) {
						if ((arr[j] > 0 && arr[j + 1] < 0) || (arr[j] < 0 && arr[j + 1] > 0)) {
							c++;
						} else
							break;
					}
					count = c;
					pw.print(count + " ");
				}
				count--;
			}
			pw.println();
		}
		pw.flush();
		pw.close();
	}
}
