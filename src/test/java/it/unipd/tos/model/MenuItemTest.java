package it.unipd.tos.model;

import static org.junit.Assert.*;

import it.unipd.tos.model.MenuItem;

import org.junit.Test;

public class MenuItemTest {

	
	// Test per il controllo del tipo di oggetto
	
	@Test
	public void getItemName_test() {
		MenuItem m = new MenuItem(MenuItem.items.Budino, "Pinguino", 4.5);
		
		assertEquals(MenuItem.items.Budino, m.getItemName());
	}
	
	// Test per il controllo del nome dell'oggetto
	
	@Test
	public void getNameMenu_test() {
		MenuItem m = new MenuItem(MenuItem.items.Budino, "Pinguino", 4.5);
		assertEquals("Pinguino",m.getNameMenu());
	}
	 
	// Test per il controllo del prezzo dell'oggetto
	
	@Test
	public void getItemPrice_test() {
		MenuItem m = new MenuItem(MenuItem.items.Budino, "Pinguino", 4.5);
		
		assertEquals(4.5, m.getPrice(), 0.0);
	}

}