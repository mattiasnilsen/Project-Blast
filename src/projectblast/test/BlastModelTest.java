package projectblast.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.newdawn.slick.Color;




import projectblast.model.BlastModel;
import projectblast.model.Direction;
import projectblast.model.Player;
import projectblast.model.Team;
import projectblast.model.Team.Side;
import projectblast.model.entity.Block;
import projectblast.model.entity.SolidBlock;
import projectblast.model.entity.hero.Brute;
import projectblast.model.entity.hero.Hero;
import projectblast.model.entity.hero.Mage;
import projectblast.model.helper.Options;
import projectblast.model.helper.Position;

public class BlastModelTest {
	
	
	@Test
	public void testModel(){
		
	
	}
	
	@Test
	public void testSnapToGrid() {
		Position pos = new Position(142,193);
		pos = BlastModel.snapToGrid(pos);
		assertTrue(pos.getX()%32 == 0);
		assertTrue(pos.getY()%32 == 0);
		
		pos = new Position(12,12);
		pos = BlastModel.snapToGrid(pos);
		assertTrue(pos.getY()%32 == 0);
		assertTrue(pos.getY()%32 == 0);
	}
	@Test
	public void testPrimary(){
		
	}
	@Test
	public void testMovePlayer(){
		List<Player> players = new ArrayList<Player>();
		Position pos = new Position(1,1);
		Direction dir = Direction.EAST;
		Team team = new Team("Test", Color.red, Side.LEFT );
		Hero mage = new Mage(pos,dir,team);
		Player player = new Player(mage);
		players.add(player);
		
		BlastModel model = new BlastModel(players);
		assertTrue(player.getHero().getTeam().getColor().equals(Color.red));
		assertTrue(player.getHero().getTeam().getSide().equals(Side.LEFT));
		assertTrue(player.getHero().getTeam().toString().equals(player.getHero().getTeam().getColor().toString() + " "+ "Test"));
		assertFalse(player.getHero().isMoving());
		model.movePlayer(1, Direction.NONE);
		assertTrue(player.getHero().isMoving());
		model.stopPlayer(1);
		assertFalse(player.getHero().isMoving());
		
		
		
		int xPos = mage.getX();
		model.movePlayer(1, Direction.EAST);
		
		mage.update();
		assertTrue(mage.getX() != xPos);
		assertTrue(mage.getX() == xPos+4);
		
		xPos = mage.getX();
		model.movePlayer(1, Direction.WEST);
		mage.update();
		assertTrue(mage.getX() == xPos-4);
		
		int yPos = mage.getY();
		model.movePlayer(1, Direction.NORTH);
		mage.update();
		assertTrue(mage.getY() == yPos-4);
		
		yPos = mage.getY();
		model.movePlayer(1, Direction.SOUTH);
		mage.update();
		assertTrue(mage.getY() == yPos+4);

		
		xPos = mage.getX();
		yPos = mage.getY();
		Position magePos = new Position(xPos, yPos);
		Hero brute = new Brute(magePos,dir,team);
		players.add(new Player(brute));
		assertTrue(mage.getPosition().equals(brute.getPosition()));
		
		model.movePlayer(2, Direction.EAST);
		brute.update();
		assertTrue(mage.getX() != brute.getX());
		assertTrue(mage.getY() == brute.getY());
		
		model.movePlayer(2, Direction.NORTH);
		brute.update();
		assertTrue(mage.getX() != brute.getX());
		assertTrue(mage.getY() != brute.getY());
	}
	@Test
	public void testIsFree(){
		List<Player> players = new ArrayList<Player>();
		Position pos = new Position(1,1);
		Direction dir = Direction.EAST;
		Team team = new Team("Test", Color.red, Side.LEFT );
		Hero mage = new Mage(new Position(35,1),dir,team);
		players.add(new Player(mage));
		Hero brute = new Brute(pos,dir,team);
		BlastModel model = new BlastModel(players);
		assertTrue(model.isFree(brute, Direction.EAST, 10));
		
		Block block = new SolidBlock(new Position(100,1));
		model.addEntity(block);
		assertFalse(model.isFree(brute, Direction.EAST, 100));

		
		block.setPosition(new Position(1,102));
		brute.setPosition(1,1);
		assertFalse(model.isFree(brute, Direction.SOUTH, 100));
		
		brute.setPosition(100,1);
		block.setPosition(1,1);
		assertFalse(model.isFree(brute, Direction.WEST, 100));

		block.setPosition(1,1);
		brute.setPosition(1,100);
		assertFalse(model.isFree(brute, Direction.NORTH, 100));
		
		
		
	}
}
