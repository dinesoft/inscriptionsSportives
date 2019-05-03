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

public class MenuPersonne {

	// ***** Personne ***** //
	
		/* Ajouter */
		public static Option ajouterPersonneOption(Inscriptions inscriptions){
			
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
		public static Option afficherListePersonneOption(Inscriptions inscriptions) {
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
		public static Option profilPersonneOption(Inscriptions inscriptions) {
			
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
//			personneSousMenu.add(ajouterAEquipePersonneOption(personne));
//			personneSousMenu.add(ajouterACompetitionPersonneOption(personne));
			personneSousMenu.add(supprimerPersonneOption(personne));
			
			
//			personneSousMenu.setAutoBack(true);
			personneSousMenu.addBack("r");
//			personneSousMenu.addQuit("q");
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
//		/* ---- Ajouter à une équipe ---- */
//		private static Option ajouterAEquipePersonneOption(Personne personne)
//		{
//			return new Option("Integrer cette personne à une équipe", "e", new Action()
//			{
//				@Override
//				public void optionSelected()
//				{
//					
//				}
//			});
//		}
//		/* ---- Ajouter à une compétition ---- */
//		private static Option ajouterACompetitionPersonneOption(Personne personne)
//		{
//			return new Option("Inscrire cette personne a une competition", "c", new Action()
//			{
//				@Override
//				public void optionSelected()
//				{
//				
//				}
//			});
//		}	
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
	
	
}
