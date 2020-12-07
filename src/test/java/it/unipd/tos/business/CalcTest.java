package it.unipd.tos.business;

import it.unipd.tos.business.Calc;
import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;

import static org.junit.Assert.*;

import org.junit.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class CalcTest {

    //TEST 1: calcolo la somma totale
    @Test
    public void total_test() {

        Calc testCalc = new Calc();
        List<MenuItem> orders = new ArrayList<MenuItem>();
        User u = new User("AleTrevi", "Alessio", "Trevisan", 21);
        LocalTime t = LocalTime.of(18, 30);
        
        orders.add(new MenuItem(MenuItem.items.Budino, "Biancaneve", 4.5));
        orders.add(new MenuItem(MenuItem.items.Gelato, "Vaniglia", 3.0));
        orders.add(new MenuItem(MenuItem.items.Bevanda, "Coca Cola", 2.5));
        
        try {
            assertEquals(10, testCalc.getOrderPrice(orders, u, t), 0.0);
        }
        catch(TakeAwayBillException e) {
           e.getException();
        }
   }
    /*TEST 2: controlla se dal totale viene fatto lo sconto del 50% sul gelato meno caro 
      se sono stati ordinati almeno 5 gelati
     */
	
	@Test 
	public void Discount50perc_for_less_expensive_icecream_among_5_test(){
		Calc testCalc = new Calc();
		List<MenuItem> orders = new ArrayList<MenuItem>();
		User u = new User("AleTrevi", "Alessio", "Trevisan", 21);
		LocalTime t = LocalTime.of(16, 00);
		
	
			orders.add(new MenuItem(MenuItem.items.Gelato, "Cioccolato", 3.5));
			orders.add(new MenuItem(MenuItem.items.Gelato, "Stracciatella", 2.5));
			orders.add(new MenuItem(MenuItem.items.Gelato, "Menta", 3));
			orders.add(new MenuItem(MenuItem.items.Budino, "Biancaneve", 4.5));
			orders.add(new MenuItem(MenuItem.items.Gelato, "Fragola", 2.0));
			orders.add(new MenuItem(MenuItem.items.Gelato, "Limone", 3.0));
			orders.add(new MenuItem(MenuItem.items.Gelato, "Limone", 3.0));
		
		
		try {
			assertEquals(20.5, testCalc.getOrderPrice(orders, u, t), 0.0);
		} catch(TakeAwayBillException e){
			e.getException();
		}
	}
}
