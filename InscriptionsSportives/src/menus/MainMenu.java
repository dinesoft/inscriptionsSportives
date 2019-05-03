package menus;
 
import inscriptions.*;


public class MainMenu {
	public static void main(String[] args) {
		new MenuInscription(inscriptions.Inscriptions.getInscriptions());
	}
}
