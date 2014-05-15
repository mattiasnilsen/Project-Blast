package projectblast.test.title;

import static org.junit.Assert.*;

import org.junit.Test;

import projectblast.model.title.Item;

public class ItemTest {

    @Test
    public void testItem() {
        boolean caughtException = false;
        try {
            String nullString = null; //This needs to be done to differentiate between Item(String s) and Item(Item i)
            new Item(nullString);
        } catch(NullPointerException ex) {
            caughtException = true;
        }
        assertTrue(caughtException);
        
        Item item = new Item("test");
        assertTrue(item.getName().equals("test"));
    }
        

    @Test
    public void testCopyConstructor() {
        boolean caughtException = false;
        try {
            Item nullItem = null; //This needs to be done to differentiate between Item(String s) and Item(Item i)
            new Item(nullItem);
        } catch(NullPointerException ex) {
            caughtException = true;
        }
        assertTrue(caughtException);
        
        Item item = new Item("test");
        Item other = new Item(item);
        assertTrue(other.getName().equals("test"));
    }

    @Test
    public void testSetSelected() {
        Item item = new Item("test");
        assertFalse(item.isSelected());
        item.setSelected(true);;
        assertTrue(item.isSelected());
    }

}
