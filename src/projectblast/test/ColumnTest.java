package projectblast.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import projectblast.model.title.Column;
import projectblast.model.title.Item;

public class ColumnTest {

	private Column column = null;
	List<Item> items = null;
	
	@Before
	public void before() {
		column = new Column("Test");
		items = new ArrayList<Item>();
		
		items.add(new Item("test"));
		items.add(new Item("test2"));
		items.add(new Item("test3"));
		items.add(new Item("test4"));
	}
	
	@Test
	public void testColumn() {
		boolean caughtException = false;
		try {
			Column col = new Column(null);
		} catch(NullPointerException ex) {
			caughtException = true;
		}
		
		assertTrue(caughtException);
		caughtException = false;
		try {
			Column col = new Column("test", null);
		} catch(NullPointerException ex) {
			caughtException = true;
		}
		assertTrue(caughtException);
		
		Column col = new Column("test", items);
		assertTrue(col.getName().equals("test"));
		assertTrue(col.getRowCount() == items.size());
	}
	
	@Test
	public void testGetItem() {
		Item item1 = new Item("Test");
		Item item2 = new Item("Test2");
		column.addItem(item1);
		column.addItem(item2);
		
		assertTrue(column.getItem(0) == item1);
		assertTrue(column.getItem(1) == item2);
	}
	
	@Test
	public void testAddItems() {
		
		boolean caughtException = false;
		try {
			column.addItem(null);
		} catch(NullPointerException ex) {
			caughtException = true;
		}
		assertTrue(caughtException);
		
		for(int i = 0; i < items.size(); ++i) {
			column.addItem(items.get(i));
			assertTrue(column.getRowCount() == i + 1);
		}
		
		List<Item> addedItems = column.getItems();
		assertTrue(addedItems.size() == items.size());
		for(int i = 0; i < items.size(); ++i) {
			assertTrue(items.get(i) == addedItems.get(i));
		}
	}
	
	@Test
	public void testGetRowPosition() {
		
		for(int i = 0; i < items.size(); ++i) {
			column.addItem(items.get(i));
		}
		//Make sure the rowposition stays within its interval even at large numbers.
		for(int i = -5000; i < 5000; ++i) {
			assertTrue(column.getRowPosition(i) >= 0);
			assertTrue(column.getRowPosition(i) < column.getRowCount());
		}
	}	
	
	@Test
	public void testSelectItem() {
		for(Item item : items) {
			column.addItem(item);
		}
		
		for(Item item : column.getItems()) {
			assertFalse(item.isSelected());
		}
		//Select the item.
		column.selectItem(2);
		assertTrue(column.getItem(2).isSelected());
		
		//Deselect the item.
		column.selectItem(2);
		assertFalse(column.getItem(2).isSelected());
		
	}
}
