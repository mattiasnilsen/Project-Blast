package projectblast;

/**
 * @author Axel Savén Östebo
 *
 */
public class CollisionBox {
	private int x, y;
	private final int width, height;
	
	public CollisionBox(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Returns true if this box intersects the other box b
	 * @param b
	 * @return
	 */
	public boolean intersects(CollisionBox b){
		if (b == null){
			return false;
		} else {
			return (x > b.getX() && x < b.getX()+b.getWidth()) && (y > b.getY() && y < b.getY()+b.getHeight());
		}
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}

	

	

	

}
