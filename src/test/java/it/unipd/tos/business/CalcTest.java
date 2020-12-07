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
		}
		catch(TakeAwayBillException e){
			e.getException();
		}
	}
	 // TEST 3: controllo se viene applicato uno sconto del 10% su un totale maggiore di 50€
	 
        @Test
		public void Discount10perc_for_Major50Euro_test(){
        	
			Calc testCalculator = new Calc();
			List<MenuItem> orders = new ArrayList<MenuItem>();
			User u = new User("AleTrevi", "Alessio", "Trevisan", 21);
			LocalTime t = LocalTime.of(16, 00);
			
				orders.add(new MenuItem(MenuItem.items.Gelato, "Vaniglia", 5));
				orders.add(new MenuItem(MenuItem.items.Bevanda, "Sprite", 10));
				orders.add(new MenuItem(MenuItem.items.Budino, "Pinguino", 10));
				orders.add(new MenuItem(MenuItem.items.Budino, "Biancaneve", 30));		
			    orders.add(new MenuItem(MenuItem.items.Budino, "Cioccolato", 20));
			
			try {
				assertEquals(67.5, testCalculator.getOrderPrice(orders, u, t), 0.0);
			} 
			catch(TakeAwayBillException e) {
				e.getException();
			}
		}
        /*TEST 4: controllo se ci sono più di 30 oggetti nell'ordine. Se ciò si verifica mostro un messaggio d'errore
        (test con 31 elementi)*/      
        @Test
    	public void CheckItemsOrderedOver30_test() throws TakeAwayBillException{	
    		Calc testCalculator = new Calc();
    		User u = new User("AleTrevi", "Alessio", "Trevisan", 21);
    		List<MenuItem> orders = new ArrayList<MenuItem>();
    		LocalTime t = LocalTime.of(16, 00);

    	
    		for(int i = 0; i < 4; i++) {
    			orders.add(new MenuItem(MenuItem.items.Gelato, "Fragola", 4.0));
    			orders.add(new MenuItem(MenuItem.items.Bevanda, "Fanta", 2.0));
    			orders.add(new MenuItem(MenuItem.items.Budino, "Vaniglia", 3.5));
    			orders.add(new MenuItem(MenuItem.items.Budino, "Pinguino", 4.5));		
    			orders.add(new MenuItem(MenuItem.items.Gelato, "Menta", 2));
    			orders.add(new MenuItem(MenuItem.items.Bevanda, "Coca Cola", 2.0));
    			orders.add(new MenuItem(MenuItem.items.Budino, "Cioccolato", 3.5));
    			
    		}
    		orders.add(new MenuItem(MenuItem.items.Budino, "Vaniglia", 3.5));
    		orders.add(new MenuItem(MenuItem.items.Budino, "Vaniglia", 3.5));
    		orders.add(new MenuItem(MenuItem.items.Budino, "Vaniglia", 3.5));
    		
    		try {
    			testCalculator.getOrderPrice(orders, u, t);
    		}
    		catch(TakeAwayBillException e) {
    			assertEquals("Error: Non è possibile ordinare più di 30 elementi nello stesso ordine", e.getException());
    		}	
    	}
        //TEST 5: controllo se viene applicata una commissione di 50 centesimi sugli ordini inferiori a 10 euro 
    	@Test
    	public void Commission50centonordersbelow10euro_test(){
    		Calc testCalculator = new Calc();
    		User u = new User("AleTrevi", "Alessio", "Trevisan", 21);
    		List<MenuItem> orders = new ArrayList<MenuItem>();
    		LocalTime t = LocalTime.of(18, 30);

    		

    			orders.add(new MenuItem(MenuItem.items.Gelato, "Cremino", 1));
    			orders.add(new MenuItem(MenuItem.items.Bevanda, "Fanta", 1));
    			orders.add(new MenuItem(MenuItem.items.Budino, "Biancaneve", 1));
    			orders.add(new MenuItem(MenuItem.items.Budino, "Pinguino", 2));		
    		
    		
    		try {
    			assertEquals(5.5, testCalculator.getOrderPrice(orders, u, t), 0.0);
    		} catch(TakeAwayBillException e){
    			e.getException();
    		}
    	}
}
