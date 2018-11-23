package JUnit;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import inscriptions.Candidat;
import inscriptions.Competition;
import inscriptions.Equipe;
import inscriptions.Inscriptions;
import inscriptions.Personne;

public class InscriptionsTest {
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
	public void testGetCompetitions()
	{
		assertTrue(inscriptions.getCompetitions().contains(varCompet1));
	}
	
	@Test
	public void testGetCandidats()
	{
		assertTrue(inscriptions.getCandidats().contains(varPersonne));
	}
	
	@Test
	public void testGetPersonnes()
	{
		assertTrue(inscriptions.getPersonnes().contains(varPersonne));
	}
	
	@Test
	public void testCreateCompetitions() 
	{
		assertTrue(inscriptions.getCompetitions().contains(varCompet1));
	}
	
	@Test
	public void testCreateEquipe()
	{
		assertTrue(inscriptions.getCandidats().contains(varEquipe));
	}
	
	@Test
	public void testCreateCandidat()
	{
		assertTrue(inscriptions.getCandidats().contains(varPersonne));
	}
	
	@Test
	public void testGetInscriptions()
	{
		assertNotNull(inscriptions);
	}
	
	@Test
	public void testReinitialiser()
	{
		inscriptions.reinitialiser();
		assertNotNull("Reinitialisation reussie", inscriptions);
	}
	
	@Test
	public void testRecharger()
	{
		assertNotNull(inscriptions.recharger());
	}	
	
	@Test
	public void testSauvegarder() throws IOException 
	{
		inscriptions.sauvegarder();
		assertNotNull("Votre sauvegarde a bien été effectuée",inscriptions);
	}

}
