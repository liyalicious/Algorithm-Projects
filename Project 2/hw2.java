import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class hw2 {

	public static void main(String[] args) throws Exception {
		String filename = args[0];
		File file = new File(filename);
		Scanner inputFile = new Scanner(file);

		while (inputFile.hasNextLine()) {

			int tcase = inputFile.nextInt();

			for (int i = 0; i < tcase; i++) {

				int total = Integer.parseInt(inputFile.next());
				int self = Integer.parseInt(inputFile.next());
				int comp = Integer.parseInt(inputFile.next());
				
				ArrayList<Company> list = new ArrayList<Company>();

				for (int j = 0; j < comp; j++) {

					Company c = new Company(inputFile.next(),
							Integer.parseInt(inputFile.next()),
							Integer.parseInt(inputFile.next()));
					
					int mon = 0;
					int temp = total;
					while(temp > self){
						if((c.getY() < ((int) Math.ceil((double)temp/2)) * c.getX()) && temp/2 >self){
							temp = temp/2;
							mon += c.getY();
						} else {
							mon += c.getX() * (temp - self);
							break;
						}
					}
					c.setTotalCost(mon);
					list.add(c);
				}
				
				Collections.sort(list);
				System.out.println("Case " + (i+1));
				for(Company c: list){
					System.out.println(c.getName() + " " + c.getTotalCost());
				}
			}
		}
	}

}
