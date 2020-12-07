package it.unipd.tos.business;

import static org.junit.Assert.*;

import java.time.LocalTime;

import org.junit.Test;

import it.unipd.tos.model.User;

public class RandomFreeTest {

//controllo che la funzione checktime ritorni true a un orario accettabile (controllo i valori accettabili più esterni)
	@Test
	public void checktimepositivetop_test() {
		
		RandomFree rnd= new RandomFree();
		LocalTime intime= LocalTime.of(18, 59);
		
		assertTrue(rnd.checktime(intime));
	}
	
	@Test
	public void checktimepositivelow_test() {
		
		RandomFree rnd= new RandomFree();
		LocalTime intime= LocalTime.of(18, 1);
		
		assertTrue(rnd.checktime(intime));
	}
//controllo che la funzione checktime escluda i valori non accettabili (gli estremi si considerano non accettati)
	@Test 
	public void checktimeover_test() {
	
		RandomFree rnd= new RandomFree();
		LocalTime time= LocalTime.of(19, 0);
		
		assertFalse(rnd.checktime(time));
		
	}
	@Test
	public void checktimebefore_test() {
		
		RandomFree rnd= new RandomFree();
		LocalTime time= LocalTime.of(18,0);
		
		assertFalse(rnd.checktime(time));
	}
	//test per il controllo delle età
	@Test
	public void checkagetrue_test() {
		User u = new User("AleTrevi", "Alessio", "Trevisan", 17);
		RandomFree rnd= new RandomFree();
		assertTrue(rnd.checkage(u));
	}
	//controllo il caso limite 18 anni
	@Test
	public void checkagefalse_test() {
		User u = new User("AleTrevi", "Alessio", "Trevisan", 18);
		RandomFree rnd= new RandomFree();
		assertFalse(rnd.checkage(u));
	}
	//test funzionamento  is_eligible (età e orario corretti) deve ritornare true
	@Test
	public void iseligibletrue_test() {
		User u = new User("AleTrevi", "Alessio", "Trevisan", 17);
		RandomFree rnd= new RandomFree();
		LocalTime intime= LocalTime.of(18, 59);
		assertTrue(rnd.is_eligible(u, intime));
		
	}
	//test funzionamento is_eligible (età non accettabile ma tempo  corretto) deve ritornare false
	@Test
	public void iseligiblefalseage_test() {
		User u = new User("AleTrevi", "Alessio", "Trevisan", 18);
		RandomFree rnd= new RandomFree();
		LocalTime intime= LocalTime.of(18, 59);
		assertFalse(rnd.is_eligible(u, intime));
		
	}
	//test funzionamento is_eligible (età accettabile ma tempo non corretto) deve ritornare false
	@Test
	public void iseligiblefalsetime_test() {
		User u = new User("AleTrevi", "Alessio", "Trevisan", 17);
		RandomFree rnd= new RandomFree();
		LocalTime intime= LocalTime.of(19, 59);
		assertFalse(rnd.is_eligible(u, intime));
		
	}
	//test funzionamento is_eligible (età non accettabile tempo non corretto) deve ritornare false
	@Test
	public void iseligiblefalseboth_test() {
		User u = new User("AleTrevi", "Alessio", "Trevisan", 18);
		RandomFree rnd= new RandomFree();
		LocalTime intime= LocalTime.of(19, 59);
		assertFalse(rnd.is_eligible(u, intime));
		
	}
	//test funzionamento checkgift in base a chosen (valore randomico), caso utente selezionato randomicamente
	@Test 
	public void checkgiftchosentrue_test() {
		User u = new User("AleTrevi","Trevisan", "Alessio", 17); 
		LocalTime t = LocalTime.of(18, 30);
		RandomFree rnd = new RandomFree();
		rnd.setROptionalValue(true);
		boolean result = rnd.checkGift(u, t);
		
		assertTrue(result);
	}
	//test funzionamento checkgift in base a chosen (valore randomico), caso utente non selezionato randomicamente
	@Test 
	public void checkgiftchosenfalse_test() {
		User u = new User("AleTrevi","Trevisan", "Alessio", 17); 
		LocalTime t = LocalTime.of(18, 30);
		RandomFree rnd = new RandomFree();
		rnd.setROptionalValue(false);
		boolean result = rnd.checkGift(u, t);
		
		assertFalse(result);
	}
	//test funzionamento checkgift in base a is_eligible (rispetta le condizioni per lo sconto e viene selezionato randomicamente) deve ritornare true
	@Test 
	public void checkgiftiseligibletrue_test() {
		User u = new User("AleTrevi","Trevisan", "Alessio", 17); 
		LocalTime t = LocalTime.of(18, 30);
		RandomFree rnd = new RandomFree();
		rnd.setROptionalValue(true);
		boolean result = rnd.checkGift(u, t);
		
		assertTrue(result);
	}
	//test funzionmamento checkgift in base a is_eligible (non rispetta le condizioni per lo sconto e viene selezionato randomicamente) deve ritornare false
	@Test 
	public void checkgiftiseligiblefalse_test() {
		User u = new User("AleTrevi","Trevisan", "Alessio", 18); 
		LocalTime t = LocalTime.of(18, 30);
		RandomFree rnd = new RandomFree();
		rnd.setROptionalValue(true);
		boolean result = rnd.checkGift(u, t);
		
		assertFalse(result);
	}
	
	

}