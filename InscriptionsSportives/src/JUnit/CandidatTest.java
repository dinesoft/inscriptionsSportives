package JUnit;

import static org.junit.Assert.*;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

import inscriptions.Candidat;
import inscriptions.Competition;
import inscriptions.Inscriptions;
import inscriptions.Personne;

public class CandidatTest {
		
	Inscriptions inscriptions = Inscriptions.getInscriptions();
	Personne Personne = inscriptions.createPersonne("Thivet","Didine","Didine@test.fr");
	Personne Personne2 = inscriptions.createPersonne("Thivet", "Didine", "Didine@test.fr");
	
		@Test
		public void testGetNom()
		{
			String getNom = Personne.getNom();
			assertEquals("Thivet",getNom);

		}

		@Test
		public void testSetNom()
		{
			Personne.setNom("Mya");
			String setNom = Personne.getNom();
			assertEquals("Mya", setNom);
			
		}

		@Test
		public void testGetCompetitions()
		{
			Competition Equitation = inscriptions.createCompetition("Mondial du saut d'obstacle", null, false);
			Equitation.add(Personne);
			assertTrue(Personne.getCompetitions().contains(Equitation));
		}

		@Test
		public void testDelete()
		{
		      Personne.delete();
		      assertTrue(!inscriptions.getCandidats().contains(Personne));
		}
		

		@Test
		public void testCompareTo()
		{
			assertEquals(0, Personne.compareTo(Personne2));
		}
		

		@Test
		public void testToString()
		{
			assertTrue(!Personne.toString().contentEquals(""));
		}
		
			
	}



