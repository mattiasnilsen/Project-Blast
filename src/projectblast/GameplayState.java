package projectblast;


import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.command.BasicCommand;
import org.newdawn.slick.command.Command;
import org.newdawn.slick.command.InputProvider;
import org.newdawn.slick.command.InputProviderListener;
import org.newdawn.slick.command.KeyControl;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

/**
 * @author Axel Savén Östebo
 * revised by Mattias Nilsen
 * revised by Axel Savén Östebo - Added entity drawing using Maps
 */
public class GameplayState extends BasicGameState implements InputProviderListener {

	private Hero hero;
	private BlastModel model;
	
	private List<Entity> entities;
	
    public GameplayState()  {
    	 ArrayList<Player> players = new ArrayList();
         players.add(new Player(hero));
         
         model = new BlastModel(players);
    	Animation[] animations = new Animation[4];
    	Image[] images = new Image[4];
    	try {
			images[0] = new Image("/data/image/SnowmanHeroDown.png");
			images[1] = new Image("/data/image/SnowmanHeroRight.png");
			images[2] = new Image("/data/image/SnowmanHeroUp.png");
			images[3] = new Image("/data/image/SnowmanHeroLeft.png");
			
		} catch (SlickException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	animations[0] = new Animation(images, 1000);
        try {
			hero = new Mage(200, 200, new Image("/data/image/SnowmanHeroDown.png"), 4, Movable.Direction.EAST, animations, new Team("bomb", Color.red));
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        MapReader m;
		try {
			m = new MapReader(new TiledMap("data/map/Map.tmx"));
			entities = m.createEntities();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    

    }
    
	@Override
	public void init(GameContainer gc, StateBasedGame game)
			throws SlickException {
	    
	    InputProvider provider = new InputProvider(gc.getInput());
	    provider.addListener(this);
	    provider.bindCommand(new KeyControl(Input.KEY_W), new BasicCommand("up"));
	    provider.bindCommand(new KeyControl(Input.KEY_S), new BasicCommand("down"));
	    provider.bindCommand(new KeyControl(Input.KEY_A), new BasicCommand("left"));
	    provider.bindCommand(new KeyControl(Input.KEY_D), new BasicCommand("right"));
	    provider.bindCommand(new KeyControl(Input.KEY_Q), new BasicCommand("primary"));
	    provider.bindCommand(new KeyControl(Input.KEY_E), new BasicCommand("secondary"));
	    
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g)
			throws SlickException {
		// TODO Remove hardcoding
	    
		//Draw all entities
	    for (Entity e: entities){
	    	e.draw(g);
	    }
	    
	    //Draw the hero
	    hero.draw(g, Color.red);
	    
	    //Draw the test
	    g.setColor(Color.red);
	    g.drawString("" + isFree(hero.getCollisionBox()), hero.getX(), hero.getY() + 32);
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta)
			throws SlickException {
		//TODO remove hardcoding
		hero.update();
		Rectangle test = new Rectangle(hero.getX() + hero.getDirection().getX(),hero.getY() + hero.getDirection().getY(),32,32);
		
	}

	public boolean isFree(Rectangle r){
		
		for (Entity e: entities){
	    	if (e.getCollisionBox().intersects(r)){
	    		return false;
	    	}
	    }
		return true;
	}
	
	
	@Override
	public int getID() {
		//This is the identifier ID for the gameplay state
		return 1;
	}

    @Override
    public void controlPressed(Command command) {
    	BasicCommand bCommand = (BasicCommand)command;
    	//TODO Change to switch case.
    	if(bCommand.getName().equals("up")) {
    		hero.startMove(Movable.Direction.NORTH);
    	} else if(bCommand.getName().equals("down")) {
    		hero.startMove(Movable.Direction.SOUTH);
    	} else if(bCommand.getName().equals("left")) {
    		hero.startMove(Movable.Direction.WEST);
    	} else if(bCommand.getName().equals("right")) {
    		hero.startMove(Movable.Direction.EAST);
    	} else if(bCommand.getName().equals("primary")){
    		hero.primaryAbility();
    	} else if(bCommand.getName().equals("secondary")){
    		hero.secondaryAbility();
    	}
    }

    @Override
    public void controlReleased(Command command) {
    	BasicCommand bCommand = (BasicCommand)command;
    	//TODO Change to switch case.
    	if(bCommand.getName().equals("up")) {
    		hero.stopMove(Movable.Direction.NORTH);
    	} else if(bCommand.getName().equals("down")) {
    		hero.stopMove(Movable.Direction.SOUTH);
    	} else if(bCommand.getName().equals("left")) {
    		hero.stopMove(Movable.Direction.WEST);
    	} else if(bCommand.getName().equals("right")) {
    		hero.stopMove(Movable.Direction.EAST);
    	} 
    }
}
