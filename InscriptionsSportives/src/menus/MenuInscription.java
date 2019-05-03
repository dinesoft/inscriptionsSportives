package menus;

import inscriptions.*;
import commandLineMenus.*;

public class MenuInscription {

	private Inscriptions inscriptions;
	
	public MenuInscription(Inscriptions inscriptions) {
		this.inscriptions = inscriptions;

		
		Menu rootMenu = new Menu("Root Menu");
		
		rootMenu.add(getCompetitionMenu(inscriptions));
		rootMenu.add(getEquipeMenu(inscriptions));
		rootMenu.add(getPersonneMenu(inscriptions));
		rootMenu.addQuit("q");
		rootMenu.start();
	}


	// ***** Personne ***** //
	private Menu getPersonneMenu(Inscriptions inscriptions)
	{
		Menu personneMenu = new Menu("Menu Personne", "Personne", "p");
		personneMenu.add(MenuPersonne.ajouterPersonneOption(inscriptions));   //Ajouter
		personneMenu.add(MenuPersonne.afficherListePersonneOption(inscriptions));  //Afficher liste
		personneMenu.add(MenuPersonne.profilPersonneOption(inscriptions));  //Consulter profil
		personneMenu.addBack("r"); // Ajout du retour vers le menu principal
		
		return personneMenu;
	}	
	
	// ***** Equipe ***** //
	private Menu getEquipeMenu(Inscriptions inscriptions)
	{
		Menu equipeMenu = new Menu("Menu Equipe", "Equipe", "e");
		equipeMenu.add(MenuEquipe.ajouterEquipeOption(inscriptions));  //Ajouter
		equipeMenu.add(MenuEquipe.afficherListeEquipeOption(inscriptions));  //Afficher liste
		equipeMenu.add(MenuEquipe.profilEquipeOption(inscriptions));  //Consulter profil
		equipeMenu.addBack("r"); // Ajout du retour vers le menu principal
		
		return equipeMenu;
	}		
	
	
	
	// ***** Competition ***** //
	private Menu getCompetitionMenu(Inscriptions inscriptions)
	{
	Menu competitionMenu = new Menu("Menu Competition", "Competition", "c");
	competitionMenu.add(MenuCompetition.ajouterCompetitionOption());  //Ajouter
	competitionMenu.add(MenuCompetition.afficherListeCompetitionOption(inscriptions));  //Afficher liste
	competitionMenu.add(MenuCompetition.profilCompetitionOption(inscriptions)); //Consulter profil
	competitionMenu.addBack("r"); // Ajout du retour vers le menu principal
	
	return competitionMenu;
	}
	
	
}
