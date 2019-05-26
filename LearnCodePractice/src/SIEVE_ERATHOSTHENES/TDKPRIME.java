package SIEVE_ERATHOSTHENES;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.io.BufferedReader;

class TDKPRIME {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

		int primes[] = new int[5000001];
		int n = 86028122;
		boolean isPrime[] = new boolean[n];
		Arrays.fill(isPrime, true);
		isPrime[0] = isPrime[1] = false;
		int ind = 1;
		for (int i = 2; i < n; i++) {
			if (isPrime[i]) {
				primes[ind++] = i;
				for (int j = 2; i * j < n; j++)
					isPrime[i * j] = false;
			}
		}

		int t = Integer.parseInt(br.readLine().trim());
		while (t-- > 0) {
			int val = Integer.parseInt(br.readLine().trim());
			pw.println(primes[val]);
		}
		pw.flush();
		pw.close();
	}
}
