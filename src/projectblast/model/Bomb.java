package projectblast.model;


public class Bomb extends Explosive {
	private boolean isDestroyed = false;
	
	public Bomb(Position position, int speed, Direction direction,
			 Hero owner) {
		super(position, speed, direction, owner);
		setName("Bomb");
	}

	@Override
	public void destroy() {
		isDestroyed = true;	
	}
	
	public boolean isDestroyed(){
		return isDestroyed;
	}

}
