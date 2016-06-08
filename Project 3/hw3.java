import java.io.File;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class hw3 {

	public static float dist(Point p1, Point p2) {
		return (float) (Math.sqrt((p1.getX() - p2.getX())
				* (p1.getX() - p2.getX()) + (p1.getY() - p2.getY())
				* (p1.getY() - p2.getY())));
	}

	public static float bruteForce(List<Point> p, int n) {
		float min = Float.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (dist(p.get(i), p.get(j)) < min) {
					min = dist(p.get(i), p.get(j));
				}
			}
		}

		return min;
	}

	public static float shortestInStrip(List<Point> strip, float d) {
		float min = d;

		Collections.sort(strip, new YComparator());

		for (int i = 0; i < strip.size(); i++) {
			for (int j = i + 1; j < strip.size()
					&& (strip.get(j).getY() - strip.get(i).getY()) < min; j++) {
				if (dist(strip.get(i), strip.get(j)) < min) {
					min = dist(strip.get(i), strip.get(j));
				}
			}
		}

		return min;
	}

	public static float closestDist(List<Point> p, int n) {
		if (n <= 3) {
			return bruteForce(p, n);
		}

		int middle = n / 2;
		Point midP = p.get(middle);

		float dLeftMin = closestDist(p.subList(0, middle), middle);
		float dRightMin = closestDist(p.subList(middle, n), n - middle);

		float d = 0;
		if (dLeftMin > dRightMin) {
			d = dRightMin;
		} else {
			d = dLeftMin;
		}

		ArrayList<Point> strip = new ArrayList<Point>();
		// int j = 0;
		for (int i = 0; i < n; i++) {
			if (Math.abs(p.get(i).getX() - midP.getX()) < d) {
				strip.add(p.get(i));
				// j++;
			}
		}

		float temp = 0;

		if (d < shortestInStrip(strip, d)) {
			temp = d;
		} else {
			temp = shortestInStrip(strip, d);
		}

		return temp;
	}

	public static float closest(ArrayList<Point> p, int n) {
		return closestDist(p, n);
	}

	public static void main(String[] args) throws Exception {

		String filename = args[0];
		File file = new File(filename);
		Scanner inputFile = new Scanner(file);

		while (inputFile.hasNextLine()) {
			int t = inputFile.nextInt();
			if (t == 0) {
				System.exit(0);
			}
			ArrayList<Point> points = new ArrayList<Point>();
			for (int i = 0; i < t; i++) {
				Point p = new Point(inputFile.nextFloat(),
						inputFile.nextFloat());
				points.add(p);
			}
			Collections.sort(points, new XComparator());
			float output = closest(points, t);
			String out = "";
			if (output >= 10001) {
				out = "infinity";
			} else {
				NumberFormat formatter = NumberFormat.getNumberInstance();
				formatter.setMinimumFractionDigits(4);
				formatter.setMaximumFractionDigits(4);
				out = formatter.format(output);
			}

			System.out.println(out);
		}
	}

}
