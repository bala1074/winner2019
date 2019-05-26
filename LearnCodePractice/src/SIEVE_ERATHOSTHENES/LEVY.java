package SIEVE_ERATHOSTHENES;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;

class LEVY {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		int k = 10001;
		boolean isPrime[] = new boolean[k];
		int primes[] = new int[10001], ind = 0;
		Arrays.fill(isPrime, true);
		isPrime[0] = isPrime[1] = false;
		for (int i = 2; i < k; i++) {
			if (isPrime[i]) {
				primes[ind++] = i;
				for (int j = i * i; j < k; j += i)
					isPrime[j] = false;
			}
		}

		int ans[] = new int[k];
		for (int i = 0; i < ind; i++) {
			for (int j = 0; j < ind; j++) {
				int val = primes[i] + primes[j] * 2;
				if (val > 10000)
					break;
				ans[val]++;
			}
		}
		int t = Integer.parseInt(br.readLine().trim());
		while (t-- > 0) {
			pw.println(ans[Integer.parseInt(br.readLine().trim())]);
		}
		pw.flush();
		pw.close();
	}
}
