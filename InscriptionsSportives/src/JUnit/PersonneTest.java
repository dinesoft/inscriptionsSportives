package JUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import inscriptions.Competition;
import inscriptions.Equipe;
import inscriptions.Inscriptions;
import inscriptions.Personne;

public class PersonneTest {

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
	public void testGetPrenom()
	{
		assertTrue(varPersonne.getPrenom().contains("prenomPersonne"));
	}

	@Test
	public void testSetPrenom(String prenom)
	{
		varPersonne.setPrenom("Mya");
		String setPrenom = varPersonne.getPrenom();
		assertEquals("Mya", setPrenom);
	}

	@Test
	public void testGetMail()
	{
		assertTrue(varPersonne.getPrenom().contains("mailPersonne"));
	}

	@Test
	public void testSetMail(String mail)
	{
		varPersonne.setMail("MyaMail");
		String setMail = varPersonne.getMail();
		assertEquals("MyaMail", setMail);
	}

	@Test
	public void testGetEquipes()
	{
		assertTrue(inscriptions.getEquipes().contains(varEquipe));
	}
	
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
