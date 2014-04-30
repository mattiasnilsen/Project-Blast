package projectblast.model;

import java.util.List;

public class StunBeam {
	private List<Paralyzer> parts;
	private int lifetime;
	
	public StunBeam(List<Paralyzer> l, int life){
		parts = l;
		lifetime = life;
	}
	
	public void tick(){
		lifetime--;
	}
	
	public boolean isDead(){
		return lifetime < 0;
	}
	
	public List<Paralyzer> getParts(){
		return parts;
	}
}
