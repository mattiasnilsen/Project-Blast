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
	
	private Map<String, Image> images;
	public ImageDatabase(){
		this.images = new HashMap<String, Image>();
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
			Image value = null;
			try {
				value = new Image(reader.next());
			} catch (SlickException e) {
			
				e.printStackTrace();
			}
			images.put(key, value);
		}
		
	}
	
	public Animation getAnimation(Entity entity){
		String name = entity.getName();
		
		Animation tmp = null;
		switch (name){
			case "Mage": case "Bomber":
				Hero hero = (Hero) entity;
				tmp = getHeroAnimation(hero);
				break;
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
				tmp = new Animation(new SpriteSheet("data/image/Error.png", 32, 32), 1000);
			} catch (SlickException e) {
				e.printStackTrace();
			} 
				break;

				
		}
		
		
		
		
		return tmp;
	}

	private Animation getSolidBlockImage(SolidBlock solidBlock) {
		String key = solidBlock.getName();
		Image image = images.get(key);
		
		return new Animation(new SpriteSheet(image, 32, 32), 1000);
	}
	
	private Animation getDestructibleBlockImage(DestructibleBlock destructibleBlock) {
		String key = destructibleBlock.getName();
		Image image = images.get(key);
		
		return new Animation(new SpriteSheet(image, 32, 32), 1000);
	}

	private Animation getExplosiveAnimation(Explosive explosive) {
		Direction direction = explosive.getDirection();
		//Color TeamColor = explosive.getOwner().getTeam().getColor();
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
			Image image = images.get(key);
		//image.setImageColor(TeamColor.r, TeamColor.g, TeamColor.b);
		 
		return new Animation(new SpriteSheet(image, 32, 32), 1000);
	}

	private Animation getHeroAnimation(Hero hero) {
		Direction direction = hero.getDirection();
		//Color TeamColor = hero.getTeam().getColor();
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
			Image image = images.get(key);
		//image.setImageColor(TeamColor.r, TeamColor.g, TeamColor.b);
		 
		return new Animation(new SpriteSheet(image, 32, 32), 1000);
	}
	

	private Animation getTowerImage(Tower tower) {
		String key = tower.getName();
		Image image = images.get(key);
		return new Animation(new SpriteSheet(image, 32, 32), 1000);
	}
}
