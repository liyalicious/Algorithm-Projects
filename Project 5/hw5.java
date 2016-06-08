import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class hw5 {

	public static boolean bfs(int graph[][], int path[], int l) {

		Arrays.fill(path, -1);
		ArrayList<Integer> q = new ArrayList<Integer>();
		boolean[] visited = new boolean[l];
		Arrays.fill(visited, false);

		q.add(0);
		visited[0] = true;
		path[0] = -1;

		int curr, next;

		while (!q.isEmpty()) {
			curr = q.get(0);
			q.remove(0);
			next = 1;

			while (next < l) {
				if (!visited[next] && graph[curr][next] > 0) {
					path[next] = curr;
					q.add(next);
					visited[next] = true;
				}

				next++;
			}
		}

		return visited[l - 1] == true;
	}

	public static int fordFulkerson(int graph[][], int[] path, int l) {

		int u, v, pathFlow;
		int maxFlow = 0;

		while (bfs(graph, path, l)) {
			pathFlow = Integer.MAX_VALUE;
			for (v = l - 1; v != 0; v = path[v]) {
				u = path[v];
				pathFlow = Math.min(pathFlow, graph[u][v]);
			}

			for (v = l - 1; v != 0; v = path[v]) {
				u = path[v];
				graph[u][v] -= pathFlow;
				graph[v][u] += pathFlow;
			}

			maxFlow += pathFlow;
		}

		return maxFlow;
	}

	public static void main(String[] args) throws Exception {
		String filename = args[0];
		File file = new File(filename);
		Scanner inputFile = new Scanner(file);

		while (inputFile.hasNextLine()) {
			int r = inputFile.nextInt();
			int c = inputFile.nextInt();
			int n = inputFile.nextInt();

			if (r == 0 && c == 0 && n == 0) {
				System.exit(0);
			} else {
				ArrayList<Person> peo = new ArrayList<Person>();
				ArrayList<String> cl = new ArrayList<String>();
				String temp = inputFile.next();
				cl.add(inputFile.next());
				peo.add(new Person(temp, cl));
				for (int i = 0; i < r - 1; i++) {
					String tem = inputFile.next();
					boolean b = false;
					int index = 0;

					ArrayList<String> m = new ArrayList<String>();
					String tem2 = inputFile.next();
					m.add(tem2);

					Person linshi = new Person(tem, m);

					for (int j = 0; j < peo.size(); j++) {
						if (peo.get(j).getName().equals(tem)) {
							b = true;
							index = j;
						}
					}

					if (b == false) {
						peo.add(linshi);
					} else {
						Person pp = peo.get(index);
						ArrayList<String> tmp = pp.getClasses();
						if (tmp.indexOf(tem2) == -1) {
							tmp.add(tem2);
						}

						pp.setClasses(tmp);
					}
				}

				ArrayList<String> A = new ArrayList<String>();
				ArrayList<String> C = new ArrayList<String>();
				ArrayList<Integer> L = new ArrayList<Integer>();
				ArrayList<Integer> H = new ArrayList<Integer>();

				for (int k = 0; k < c; k++) {
					String t = inputFile.next();
					A.add(t);
					C.add(t);
					int t2 = inputFile.nextInt();
					L.add(t2);
				}

				Collections.sort(A);

				for (int q = 0; q < A.size(); q++) {
					H.add(L.get(C.indexOf(A.get(q))));
				}

				int size = peo.size();
				int l = size + c + 2;
				int[] pa = new int[l];
				int[][] graph = new int[l][l];

				for (int s = 1; s < size + 1; s++) {
					graph[0][s] = n;
				}

				for (Person p : peo) {
					Collections.sort(p.getClasses());
				}

				for (int v = 1; v < size + 1; v++) {
					ArrayList<String> nr = new ArrayList<String>();
					ArrayList<Integer> d = new ArrayList<Integer>();

					for (String w : A) {
						if (peo.get(v - 1).getClasses().indexOf(w) == -1) {
							nr.add(w);
						}
					}

					for (String str : nr) {
						int y = A.indexOf(str) + 1;
						d.add(y);
					}

					for (int x = size + 1; x < size + c + 1; x++) {
						if (d.indexOf(x - size) == -1) {
							graph[v][x] = 1;
						} else {
							graph[v][x] = 0;
						}
					}
				}

				for (int j = size + 1; j < size + c + 1; j++) {
					graph[j][l - 1] = H.get(j - size - 1);
				}
				
				if (fordFulkerson(graph, pa, l) == size * n) {
					System.out.println("YES");
				} else {
					System.out.println("NO");
				}

			}
		}

	}

}
