package projectblast.view;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import projectblast.model.title.ITitleModel;

public class TitleView {

	ITitleModel model;
	Image playGame;
	Image gameSettings;
	Image exitGame;
	Image arrow;
	Image arrowSelected;
	Image mage;
	Image bomber;
	Image brute;
	
	public TitleView(ITitleModel model) {
		this.model = model;
		
	    try {
			playGame = new Image("data/image/PlayGame.png");
			gameSettings = new Image("data/image/GameSettings.png");
			exitGame = new Image("data/image/ExitGame.png");
			arrow = new Image("data/image/Arrow.png");
			arrowSelected = new Image("data/image/ArrowSelected.png");
			mage = new Image("data/image/SnowmanHeroDownV3.png");
			bomber = new Image("data/image/SnowmanHeroDownV3.png");
			brute = new Image("data/image/KnowmanHeroDown.png");
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
		
		
		if(model.getPlayerHero(1).name().equals("MAGE")){
			mage.draw(700, 100);
		} else if(model.getPlayerHero(1).name().equals("BRUTE")){
			brute.draw(700,100);
		} else if(model.getPlayerHero(1).name().equals("BOMBER")){
			bomber.draw(700,100);
		}

		if(model.getPlayerHero(2).name().equals("MAGE")){
			mage.draw(1100, 100);
		} else if(model.getPlayerHero(2).name().equals("BRUTE")){
			brute.draw(1100, 100);
		} else if(model.getPlayerHero(2).name().equals("BOMBER")){
			bomber.draw(1100, 100);
		}
		
		if(model.isSelected()){
			arrowSelected.draw((model.getSelectedColumn() + 1)*400, (model.getSelectedRow() + 1)*100);
		}else{
			arrow.draw((model.getSelectedColumn() + 1)*400, (model.getSelectedRow() + 1)*100);
		}
		
		
		
	}
	
	
}
