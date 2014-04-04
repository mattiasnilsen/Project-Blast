package projectblast.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.Renderable;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import projectblast.model.*;
import projectblast.model.Movable.Direction;

/**
 * 
 * @author A.Freudenthaler
 *
 */
public class ImageDatabase {
	
	private Map<String, String> images = new HashMap<String, String>();
	public ImageDatabase(){
		
		init();
	}
	
	private void init() {
		Scanner reader = null;
		try {
			reader = new Scanner(new File("data/image/images.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while(reader.hasNext()){
			String key = reader.next();
			String path = reader.next();
			images.put(key, path);
		}
		
	}
	
	public Renderable getRenderable(Entity entity){
		String name = entity.getName();
		
		Renderable tmp = null;
		switch (name){
			case "Mage": case "Bomber":
				Hero hero = (Hero) entity;
				tmp = getHeroAnimation(hero);
			case "Tower":
				Tower tower = (Tower) entity;
				tmp = getTowerImage(tower);
				break;
			case "Fireball": case "Bomb":
				Explosive explosive = (Explosive) entity;
				tmp = getExplosiveAnimation(explosive);
				break;
			case "SolidBlock":
				SolidBlock solidBlock = (SolidBlock) entity;
				tmp = getSolidBlockImage(solidBlock);
				break;
			case "DestructibleBlock":
				DestructibleBlock destructibleBlock = (DestructibleBlock) entity;
				tmp = getDestructibleBlockImage(destructibleBlock);
				break;
				
			default:
			try {
				tmp = new Image("data/image/Error.png");
			} catch (SlickException e) {
				e.printStackTrace();
			} 
				break;

				
		}
		
		
		
		
		return tmp;
	}

	private Image getSolidBlockImage(SolidBlock solidBlock) {
		String key = solidBlock.getName();
		Image image = null;
		try {
			 image = new Image(images.get(key));
		} catch (SlickException e) {	
			e.printStackTrace();
		}
		return image;
	}
	
	private Image getDestructibleBlockImage(DestructibleBlock destructibleBlock) {
		String key = destructibleBlock.getName();
		Image image = null;
		try {
			 image = new Image(images.get(key));
		} catch (SlickException e) {	
			e.printStackTrace();
		}
		return image;
	}

	private Animation getExplosiveAnimation(Explosive explosive) {
		Direction direction = explosive.getDirection();
		Color TeamColor = explosive.getOwner().getTeam().getColor();
		String key = explosive.getName();

			if(direction.equals(Direction.EAST)){
				key += "Right";
			}else if(direction.equals(Direction.NORTH)){
				key += "Up";
			}else if(direction.equals(Direction.SOUTH)){
				key += "Down";
			}else if(direction.equals(Direction.WEST)){
				key += "Left";
			} 
		Image image = null;
		try {
			image = new Image(images.get(key));
		} catch (SlickException e) {
			e.printStackTrace();
		}	
		image.setImageColor(TeamColor.r, TeamColor.g, TeamColor.b);
		 
		return new Animation(new SpriteSheet(image, 32, 32), 1000);
	}

	private Animation getHeroAnimation(Hero hero) {
		Direction direction = hero.getDirection();
		Color TeamColor = hero.getTeam().getColor();
		String key = hero.getName();

			if(direction.equals(Direction.EAST)){
				key += "Right";
			}else if(direction.equals(Direction.NORTH)){
				key += "Up";
			}else if(direction.equals(Direction.SOUTH)){
				key += "Down";
			}else if(direction.equals(Direction.WEST)){
				key += "Left";
			} 
		Image image = null;
		try {
			image = new Image(images.get(key));
		} catch (SlickException e) {
			e.printStackTrace();
		}	
		image.setImageColor(TeamColor.r, TeamColor.g, TeamColor.b);
		 
		return new Animation(new SpriteSheet(image, 32, 32), 1000);
	}
	

	private Image getTowerImage(Tower tower) {
		String key = tower.getName();
		Image image = null;
		try {
			 image = new Image(images.get(key));
		} catch (SlickException e) {	
			e.printStackTrace();
		}
		return image;
	}
}
