package ca.ahuntsic.projet2.classes;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public abstract class CompteBanquaire implements Gestion {
	
	// les attributs
	protected String identifiant;
	protected double solde;
	protected Date dateOuverture;
	protected ArrayList<Operation> operations;
	protected final int LIMITE_DEPOT = 10000;
	protected static int nbCompte;
	
	/**
	 * constructeur sans parametre
	 */
	public CompteBanquaire() {
		setDateOuverture(Date.valueOf(LocalDate.now()));
		operations = new ArrayList<>();
		nbCompte++;
	}
	
	/**
	 * constructeur avec un parametre
	 * @param numeroSuccursale : le numero de la succursale de la banque
	 */
	public CompteBanquaire(String numeroSuccursale) {
		setIdentifiant(creationIdentification(numeroSuccursale));
		setDateOuverture(Date.valueOf(LocalDate.now()));
		operations = new ArrayList<>();
		nbCompte++;
	}
	
	/**
	 * constructeur avec un parametre
	 * @param solde : le solde du compte
	 * @param numeroSuccursale : le numero de la succursale de la banque
	 * @param solde : le montant a mettre dans le compte
	 */
	public CompteBanquaire(double solde, String numeroSuccursale) {
		this(numeroSuccursale);
		setSolde(solde);
	}
	
	/**
	 * cree un numero pour le comptecourant
	 * @param numeroSuccursale : le numero de la succursale de la banque
	 * @return : le l'identifiant
	 */
	public String creationIdentification(String numeroSuccursale) {
		return Generateur.genererCode(4, this.nbCompte, Integer.parseInt(numeroSuccursale));
	}
	
	/**
	 * ajoute une operation au compte
	 * @param operation : l'operation a ajouter au compte
	 */
	public void ajouterOperation(Operation operation) {
		operations.add(operation);
	}
	/**
	 * depos de l'argent sur le compte
	 */
	@Override
	public void deposer(double somme) {
		// verifie que la somme n'est pas negative
			if(somme > 0) {
				//verifi si la somme du retrait ne deppasse pas les limites de retrait
				if(somme < this.LIMITE_DEPOT) {
					this.setSolde(this.getSolde() + somme);
				}
				// message d'erreur si la somme du retrait deppase la limite de retrait
				else {
					throw new IllegalArgumentException("veuillez entrer un montant inferieur a : " + this.LIMITE_DEPOT);
				}
			}
			// message d'erreur si la somme de retrait est negative
		else {
			throw new IllegalArgumentException("veuillez entrer un chiffre negatif");
		}
	}
	
	/**
	 * affiche l'etat du compte
	 * @return : le toString du compte ainsi que ses transactions
	 */
	public String etatCompte() {
		String etatCompte = "";
		etatCompte += this.toString();
		for(Operation o : operations) {
			if(o != null) {
				etatCompte += "\n\t\t" + o.toString();
			}
		}
		return etatCompte;
	}
	
	/**
	 * affiche le type de compte, son identification, sa date de creation ainsi que son solde
	 */
	public String toString() {
		return this.getClass().getSimpleName() + " : " + identifiant + ", Ouvert le " + dateOuverture + ", " + solde + " $, ";
	}
	
	/**
	 * @return the numero
	 */
	public String getIdentifiant() {
		return identifiant;
	}
	/**
	 * @param numero the numero to set
	 */
	public void setIdentifiant(String numero) {
		this.identifiant = numero;
	}
	/**
	 * @return the solde
	 */
	public double getSolde() {
		return solde;
	}
	/**
	 * @param solde the solde to set
	 */
	public void setSolde(double solde) {
		this.solde = solde;
	}
	/**
	 * @return the dateOuverture
	 */
	public Date getDateOuverture() {
		return dateOuverture;
	}
	/**
	 * @param dateOuverture the dateOuverture to set
	 */
	public void setDateOuverture(Date dateOuverture) {
		this.dateOuverture = dateOuverture;
	}
	/**
	 * @return the operations
	 */
	public ArrayList<Operation> getOperations() {
		return operations;
	}
	/**
	 * @param operations the operations to set
	 */
	public void setOperations(ArrayList<Operation> operations) {
		this.operations = operations;
	}
	/**
	 * @return the lIMITE_DEPOT
	 */
	public int getLIMITE_DEPOT() {
		return LIMITE_DEPOT;
	}
	
	/**
	 * test la class CompteBanquaire. par contre il faut enlever le abstract
	 * @param args
	 */
	public static void main(String[] args) {
		//CompteBanquaire test = new CompteBanquaire(250.00, "23");
		//	CompteBanquaire test2 = new CompteBanquaire(250.00, "23");
		//Operation testOperation = new Operation(LocalDate.now(), TypeOperation.DEPOT, 250.00);
		//test2.ajouterOperation(testOperation);
		//System.out.println("Le compte : " + test2.toString());
		//System.out.println(test2.etatCompte());
	}
}
