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


public class MenuEquipe {
	
	/* Ajouter */
	public static Option ajouterEquipeOption(Inscriptions inscriptions) {
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
	public static Option afficherListeEquipeOption(Inscriptions inscriptions) {
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
	public static  Option profilEquipeOption(Inscriptions inscriptions) {
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
	

}
