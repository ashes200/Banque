package ca.ahuntsic.projet2.classes;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class Epargne extends CompteBanquaire {

	// les attributs
	private double interet;
	private double tauxInteret;
	
	/**
	 * constructeur sans parametre
	 */
	public Epargne() {
		super();
		setInteret(0.1);
	}

	/**
	 * constructeur avec parametre
	 * @param numeroSuccursale : le numero de la sucursale de la banque
	 * @param tauxInteret : le taux d'interet appliquer au compte
	 */
	public Epargne(String numeroSuccursale, double tauxInteret) {
		super(numeroSuccursale);
		setTauxInteret(tauxInteret);
	}

	/**
	 * 
	 * constructeur avec parametre
	 * @param numeroSuccursale : le numero de la sucursale de la banque
	 * @param tauxInteret : le taux d'interet appliquer au compte
	 * @param solde : le montant a mettre dans le compte
	 */
	public Epargne(double solde, String numeroSuccursale, double tauxInteret) {
		super(solde, numeroSuccursale);
		setTauxInteret(tauxInteret);
	}

	/**
	 * retrait de l'argent du compte : cette option n'est pas possible sur ce compte
	 */
	@Override
	public void retirer(double somme) {
		throw new IllegalArgumentException("Vous ne pouvez pas retirer d'argent "
				+ "avec un compte epargne");
	}

	
	/**
	 * calcule le taux d'interet depuis la date de depot et l'ajoute a l'interet deja gagner
	 */
	public void calculeInteret(LocalDate dateDepot) {
		int differenceJours = (int) ChronoUnit.DAYS.between(dateDepot, LocalDate.now());
		interet += ((solde * differenceJours * tauxInteret) / 100) / 365;
	}
	
	/**
	 * affiche le type de compte, son identification, sa date de creation ainsi que son solde
	 * son taux d'interet et ses interets
	 */
	public String toString() {
		return super.toString() + "Taux : " + tauxInteret + "%, Interet : " + interet + "$";
	}

	/**
	 * @return the interet
	 */
	public double getInteret() {
		return interet;
	}

	/**
	 * @param interet the interet to set
	 */
	public void setInteret(double interet) {
		this.interet = interet;
	}

	/**
	 * @return the tauxInteret
	 */
	public double getTauxInteret() {
		return tauxInteret;
	}

	/**
	 * @param tauxInteret the tauxInteret to set
	 */
	public void setTauxInteret(double tauxInteret) {
		this.tauxInteret = tauxInteret;
	}
	
	/**
	 * test de la class Epargne
	 * @param args
	 */
	public static void main(String[] args) {
		Epargne ep = new Epargne(36500.0, "23", 5.0);
		//System.out.println(ep);
		ep.calculeInteret(LocalDate.parse("2022-07-21"));
		//System.out.println("apres depos" + ep);
	}
}
