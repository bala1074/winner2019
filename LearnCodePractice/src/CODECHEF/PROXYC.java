package CODECHEF;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

class PROXYC {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		int t = getInt(br.readLine());
		while (t-- > 0) {
			int n = getInt(br.readLine());
			String str = br.readLine().trim();
			int pc = 0, ac = 0;
			for (int i = 0; i < n; i++)
				if (str.charAt(i) == 'P')
					pc++;
				else
					ac++;
			int now = 100 * pc / n;
			if (now >= 75) {
				pw.println(0);
			} else {
				int req = 0;
				while (true) {
					now = 100 * (pc + req) / n;
					if (now < 75) {
						req++;
					} else
						break;
				}
				int _ans = req;
				for (int i = 2; i < n - 2; i++) {
					if (str.charAt(i) == 'A') {
						if ((str.charAt(i - 1) == 'P' || str.charAt(i - 2) == 'P')
								&& (str.charAt(i + 1) == 'P' || str.charAt(i + 2) == 'P'))
							req--;
					}
					if (req < 0)
						break;
				}
				pw.println(req <= 0 ? _ans : -1);
			}

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
