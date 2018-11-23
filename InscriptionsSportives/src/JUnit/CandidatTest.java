package JUnit;

import static org.junit.Assert.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import inscriptions.Candidat;
import inscriptions.Competition;
import inscriptions.Equipe;
import inscriptions.Inscriptions;
import inscriptions.Personne;

public class CandidatTest {
	Inscriptions inscriptions ;
	
	Personne varPersonne ;
	Personne varPersonne2 ;
	
	Equipe varEquipe ;; 
	
	Competition varCompet1 ;
	Competition varCompet2 ;
	Competition varCompet1bis ;

	
	@Before
	public void setUp() {
		
		inscriptions = Inscriptions.getInscriptions();
		
		varPersonne = inscriptions.createPersonne("nomPersonne", "prenomPersonne", "mailPersonne");
		varPersonne2 = inscriptions.createPersonne("nomPersonne2", "prenomPersonne2", "mailPersonne2");
		
		varEquipe = inscriptions.createEquipe("NomEquipe"); 
		
		varCompet1 = inscriptions.createCompetition("NomCompet1", LocalDate.now().plusDays(40), true);
		varCompet2 = inscriptions.createCompetition("NomCompet2", LocalDate.now().plusDays(40), false);
		varCompet1bis = inscriptions.createCompetition("NomCompet1", LocalDate.now().plusDays(40), true);
	}

	@After
	public void tearDown() {
		Inscriptions.getInscriptions().reinitialiser();
	}
	
	
		@Test
		public void testGetNom()
		{
			String getNom = varPersonne.getNom();
			assertEquals("nomPersonne",getNom);
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
			Competition equitation = inscriptions.createCompetition("Mondial du saut d'obstacle", LocalDate.now().plusDays(40), false);
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
			assertEquals(0, varPersonne.compareTo(varPersonne));
		}
		
		@Test
		public void testToString()
		{
			assertTrue(!varPersonne.toString().contentEquals(""));
		}
		
			
	}



