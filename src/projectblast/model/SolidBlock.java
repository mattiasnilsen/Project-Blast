package projectblast.model;

import org.newdawn.slick.Image;
/**
 *
 * @author Alex Tao
 */
public class SolidBlock extends Block{
	
	public SolidBlock(Position position, Image sprite) {
		super(position, sprite);
		setName("SolidBlock");
	}

}
