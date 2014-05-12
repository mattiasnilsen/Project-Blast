package projectblast.model.core;

import java.util.ArrayList;
import java.util.List;

import projectblast.model.Position;
import projectblast.model.hazard.IHazard;

public abstract class Core implements ICore {

	private int lifeTime;
	private boolean isCreated;
	private Position startPos;
	private List<IHazard> parts;
	
	public Core(int lifeTime, Position startPos) {
		this.lifeTime = lifeTime;
		this.startPos = startPos;
		this.parts = new ArrayList<IHazard>();
	}
	
	@Override
	public void tick() {
		lifeTime--;
	}

	@Override
	public boolean isDead() {
		return lifeTime <= 0;
	}

	@Override
	public List<IHazard> getParts() {
		return parts;
	}
	
	public void addPart(IHazard part) {
		parts.add(part);
	}

	@Override
	public boolean isCreated() {
		return isCreated;
	}
	
	public void setCreated(boolean created) {
		isCreated = created;
	}
	
	public Position getStartingPosition() {
		return startPos;
	}
}
