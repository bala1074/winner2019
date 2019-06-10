package CODECHEF;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

class CHFING {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		int t = getInt(br.readLine());
		int MOD = 1000000007;
		while (t-- > 0) {
			String[] str = br.readLine().trim().split(" ");
			long n = getLong(str[0]), k = getLong(str[1]);
			pw.println(k - 1);
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
