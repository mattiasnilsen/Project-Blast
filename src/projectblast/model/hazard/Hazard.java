package projectblast.model.hazard;

import org.newdawn.slick.geom.Rectangle;

import projectblast.model.Entity;
import projectblast.model.Position;

public abstract class Hazard extends Entity{

	public Hazard(Position position, Rectangle box) {
		super(position, box);
		
	}

}
