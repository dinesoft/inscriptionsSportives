package JUnit;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import inscriptions.Competition;
import inscriptions.Equipe;
import inscriptions.Inscriptions;
import inscriptions.Personne;

public class EquipeTest {

	Inscriptions inscriptions ;
	
	Personne varPersonne ;
	
	Equipe varEquipe ;; 
	
	Competition varCompet1 ;
	Competition varCompet2 ;
	Competition varCompet1bis ;

	
	@Before
	public void setUp() {
		
		inscriptions = Inscriptions.getInscriptions();
		
		varPersonne = inscriptions.createPersonne("nomPersonne", "prenomPersonne", "mailPersonne");
		
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
	public void testGetMembres()
	{
		varEquipe.add(varPersonne);
		assertTrue(varEquipe.getMembres().contains(varPersonne));
	}
	
	@Test
	public void testAdd()
	{
		varEquipe.add(varPersonne);
		assertTrue(varEquipe.getMembres().contains(varPersonne));
	}
	
	@Test
	public void testRemove()
	{
		varEquipe.add(varPersonne);
		int sizeBefore = varEquipe.getMembres().size();
		varEquipe.remove(varPersonne);
		int sizeAfter = varEquipe.getMembres().size();
		assertEquals(sizeBefore - 1, sizeAfter);
	}
	
//	@Test
//	public void testGetPersonnesAAjouter()
//	{
////		varEquipe.add(varPersonne);
//		assertEquals(varPersonne, varEquipe.getPersonnesAAjouter());
//		assertTrue(varEquipe.getPersonnesAAjouter().contains(varPersonne));
//	}
	
	@Test
	public void testDelete()
	{
		varEquipe.add(varPersonne);
		varPersonne.delete();
		assertTrue(!varEquipe.getMembres().contains(varPersonne));
		assertTrue(!inscriptions.getPersonnes().contains(varPersonne));
		assertTrue(!inscriptions.getCandidats().contains(varPersonne));
	}

}
