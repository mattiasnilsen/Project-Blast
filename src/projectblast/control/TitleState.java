package projectblast.control;

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
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import projectblast.model.ITitleModel;
import projectblast.model.Movable.Direction;
import projectblast.model.title.TitleModel;
import projectblast.view.TitleView;

public class TitleState extends BasicGameState implements InputProviderListener {
	
	TitleView view;
	ITitleModel model;
	InputProvider provider;
	int choice = 0;
	StateBasedGame game;

	
	@Override
	public void init(GameContainer gc, StateBasedGame game)
			throws SlickException {
		provider = new InputProvider(gc.getInput());
	    provider.addListener(this);
	    provider.bindCommand(new KeyControl(Input.KEY_UP), new BasicCommand("UP"));
	    provider.bindCommand(new KeyControl(Input.KEY_DOWN), new BasicCommand("DOWN"));
	    provider.bindCommand(new KeyControl(Input.KEY_RIGHT), new BasicCommand("RIGHT"));
	    provider.bindCommand(new KeyControl(Input.KEY_LEFT), new BasicCommand("LEFT"));
	    provider.bindCommand(new KeyControl(Input.KEY_ENTER), new BasicCommand("ENTER"));
	    this.model = new TitleModel();
	    this.view = new TitleView(model);
	    this.game = game;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g)
			throws SlickException {
		view.render(g);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta)
			throws SlickException {
		model.update(gc, game, delta);
	}

	@Override
	public int getID() {
		return 0;
	}

	@Override
	public void controlPressed(Command c) {
		BasicCommand b = (BasicCommand) c;
		switch(b.getName()) {
	    case "UP":
	    	model.up();
	    	break;
	    case "DOWN":
	    	model.down();
	    	break;
	    case "LEFT":
	    	model.left();
	        break;
	    case "RIGHT":
	    	model.right();
	    	break;
	    case "ENTER":
	    	model.select();
	    	break;
	    default:
	    	
	    	break;
	    }
	}

	@Override
	public void controlReleased(Command c) {
		
		
	}

}
