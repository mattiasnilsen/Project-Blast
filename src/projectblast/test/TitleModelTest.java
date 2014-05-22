package projectblast.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import projectblast.model.title.TitleModel;
import projectblast.model.helper.Constants;
import projectblast.model.helper.Constants.HeroChoice;

public class TitleModelTest {

    private TitleModel titleModel = null;
    
    @Before
    public void before() {
        titleModel = new TitleModel();
    }
    
    @Test
    public void testTitleModel() {
       assertFalse(titleModel.isSelected());
       assertTrue(titleModel.getSelectedColumn() == 0);
       assertTrue(titleModel.getSelectedRow() == 0);
    }

    @Test
    public void testGetPlayerHero() {
        assertTrue(titleModel.getPlayerHero(1) == Constants.PLAYER_1_DEFAULT_HERO);
        assertTrue(titleModel.getPlayerHero(2) == Constants.PLAYER_2_DEFAULT_HERO);
        
        titleModel.right(); //Navigate to player ones hero choice.
        titleModel.select(); //Select this item, it enables us to change hero.
        titleModel.right(); //Player ones hero should now have changed.
        assertFalse(titleModel.getPlayerHero(1) == Constants.PLAYER_1_DEFAULT_HERO);
        titleModel.left(); //Change it back
        assertTrue(titleModel.getPlayerHero(1) == Constants.PLAYER_1_DEFAULT_HERO);
        
        titleModel.select(); //Unselect the current column.
        
        titleModel.right(); //Navigate to Player twos hero choice.
        titleModel.select(); //Select this item, to enable us to change hero.
        titleModel.left(); //Player twos hero should now have changed.
        assertFalse(titleModel.getPlayerHero(2) == Constants.PLAYER_2_DEFAULT_HERO);
        titleModel.right(); //Change it back
        assertTrue(titleModel.getPlayerHero(2) == Constants.PLAYER_2_DEFAULT_HERO);
    }

    @Test
    public void testDown() {
        assertTrue(titleModel.getSelectedRow() == 0);
        titleModel.down();
        assertTrue(titleModel.getSelectedRow() == 1);
    }

    @Test
    public void testUp() {
        assertTrue(titleModel.getSelectedRow() == 0);
        titleModel.up();
        assertFalse(titleModel.getSelectedRow() == -1);
        titleModel.down();
        titleModel.down();
        assertTrue(titleModel.getSelectedRow() == 1);
        titleModel.up();
        assertTrue(titleModel.getSelectedRow() == 0);
    }
    
    @Test
    public void testRight() {
        assertTrue(titleModel.getSelectedColumn() == 0);
        titleModel.right();
        assertTrue(titleModel.getSelectedColumn() == 1);
    }

    @Test
    public void testLeft() {
        assertTrue(titleModel.getSelectedColumn() == 0);
        titleModel.left();
        assertFalse(titleModel.getSelectedColumn() == -1);
        titleModel.right();
        titleModel.right();
        assertTrue(titleModel.getSelectedColumn() == 1);
        titleModel.left();
        assertTrue(titleModel.getSelectedColumn() == 0);
    }

    @Test
    public void testSelect() {
        assertFalse(titleModel.isSelected());
        titleModel.select();
        assertTrue(titleModel.isSelected());
    }

    @Test
    public void testGetSelectedItem() {
        assertFalse(titleModel.isSelected());
        assertTrue(titleModel.getSelectedItem() == null);
        titleModel.select();
        assertTrue(titleModel.getSelectedItem() != null);
    }
}
