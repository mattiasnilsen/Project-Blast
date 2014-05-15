package projectblast.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import projectblast.model.title.HeroColumn;
import projectblast.model.title.ITitleModel.HeroChoice;

public class HeroColumnTest {

    private HeroColumn heroColumn = null;
    private final HeroChoice HERO_CHOICE = HeroChoice.BOMBER;
    
    @Before
    public void before() {
        heroColumn = new HeroColumn("test", HERO_CHOICE);
    }
    
    @Test
    public void testNextHero() {
        assertTrue(heroColumn.getSelectedHero() == HERO_CHOICE);
        heroColumn.nextHero();
        assertTrue(HERO_CHOICE != heroColumn.getSelectedHero());
        heroColumn.previousHero();
        assertTrue(HERO_CHOICE == heroColumn.getSelectedHero());
    }

    @Test
    public void testPreviousHero() {
        assertTrue(heroColumn.getSelectedHero() == HERO_CHOICE);
        heroColumn.previousHero();
        assertTrue(HERO_CHOICE != heroColumn.getSelectedHero());
        heroColumn.nextHero();
        assertTrue(HERO_CHOICE == heroColumn.getSelectedHero());
    }

}
