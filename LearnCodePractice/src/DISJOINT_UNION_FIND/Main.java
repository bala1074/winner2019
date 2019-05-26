package DISJOINT_UNION_FIND;

public class Main {
	public static void main(String[] args) {

	}

	public static int find(int arr[], int val) {
		if (arr[val] == val)
			return val;
		return arr[val] = find(arr, arr[val]);
	}

	public static void union(int arr[], int u, int v) {
		int s1 = find(arr, u);
		int s2 = find(arr, v);
		if (s1 == s2)
			return;
		else
			arr[s1] = s2;
	}
}
