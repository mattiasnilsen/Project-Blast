package projectblast.model.attribute;

import projectblast.model.helper.Constants;

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
        } else {
        	return NONE;
        }
	}
	
	public Direction opposite(){
	    Direction opposite = Direction.getDirection(getX() * -1, getY() * -1);
	    
		return opposite;
	}
	/**
	 * Returns the direction the entity at position 1 needs to face the entity at position 2.
	 * @param x1 The x coordinate of the first entity
	 * @param y1 The y coordinate of the first entity
	 * @param x2 The x coordinate of the second entity
	 * @param y2 The y coordinate of the second entity
	 * @return The direction entity 1 needs to face entity 2.
	 */
	public static Direction getRelativeDirection(int x1, int y1, int x2, int y2) {
		int x = x1 - x2;
		int y = y1 - y2;
		
		//The values needs to be rounded since a grid system is used, even if the entity is at the edge of the
		//north facing tile it should still return Direction.North
		if(x > -Constants.TILE_SIZE && x < Constants.TILE_SIZE) {
			x = 0;
		}
		
		if(y > -Constants.TILE_SIZE && y < Constants.TILE_SIZE) {
			y = 0;
		}
		
		if(x != 0)
			x = x / Math.abs(x);
		if(y != 0)
			y = y / Math.abs(y);
		
		return getDirection(x, y).opposite();
	}
	
	public static Direction getRelativeDirection(Position position1, Position position2) {
		return getRelativeDirection(position1.getX(), position1.getY(), position2.getX(), position2.getY());
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