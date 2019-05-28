package CODECHEF;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

class CV {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

		int t = getInt(br.readLine());
		while (t-- > 0) {
			int n = getInt(br.readLine().trim());
			String str = br.readLine().trim();

			int count = 0;
			for (int i = 0; i < str.length() - 1; i++) {
				if ("aeiou".indexOf(str.charAt(i)) < 0 && "aeiou".indexOf(str.charAt(i + 1)) >= 0)
					count++;
			}
			pw.println(count);
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

	public static double getDouble(String str) {
		return Double.parseDouble(str.trim());
	}

	public static float getFloat(String str) {
		return Float.parseFloat(str.trim());
	}

}
