package projectblast.model;

/**
 * Possible directions an object can be moved.
 */
public enum Direction {
	NORTH(0, -1),
	SOUTH(0, 1),
	WEST(-1, 0),
	EAST(1, 0),
	NORTHWEST(-1, -1),
	NORTHEAST(1, -1),
	SOUTHWEST(-1, 1),
	SOUTHEAST(1, 1),
	NONE(0,0);
	
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
	
	public Direction clockwise(){
		if (this.equals(NORTH)){
			return NORTHEAST;
		} else if (this.equals(NORTHEAST)){
			return EAST;
		} else if (this.equals(EAST)){
			return SOUTHEAST;
		} else if (this.equals(SOUTHEAST)){
            return SOUTH;
        }  else if (this.equals(SOUTH)){
            return SOUTHWEST;
        }  else if (this.equals(SOUTHWEST)){
            return WEST;
        }  else if (this.equals(WEST)){
            return NORTHWEST;
        }  else if (this.equals(NORTHWEST)){
            return NORTH;
        } 
		return this;
	}
	
	public Direction opposite(){
	    Direction opposite = Direction.getDirection(getX() * -1, getY() * -1);
	    
		return opposite;
	}
	
	public static Direction getDirection(int x, int y) {
	    for(Direction dir : Direction.values()) {
	        if(dir.getX() == x && dir.getY() == y) {
	            return dir;
	        }
	    }
	    return NONE;
	}
}