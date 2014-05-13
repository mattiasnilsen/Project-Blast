package projectblast.model.entity.hazard;

import org.newdawn.slick.geom.Rectangle;

import projectblast.model.entity.Entity;
import projectblast.model.helper.Position;

public abstract class Hazard extends Entity{

	public Hazard(Position position, Rectangle box) {
		super(position, box);
		
	}

}
