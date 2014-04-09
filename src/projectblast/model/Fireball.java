package projectblast.model;


/**
 * 
 * @author A.Freudenthaler
 *
 */
public class Fireball extends Explosive {
	private Hero owner;
	
	public Fireball(Position position,  int speed,  Direction direction, Hero owner) {
		super(position,  speed, direction,  owner);
		startMove();
		setName("Fireball");
		this.owner = owner;
	}

	@Override
	public Explosion destroy() {
		if(owner.getCollisionBox().intersects(this.getCollisionBox())|| this.getCollisionBox().intersects(owner.getCollisionBox())){
			throw new NullPointerException("Fireball hit its owner");
		}else{
			return explode();
		}
		}

}
