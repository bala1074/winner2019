package CODECHEF;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

class KS2 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		int t = getInt(br.readLine());
		while (t-- > 0) {
			String inp = br.readLine().trim();
			int sum = 0;
			for (int i = 0; i < inp.length(); i++) {
				sum += (inp.charAt(i) - '0');
			}
			int val = (int) Math.ceil(1.0 * sum / 10) * 10;
			pw.println(inp + "" + (val - sum));
		}
		pw.flush();
		pw.close();
	}

	public static int getInt(String str) {
		return Integer.parseInt(str.trim());
	}

	public static long getLong(String str) {
		return Long.parseLong(str.trim());
	}

	public static Double getDouble(String str) {
		return Double.parseDouble(str.trim());
	}
}
