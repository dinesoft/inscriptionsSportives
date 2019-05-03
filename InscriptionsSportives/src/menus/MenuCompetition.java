package menus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.SortedSet;

import commandLineMenus.Action;
import commandLineMenus.List;
import commandLineMenus.Menu;
import commandLineMenus.Option;
import commandLineMenus.rendering.examples.util.InOut;
import inscriptions.Competition;
import inscriptions.Equipe;
import inscriptions.Inscriptions;
import inscriptions.Personne;

public class MenuCompetition {

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
	public static Option ajouterCompetitionOption(){
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
	public static Option afficherListeCompetitionOption(Inscriptions inscriptions) {
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
	public static  Option profilCompetitionOption(Inscriptions inscriptions) {
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
					System.out.println("C'est une competition en équipe");
				
				else
					System.out.println("C'est une competition en solo, c'est du chacun pour soi" );
				
				System.out.println("Voici les candidats inscrits :"+competition.getCandidats());
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
		if (competition.estEnEquipe() == true) 
		{
        return new List<>("Ajouter une équipe à la compétition", "a",     		
        		
        () -> new ArrayList<>(Inscriptions.getInscriptions().getEquipes()),
       (index, element) -> {competition.add((Equipe) element);});
        
       }
        		else
				{
        	        return new List<>("Ajouter un candidat à la compétition", "a",     		
        	        		
        	                () -> new ArrayList<>(Inscriptions.getInscriptions().getPersonnes()),
        	               (index, element) -> {competition.add((Personne) element);});
				}	
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
