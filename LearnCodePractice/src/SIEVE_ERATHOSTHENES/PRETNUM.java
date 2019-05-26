package SIEVE_ERATHOSTHENES;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

class PRETNUM {

	public static boolean isPrime(long val) {
		if (val <= 1)
			return false;
		if (val == 2)
			return true;
		long sq = (long) Math.sqrt(val);
		for (int i = 2; i <= sq; i++) {
			if (val % i == 0)
				return false;
		}
		return true;

	}

	public static void main(String[] args) throws Exception {

		System.out.println(fast_exp(10, 4));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

		int t = Integer.parseInt(br.readLine().trim());
		while (t-- > 0) {
			String str[] = br.readLine().trim().split(" ");
			long l = Long.parseLong(str[0].trim());
			long r = Long.parseLong(str[1].trim());

			int count = 0;
			for (; l <= r; l++) {
				if (l == 1)
					continue;
				if (isPrime(l))
					count++;
				else {
					long ll = l;
					boolean cc = false;
					long sq = (long) Math.sqrt(ll);
					for (int i = 2; i <= sq; i++) {
						if (cc)
							break;
						if (ll % i == 0) {
							cc = true;
							int inc = 0;
							while (ll % i == 0) {
								ll = ll / i;
								inc++;
							}
						}
					}
				}
				// System.out.println(l + " " + count);
			}
			pw.println(count);
		}
		pw.flush();
		pw.close();
	}

	static long MOD = 1000000007;

	public static long fast_exp(long base, int exp) {
		long res = 1;
		while (exp > 0) {
			if (exp % 2 == 1)
				res = (res * base);
			base = (base * base);
			exp /= 2;
			System.out.println(res + " " + base + " " + exp);
		}
		return res;
	}
}

// 5 1,5