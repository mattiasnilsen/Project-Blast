package projectblast.model.hero;


import java.util.ArrayList;
import java.util.List;

import projectblast.model.ICore;
import projectblast.model.Id;
import projectblast.model.Position;
import projectblast.model.Team;
import projectblast.model.explosive.Bomb;
import projectblast.model.explosive.Explosive;
import projectblast.model.powerups.*;




/**
 * 
 * @author Mattias Nilsen
 *
 */
public class Bomber extends Hero {
	private List<Explosive> bombs = new ArrayList<Explosive>();
	private List<Explosive> trash = new ArrayList<Explosive>();
    public Bomber(Position position,  int speed, Direction direction,  Team team) {
        super(position, speed, direction, team);
        setName(Id.BOMBER);
    }

    @Override
    public Explosive primaryAbility() {
    	if(getAmmo()<= 0){
			return null;
		}else{
			setAmmo(getAmmo()-1);
			Bomb bomb = new Bomb(new Position(snapToGrid(getX()) + getDirection().getX()*0, snapToGrid(getY()) + getDirection().getY()*0)  ,0 ,Direction.NORTH ,this);
			bombs.add(bomb);
			return bomb;
		}
    }

    @Override
    public ICore secondaryAbility() {
    	for(Explosive ex: bombs){
			ex.destroy();
		}
    	return null; //No ICore needed
       	//return new DetonatorCore(bombs);
    }

	@Override
	protected void addInitialPowerUps() {
		addPowerUp(new SpeedPowerUp());
		addPowerUp(new SpeedPowerUp());
		addPowerUp(new SpeedPowerUp());
		addPowerUp(new SpeedPowerUp());
		addPowerUp(new RangePowerUp());
		addPowerUp(new AmmoPowerUp());

		
	}
	
	public void update(){
		
		for(Explosive b : bombs){
			if(b.isDestroyed()){
				trash.add(b);
			}
		}
		bombs.removeAll(trash);
	}

}
