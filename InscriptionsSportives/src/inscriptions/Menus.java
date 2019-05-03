package inscriptions;

import java.util.SortedSet;
import java.time.LocalDate;
import java.util.ArrayList;

import commandLineMenus.Action;
import commandLineMenus.List;
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
		
		// Les options du menu
		
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
		
		// ***** Equipe ***** //
		equipeMenu.add(ajouterEquipeOption());  //Ajouter
		equipeMenu.add(afficherListeEquipeOption());  //Afficher liste
		equipeMenu.add(profilEquipeOption());  //Consulter profil

		// ***** Competition ***** //
		competitionMenu.add(ajouterCompetitionOption());  //Ajouter
		competitionMenu.add(afficherListeCompetitionOption());  //Afficher liste
		competitionMenu.add(profilCompetitionOption()); //Consulter profil
		
		// Ajout du retour vers le menu principal
		personneMenu.addBack("r");
		equipeMenu.addBack("r");
		competitionMenu.addBack("r");
		
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
		
		return new List<Personne>("Editer le profil d'une personne", "e", 
				() -> new ArrayList<>(inscriptions.getPersonnes()),
				(element) -> getPersonneSousMenu(element)
				);		
	} 

	/* Accéder aux options d'une personne*/
	private static Option getPersonneSousMenu(Personne personne)
	{
		Menu personneSousMenu = new Menu("Accéder aux options pour  "
				+personne.getPrenom()+" "+personne.getNom(),null);
		
		personneSousMenu.add(voirProfilPersonneOption(personne));
		personneSousMenu.add(editerPrenomPersonneOption(personne));
		personneSousMenu.add(editerNomPersonneOption(personne));
//		personneSousMenu.add(ajouterAEquipePersonneOption(personne));
//		personneSousMenu.add(ajouterACompetitionPersonneOption(personne));
		personneSousMenu.add(supprimerPersonneOption(personne));
		
		
//		personneSousMenu.setAutoBack(true);
		personneSousMenu.addBack("r");
//		personneSousMenu.addQuit("q");
		return personneSousMenu;
	}
	/* ---- Profil ---- */
	private static Option voirProfilPersonneOption(Personne personne)
	{
		return new Option("Consulter profil", "p", new Action()
		{
			@Override
			public void optionSelected()
			{
				System.out.println("Prénom : "+personne.getPrenom()+"\nNom : "+
						personne.getNom()+"\nMail : "+personne.getMail());
				if(!personne.getEquipes().isEmpty())
				{
					System.out.println(personne.getEquipes().toString());
				}
				else
				{
					System.out.println(personne.getPrenom()+ " n'est dans aucune équipe");
				}
				if(!personne.getCompetitions().isEmpty())
				{
					System.out.println("Participe à "+personne.getCompetitions().toString());
				}
				else
				{
					System.out.println(personne.getPrenom()+" ne participe à aucune compétition");
				}
			}
		});
	}
	/* ---- Editer prénom ---- */
	private static Option editerPrenomPersonneOption(Personne personne){
		return
		new Option("Modifier le prénom", "m", new Action()
		{
			@Override
			public void optionSelected()
			{
				System.out.println("Veuillez saisir le nouveau prenom :");
				String prenom = InOut.getString("Prenom :");
				personne.setPrenom(prenom);
				System.out.println("Le prénom à bien été changé");
			}
		});
	}	
	/* ---- Editer nom ---- */
	private static Option editerNomPersonneOption(Personne personne){
		return
		new Option("Modifier le nom", "n", new Action()
		{
			@Override
			public void optionSelected()
			{
				System.out.println("Veuillez saisir le nouveau nom :");
				String nom = InOut.getString("Nom :");
				personne.setNom(nom);
				System.out.println("Le prénom à bien été changé");
			}
		});
	}
