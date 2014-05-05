package projectblast.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Core implements ICore {

	private int lifeTime;
	private boolean isCreated;
	private Position startPos;
	private List<IBurst> parts;
	
	public Core(int lifeTime, Position startPos) {
		this.lifeTime = lifeTime;
		this.startPos = startPos;
		this.parts = new ArrayList<IBurst>();
	}
	
	@Override
	public void tick() {
		lifeTime--;
	}

	@Override
	public boolean isDead() {
		return lifeTime < 0;
	}

	@Override
	public List<IBurst> getParts() {
		return parts;
	}
	
	public void addPart(IBurst part) {
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
