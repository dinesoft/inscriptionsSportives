package inscriptions;

import commandLineMenus.Action;
import commandLineMenus.Menu;
import commandLineMenus.Option;
import commandLineMenus.rendering.examples.util.InOut;

public class Menus
{
	public static void main(String[] args)
	{
		// Creates the root menu of the application
		Menu rootMenu = new Menu("Root Menu");
		
		// Creates two options
		
		Menu personneMenu = new Menu("Menu Personne", "Personne", "p");
		Menu equipeMenu = new Menu("Menu Equipe", "Equipe", "e");
		Menu competitionMenu = new Menu("Menu Competition", "Competition", "c");
		
		// Adds an option to the rootMenu 
		
		
		// Adds the sub-menu sayHelloMenu to the rootMenu
		// Please notice that since Menu extends Option, polymorphism allows us to pass the Menu sayHelloMenu where an Option was expected.
		rootMenu.add(personneMenu);
		rootMenu.add(equipeMenu);
		rootMenu.add(competitionMenu);
		
		// Adds the quit option
		rootMenu.addQuit("q");
		
		// Defines the action that will be triggered if the calculator is selected.


		
		// Please notice that the action can be passed to the constructor of Option 
		personneMenu.add(				
				new Option("Ajouter", "a", new Action()
				{
					public void optionSelected()
					{
						System.out.println("Qui voulez-vous ajouter ?");
					}
				}));
		personneMenu.add(
				new Option("Afficher", "f", new Action()
				{
					public void optionSelected()
					{
						System.out.println("Il faudra afficher");
					}
				}));
		equipeMenu.add(				
				new Option("Ajouter", "a", new Action()
				{
					public void optionSelected()
					{
						System.out.println("Quelle équipe voulez-vous ajouter ?");
					}
				}));
		competitionMenu.add(				
				new Option("Afficher", "f", new Action()
				{
					public void optionSelected()
					{
						System.out.println("afficher les competitions");
					}
				}));
		
		// Adds an option to go back to the rootMenu
		personneMenu.addBack("r");
		equipeMenu.addBack("r");
		competitionMenu.addBack("r");
		
		// Once an option has been selected in sayHelloMenu, and the associated action is done, we will automatically go back to the rootMenu. 
	    personneMenu.setAutoBack(true);
	    equipeMenu.setAutoBack(true);
	    competitionMenu.setAutoBack(true);
		
		rootMenu.start();
	}
}