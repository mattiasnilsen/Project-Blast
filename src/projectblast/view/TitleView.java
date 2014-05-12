package projectblast.view;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class TitleView {

	Image playGame;
	Image gameSettings;
	Image exitGame;
	Image arrow;
	
	public TitleView() {
	    try {
			playGame = new Image("data/image/PlayGame.png");
			gameSettings = new Image("data/image/GameSettings.png");
			exitGame = new Image("data/image/ExitGame.png");
			arrow = new Image("data/image/Arrow.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void render(Graphics g) {
		
	}
}
