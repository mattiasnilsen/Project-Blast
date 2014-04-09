package projectblast.model;

import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.tiled.TiledMap;

/**
 * 
 * @author Axel Savén Östebo
 *
 */
public class MapReader {
	
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
						list.add(new SolidBlock(new Position(x * Constants.TILE_SIZE, y * Constants.TILE_SIZE)));
						break;
					case "box":
						list.add(new DestructibleBlock(new Position(x * Constants.TILE_SIZE, y * Constants.TILE_SIZE)));
						break;
					case "tower":
						list.add(new Tower(new Position(x * Constants.TILE_SIZE, y * Constants.TILE_SIZE)));
						break;
					}
				}
			}
		}
		return list;
	}
}
