package projectblast.model.helper;

import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.tiled.TiledMap;

import projectblast.model.entity.DestructibleBlock;
import projectblast.model.entity.Entity;
import projectblast.model.entity.SolidBlock;
import projectblast.model.entity.Tower;
import projectblast.model.powerup.AmmoPowerUp;
import projectblast.model.powerup.IPowerUp;
import projectblast.model.powerup.RangePowerUp;
import projectblast.model.powerup.SpeedPowerUp;

/**
 * 
 * @author Axel Savén Östebo
 *
 */
public class MapReader {
	
	/**
	 * Returns a list of all entities in the specified map
	 * @param map - Map to search for entities
	 * @return A list of entities
	 */
	public static List<Entity> createEntities(TiledMap map){
		List<Entity> list = new LinkedList<Entity>();
		
		//Step through the map and read property values
		for (int x = 0; x < map.getWidth(); x++){
			for (int y = 0; y < map.getHeight(); y++){
				
				//Fetch tile id and get its type, with air as default
				String prop = map.getTileProperty(map.getTileId(x, y, 0), "type", "air");
				
				if (!"air".equals(prop)){
					switch(prop) {
					case "block":
						list.add(new SolidBlock(new Position(x * Constants.TILE_SIZE, y * Constants.TILE_SIZE + Constants.MAP_YOFFSET)));
						break;
					case "box":
						list.add(new DestructibleBlock(new Position(x * Constants.TILE_SIZE, y * Constants.TILE_SIZE + Constants.MAP_YOFFSET)));
						break;
					case "tower":
						String power = map.getTileProperty(map.getTileId(x, y, 0), "powerup", "speed");
						IPowerUp pow;
						switch (power){
						case "speed":
							pow = new SpeedPowerUp();
							break;
						case "range":
							pow = new RangePowerUp();
							break;
						case "ammo":
							pow = new AmmoPowerUp();
							break;
						default:
							throw new IllegalArgumentException(power + " is not a valid powerup.");
						}
						
						list.add(new Tower(pow,new Position(x * Constants.TILE_SIZE, y * Constants.TILE_SIZE + Constants.MAP_YOFFSET)));
						break;
					}
				}
				
			}
		}
		return list;
	}
	
	/**
	 * Returns the spawn point for the player with id id in the map map. The first id is 1.
	 * If no such spawn point exists, null is returned.
	 * @param map - The map to read
	 * @param id - Player id
	 * @return Position of spawn, if it exists
	 */
	public static Position getSpawnPoint(TiledMap map, int id){
		
		//Step through the map and read property values
		for (int x = 0; x < map.getWidth(); x++){
			for (int y = 0; y < map.getHeight(); y++){
				//Fetch tile id and get its type, with air as default
				String prop = map.getTileProperty(map.getTileId(x, y, 0), "spawn", "0");
				if (!"0".equals(prop)){
					return new Position(x * Constants.TILE_SIZE, y * Constants.TILE_SIZE);
				}
			}
		}
		return null;
	}
}
