package Mock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;

class STACKS {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

		int t = Integer.parseInt(br.readLine().trim());
		while (t-- > 0) {
			int n = Integer.parseInt(br.readLine().trim());
			String in[] = br.readLine().trim().split(" ");
			int arr[] = new int[n], i = 0;
			for (String s : in)
				arr[i++] = Integer.parseInt(s.trim());

			int[] stack = new int[n];
			Arrays.fill(stack, Integer.MAX_VALUE);

			int stkind = 0, tillMax = 0;
			for (i = 0; i < n; i++) {
				if (tillMax > arr[i]) {
					boolean isv = false;
					tillMax = 0;
					for (int sti = 0; sti <= stkind; sti++) {
						if (stack[sti] > arr[i] && !isv) {
							stack[sti] = arr[i];
							isv = true;
						} else {
							tillMax = tillMax < stack[sti] ? stack[sti] : tillMax;
						}
					}

				} else {
					stack[++stkind] = arr[i];
					tillMax = arr[i];
				}
			}
			pw.print((stkind + 1) + " ");
			for (i = 0; i <= stkind; i++)
				pw.print(stack[i] + " ");
			pw.println();

		}
		pw.flush();
		pw.close();
	}
}
