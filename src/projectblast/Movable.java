package projectblast;

/**
 * 
 * @author franton
 * revised by Mattias Nilsen
 *	An interface for all Movable things in Project Blast.
 */
public interface Movable {
	
	public enum Direction {
		NORTH(0, -1),
		SOUTH(0, 1),
		WEST(-1, 0),
		EAST(1, 0);
		
		private int x;
		private int y;
		
		Direction(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public int getX() {
			return x;
		}
		
		public int getY() {
			return y;
		}
	}
	
	public void move(Direction direction);
	
	public void move(int dx, int dy);
	
	public void startMove(Direction direction);
	
	public void stopMove();
	
	public void place(int x, int y);
	
}
