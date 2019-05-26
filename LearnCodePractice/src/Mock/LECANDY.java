package Mock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

class LECANDY {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

		int t = Integer.parseInt(br.readLine().trim());
		while (t-- > 0) {
			String in[] = br.readLine().trim().split(" ");
			int n = Integer.parseInt(in[0].trim());
			int c = Integer.parseInt(in[1].trim());
			in = br.readLine().trim().split(" ");
			int sum = 0;
			for (String s : in)
				sum += Integer.parseInt(s.trim());

			if (sum <= c)
				pw.println("Yes");
			else
				pw.println("No");
		}
		pw.flush();
		pw.close();
	}
}
