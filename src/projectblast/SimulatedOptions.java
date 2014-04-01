package projectblast;

import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import projectblast.model.BlastModel;
import projectblast.model.Bomber;
import projectblast.model.IBlastModel;
import projectblast.model.Mage;
import projectblast.model.Movable;
import projectblast.model.Player;
import projectblast.model.Team;
import projectblast.view.BlastView;
import projectblast.view.IBlastView;

/**
 * @author Axel Savén Östebo
 * A class for simulating what later will be done before matches
 *
 */
public final class SimulatedOptions {

	/**
	 * Returns a simulated list of players. This will later be done by the users before matches.
	 * @return List
	 */
	public static List<Player> getSimulatedPlayerList(){
		List<Player> p = new LinkedList<Player>();
		
		Animation[] animations = new Animation[4];
    	Image[] images = new Image[4];
    	try {
			images[0] = new Image("/data/image/SnowmanHeroDown.png");
			images[1] = new Image("/data/image/SnowmanHeroRight.png");
			images[2] = new Image("/data/image/SnowmanHeroUp.png");
			images[3] = new Image("/data/image/SnowmanHeroLeft.png");
			
		} catch (SlickException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
    	
    	animations[0] = new Animation(images, 1000);
    	
		p.add(new Player(new Mage(200, 200, images[0], 4, Movable.Direction.EAST, animations, new Team("bomb", Color.red))));
		p.add(new Player(new Bomber(100, 400, images[0], 4, Movable.Direction.WEST, animations, new Team("fire", Color.blue))));
		
		
		return p;
	}
	/**
	 * Returns a simulated model as a temporary source
	 * @return Model
	 */
	public static IBlastModel getSimulatedModel(){
		return new BlastModel(getSimulatedPlayerList());
	}
	/**
	 * Returns a simulated view as a temporary source
	 * @return View
	 */
	public static IBlastView getSimulatedView(){
		return new BlastView(getSimulatedModel());
	}

}
