package projectblast.model;

import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class ExplosionCore implements ICore{
	private List<IBurst> parts;
	private int lifetime;
	
	public ExplosionCore(List<IBurst> l, int life){
		parts = l;
		lifetime = life;
	}
	
	public void tick(){
		lifetime--;
	}
	
	public boolean isDead(){
		return lifetime < 0;
	}
	
	public List<IBurst> getParts(){
		return parts;
	}
}
