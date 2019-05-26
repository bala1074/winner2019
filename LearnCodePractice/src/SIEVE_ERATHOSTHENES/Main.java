package SIEVE_ERATHOSTHENES;

import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
		sieve();
	}

	public static void sieve() {
		int n = 1000;
		boolean isPrime[] = new boolean[n];
		Arrays.fill(isPrime, true);
		isPrime[0] = isPrime[1] = false;
		for (int i = 2; i < n; i++) {
			if (isPrime[i])
				for (int j = 2; i * j < n; j++)
					isPrime[i * j] = false;
		}
		System.out.println(Arrays.toString(isPrime));
	}

}
