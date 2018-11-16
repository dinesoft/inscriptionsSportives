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

import org.junit.Test;

import inscriptions.Candidat;
import inscriptions.Competition;
import inscriptions.Equipe;
import inscriptions.Inscriptions;
import inscriptions.Personne;

public class InscriptionsTest {

	@Test
	public SortedSet<Competition> testGetCompetitions()
	{
		return Collections.unmodifiableSortedSet(competitions);
	}
	
	@Test
	public SortedSet<Candidat> testGetCandidats()
	{
		return Collections.unmodifiableSortedSet(candidats);
	}

	@Test
	public SortedSet<Personne> testGetPersonnes()
	{
		SortedSet<Personne> personnes = new TreeSet<>();
		for (Candidat c : getCandidats())
			if (c instanceof Personne)
				personnes.add((Personne)c);
		return Collections.unmodifiableSortedSet(personnes);
	}

	@Test
	public SortedSet<Equipe> testGetEquipes()
	{
		SortedSet<Equipe> equipes = new TreeSet<>();
		for (Candidat c : getCandidats())
			if (c instanceof Equipe)
				equipes.add((Equipe)c);
		return Collections.unmodifiableSortedSet(equipes);
	}

	@Test
	public Competition testCreateCompetition(String nom, 
			LocalDate dateCloture, boolean enEquipe)
	{
		Competition competition = new Competition(this, nom, dateCloture, enEquipe);
		competitions.add(competition);
		return competition;
	}

	@Test
	public Personne testCreatePersonne(String nom, String prenom, String mail)
	{
		Personne personne = new Personne(this, nom, prenom, mail);
		candidats.add(personne);
		return personne;
	}
	
	@Test
	public Equipe testCreateEquipe(String nom)
	{
		Equipe equipe = new Equipe(this, nom);
		candidats.add(equipe);
		return equipe;
	}
	
	@Test
	void testDelete(Competition competition)
	{
		competitions.remove(competition);
	}
	
	@Test
	void testDelete(Candidat candidat)
	{
		candidats.remove(candidat);
	}
	
	@Test
	public static Inscriptions testGetInscriptions()
	{
		
		if (inscriptions == null)
		{
			inscriptions = readObject();
			if (inscriptions == null)
				inscriptions = new Inscriptions();
		}
		return inscriptions;
	}

	@Test
	public Inscriptions testReinitialiser()
	{
		inscriptions = new Inscriptions();
		return getInscriptions();
	}
	
	@Test
	public Inscriptions testRecharger()
	{
		inscriptions = null;
		return getInscriptions();
	}
	
	@Test
	private static Inscriptions readObject()
	{
		ObjectInputStream ois = null;
		try
		{
			FileInputStream fis = new FileInputStream(FILE_NAME);
			ois = new ObjectInputStream(fis);
			return (Inscriptions)(ois.readObject());
		}
		catch (IOException | ClassNotFoundException e)
		{
			return null;
		}
		finally
		{
				try
				{
					if (ois != null)
						ois.close();
				} 
				catch (IOException e){}
		}	
	}	
	
	@Test
	public void sauvegarder() throws IOException
	{
		ObjectOutputStream oos = null;
		try
		{
			FileOutputStream fis = new FileOutputStream(FILE_NAME);
			oos = new ObjectOutputStream(fis);
			oos.writeObject(this);
		}
		catch (IOException e)
		{
			throw e;
		}
		finally
		{
			try
			{
				if (oos != null)
					oos.close();
			} 
			catch (IOException e){}
		}
	}
	
	@Test
	public String toString()
	{
		return "Candidats : " + getCandidats().toString()
			+ "\nCompetitions  " + getCompetitions().toString();
	}
	
	

}
