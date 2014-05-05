package projectblast.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import projectblast.model.*;
import projectblast.model.Movable.Direction;
import projectblast.model.explosive.Explosive;
import projectblast.model.hero.Hero;

/**
 * 
 * @author Anton Freudenthaler
 *
 */
public class ImageDatabase {
	
	private Map<String, Image> images; //A map for images. The key should include objectname and state.
	public ImageDatabase(){
		this.images = new HashMap<String, Image>();
		init(); //Gets image path from a text file and puts it with proper key.
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
			String s = "null";
			try {
				s = reader.next();
				value = new Image(s);
			} catch (SlickException e) {
				throw new NullPointerException(s + " is not a valid image.");
			}
			images.put(key, value);
		}
		
	}
	
	public Animation getAnimation(Entity entity){
		Id name = entity.getName();
		Animation tmp = null;
		switch (name){
			case MAGE: case BOMBER: case BRUTE: case ENFORCER:
				Hero hero = (Hero) entity;
				tmp = getHeroAnimation(hero);
				break;
			case TOWER:
				Tower tower = (Tower) entity;
				tmp = getTowerImage(tower);
				break;
			case FIREBALL: case BOMB: case DRONE:
				Explosive explosive = (Explosive) entity;
				tmp = getExplosiveAnimation(explosive);
				break;
			case SOLIDBLOCK:
				SolidBlock solidBlock = (SolidBlock) entity;
				tmp = getSolidBlockImage(solidBlock);
				break;
			case DESTRUCTIBLEBLOCK:
				DestructibleBlock destructibleBlock = (DestructibleBlock) entity;
				tmp = getDestructibleBlockImage(destructibleBlock);
				break;
			case EXPLOSION:
				Explosion explosion = (Explosion) entity;
				tmp = getExplosionImage(explosion);
				break;
			case PARALYZER:
				Paralyzer paralyzer = (Paralyzer) entity;
				tmp = getParalyzerImage(paralyzer);
				break;
			default:
			try {
				//If entity name is something else than the above, an error image is used. 
				tmp = new Animation(new SpriteSheet("data/image/Error.png", 48, 48), 1000); 
			} catch (SlickException e) {
				e.printStackTrace();
			} 
				break;
		}

		return tmp;
	}

	private Animation getExplosionImage(Explosion explosion) {
		String key = explosion.getName().toString();
		Image image = images.get(key);
		return new Animation(new SpriteSheet(image, 48, 48), 1000);
	}

	private Animation getSolidBlockImage(SolidBlock solidBlock) {
		String key = solidBlock.getName().toString();
		Image image = images.get(key);
		return new Animation(new SpriteSheet(image, 48, 48), 1000);
	}
	
	private Animation getDestructibleBlockImage(DestructibleBlock destructibleBlock) {
		String key = destructibleBlock.getName().toString();
		Image image = images.get(key);
		
		return new Animation(new SpriteSheet(image, 48, 48), 1000);
	}

	private Animation getExplosiveAnimation(Explosive explosive) {
		Direction direction = explosive.getDirection();
		//Color teamColor = explosive.getOwner().getTeam().getColor();
		String key = explosive.getName().toString();
		key += getDirectionState(direction);
		
		Image image = images.get(key);
		//image.setImageColor(teamColor.r, tTeamColor.g, teamColor.b);
		 
		return new Animation(new SpriteSheet(image, 48, 48), 1000);
	}

	private Animation getHeroAnimation(Hero hero) {
		Direction direction = hero.getDirection();
		
		Color teamColor = hero.getTeam().getColor();
		String key = hero.getName().toString();

		key += getDirectionState(direction);
		Image image = images.get(key);
		
		
		image.setImageColor(teamColor.r , teamColor.g, teamColor.b);
		//Animation uses an images imageColor when drawing apparently
		Image[] test = new Image[1];
		test[0] = image.getSubImage(0, 0, 48, 48);
		test[0].setImageColor(teamColor.r , teamColor.g, teamColor.b);
		if(key == "MAGEEAST"){ //testing picture
			test[1] = image.getSubImage(32, 0, 48, 48);
			test[1].setImageColor(teamColor.r , teamColor.g, teamColor.b);
		}
		
		 return new Animation(test, 1);
		// For some reason Animation doesn't use spriteSheets imageColor when drawing
		//SpriteSheet spriteSheet = new SpriteSheet(image, 48, 48);
		//spriteSheet.setImageColor(teamColor.r , teamColor.g, teamColor.b);
		
		//return new Animation(spriteSheet, 1000);
	}
	

	private Animation getTowerImage(Tower tower) {
		String key = tower.getName().toString() + tower.getHealth();
		Image test[] = new Image[1];
		Image image = images.get(key);
		test[0] = image;
		Color teamColor = tower.getOwner().getColor();
		image.setImageColor(teamColor.r , teamColor.g, teamColor.b);
		return new Animation(test, 1);
	}
	
	private Animation getParalyzerImage(Paralyzer p) {
		String key = p.getName().toString();
		Image image = images.get(key);
		
		return new Animation(new SpriteSheet(image, 48, 48), 1000);
	}
	private String getDirectionState(Direction direction){
		String tmp;
		switch(direction.toString()){
			case "EAST":
				tmp = "EAST";
				break;
			case "NORTH": case "NORTHWEST": case "NORTHEAST": case "NONE":
				tmp = "NORTH";
				break;
			case "SOUTH": case "SOUTHWEST": case "SOUTHEAST":
				tmp = "SOUTH";
				break;
			case "WEST":
				tmp = "WEST";
				break;
			default:
				tmp = "ERROR";
				break;
		}
		return tmp; 
				
	}
	
}
