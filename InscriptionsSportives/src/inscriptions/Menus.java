package inscriptions;

import commandLineMenus.Action;
import commandLineMenus.Menu;
import commandLineMenus.Option;
import commandLineMenus.rendering.examples.util.InOut;

public class Menus
{
	public static void main(String[] args)
	{
		// Menu principal
		Menu rootMenu = new Menu("Root Menu");
		
		// Les options du menus
		
		Menu personneMenu = new Menu("Menu Personne", "Personne", "p");
		Menu equipeMenu = new Menu("Menu Equipe", "Equipe", "e");
		Menu competitionMenu = new Menu("Menu Competition", "Competition", "c");
		
		
		// Ajout des sous-menus
		
		rootMenu.add(personneMenu);
		rootMenu.add(equipeMenu);
		rootMenu.add(competitionMenu);
		
		// Ajout de l'option pour quitter
		rootMenu.addQuit("q");
		
		


		
		// Ajout des options possibles dans les sous-menus Personne / Equipe / Competition

		// ***** Personne ***** //
		personneMenu.add(				
				new Option("Ajouter une personne", "a", new Action()
				{
					public void optionSelected()
					{
						System.out.println("Qui voulez-vous ajouter ?");
					}
				}));
		personneMenu.add(
				new Option("Afficher la liste des personnes", "f", new Action()
				{
					public void optionSelected()
					{
						System.out.println("Il faudra afficher");
					}
				}));
		personneMenu.add(
				new Option("Consulter le profil d'une personne", "c", new Action()
				{
					public void optionSelected()
					{
						System.out.println("Modifier / supprimer");
					}
				}));
		personneMenu.add(
				new Option("Inscrire une personne ‡ une compÈtion ou une Èquipe", "i", new Action()
				{
					public void optionSelected()
					{
						System.out.println("Inscription");
					}
				}));
		
		// ***** Equipe ***** //
		equipeMenu.add(				
				new Option("Ajouter", "a", new Action()
				{
					public void optionSelected()
					{
						System.out.println("Quelle Èquipe voulez-vous ajouter ?");
					}
				}));
		equipeMenu.add(				
				new Option("Afficher la liste des Èquipe", "f", new Action()
				{
					public void optionSelected()
					{
						System.out.println("Afficher liste");
					}
				}));
		equipeMenu.add(				
				new Option("Consulter le profil d'une Èquipe", "c", new Action()
				{
					public void optionSelected()
					{
						System.out.println("Quelle Èquipe voulez-vous ajouter ?");
					}
				}));
		equipeMenu.add(				
				new Option("Inscrire une Èquipe ‡ une compÈtition", "i", new Action()
				{
					public void optionSelected()
					{
						System.out.println("Quelle Èquipe voulez-vous ajouter ?");
					}
				}));

		// ***** Competition ***** //
		competitionMenu.add(				
				new Option("Ajouter", "a", new Action()
				{
					public void optionSelected()
					{
						System.out.println("afficher les competitions");
					}
				}));
		competitionMenu.add(				
				new Option("Afficher la liste des compÈtitions", "f", new Action()
				{
					public void optionSelected()
					{
						System.out.println("afficher les competitions");
					}
				}));
		competitionMenu.add(				
				new Option("Consulter la fiche d'une compÈtion", "c", new Action()
				{
					public void optionSelected()
					{
						System.out.println("afficher les competitions");
					}
				}));
		
		// Ajout du retour vers le menu principal
		personneMenu.addBack("r");
		equipeMenu.addBack("r");
		competitionMenu.addBack("r");
		
		// Retour automatique vers le menu principal
	    personneMenu.setAutoBack(true);
	    equipeMenu.setAutoBack(true);
	    competitionMenu.setAutoBack(true);
		
		rootMenu.start();
	}
}