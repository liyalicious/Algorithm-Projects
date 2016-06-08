import java.util.ArrayList;


public class Person {
	
	private String name;
	private ArrayList<String> classes;
	
	public Person(String n, ArrayList<String> c) {
		this.name = n;
		this.classes = c;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<String> getClasses() {
		return classes;
	}

	public void setClasses(ArrayList<String> classes) {
		this.classes = classes;
	}

}
