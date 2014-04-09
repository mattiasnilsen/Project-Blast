package projectblast.model;

import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.Color;

import projectblast.view.BlastView;
import projectblast.view.IBlastView;

/**
 * @author Axel Savén Östebo
 * A class for simulating what later will be done before matches
 *
 */
public final class SimulatedOptions {
	private static IBlastModel model = null;
	private static IBlastView view = null;

	/**
	 * Returns a simulated list of players. This will later be done by the users before matches.
	 * @return List
	 */
	private static List<Player> getSimulatedPlayerList(){
		List<Player> p = new LinkedList<Player>();
		
		
	
		p.add(new Player(new Mage(new Position(200, 200), 4, Movable.Direction.EAST, new Team("bomb", Color.red))));
		p.add(new Player(new Bomber(new Position(100, 400), 4, Movable.Direction.WEST,  new Team("fire", Color.blue))));
		
		
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
	public static IBlastView getSimulatedView(){
		if (view == null){
			view = new BlastView(getSimulatedModel());
		}
		return view;
	}

}
