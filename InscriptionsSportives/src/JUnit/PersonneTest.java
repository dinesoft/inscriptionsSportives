package JUnit;

import java.util.Collections;
import java.util.Set;

import inscriptions.Equipe;

public class PersonneTest {

	@Test
	public String testGetPrenom()
	{
		return prenom;
	}

	@Test
	public void testSetPrenom(String prenom)
	{
		this.prenom = prenom;
	}

	@Test
	public String testGetMail()
	{
		return mail;
	}

	@Test
	public void testSetMail(String mail)
	{
		this.mail = mail;
	}

	@Test
	public Set<Equipe> testGetEquipes()
	{
		return Collections.unmodifiableSet(equipes);
	}
	
	@Test
	boolean testAdd(Equipe equipe)
	{
		return equipes.add(equipe);
	}
	
	@Test
	boolean testRemove(Equipe equipe)
	{
		return equipes.remove(equipe);
	}
	
	@Test
	public void testDelete()
	{
		super.delete();
		for (Equipe e : equipes)
			e.remove(this);
	}
	
	@Test
	public String testToString()
	{
		return super.toString() + " membre de " + equipes.toString();
	}
}
