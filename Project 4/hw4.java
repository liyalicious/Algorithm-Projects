import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class hw4 {

	private static ArrayList<ArrayList<Integer>> table;
	private static int max;

	public static int dp(int i, int j, int R, int C, ArrayList<ArrayList<Integer>> grid) {

		if (table.get(i).get(j) != 0) {
			if (table.get(i).get(j) > max) {
				max = table.get(i).get(j);
			}
			return table.get(i).get(j);
		}

		int up = 0, down = 0, left = 0, right = 0;

		if (i > 0 && grid.get(i).get(j) < grid.get(i - 1).get(j)) {
			up = dp(i - 1, j, R, C, grid);
		}

		if (i < R - 1 && grid.get(i).get(j) < grid.get(i + 1).get(j)) {
			down = dp(i + 1, j, R, C, grid);
		}

		if (j > 0 && grid.get(i).get(j) < grid.get(i).get(j - 1)) {
			left = dp(i, j - 1, R, C, grid);
		}

		if (j < C - 1 && grid.get(i).get(j) < grid.get(i).get(j + 1)) {
			right = dp(i, j + 1, R, C, grid);
		}

		table.get(i)
				.set(j, Math.max(Math.max(up, down), Math.max(left, right)) + 1);
		if (table.get(i).get(j) > max) {
			max = table.get(i).get(j);
		}
		return table.get(i).get(j);
	}

	public static void main(String[] args) throws Exception {
		String filename = args[0];
		File file = new File(filename);
		Scanner inputFile = new Scanner(file);

		while (inputFile.hasNextLine()) {
			int t = inputFile.nextInt();

			for (int i = 0; i < t; i++) {
				String city = inputFile.next();
				int R = inputFile.nextInt();
				int C = inputFile.nextInt();
				max = 0;
				
				ArrayList<ArrayList<Integer>> g = new ArrayList<ArrayList<Integer>>(R);
				for (int j = 0; j < R; j++) {
					ArrayList<Integer> m = new ArrayList<Integer>(C);
					for (int k = 0; k < C; k++) {
						m.add(inputFile.nextInt());
					}
					g.add(m);
				}
				
				table = new ArrayList<ArrayList<Integer>>(R);
				for (int j = 0; j < R; j++) {
					ArrayList<Integer> n = new ArrayList<Integer>(C);
					for (int k = 0; k < C; k++) {
						n.add(0);
					}
					table.add(n);
				}
				
				for (int j = 0; j < R; j++) {
					for (int k = 0; k < C; k++) {
						dp(j, k, R, C, g);
					}
				}

				System.out.println(city + ": " + max);
			}
		}

	}

}
