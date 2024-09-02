package ca.ahuntsic.projet2.classes;

import java.sql.Date;
import java.time.LocalDate;

public class Classique extends CompteBanquaire {

	// les attributs
	private int decouverte;
	private final int LIMITE_RETRAIT = 1000;
	private final int LIMITE_DECOUVERT = -500;
	
	/**
	 * constructeur sans parametre
	 */
	public Classique() {
		super();
	}

	/**
	 * constructeur avec un parametre
	 * @param numeroSuccursale : le numero de la succursale de la banque
	 */
	public Classique(String numeroSuccursale) {
		super(numeroSuccursale);
	}

	/**
	 * constructeur avec un parametre
	 * @param numeroSuccursale : le numero de la succursale de la banque
	 * @param solde : le montant a mettre dans le compte
	 */
	public Classique(double solde, String numeroSuccursale) {
		super(solde, numeroSuccursale);
	}
	
	/**
	 * defini le decouvert en fontion du solde
	 */
	public void decouvert() {
		if(this.solde <= 0) {
			setDecouverte(0);
		}
		else {
			setDecouverte((int)this.getSolde());
		}
	}

	/**
	 * retrait de l'argent du compte
	 */
	@Override
	public void retirer(double somme) {
		// verifie que la somme n'est pas negative
		if(somme > 0) {
			//verifi si la somme du retrait ne deppasse pas les limites de retrait
			if(somme < this.LIMITE_RETRAIT) {
				// verifie si le retrait permet de rester au dessus de la limite du decouvert
				double maxRetraitPossible = (LIMITE_DECOUVERT * -1) + solde;
				if(somme <= maxRetraitPossible) {
					this.setSolde(this.getSolde() - somme);
					decouvert();
				}
				// message d'erreur si le retrait fait deppasser la limite de decouvert
				else {
					throw new IllegalArgumentException("vous deppasserez la limite de decouvert possible avec ce retrait."
							+ " veillez retirer au maximum : " + maxRetraitPossible);
				}
			}
			// message d'erreur si la somme du retrait deppase la limite de retrait
			else {
				throw new IllegalArgumentException("veuillez entrer un montant inferieur a : " + this.LIMITE_RETRAIT);
			}
		}
		// message d'erreur si la somme de retrait est negative
		else {
			throw new IllegalArgumentException("veuillez entrer un chiffre negatif");
		}
		
	}

	/**
	 * @return the decouverte
	 */
	public int getDecouverte() {
		return decouverte;
	}

	/**
	 * @param decouverte the decouverte to set
	 */
	public void setDecouverte(int decouverte) {
		this.decouverte = decouverte;
	}

	/**
	 * @return the lIMITE_RETRAIT
	 */
	public int getLIMITE_RETRAIT() {
		return LIMITE_RETRAIT;
	}

	/**
	 * @return the lIMITE_DECOUVERT
	 */
	public int getLIMITE_DECOUVERT() {
		return LIMITE_DECOUVERT;
	}

	/**
	 * test la class Classique
	 * @param args
	 */
	public static void main(String[] args) {
		Classique classic = new Classique(200.0, "23");
		//System.out.println(classic);
		classic.retirer(100);
		//System.out.println("apres retrait : " + classic);
		classic.deposer(100);
		//System.out.println("apres depot : " + classic);
		classic.retirer(500);
		classic.retirer(100);
		classic.retirer(100);
		//System.out.println(classic);
		classic.ajouterOperation(new Operation(Date.valueOf(LocalDate.now()), TypeOperation.DEPOT, 200.0));
		classic.ajouterOperation(new Operation(Date.valueOf(LocalDate.now()), TypeOperation.RETRAIT, 10.0));
		//System.out.println(classic.etatCompte());
	}
}
