package projectblast.model.helper;

import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.Color;

import projectblast.model.BlastModel;
import projectblast.model.Direction;
import projectblast.model.IBlastModel;
import projectblast.model.Player;
import projectblast.model.Team;
import projectblast.model.entity.hero.Brute;
import projectblast.model.entity.hero.Mage;

/**
 * @author Axel Savén Östebo
 * A class for simulating what later will be done before matches
 *
 */
public final class SimulatedOptions {
	private static IBlastModel model = null;
	//private static IBlastView view = null;

	/**
	 * Returns a simulated list of players. This will later be done by the users before matches.
	 * @return List
	 */
	private static List<Player> getSimulatedPlayerList(){
		List<Player> p = new LinkedList<Player>();
		
		p.add(new Player(new Mage(new Position(32, 635), Direction.NONE, new Team("Main", new Color(255,0,128), Team.Side.LEFT))));
		p.add(new Player(new Brute(new Position(1200, 32), Direction.NONE,  new Team("Secondary", new Color(0,255,128), Team.Side.RIGHT))));
		
		
		return p;
	}
	/**
	 * Returns a simulated model as a temporary source
	 * @return Model
	 */
	public static IBlastModel getSimulatedModel(){
		if (model == null){
			model = new BlastModel(getSimulatedPlayerList());
		}
		return model;
	}
	/**
	 * Returns a simulated view as a temporary source
	 * @return View
	 */
	/*public static IBlastView getSimulatedView(){
		if (view == null){
			view = new BlastView(getSimulatedModel());
		}
		return view;
	}*/

}