//	/* ---- Ajouter à une équipe ---- */
//	private static Option ajouterAEquipePersonneOption(Personne personne)
//	{
//		return new Option("Integrer cette personne à une équipe", "e", new Action()
//		{
//			@Override
//			public void optionSelected()
//			{
//				
//			}
//		});
//	}
//	/* ---- Ajouter à une compétition ---- */
//	private static Option ajouterACompetitionPersonneOption(Personne personne)
//	{
//		return new Option("Inscrire cette personne a une competition", "c", new Action()
//		{
//			@Override
//			public void optionSelected()
//			{
//			
//			}
//		});
//	}	
	/* ---- Supprimer ---- */
	private static Option supprimerPersonneOption(Personne personne)
	{
		return 
		new Option("Inscrire cette personne a une competition", "c", new Action()
		{
			@Override
			public void optionSelected()
		    {
		        personne.delete();
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
	private static  Option profilEquipeOption() {
	return  
	new List<Equipe>("Editer le profil d'une équipe", "e", 
						() -> new ArrayList<>(inscriptions.getEquipes()),
						(element) -> getEquipeSousMenu(element)
						);		
		
	}
	
	private static Option getEquipeSousMenu(Equipe equipe)
	{
		Menu equipeSousMenu = new Menu("Accéder aux options pour  "
				+equipe.getNom(),null);
		
		equipeSousMenu.add(voirProfilEquipeOption(equipe));
		equipeSousMenu.add(editerNomEquipeOption(equipe));
		equipeSousMenu.add(ajouterMembreAEquipeOption(equipe));
		equipeSousMenu.add(supprimerMembreAEquipeOption(equipe));
		equipeSousMenu.add(supprimerEquipeOption(equipe));
//		equipeSousMenu.add(ajouterACompetitionEquipeOption(equipe));
		
		
		
		equipeSousMenu.setAutoBack(true);
		equipeSousMenu.addQuit("q");
		return equipeSousMenu;	
		
	}	
	
	/* ---- Profil ---- */	
	private static Option voirProfilEquipeOption(Equipe equipe) {
		return 
		new Option("Consulter profil", "p", new Action()
		{
			@Override
			public void optionSelected()
			{
				System.out.println("Nom : "+ equipe.getNom());
				
				if(!equipe.getCompetitions().isEmpty())
				{
					System.out.println("Participe à "+equipe.getCompetitions().toString());
				}
				else 
				{
					System.out.println(equipe.getNom()+" ne participe à aucune compétition, c'est un peu triste ");
				}
				if(!equipe.getMembres().isEmpty())
				{
					System.out.println("Membres : "+ equipe.getMembres());
				}
				else 
				{
					System.out.println("Personne n'a encore la chance de faire parti de cette équipe... ");
				}
//				System.out.println("Membres : "+ equipe.getMembres());
				
			}
		});		
	}
	/* ---- Editer nom ---- */
	private static Option editerNomEquipeOption(Equipe equipe){
		return
		new Option("Modifier le nom de l'équipe", "m", new Action()
		{
			@Override
			public void optionSelected()
			{
				System.out.println("Veuillez saisir le nouveau nom :");
				String nom = InOut.getString("Nom :");
				equipe.setNom(nom);
				System.out.println("Le nom de l'équipe à bien été changé");
			}
		});
	}	
	/* ---- Ajouter membre ---- */
    private static List<Personne> ajouterMembreAEquipeOption(Equipe equipe)
    {
        return new List<>("Ajouter un membre à l'équipe", "a",
        () -> new ArrayList<>(Inscriptions.getInscriptions().getPersonnes()),
        (index, element) -> {equipe.add(element);}
        );
    }	
    /* ---- Supprimer membre ---- */
    private static List<Personne> supprimerMembreAEquipeOption(Equipe equipe)
    {
        return new List<>("Supprimer un membre", "s",
        () -> new ArrayList<>(equipe.getMembres()),
        (index, element) -> {equipe.remove(element);}
        );
    }    	
    /* ---- Supprimer équipe ---- */
    private static Option supprimerEquipeOption(Equipe equipe)
    {
        return new Option("Supprimer l'équipe", "S",
        () ->
        {
            equipe.delete();
        }
        );
    }   
	
//	private static Option ajouterACompetitionEquipeOption(Equipe equipe) {.}
	

    
	// ***** Competition ***** //
	/* Ajouter */	
//	private static Option ajouterCompetitionOption() {
//		return
//		new Option("Ajouter", "a", new Action()
//		{
//			public void optionSelected()
//			{
//				System.out.println("Ajoutez une nouvelle compétition");
//				String nom = InOut.getString("Nom de l'évènement: ");
//				System.out.println("À quelle date la compétition va-t-elle avoir lieu ?");
//				int dateCloture = InOut.getInt(" jj/mm/aaaa : ");
//			}
//		});
//	}
    private static Option ajouterCompetitionOption(){
        return new Option("Ajouter une compétition", "a", () -> {
        	
        	
        	try {
                Inscriptions.getInscriptions().createCompetition(InOut.getString("Nom de l'évènement : "), 
                		LocalDate.of(InOut.getInt("Date de la compétition \nAnnee :"),
                 InOut.getInt("Mois :"),
                 InOut.getInt("Jour :")), 
                 InOut.getInt("0 - Compétition solo \n1 - Compétition en Equipe : ") == 1);
                } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }	
	/* Afficher liste */
	private static Option afficherListeCompetitionOption() {
		return
		new Option("Afficher la liste des compétitions", "f", new Action()
		{
			public void optionSelected()
			{
				System.out.println("Voici la liste de toutes les compétitions ");
				SortedSet<Competition> lesCompetitions = inscriptions.getCompetitions();
				System.out.println(lesCompetitions);
				
			}
		});
	}
	/* Consulter profil */
	private static  Option profilCompetitionOption() {
	return  
	new List<Competition>("Editer la fiche d'une compétition", "e", 
						() -> new ArrayList<>(inscriptions.getCompetitions()),
						(element) -> getCompetitionSousMenu(element)
						);		
		
	}
	private static Option getCompetitionSousMenu(Competition competition)
	{
		Menu competitionSousMenu = new Menu("Accéder aux options pour  "
				+competition.getNom(),null);
		
		competitionSousMenu.add(voirFicheCompetitionOption(competition));
		competitionSousMenu.add(editerNomCompetitionOption(competition));
		competitionSousMenu.add(ajouterCandidatACompetitionOption(competition));
		competitionSousMenu.add(supprimerCandidatACompetitionOption(competition));
		competitionSousMenu.add(supprimerCompetitionOption(competition));
		
		competitionSousMenu.addBack("r");
		return competitionSousMenu;	
		
	}
	/* ---- Afficher profil ---- */
	private static Option voirFicheCompetitionOption(Competition competition)
	{
		return new Option("Voir la fiche détaillée d'une compétition", "v", new Action()
		{
			@Override
			public void optionSelected()
			{
				System.out.println("Nom de la compétition : " +competition.getNom());
				if (competition.inscriptionsOuvertes() == true) 
					System.out.println("Les inscriptions sont ouvertes " );
				else
					System.out.println("Les inscriptions sont cloturées " );
				System.out.println("Date cloture : " +competition.getDateCloture());
				if (competition.estEnEquipe() == true) 
					System.out.println("C'est une competition en équipe" );
				else
					System.out.println("C'est une competition en solo, c'est du chacun pour soi" );
			}
		});
	}	
	/* ---- Editer nom ---- */
	private static Option editerNomCompetitionOption(Competition competition)
	{
		return new Option("Modifier le nom de la compétition", "m", new Action()
		{
			@Override
			public void optionSelected()
			{
				System.out.println("Veuillez saisir le nouveau super nom (choisissez bien cette fois)");
				String nom = InOut.getString("Nom : ");
				competition.setNom(nom);
				System.out.println("Le nouveau nom est : "+competition.getNom() + " c'est beaucoup plus sympa !");
			}
		});
	}
	/* ---- Ajouter candidat ---- */
	private static Option ajouterCandidatACompetitionOption(Competition competition)
	{
        return new List<>("Ajouter une personne dans la compétition", "a",
        () -> new ArrayList<>(Inscriptions.getInscriptions().getCandidats()),
        (index, element) -> {competition.add((Personne) element);}
        );
	}
	/* ---- Supprimer candidat, attention c'est méchant ---- */
	private static Option supprimerCandidatACompetitionOption(Competition competition)
	{
		return new List<>("Supprimer un candidat", "s", () ->
        new ArrayList<>(competition.getCandidats()),
        (index, element) -> {competition.remove(element);
        });
	}
	/* ---- Supprimer compétition, attention il n'y a pas de retour en arrière ---- */
	private static Option supprimerCompetitionOption(Competition competition)
	{
		return new Option("Supprimer la compétition", "S", new Action()
		{
			@Override
			public void optionSelected()
			{
				competition.delete();
				System.out.println("La compétition"+ competition.getNom() + "à bien été supprimée, ne le regrettez pas car vous ne pourrez pas revenir en arrière");
			}
		});
		
	}	
	
}