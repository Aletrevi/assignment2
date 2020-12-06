package it.unipd.tos.model;

import static org.junit.Assert.*;

import org.junit.Test;

import it.unipd.tos.model.User;

public class UserTest {
	/*
     Test per il controllo dei campi dati utente
     */
	@Test
	public void getUsername_test() {
		User u = new User("AleTrevi", "Alessio", "Trevisan", 21);
		assertEquals("AleTrevi", u.getUsername());
	}
	@Test
	public void getName_test()
	{
		User u = new User("AleTrevi", "Alessio", "Trevisan", 21);
		assertEquals("Alessio", u.getName());	
	}
	
	@Test
	public void getSurname_test() {
		
		User u = new User("AleTrevi", "Alessio", "Trevisan", 21);
		assertEquals("Trevisan", u.getSurname());
	}
    @Test
    public void getAge_test() {

		User u = new User("AleTrevi", "Alessio", "Trevisan", 21);
		assertEquals(21, u.getAge());
    }
   

}