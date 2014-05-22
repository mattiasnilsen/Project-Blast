package projectblast.model.entity.hero;

import projectblast.model.attribute.Direction;
import projectblast.model.attribute.Position;
import projectblast.model.attribute.Team;
import projectblast.model.core.ICore;
import projectblast.model.entity.Id;
import projectblast.model.entity.explosive.Explosive;

public class Enforcer extends Hero{

	public Enforcer(Position position, Direction direction, Team team) {
		super(position, direction, team);
		setName(Id.ENFORCER);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void addInitialPowerUps() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Explosive primaryAbility() {
		if(getAmmo() != 0){
		return null;
		}
		return null;
	}

	@Override
	public ICore secondaryAbility() {
		// TODO Auto-generated method stub
		return null;
	}

}
