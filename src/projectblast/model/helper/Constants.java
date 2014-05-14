package projectblast.model.helper;

public final class Constants {
	public static final int GAME_WIDTH     = 1280;
	public static final int GAME_HEIGHT    = 720 + 64;
	public static final int TILE_SIZE      = 32;
	public static final int FRAMERATE      = 60;
	
	public static final int MAP_YOFFSET 	= 64;
	public static final int MAP_XOFFSET		= 0;
	
	//Amount of frames explosions stay on screen
	public static final int EXPLOSION_TIME = 30;
	public static final int PARALYZER_TIME = 30;
	
	public static final int STUN_DURATION = 60;
	
	public static final int TOWER_STARTING_HEALTH = 3;
	public static final int TOWER_POWERUP_INTERVAL = 10 * FRAMERATE;
	
	public static final int HERO_START_SPEED = 0;
	
	
	public static final String IMAGE_LIST_PATH = "data/image/images.txt";
}