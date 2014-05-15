package projectblast.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import projectblast.model.title.HeroColumn;
import projectblast.model.title.ITitleModel.HeroChoice;

public class HeroColumnTest {

    private HeroColumn heroColumn = null;
    
    @Before
    public void before() {
        heroColumn = new HeroColumn("test");
    }
    
    @Test
    public void testNextHero() {
        HeroChoice hero = heroColumn.getSelectedHero();
        heroColumn.nextHero();
        assertTrue(hero != heroColumn.getSelectedHero());
        heroColumn.previousHero();
        assertTrue(hero == heroColumn.getSelectedHero());
    }

    @Test
    public void testPreviousHero() {
        HeroChoice hero = heroColumn.getSelectedHero();
        heroColumn.previousHero();
        assertTrue(hero != heroColumn.getSelectedHero());
        heroColumn.nextHero();
        assertTrue(hero == heroColumn.getSelectedHero());
    }

}
