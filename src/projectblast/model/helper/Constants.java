package projectblast.model.helper;

import projectblast.model.title.ITitleModel.HeroChoice;

public final class Constants {
	public static final int MAP_XOFFSET		= 0;
	public static final int MAP_YOFFSET 	= 64;
	
	
	public static final int GAME_WIDTH     	= 1280;
	public static final int GAME_HEIGHT    	= 720 + 64;
	public static final int TILE_SIZE      	= 32;
	public static final int FRAMERATE      	= 60;
	public static final int TILE_AMOUNT_X  	= GAME_WIDTH/TILE_SIZE;
	public static final int TILE_AMOUNT_Y	= GAME_HEIGHT/TILE_SIZE;
	
	
	
	
	//Amount of frames explosions stay on screen
	public static final int EXPLOSION_TIME = 30;
	public static final int PARALYZER_TIME = 30;
	
	public static final int FIST_TIME = 20;
	
	public static final int STUN_DURATION = 60;
	
	public static final int BOMB_LIFE = 120;
	
	//Tower
	public static final int TOWER_STARTING_HEALTH = 3;
	public static final int TOWER_POWERUP_INTERVAL = 10 * FRAMERATE;
	//Tower cannon
	public static final int TOWER_FIRING_DELAY = 50;
	public static final int TOWER_RELOAD_DELAY = 120;
	public static final int TOWER_SEARCH_DELAY = 0;
	public static final int TOWER_FIRING_RANGE = 4;
	
	public static final int HERO_START_SPEED = 0;
	
	
	public static final String IMAGE_LIST_PATH = "data/image/images.txt";
	
	public static final int TITLE_STATE_ID = 1;
	public static final int GAME_STATE_ID = 2;
	
	public static final HeroChoice PLAYER_1_DEFAULT_HERO = HeroChoice.MAGE;
	public static final HeroChoice PLAYER_2_DEFAULT_HERO = HeroChoice.BRUTE;
	
	//Powerup
	public static final int AMMO_POWERUP_MODIFIER = 1;
	public static final int AMMO_MAX = 5;
	public static final int RANGE_POWERUP_MODIFIER = 1;
	public static final int RANGE_MAX = 5;
	public static final int SPEED_POWERUP_MODIFIER = 1;
	public static final int SPEED_MAX = 5;
}