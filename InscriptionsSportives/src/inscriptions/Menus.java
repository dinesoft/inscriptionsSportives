package inscriptions;

import java.util.SortedSet;

import commandLineMenus.Action;
import commandLineMenus.Menu;
import commandLineMenus.Option;
import commandLineMenus.rendering.examples.util.InOut;

import  inscriptions.Inscriptions;
import  inscriptions.Personne;

public class Menus
{
	
	private static Inscriptions inscriptions;
	
	
	public static void main(String[] args)
	{
		
		inscriptions = Inscriptions.getInscriptions();
		
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
		
		personneMenu.add(ajouterPersonneOption());   //Ajouter
		personneMenu.add(afficherListePersonneOption());  //Afficher liste
		personneMenu.add(profilPersonneOption());  //Consulter profil
		personneMenu.add(inscrirePersonneOption());  //Inscrire une personne à une équipe ou une compétition
		
		// ***** Equipe ***** //
		equipeMenu.add(ajouterEquipeOption());  //Ajouter
		equipeMenu.add(afficherListeEquipeOption());  //Afficher liste
		equipeMenu.add(profilEquipeOption());  //Consulter profil
		equipeMenu.add(inscrireEquipeOption()); //Inscrire une équipe à une compétition

		// ***** Competition ***** //
		competitionMenu.add(ajouterCompetitionOption());  //Ajouter
		competitionMenu.add(afficherListeCompetitionOption());  //Afficher liste
		competitionMenu.add(profilCompetitionOption()); //Consulter profil
		
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
	
	// Création des options
	
	// ***** Personne ***** //
	/* Ajouter */
	private static Option ajouterPersonneOption(){
		
		return
		new Option("Ajouter une personne", "a", new Action()
		{
			public void optionSelected()
			{
				System.out.println("Qui voulez-vous ajouter ?");
				String nom = InOut.getString("Nom : ");
				String prenom = InOut.getString("Prénom : ");
				String mail = InOut.getString("Email : ");
				inscriptions.createPersonne(nom, prenom, mail);
			}
		});
	}
	/* Afficher liste */
	private static Option afficherListePersonneOption() {
		return
		new Option("Afficher la liste des personnes", "f", new Action()
		{
			public void optionSelected()
			{
				System.out.println("Voilà les personnes");
				SortedSet<Personne> lesPersonnes = inscriptions.getPersonnes();
				System.out.println(lesPersonnes);
			}
		});
	}
	/* Consulter profil */
	private static Option profilPersonneOption() {
		return
		new Option("Consulter le profil d'une personne", "c", new Action()
		{
			public void optionSelected()
			{
				System.out.println("Modifier / supprimer");
			}
		});
	}
	/* Inscrire à une équipe ou une compétition */
	private static Option inscrirePersonneOption() {
		return
		new Option("Inscrire une personne ‡ une compÈtion ou une Èquipe", "i", new Action()
		{
			public void optionSelected()
			{
				System.out.println("Inscription");
			}
		});
	}
	// ***** Equipe ***** //
	/* Ajouter */
	private static Option ajouterEquipeOption() {
		return
		new Option("Ajouter", "a", new Action()
		{
			public void optionSelected()
			{
				System.out.println("Quelle Équipe voulez-vous ajouter ?");
				String nom = InOut.getString("Le nom : ");
				inscriptions.createEquipe(nom);
			}
		});
	}
	/* Afficher liste */
	private static Option afficherListeEquipeOption() {
		return
		new Option("Afficher la liste des Èquipe", "f", new Action()
		{
			public void optionSelected()
			{
				System.out.println("Afficher liste");
				SortedSet<Equipe> lesEquipes = inscriptions.getEquipes();
				System.out.println(lesEquipes);
			}
		});
	}
	/* Consulter profil */
	private static Option profilEquipeOption() {
		return
		new Option("Consulter le profil d'une Équipe", "c", new Action()
		{
			public void optionSelected()
			{
				System.out.println("Modifier");
			}
		});
	}
	/* Inscrire à une compétition */
	private static Option inscrireEquipeOption() {
		return
		new Option("Inscrire une Équipe à une compÈtition", "i", new Action()
		{
			public void optionSelected()
			{
				System.out.println("À quelle compétition voulez vous inscrire cette équipe ?");
			}
		});
	}
	// ***** Competition ***** //
	/* Ajouter */	
	private static Option ajouterCompetitionOption() {
		return
		new Option("Ajouter", "a", new Action()
		{
			public void optionSelected()
			{
				System.out.println("Ajoutez une nouvelle compétition");
				String nom = InOut.getString("Nom de l'évènement: ");
				System.out.println("À quelle date la compétition va-t-elle avoir lieu ?");
				int dateCloture = InOut.getInt(" jj/mm/aaaa : ");
			}
		});
	}
	/* Afficher liste */
	private static Option afficherListeCompetitionOption() {
		return
		new Option("Afficher la liste des compÈtitions", "f", new Action()
		{
			public void optionSelected()
			{
				System.out.println("afficher les competitions");
				SortedSet<Competition> lesCompetitions = inscriptions.getCompetitions();
				System.out.println(lesCompetitions);
			}
		});
	}
	/* Consulter profil */
	private static Option profilCompetitionOption() {
		return
		new Option("Consulter la fiche d'une compÈtion", "c", new Action()
		{
			public void optionSelected()
			{
				System.out.println("afficher une compétition particulière");
			}
		});
	}
	
}