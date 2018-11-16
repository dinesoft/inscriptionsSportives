package JUnit;

import static org.junit.Assert.*;


import java.io.Serializable;
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


public class CompetitionTest {
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
		
		varCompet1 = inscriptions.createCompetition("NomCompet1", null, true);
		varCompet2 = inscriptions.createCompetition("NomCompet2", null, false);
		varCompet1bis = inscriptions.createCompetition("NomCompet1", null, true);
	}

	@After
	public void tearDown() {
		Inscriptions.getInscriptions().reinitialiser();
	}
	
	
	@Test
	public void testGetNomCompetition()
	{
		String getNom = varCompet1.getNom();
		assertEquals("NomCompet1",getNom);

	}
	
    @Test
    public void testSetNom() {
        varCompet1.setNom("NomCompet3");
        String setNom = varCompet1.getNom();
		assertEquals("NomCompet3", setNom);
    }
    
    @Test
    public void testGetCandidats() {
        varCompet2.add(varPersonne);
        assertTrue(varCompet2.getCandidats().contains(varPersonne));
    }

    @Test
    public void testAddEquipe() {  
        varCompet1.add(varEquipe);
        assertTrue(varCompet1.getCandidats().contains(varEquipe));
    }
    
    @Test
	public void testRemove()
	{
    		varCompet2.add(varPersonne);
        int sizeBefore = varCompet2.getCandidats().size();
        varCompet2.remove(varPersonne);
        int sizeAfter = varCompet2.getCandidats().size();
        assertEquals(sizeBefore - 1, sizeAfter);
	}
    
    @Test
    public void testGetCandidatsAInscrire()
    {
    		//varCompet2.add(varPersonne);
    		//assertTrue(varCompet2.getCandidatsAInscrire().contains(varPersonne));
    }
    
	@Test
	public void testDelete()
	{
		varCompet2.add(varPersonne);
		varCompet2.delete();
		assertTrue(!varPersonne.getCompetitions().contains(varCompet2));
	}
	
	@Test
	public void testCompareTo()
	{
		assertEquals(0, varCompet1.compareTo(varCompet1bis));
	}
}
