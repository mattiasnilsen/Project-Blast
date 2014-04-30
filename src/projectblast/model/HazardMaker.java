package projectblast.model;

import java.util.List;

import org.newdawn.slick.geom.Rectangle;

public interface HazardMaker {
	
	public void tick();
	
	public boolean isDead();
	
	public boolean isCreated();
	
	public List<Paralyzer> getParts();
	
	public boolean step(Entity entity);
	
	public void create();
	
	public Position getNextPosition();
}
