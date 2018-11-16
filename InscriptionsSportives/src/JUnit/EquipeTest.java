package JUnit;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.Test;

import inscriptions.Competition;
import inscriptions.Equipe;
import inscriptions.Inscriptions;
import inscriptions.Personne;

public class EquipeTest {

	Inscriptions inscriptions = Inscriptions.getInscriptions();
	Personne Personne = inscriptions.createPersonne("nomPersonne", "prenomPersonne", "mailPersonne");
	Equipe VarEquipe = inscriptions.createEquipe("NomEquipe"); 
	Competition VarCompet1 = inscriptions.createCompetition("NomCompet1", null, true);
	Competition VarCompet2 = inscriptions.createCompetition("NomCompet2", null, false);

	@Test
	public void testGetMembres()
	{
		VarEquipe.add(Personne);
		assertEquals(Personne,VarEquipe.getMembres());
	}

}
