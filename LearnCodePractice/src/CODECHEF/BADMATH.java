package CODECHEF;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

class BADMATH {

	// This is the simple example for power of brain, i tried a lot got stuck for
	// almost an hour by use complex stack RE, simple solution

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

		int t = Integer.parseInt(br.readLine().trim());
		while (t-- > 0) {
			String inp[] = br.readLine().trim().split(" ");
			int n = Integer.parseInt(inp[0].trim()), k = Integer.parseInt(inp[1].trim());
			String bry = br.readLine().trim();

			int low = Integer.parseInt(bry.replace('_', '0'), 2);
			int high = Integer.parseInt(bry.replace('_', '1'), 2);
			System.out.println((high / k) - (((low == 0 ? 1 : low) - 1) / k));

			int count = 0;
			for (; low <= high; low++)
				if (low > 0 && low % k == 0)
					count++;

			System.out.println(count);
			System.out.println(low + " " + high);
		}

	}

}
