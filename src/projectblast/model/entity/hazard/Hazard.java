package projectblast.model.entity.hazard;

import org.newdawn.slick.geom.Rectangle;

import projectblast.model.attribute.Position;
import projectblast.model.entity.Entity;

public abstract class Hazard extends Entity{

	public Hazard(Position position, Rectangle box) {
		super(position, box);
		
	}

}
