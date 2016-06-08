
public class Point {
	private float x;
	private float y;
	
	public Point(){
		x = y = 0;
	}
	
	public Point(float x_, float y_){
		this.x = x_;
		this.y = y_;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

}