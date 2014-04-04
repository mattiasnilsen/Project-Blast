package projectblast.view;

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
	
	public ImageDatabase(String textInput){
		
		init();
	}
	
	private void init() {
		
		
	}
	
	public Renderable getRenderable(Entity entity){
		String name = "hej";
				//TODO entity.getName();
		
		Renderable tmp;
		switch (name){
			case "Mage":
				Mage mage = (Mage) entity;
				tmp = getMageAnimation(mage);
				break;
			case "Bomber":
				Bomber bomber = (Bomber) entity;
				tmp = getBomberAnimation(bomber);
			case "Tower":
				Tower tower = (Tower) entity;
				tmp = getTowerImage(tower);
				break;
			case "Fireball":
				Fireball fireball = (Fireball) entity;
				tmp = getFireballImage(fireball);
				break;
				
			default:
				tmp = null; //TODO default renderable!
				break;

				
		}
		
		
		
		
		return tmp;
	}

	private Animation getMageAnimation(Mage mage) {
		Direction direction = mage.getDirection();
		Color TeamColor = mage.getTeam().getColor();
		Animation animation = null; //TODO default animation!
		Image image = null;
		try{
			if(direction.equals(Direction.EAST)){
				image = new Image("/data/image/SnowmanHeroRight.png");
			}else if(direction.equals(Direction.NORTH)){
				image = new Image("/data/image/SnowmanHeroUp.png");
			}else if(direction.equals(Direction.SOUTH)){
				image = new Image("/data/image/SnowmanHeroDown.png");
			}else if(direction.equals(Direction.WEST)){
				image = new Image("/data/image/SnowmanHeroLeft.png");	
			} 
		}catch (SlickException e) {
			e.printStackTrace();
		}
		image.setImageColor(TeamColor.r, TeamColor.g, TeamColor.b);
		animation = new Animation(new SpriteSheet(image, 32, 32), 1000);
		return animation;
	}
	
	private Animation getBomberAnimation(Bomber bomber) {
		Direction direction = bomber.getDirection();
		Color TeamColor = bomber.getTeam().getColor();
		Animation animation = null; //TODO default animation!
		Image image = null;
		try{
			if(direction.equals(Direction.EAST)){
				image = new Image("/data/image/SnowmanHeroRight.png");
			}else if(direction.equals(Direction.NORTH)){
				image = new Image("/data/image/SnowmanHeroUp.png");
			}else if(direction.equals(Direction.SOUTH)){
				image = new Image("/data/image/SnowmanHeroDown.png");
			}else if(direction.equals(Direction.WEST)){
				image = new Image("/data/image/SnowmanHeroLeft.png");	
			} 
		}catch (SlickException e) {
			e.printStackTrace();
		}
		image.setImageColor(TeamColor.r, TeamColor.g, TeamColor.b);
		animation = new Animation(new SpriteSheet(image, 32, 32), 1000);
		return animation;
	}

	private Image getFireballImage(Fireball fireball) {
		
		return null;
	}

	private Image getTowerImage(Tower tower) {
		
		return null;
	}
}
