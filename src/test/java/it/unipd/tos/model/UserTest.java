package it.unipd.tos.model;

import static org.junit.Assert.*;

import org.junit.Test;

import it.unipd.tos.model.User;

public class UserTest {
	
     //Test per il controllo dei metodi che ritornanocampi dati utente
   //test per il metodo getUsername
	@Test
	public void getUsername_test() {
		User u = new User("AleTrevi", "Alessio", "Trevisan", 21);
		assertEquals("AleTrevi", u.getUsername());
	}
	 //test per il metodo getName
	@Test
	public void getName_test()
	{
		User u = new User("AleTrevi", "Alessio", "Trevisan", 21);
		assertEquals("Alessio", u.getName());	
	}
	 //test per il metodo getSurname
	@Test
	public void getSurname_test() {
		
		User u = new User("AleTrevi", "Alessio", "Trevisan", 21);
		assertEquals("Trevisan", u.getSurname());
	}
	 //test per il metodo getAge
    @Test
    public void getAge_test() {

		User u = new User("AleTrevi", "Alessio", "Trevisan", 21);
		assertEquals(21, u.getAge());
    }
   

}