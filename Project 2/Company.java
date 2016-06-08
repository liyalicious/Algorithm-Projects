
public class Company implements Comparable<Company>{
	
	private String name;
	private int x;
	private int y;
	private int totalCost;
	
	public Company(){
		x = y = totalCost = 0;
	}

	public Company(String name_, int x_, int y_){
		this.name = name_;
		this.x = x_;
		this.y = y_;
		totalCost = 0;
	}

	public String getName() {
		return name;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
	}

	@Override
	public int compareTo(Company o) {
		if(this.totalCost == o.getTotalCost()){
			return this.name.compareTo(o.getName());
		}
		return (this.totalCost - o.getTotalCost());
	}
}
