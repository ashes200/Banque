package ca.ahuntsic.projet2.classes;

/**
 * Identification: Description de la classe Auteur : debra
 */
public class Identification {

	// les attributs
	private String pseudo;
	private String password;

	/**
	 * constructeur sans parametre
	 */
	public Identification() {
	}

	/**
	 * constructeur avec parametre
	 *
	 * @param pseudo
	 * @param password
	 */
	public Identification(String pseudo, String password) {
		setPseudo(pseudo);
		setPassword(password);
	}

	/**
	 * @return the pseudo
	 */
	public String getPseudo() {
		return pseudo;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param pseudo the pseudo to set
	 */
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
