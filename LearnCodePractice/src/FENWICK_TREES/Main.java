package FENWICK_TREES;

public class Main {

	public static void main(String[] args) {

		int n = 5;
		int arr[] = new int[] { 0, 1, 2, -3, 4, 5 };
		int BIT[] = new int[n + 1];
		for (int i = 1; i <= 5; i++)
			update(BIT, i, arr[i], n + 1);

		System.out.println(query(BIT, 4));

	}

	public static void update(int BIT[], int ind, int val, int size) {
		for (; ind < size; ind += ind & -ind)
			BIT[ind] += val;
	}

	public static int query(int BIT[], int ind) {
		int sum = 0;
		for (; ind > 0; ind -= ind & -ind)
			sum += BIT[ind];
		return sum;
	}
}
