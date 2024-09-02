package ca.ahuntsic.projet2.classes;

import java.util.ArrayList;

/**
 * Authentification: Description de la classe Auteur : debra
 */
public class Authentification {

	// Attribut
	public static ArrayList<Identification> id;

	/**
	 * constructeur sans parametre qui initialise deux identitées
	 */
	public Authentification() {
		id = new ArrayList<Identification>();
		id.add(new Identification("seniorita", "mot de passe"));
		id.add(new Identification("debraise", "2braise"));
	}

	/**
	 * verifie si le pseudo et le password corespondent a une identité enregitré
	 *
	 * @param pseudo
	 * @param password
	 * @return
	 */
	public boolean verification(String pseudo, String password) {
		boolean verif = false;
		for (Identification ident : id) {
			if (ident.getPseudo().equalsIgnoreCase(pseudo) && ident.getPassword().equalsIgnoreCase(password)) {
				verif = true;
			}
		}
		return verif;
	}

	/**
	 * @return the id
	 */
	public static ArrayList<Identification> getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public static void setId(ArrayList<Identification> id) {
		Authentification.id = id;
	}

}
