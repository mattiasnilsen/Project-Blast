package projectblast.model.entity;

public enum Id {
	BOMB,
	BOMBER,
	BRUTE,
	DESTRUCTIBLEBLOCK,
	DRONE,
	ENFORCER,
	EXPLOSION,
	FIREBALL,
	FIST,
	MAGE,
	SOLIDBLOCK,
	TOWER, 
	PARALYZER;
	
	public String getName(){
		return this.toString().charAt(0) + this.toString().substring(1).toLowerCase();
	}
}
