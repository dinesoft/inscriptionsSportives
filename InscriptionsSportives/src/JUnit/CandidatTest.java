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
	Personne varPersonne = inscriptions.createPersonne("Thivet","Didine","Didine@test.fr");
	Personne varPersonne2 = inscriptions.createPersonne("Thivet", "Didine", "Didine@test.fr");
	
		@Test
		public void testGetNom()
		{
			String getNom = varPersonne.getNom();
			assertEquals("Thivet",getNom);
		}

		@Test
		public void testSetNom()
		{
			varPersonne.setNom("Mya");
			String setNom = varPersonne.getNom();
			assertEquals("Mya", setNom);
		}

		@Test
		public void testGetCompetitions()
		{
			Competition equitation = inscriptions.createCompetition("Mondial du saut d'obstacle", null, false);
			equitation.add(varPersonne);
			assertTrue(varPersonne.getCompetitions().contains(equitation));
		}

		@Test
		public void testDelete()
		{
		      varPersonne.delete();
		      assertTrue(!inscriptions.getCandidats().contains(varPersonne));
		}
		
		@Test
		public void testCompareTo()
		{
			assertEquals(0, varPersonne.compareTo(varPersonne2));
		}
		
		@Test
		public void testToString()
		{
			assertTrue(!varPersonne.toString().contentEquals(""));
		}
		
			
	}



