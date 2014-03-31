package projectblast;

import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/**
 * 
 * @author Axel Savén Östebo
 *
 */
public class MapReader {
	private TiledMap map;
	
	MapReader(TiledMap map){
		this.map = map;
	}
	
	public List<Entity> createEntities(){
		List<Entity> list = new LinkedList<Entity>();
		
		//Step through the map and read property values
		for (int x = 0; x < map.getWidth(); x++){
			for (int y = 0; y < map.getHeight(); y++){
				//Fetch tile id and get its type, with air as default
				String prop = map.getTileProperty(map.getTileId(x, y, 0), "type", "air");
				if (!"air".equals(prop)){
					if ("block".equals(prop)){
						//TODO fix sprite here
						try {
							list.add(new SolidBlock(x*Constants.TILE_SIZE,y*Constants.TILE_SIZE,
									new Image("data/image/BlockRed.png")));
						} catch (SlickException e) {
							e.printStackTrace();
						}
					}
					if ("box".equals(prop)){
						//TODO fix sprite here
						try {
							list.add(new DestructibleBlock(x*Constants.TILE_SIZE,y*Constants.TILE_SIZE,
									new Image("data/image/BlockGreen.png")));
						} catch (SlickException e) {
							e.printStackTrace();
						}
					}
					if ("tower".equals(prop)){
						//TODO fix sprite here
						try {
							list.add(new Tower(x*Constants.TILE_SIZE,y*Constants.TILE_SIZE,
									new Image("data/image/Tower.png")));
						} catch (SlickException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		
		return list;
	}

}
