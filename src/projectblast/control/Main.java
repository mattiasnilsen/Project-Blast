package projectblast.control;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import projectblast.model.Constants;

public class Main {
	
	public static void main(String args[]) {
        try {
            AppGameContainer container = new AppGameContainer(new BlastGame("Project Blast!"));
            container.setDisplayMode(Constants.GAME_WIDTH, Constants.GAME_HEIGHT, false);
            container.setTargetFrameRate(Constants.FRAMERATE);
            container.start();
        } catch (SlickException e) { //TODO Proper exception handling. 
            e.printStackTrace();
        }
    }
}
