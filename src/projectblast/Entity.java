package projectblast;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Entity implements Drawable {
	private Image sprite;
	private int x,y;

	public Entity(int x, int y, Image sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(sprite, x, y);
	}

	public Image getSprite() {
		return sprite;
	}

	public void setSprite(Image sprite) {
		this.sprite = sprite;
	}
	
	public boolean isOnGrid(){
		//TODO Remove hard code
		final int GRID = 32;
		return (x%GRID == 0 && y%GRID == 0);
	}

}
