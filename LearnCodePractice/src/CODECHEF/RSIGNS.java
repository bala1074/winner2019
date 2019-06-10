package CODECHEF;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigInteger;

class RSIGNS {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		int t = getInt(br.readLine());
		while (t-- > 0) {
			int k = getInt(br.readLine());
			BigInteger MOD = BigInteger.valueOf(1000000007);
			pw.println((BigInteger.valueOf(2).modPow(BigInteger.valueOf(k - 1), MOD).multiply(BigInteger.valueOf(10)))
					.mod(MOD));
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