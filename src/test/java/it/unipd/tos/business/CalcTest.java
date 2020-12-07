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

    //TEST ISSUE 1: calcolo la somma totale
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
    /*TEST ISSUE 2: controlla se dal totale viene fatto lo sconto del 50% sul gelato meno caro 
      se sono stati ordinati almeno 5 gelati
     */
	
	@Test 
	public void Discount50perc_for_less_expensive_icecream_more_than_5_test(){
		Calc testCalc = new Calc();
		List<MenuItem> orders = new ArrayList<MenuItem>();
		User u = new User("AleTrevi", "Alessio", "Trevisan", 21);
		LocalTime t = LocalTime.of(16, 00);
		
	
			
			orders.add(new MenuItem(MenuItem.items.Gelato, "Stracciatella", 2.0));
			orders.add(new MenuItem(MenuItem.items.Gelato, "Menta", 3));
			orders.add(new MenuItem(MenuItem.items.Budino, "Biancaneve", 4.5));
			orders.add(new MenuItem(MenuItem.items.Gelato, "Fragola", 2.0));
			orders.add(new MenuItem(MenuItem.items.Gelato, "Limone", 3.0));
			orders.add(new MenuItem(MenuItem.items.Gelato, "Limone", 3.0));
			orders.add(new MenuItem(MenuItem.items.Bevanda,"Coca cola", 2.0));
			orders.add(new MenuItem(MenuItem.items.Gelato, "Menta", 3));
		
		
		try {
			assertEquals(21.5, testCalc.getOrderPrice(orders, u, t), 0.0);
		}
		catch(TakeAwayBillException e){
			e.getException();
		}
	}
	//controllo il caso limite in cui ho cinque gelati pertanto non ho diritto allo sconto del 50% sul meno caro
	@Test 
	public void NoDiscount50perc_for_less_expensive_icecream_for_5_test(){
		Calc testCalc = new Calc();
		List<MenuItem> orders = new ArrayList<MenuItem>();
		User u = new User("AleTrevi", "Alessio", "Trevisan", 21);
		LocalTime t = LocalTime.of(16, 00);
		
	
			
			orders.add(new MenuItem(MenuItem.items.Gelato, "Stracciatella", 2.0));
			orders.add(new MenuItem(MenuItem.items.Gelato, "Menta", 3));
			orders.add(new MenuItem(MenuItem.items.Budino, "Biancaneve", 4.5));
			orders.add(new MenuItem(MenuItem.items.Gelato, "Fragola", 2.0));
			orders.add(new MenuItem(MenuItem.items.Gelato, "Limone", 3.0));
			orders.add(new MenuItem(MenuItem.items.Gelato, "Limone", 3.0));
			orders.add(new MenuItem(MenuItem.items.Bevanda,"Coca cola", 2.0));
			
		
		
		try {
			assertEquals(19.5, testCalc.getOrderPrice(orders, u, t), 0.0);
		}
		catch(TakeAwayBillException e){
			e.getException();
		}
	}
	 /* TEST ISSUE 3: controllo se viene applicato uno sconto del 10% su un totale(gelati e budini) maggiore di 50€ (lo sconto non è chiaro dal testo
	    pertanto qui si applica prezzo totale,bibite incluse)
	  */
	 
        @Test
		public void Discount10perc_for_Major50Euro_test(){
        	
			Calc testCalc = new Calc();
			List<MenuItem> orders = new ArrayList<MenuItem>();
			User u = new User("AleTrevi", "Alessio", "Trevisan", 21);
			LocalTime t = LocalTime.of(16, 00);
			
				orders.add(new MenuItem(MenuItem.items.Gelato, "Vaniglia", 5));
				orders.add(new MenuItem(MenuItem.items.Bevanda, "Sprite", 10));
				orders.add(new MenuItem(MenuItem.items.Budino, "Pinguino", 10));
				orders.add(new MenuItem(MenuItem.items.Budino, "Biancaneve", 30));		
			    orders.add(new MenuItem(MenuItem.items.Budino, "Cioccolato", 20));
			
			try {
				assertEquals(67.5, testCalc.getOrderPrice(orders, u, t), 0.0);
			} 
			catch(TakeAwayBillException e) {
				e.getException();
			}
		}
        //controllo il caso limite ovvero quando il costo totale di gelati e budini risulta essere esattamente 50€ quindi non ho diritto allo sconto
        @Test
  		public void NoDiscount10perc_for_50Euro_test(){
          	
  			Calc testCalc = new Calc();
  			List<MenuItem> orders = new ArrayList<MenuItem>();
  			User u = new User("AleTrevi", "Alessio", "Trevisan", 21);
  			LocalTime t = LocalTime.of(16, 00);
  			
  				orders.add(new MenuItem(MenuItem.items.Gelato, "Vaniglia", 5));
  				orders.add(new MenuItem(MenuItem.items.Bevanda, "Sprite", 10));
  				orders.add(new MenuItem(MenuItem.items.Budino, "Pinguino", 10));
  				orders.add(new MenuItem(MenuItem.items.Budino, "Biancaneve", 15));		
  			    orders.add(new MenuItem(MenuItem.items.Budino, "Cioccolato", 20));
  			
  			try {
  				assertEquals(60, testCalc.getOrderPrice(orders, u, t), 0.0);
  			} 
  			catch(TakeAwayBillException e) {
  				e.getException();
  			}
  		}
        /*TEST ISSUE 4: controllo se ci sono più di 30 oggetti nell'ordine. Se ciò si verifica mostro un messaggio d'errore
        (test con 31 elementi)
        */      
        @Test
    	public void CheckItemsOrderedOver30_test() throws TakeAwayBillException{	
    		Calc testCalc = new Calc();
    		User u = new User("AleTrevi", "Alessio", "Trevisan", 21);
    		List<MenuItem> orders = new ArrayList<MenuItem>();
    		LocalTime t = LocalTime.of(16, 00);

    	
    		for(int i = 0; i < 4; i++) {
    			orders.add(new MenuItem(MenuItem.items.Bevanda, "The alla pesca", 4.0));
    			orders.add(new MenuItem(MenuItem.items.Bevanda, "Fanta", 2.0));
    			orders.add(new MenuItem(MenuItem.items.Bevanda, "The al limone", 3.5));
    			orders.add(new MenuItem(MenuItem.items.Bevanda, "Sprite", 4.5));		
    			orders.add(new MenuItem(MenuItem.items.Gelato,  "Menta", 2));
    			orders.add(new MenuItem(MenuItem.items.Bevanda, "Coca Cola", 2.0));
    			orders.add(new MenuItem(MenuItem.items.Bevanda, "Chinotto", 3.5));
    			
    		}
    		orders.add(new MenuItem(MenuItem.items.Budino, "Vaniglia", 3.5));
    		orders.add(new MenuItem(MenuItem.items.Budino, "Vaniglia", 3.5));
    		orders.add(new MenuItem(MenuItem.items.Budino, "Vaniglia", 3.5));
    		
    		try {
    			assertEquals(80.5, testCalc.getOrderPrice(orders, u, t), 0.0);
    		}
    		catch(TakeAwayBillException e) {
    			assertEquals("Error: Non è possibile ordinare più di 30 elementi nello stesso ordine", e.getException());
    		}	
    	}
        //controllo il caso limite in cui ordino esattamente 30 elementi
        @Test
    	public void CheckItemsOrderedExactly30_test() throws TakeAwayBillException{	
    		Calc testCalc = new Calc();
    		User u = new User("AleTrevi", "Alessio", "Trevisan", 21);
    		List<MenuItem> orders = new ArrayList<MenuItem>();
    		LocalTime t = LocalTime.of(16, 00);

    	
    		for(int i = 0; i < 5; i++) {
    			orders.add(new MenuItem(MenuItem.items.Bevanda, "Sprite", 4.0));
    			orders.add(new MenuItem(MenuItem.items.Bevanda, "Fanta", 2.0));
    			orders.add(new MenuItem(MenuItem.items.Budino, "Vaniglia", 3.5));
    			orders.add(new MenuItem(MenuItem.items.Budino, "Pinguino", 4.5));		
    			orders.add(new MenuItem(MenuItem.items.Gelato, "Menta", 2));
    		}
	
    		try {
    			assertEquals(80, testCalc.getOrderPrice(orders, u, t), 0.0);
    		}
    		catch(TakeAwayBillException e) {
    			assertEquals("Error: Non è possibile ordinare più di 30 elementi nello stesso ordine", e.getException());
    		}	
    	}
        //TEST ISSUE 5: controllo se viene applicata una commissione di 50 centesimi sugli ordini inferiori a 10 euro 
    	@Test
    	public void Commission50centonordersbelow10euro_test(){
    		Calc testCalc = new Calc();
    		User u = new User("AleTrevi", "Alessio", "Trevisan", 21);
    		List<MenuItem> orders = new ArrayList<MenuItem>();
    		LocalTime t = LocalTime.of(18, 30);

    		

    			orders.add(new MenuItem(MenuItem.items.Gelato, "Cremino", 1));
    			orders.add(new MenuItem(MenuItem.items.Bevanda, "Fanta", 1));
    			orders.add(new MenuItem(MenuItem.items.Budino, "Biancaneve", 1));
    			orders.add(new MenuItem(MenuItem.items.Budino, "Pinguino", 2));		
    		
    		
    		try {
    			assertEquals(5.5, testCalc.getOrderPrice(orders, u, t), 0.0);
    		} catch(TakeAwayBillException e){
    			e.getException();
    		}
    	}
    	//controllo il caso limite con un ordine il cui costo è esattamente 10 euro (non deve pagare alcuna commissione)
    	@Test
    	public void NoCommission50centonordersExactly10eur_test(){
    		Calc testCalc = new Calc();
    		User u = new User("AleTrevi", "Alessio", "Trevisan", 21);
    		List<MenuItem> orders = new ArrayList<MenuItem>();
    		LocalTime t = LocalTime.of(18, 30);

    		
         
    			orders.add(new MenuItem(MenuItem.items.Gelato, "Cremino", 1));
    			orders.add(new MenuItem(MenuItem.items.Bevanda, "Fanta", 1));
    			orders.add(new MenuItem(MenuItem.items.Budino, "Biancaneve", 1));
    			orders.add(new MenuItem(MenuItem.items.Budino, "Pinguino", 3.5));		
    			orders.add(new MenuItem(MenuItem.items.Budino, "Pinguino", 3.5));	
    		
    		try {
    			assertEquals(10, testCalc.getOrderPrice(orders, u, t), 0.0);
    		} catch(TakeAwayBillException e){
    			e.getException();
    		}
    	}
    	//TEST ISSUE 6: controllo che un utente selezionabile per lo sconto che viene scelto riceva effettivamente lo sconto
      	@Test
    	public void getOrderPriceRandomFreeSelected_test() {
    		
    		Calc testCalc = new Calc();
    		User u = new User("AleTrevi","Alessio", "Trevisan", 17);
    		List<MenuItem> orders = new ArrayList<MenuItem>();
    		LocalTime t = LocalTime.of(18, 30);

    			orders.add(new MenuItem(MenuItem.items.Gelato, "Cioccolato", 4));
    			orders.add(new MenuItem(MenuItem.items.Bevanda, "Sprite", 4));
    			orders.add(new MenuItem(MenuItem.items.Budino, "Cacao", 4));		
    	
    		try {
    			testCalc.ra.setROptionalValue(true); 
    			double total = testCalc.getOrderPrice(orders, u, t);
    			assertEquals(0.0, total, 0.0);
    		} 
    		catch(TakeAwayBillException e) {
    			e.getException();
    		}
    	}
      	//controllo che un utente maggiorenne non possa ricevere lo sconto nemmeno se viene randomicamente scelto (utilizzo il caso limite 18 anni)
     	@Test
    	public void getOrderPriceRandomFreeTooOld_test() {
    		
    		Calc testCalc = new Calc();
    		User u = new User("AleTrevi","Alessio", "Trevisan", 18);
    		List<MenuItem> orders = new ArrayList<MenuItem>();
    		LocalTime t = LocalTime.of(18, 30);

    			orders.add(new MenuItem(MenuItem.items.Gelato, "Cioccolato", 4));
    			orders.add(new MenuItem(MenuItem.items.Bevanda, "Sprite", 4));
    			orders.add(new MenuItem(MenuItem.items.Budino, "Cacao", 4));	
    			
    		try {
    			testCalc.ra.setROptionalValue(true); 
    			double total = testCalc.getOrderPrice(orders, u, t);
    			assertEquals(12, total, 0.0);
    		} 
    		catch(TakeAwayBillException e) {
    			e.getException();
    		}
    	}
     	/*controllo che un utente selezionabile per l'età non riceva lo sconto nemmeno se viene selezionato poichè effettua
     	   l'ordine prima delle 18:01 (uso il caso limite delle 18, dal testo non si capisce se gli estremi vadano inclusi pertanto li escludo)
     	 */ 
     	@Test
     	 public void getOrderPriceRandomFreeTooEarly_test() {
    		
    		Calc testCalc = new Calc();
    		User u = new User("AleTrevi","Alessio", "Trevisan", 17);
    		List<MenuItem> orders = new ArrayList<MenuItem>();
    		LocalTime t = LocalTime.of(18, 0);
	
    			orders.add(new MenuItem(MenuItem.items.Gelato, "Cioccolato", 4));
    			orders.add(new MenuItem(MenuItem.items.Bevanda, "Sprite", 4));
    			orders.add(new MenuItem(MenuItem.items.Budino, "Cacao", 4));		
    		
    		try {
    			testCalc.ra.setROptionalValue(true); 
    			double total = testCalc.getOrderPrice(orders, u, t);
    			assertEquals(12, total, 0.0);
    		} 
    		catch(TakeAwayBillException e) {
    			e.getException();
    		}
    	}
    	/*controllo che un utente selezionabile per l'età non riceva lo sconto nemmeno se viene selezionato poichè effettua
  	    l'ordine dopo le 18:59 (uso il caso limite delle 19, dal testo non si capisce se gli estremi vadano inclusi pertanto li escludo)
  	    */ 
      	@Test
    	public void getOrderPriceRandomFreeTooLate_test() {
    		
    		Calc testCalc = new Calc();
    		User u = new User("AleTrevi","Alessio", "Trevisan", 17);
    		List<MenuItem> orders = new ArrayList<MenuItem>();
    		LocalTime t = LocalTime.of(19, 0);

    			orders.add(new MenuItem(MenuItem.items.Gelato, "Cioccolato", 4));
    			orders.add(new MenuItem(MenuItem.items.Bevanda, "Sprite", 4));
    			orders.add(new MenuItem(MenuItem.items.Budino, "Cacao", 4));		
    			
    		try {
    			testCalc.ra.setROptionalValue(true); 
    			double total = testCalc.getOrderPrice(orders, u, t);
    			assertEquals(12, total, 0.0);
    		} 
    		catch(TakeAwayBillException e) {
    			e.getException();
    		}
    	}
        //controllo che un utente selezionabile per orario ed età non riceva lo sconto se non viene selezionato randomicamente
      	@Test
    	public void getOrderPriceRandomFreeNotSelected_test() {
    		
    		Calc testCalc = new Calc();
    		User u = new User("AleTrevi","Alessio", "Trevisan", 17);
    		List<MenuItem> orders = new ArrayList<MenuItem>();
    		LocalTime t = LocalTime.of(18, 30);

    	
    			orders.add(new MenuItem(MenuItem.items.Gelato, "Cioccolato", 4));
    			orders.add(new MenuItem(MenuItem.items.Bevanda, "Sprite", 4));
    			orders.add(new MenuItem(MenuItem.items.Budino, "Cacao", 4));		
    		
    		
    		try {
    			testCalc.ra.setROptionalValue(false);
    			double total = testCalc.getOrderPrice(orders, u, t);
    			assertEquals(12, total, 0.0);
    		} catch(TakeAwayBillException e) {
    			e.getException();
    		}
    	}
}
