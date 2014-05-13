package projectblast.model.core;

import java.util.ArrayList;
import java.util.List;

import projectblast.model.Direction;
import projectblast.model.entity.hazard.Hazard;
import projectblast.model.helper.Position;

public abstract class Core implements ICore {

	private int lifeTime;
	private boolean isCreated;
	private Position startPos;
	private List<Hazard> parts;
	private List<Direction> directionList;
	
	public Core(int lifeTime, Position startPos, List<Direction> directionList) {
		this.lifeTime = lifeTime;
		this.startPos = startPos;
		this.parts = new ArrayList<Hazard>();
		this.setDirectionList(directionList);
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
	public List<Hazard> getParts() {
		return parts;
	}
	
	public void addPart(Hazard part) {
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

	public List<Direction> getDirectionList() {
		return directionList;
	}

	public void setDirectionList(List<Direction> directionList) {
		this.directionList = directionList;
	}
}
