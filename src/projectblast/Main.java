package projectblast;

import org.newdawn.slick.AppGameContainer;

public class Main {
	public static void main(String args[]) {
        try {
            AppGameContainer container = new AppGameContainer(new BlastGame("Project Blast!"));
            container.setDisplayMode(Constants.GAME_WIDTH, Constants.GAME_HEIGHT, false);
            container.setTargetFrameRate(Constants.FRAMERATE);
            container.start();
        } catch (Exception e) { //TODO Proper exception handling. 
            
        }
    }
}
