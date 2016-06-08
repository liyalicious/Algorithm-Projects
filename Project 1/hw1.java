import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class hw1 {

	public static String commaDollar(String str) {
		String str2 = str.substring(0, str.length() - 3);
		if (str2.length() > 3) {
			for (int i = str2.length() - 3; i > 0; i = i - 3) {
				str2 = str2.substring(0, i) + ","
						+ str2.substring(i, str2.length());
			}
		}
		return "$" + str2 + str.substring(str.length() - 3, str.length());
	}

	public static void main(String[] args) throws Exception {

		String filename = args[0];
		File file = new File(filename);
		Scanner inputFile = new Scanner(file);

		while (inputFile.hasNextLine()) {
			// System.out.println("Please input amount: ");
			// Scanner input = new Scanner(System.in);
			String val = inputFile.nextLine();
			
			if (val.equals("-1.00")) {
				System.exit(0);
			}
			
			int num = Integer.parseInt(val.substring(val.length() - 2,
					val.length()));


			String a = commaDollar(val);
			System.out.print(a);

			while (num >= 25) {
				System.out.print(" Q");
				num -= 25;
			}

			while (num >= 10) {
				System.out.print(" D");
				num -= 10;
			}

			while (num >= 5) {
				System.out.print(" N");
				num -= 5;
			}

			while (num >= 1) {
				System.out.print(" P");
				num -= 1;
			}

			System.out.println();

		}
	}

}
