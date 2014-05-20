package projectblast.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import projectblast.model.Direction;
import projectblast.model.entity.hero.*;
import projectblast.model.helper.Constants;
import projectblast.model.helper.Position;
import projectblast.model.powerup.IPowerUp;
import projectblast.model.powerup.AmmoPowerUp;

public class AmmoPowerUpTest {

    private Hero testHero = null;
    private IPowerUp powerUp = null;
    
    @Before
    public void before() {
        testHero = new Mage(new Position(0, 0), Direction.NORTH, null);
        powerUp = new AmmoPowerUp();
    }
    
    @Test
    public void testApply() {
        int heroAmmo = testHero.getAmmo();
        powerUp.apply(testHero);
        assertTrue(testHero.getAmmo() == heroAmmo + Constants.AMMO_POWERUP_MODIFIER);
    }

    @Test
    public void testReverse() {
    	powerUp.apply(testHero);
        int heroAmmo = testHero.getAmmo();
        powerUp.reverse(testHero);
        assertTrue(testHero.getAmmo() == heroAmmo - Constants.AMMO_POWERUP_MODIFIER);
    }

    @Test
    public void testToString() {
        assertTrue(powerUp.toString().equals("Ammo"));
    }

}
