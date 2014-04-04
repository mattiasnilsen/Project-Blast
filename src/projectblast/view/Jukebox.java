package projectblast.view;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class Jukebox {
	private static boolean initialized = false;
	private static Sound SOUND_EXPLOSION, SOUND_DEATH, SOUND_PLACEBOMB, SOUND_FIREBALL;
	private static Music MUSIC_TITLE, MUSIC_SETTINGS, MUSIC_BATTLE;
	
	public static void playSound(Sound s){
		if (!initialized){
			initialize();
		}
	}
	
	private static void initialize(){
		initialized = true;
		try {
			//Sound
			SOUND_EXPLOSION = new Sound("data/sound/Boom.ogg");
			SOUND_DEATH     = new Sound("data/sound/Boom.ogg");
			SOUND_PLACEBOMB = new Sound("data/sound/Noise.ogg");
			SOUND_FIREBALL  = new Sound("data/sound/Noise.ogg");
			
			//Music
			MUSIC_TITLE    = new Music("data/music/GarageTownDifferent.ogg");
			MUSIC_SETTINGS = new Music("data/music/GarageTownDifferent.ogg");
			MUSIC_BATTLE   = new Music("data/music/DrumsInTheDeepV2.ogg");
			
			
		} catch (SlickException e) {
			System.out.println("One or more sounds not found!");
			e.printStackTrace();
		}
	}
	
}
