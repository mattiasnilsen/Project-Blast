package projectblast.model;

import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class ExplosionCore implements Drawable{
	private List<Explosion> parts;
	private int lifetime;
	
	public ExplosionCore(List<Explosion> l, int life){
		parts = l;
		lifetime = life;
	}
	
	public void tick(){
		lifetime--;
	}
	
	public boolean isDead(){
		return lifetime <= 0;
	}
	
	public List<Explosion> getParts(){
		return parts;
	}
	

	@Override
	public void draw(Graphics g) {
		for (Explosion e: parts){
			e.draw(g);
		}
	}

	@Override
	public void draw(Graphics g, Color teamColor) {
		for (Explosion e: parts){
			e.draw(g,teamColor);
		}
	}
}
