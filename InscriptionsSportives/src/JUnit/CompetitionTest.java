package JUnit;

import static org.junit.Assert.*;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

import inscriptions.Candidat;
import inscriptions.Competition;
import inscriptions.Equipe;
import inscriptions.Inscriptions;
import inscriptions.Personne;


public class CompetitionTest {

	Inscriptions inscriptions = Inscriptions.getInscriptions();
	Competition VarCompet1 = inscriptions.createCompetition("NomCompet1", null, true);
	Competition VarCompet2 = inscriptions.createCompetition("NomCompet2", null, false);
	Competition VarCompet1bis = inscriptions.createCompetition("NomCompet1", null, true);
	
	@Test
	public void testGetNomCompetition()
	{
		String getNom = VarCompet1.getNom();
		assertEquals("NomCompet1",getNom);

	}
	
    @Test
    public void testSetNom() {
        VarCompet1.setNom("NomCompet3");
        String setNom = VarCompet1.getNom();
		assertEquals("NomCompet3", setNom);
    }
    
    @Test
    public void testGetCandidats() {
        Personne Personne1 = inscriptions.createPersonne("Nom1", "Prenom1", "Email1");
        VarCompet2.add(Personne1);
        assertTrue(VarCompet2.getCandidats().contains(Personne1));
    }

    @Test
    public void testAddEquipe() {
        Equipe VarEquipe = inscriptions.createEquipe("NomEquipe"); 
        VarCompet1.add(VarEquipe);
        assertTrue(VarCompet1.getCandidats().contains(VarEquipe));
    }
    
    @Test
	public void testRemove()
	{
    		Personne Personne = inscriptions.createPersonne("nomPersonne", "prenomPersonne", "mailPersonne");
    		VarCompet2.add(Personne);
        int sizeBefore = VarCompet2.getCandidats().size();
        VarCompet2.remove(Personne);
        int sizeAfter = VarCompet2.getCandidats().size();
        assertEquals(sizeBefore - 1, sizeAfter);
	}
    
	@Test
	public void testDelete()
	{
		Personne Personne = inscriptions.createPersonne("nomPersonne", "prenomPersonne", "mailPersonne");
		VarCompet2.add(Personne);
		VarCompet2.delete();
		assertTrue(!Personne.getCompetitions().contains(VarCompet2));
	}
	
	@Test
	public void testCompareTo()
	{
		assertEquals(0, VarCompet1.compareTo(VarCompet1bis));
	}
}
