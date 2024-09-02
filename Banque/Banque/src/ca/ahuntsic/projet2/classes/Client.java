/**
 * 
 */
package ca.ahuntsic.projet2.classes;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

/**
 * @author debra
 *
 */
public class Client implements Gestion {
	
	// les attributs
	private String nom;
	private String prenom;
	ArrayList<CompteBanquaire> comptes;
	private Operation op;
	static int indiceCourant;
	private int MAX_NB_COMPTE = 10;
	
	/**
	 * constructeur sans parametre
	 */
	public Client() {
		this("xxxx", "xxxx");
	}
	
	/**
	 * constructeur avec parametre
	 * @param nom : le nom du client
	 * @param Prenom : le prenom du client
	 */
	public Client(String nom, String Prenom) {
		setNom(nom);
		setPrenom(Prenom);
		comptes = new ArrayList<>();
	}
	
	/**
	 * cree/ajoute un compte banquaire au client courant
	 * @param compteBanquaire : le compte banquaire a jouter au client
	 * @throws Exception : impossible de creer un compte si le nombre maximun
	 * de compte a deja ete creer
	 */
	public void ouvrirCompteBanquaire(CompteBanquaire compteBanquaire) throws Exception {
		if(comptes.size() < MAX_NB_COMPTE) {
		comptes.add(compteBanquaire);
		}
		else {
			throw new Exception("impossible de cree un nouveau compte banquaire car,"
					+ "le nombre maximun de compte possible est atteint");
		}
	}
	
	/**
	 * ferme le compte positionner a l'indice donner
	 * @param indice
	 */
	public void fermerCompteBanquaire() {
		comptes.remove(indiceCourant);
	}
	
	/**
	 * trouve l'indice du compte ayant l'identifiant donner
	 * @param indentifiant
	 * @return
	 */
	public void obtenirIndice(String indentifiant) {
		for(CompteBanquaire cb : comptes) {
			if(cb.getIdentifiant().equalsIgnoreCase(indentifiant)) {
				setIndiceCourant(comptes.indexOf(cb));
			}
		}
	}
	
	/**
	 * affiche les etats des differents comptes possedee par le client
	 * @return
	 */
	public String consulterCompte() {
		String retour = this.nom + "\n";
		for(CompteBanquaire c : comptes) {
			retour += c.etatCompte() + "\n";
		}
		return retour;
	}
	
	/**
	 * affiche les etats des differents comptes epargne possedee par le client
	 * @return
	 */
	public String consulterEpargne() {
		String retour = this.nom + "\n";
		for(CompteBanquaire c : comptes) {
			if(c.getClass() == Epargne.class) {
				retour += c.etatCompte() + "\n";
			}
		}
		return retour;
	}
	
	/**
	 * affiche le solde total du clients apres avoir additionner tous ses soldes de tous ses comptes
	 * @return
	 */
	public double obtenirSoldeTotal() {
		int totalSolde = 0;
		for(CompteBanquaire c : comptes) {
			totalSolde += c.getSolde();
		}
		return totalSolde;
	}
	
	/**
	 * affiche le nombre de compte ouvert par le client
	 * @return
	 */
	public int nbCompte() {
		return comptes.size();
	}
	
	/**
	 * creer une operation
	 * @param operation : l'operation a ajouter
	 */
	public void operation(Operation operation) {
		setOp(operation);
	}
	
	/**
	 * retrait de l'argent du compte
	 */
	@Override
	public void retirer(double somme) {
		comptes.get(indiceCourant).retirer(somme);
		comptes.get(indiceCourant).ajouterOperation(op);
	}

	/**
	 * depos de l'argent sur le compte
	 */
	@Override
	public void deposer(double somme) {
		comptes.get(indiceCourant).deposer(somme);
		comptes.get(indiceCourant).ajouterOperation(op);
	}

	/**
	 * le hashcode
	 */
	@Override
	public int hashCode() {
		return Objects.hash(MAX_NB_COMPTE, comptes, op);
	}

	/**
	 * le equals
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		return MAX_NB_COMPTE == other.MAX_NB_COMPTE && Objects.equals(comptes, other.comptes)
				&& Objects.equals(op, other.op);
	}

	/**
	 * compare le client courant avec le client donne en parametre
	 * @param cl : le client qu'on veut comparer avec le client courant
	 * @return
	 */
	public boolean unique(Client cl) {
		boolean retour = false;
		if(this.equals(cl)) {
			retour = true;
		}
		return retour;
	}
	
	/**
	 * affiche les information du client ainsi que ses comptes
	 */
	public String toString() {
		String retour = nom + " " + prenom + "\n";
		for(CompteBanquaire c : comptes) {
			retour += c.toString() + "\n";
		}
		return retour;
	}
	
	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}
	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	/**
	 * @return the comptes
	 */
	public ArrayList<CompteBanquaire> getComptes() {
		return comptes;
	}
	/**
	 * @param comptes the comptes to set
	 */
	public void setComptes(ArrayList<CompteBanquaire> comptes) {
		this.comptes = comptes;
	}
	/**
	 * @return the mAX_NB_COMPTE
	 */
	public int getMAX_NB_COMPTE() {
		return MAX_NB_COMPTE;
	}
	/**
	 * @param mAX_NB_COMPTE the mAX_NB_COMPTE to set
	 */
	public void setMAX_NB_COMPTE(int mAX_NB_COMPTE) {
		MAX_NB_COMPTE = mAX_NB_COMPTE;
	}

	/**
	 * @return the indiceCourant
	 */
	public int getIndiceCourant() {
		return indiceCourant;
	}

	/**
	 * @param indiceCourant the indiceCourant to set
	 */
	public void setIndiceCourant(int indiceCourant) {
		this.indiceCourant = indiceCourant;
	}
	
	/**
	 * @return the op
	 */
	public Operation getOp() {
		return op;
	}

	/**
	 * @param op the op to set
	 */
	public void setOp(Operation op) {
		this.op = op;
	}

	/**
	 * test la class Client
	 * @param args
	 */
	public static void main(String[] args) {
		Client cl = new Client("marco", "paulo");
		Classique classic = new Classique(200.0, "23");
		Epargne ep = new Epargne(36500.0, "23", 5.0);
		Epargne ep2 = new Epargne(200.0, "23", 5.0);
		try {
			cl.ouvrirCompteBanquaire(classic);
			cl.ouvrirCompteBanquaire(ep);
			cl.ouvrirCompteBanquaire(ep2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(cl.toString());
		cl.obtenirIndice(ep2.getIdentifiant());
		//System.out.println(ep2.getIdentifiant());
		//System.out.println(cl.getIndiceCourant());
		cl.fermerCompteBanquaire();
		//System.out.println(cl.toString());
		//System.out.println("all compte \n" + cl.consulterCompte());
		//System.out.println("all compte epargne \n" + cl.consulterEpargne());
		//System.out.println("solde total " + cl.obtenirSoldeTotal());
		//System.out.println("nb compte " + cl.nbCompte());
		
		cl.obtenirIndice(classic.getIdentifiant());
		cl.operation(new Operation(Date.valueOf(LocalDate.now()), TypeOperation.RETRAIT, 300.0));
		cl.retirer(300.0);
		//cl.obtenirIndice(ep.getIdentifiant());
		//cl.retirer(300.0);
		//System.out.println(cl.toString());
		//System.out.println("all compte \n" + cl.consulterCompte());
		//System.out.println("all compte epargne \n" + cl.consulterEpargne());
		
		
	}
}
