package projectblast.view;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import projectblast.model.ITitleModel;

public class TitleView {

	ITitleModel model;
	Image playGame;
	Image gameSettings;
	Image exitGame;
	Image arrow;
	Image player1;
	Image player2;
	
	public TitleView(ITitleModel model) {
		this.model = model;
	    try {
			playGame = new Image("data/image/PlayGame.png");
			gameSettings = new Image("data/image/GameSettings.png");
			exitGame = new Image("data/image/ExitGame.png");
			arrow = new Image("data/image/Arrow.png");
			player1 = new Image("data/image/SnowmanHeroDownV3.png");;
			player2 = new Image("data/image/SnowmanHeroDownV3.png");;
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void render(Graphics g) {
		g.drawString("Blasting fun!", 100, 50);
		playGame.draw(100, 100);
		gameSettings.draw(100, 200);
		exitGame.draw(100, 300);
		player1.draw(700, 100);
		player2.draw(1100, 100);
		arrow.draw((model.getSelectedColumn() + 1)*400, (model.getSelectedRow() + 1)*100);
	}
}
