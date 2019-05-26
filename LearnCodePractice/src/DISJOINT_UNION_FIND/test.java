package DISJOINT_UNION_FIND;

import java.util.Arrays;
import java.util.HashSet;

public class test {
	public static void main(String[] args) {

		int arr[] = new int[] { 0, 1, 2, 3, 4, 5 };

		System.out.println(find(arr, 2, null));
		union(arr, 2, 3);
		union(arr, 1, 5);
		union(arr, 1, 3);
		System.out.println(Arrays.toString(arr));
		int count = 0;
		HashSet isv = new HashSet<>();
		HashSet<Integer> sets = new HashSet<>();
		for (int i = 1; i <= 5; i++) {
			sets.add(find(arr, i, isv));
		}
		System.out.println(sets + " " + sets.size());

	}

	public static void union(int arr[], int u, int v) {
		int s1 = find(arr, u, null);
		int s2 = find(arr, v, null);
		arr[s1] = s2;
		find(arr, u, null);
	}

	public static int find(int arr[], int ind, HashSet isv) {
		if (isv != null)
			isv.add(ind);
		if (arr[ind] == ind)
			return ind;
		return arr[ind] = find(arr, arr[ind], isv);
	}
}
