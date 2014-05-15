package projectblast.view;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public abstract class Jukebox {
	
	
	public enum Sounds{
		EXPLOSION("Boom.ogg"), DEATH("WarningSiren.ogg"), PLACEBOMB("Noise.ogg"), FIREBALL("Noise.ogg");
		
		private final String folder = "data/sound/";
		private final String soundFile;
		
		private Sounds(String s){
			this.soundFile = s;
		}
		
		public Sound getSound(){
			try {
				return new Sound(folder + soundFile);
			} catch (SlickException e) {
				e.printStackTrace();
				return null;
			}
		}
	}
	
	public enum Musics{
		TITLE("DrumsInTheDeepV2.ogg"), SETTINGS("GarageTownDifferent.ogg"), BATTLE("DrumsInTheDeepV2.ogg");
		
		private final String folder = "data/music/";
		private final String musicFile;
		
		private Musics(String s){
			this.musicFile = s;
		}
		
		public Music getMusic(){
			try {
				return new Music(folder + musicFile);
			} catch (SlickException e) {
				e.printStackTrace();
				return null;
			}
		}
	}
}
