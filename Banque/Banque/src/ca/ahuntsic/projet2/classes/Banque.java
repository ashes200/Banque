/**
 * 
 */
package ca.ahuntsic.projet2.classes;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * @author debra
 *
 */
public class Banque implements Gestion {
	private String nom; 
	private String numeroSuccursale;
	static int indiceCourant;
	ArrayList<Client> listeClient;
	
	/**
	 * constructeur sans parametre
	 */
	public Banque() {
		this("xxxx", "1");
	}
	
	/**
	 * constructeur avec parametres
	 * @param nom : le nom de la banque
	 * @param numeroSuccursale : le numero de succursale de la banque
	 */
	public Banque(String nom, String numeroSuccursale) {
		setNom(nom);
		setNumeroSuccursale(numeroSuccursale);
		listeClient = new ArrayList<>();
	}
	
	/**
	 * serialise l'objet courant dans un fichier xml
	 */
	public void banqueToXML(){
		SerialisationXML.objectToXML(this, "./ressource/sauvegarde.xml");
	}
	
	/**
	 * deserialise un fichier en un objet de type banque
	 * @return
	 */
	public static Banque XMLToBanque() {
		return SerialisationXML.XMLToObject("./ressource/sauvegarde.xml");
	}
	
	/**
	 * retourne l'indice du client dans la liste de client de la banque
	 * @param nom : le nom du client dont on recherche l'indice
	 */
	public void obtenirIndice(String nom) {
		for(Client cl : listeClient) {
			if(cl.getNom().equalsIgnoreCase(nom)) {
				setIndiceCourant(listeClient.indexOf(cl));
			}
		}
	}
	
	/**
	 * retourne l'indice du compte banquaire dans la liste de compte banquaire du client
	 * @param indice : l'indice du compte dans la liste de compte du client
	 */
	public void obtenirIndiceCompte(int indice) {
		listeClient.get(indiceCourant).setIndiceCourant(indice);;
	}
	
	/**
	 * creer une operation avec les valeur demander
	 * @param montant : le montant de l'operation
	 * @param typeOp : le type de l'operation : RETRAIT OU DEPOS
	 * @return : l'operation cree
	 */
	public Operation operation(double montant, TypeOperation typeOp) {
		return new Operation(Date.valueOf(LocalDate.now()), typeOp, montant);
	}
	
	/**
	 * retrait de l'argent du compte
	 */
	@Override
	public void retirer(double somme) {
		listeClient.get(indiceCourant).retirer(somme);
		listeClient.get(indiceCourant).operation(operation(somme, TypeOperation.RETRAIT));
		
	}

	/**
	 * depot de l'argent dans le compte
	 */
	@Override
	public void deposer(double somme) {
		listeClient.get(indiceCourant).deposer(somme);
		listeClient.get(indiceCourant).operation(operation(somme, TypeOperation.DEPOT));
	}

	public void virement(String identification, double somme) {
		retirer(somme);
		for(CompteBanquaire c : this.getListeClient().get(indiceCourant).getComptes()) {
			if(c.getIdentifiant().equalsIgnoreCase(identification)) {
				c.deposer(somme);
			}
		}
		
	}
	
	/**
	 * compare les clients de la banque avec le client donner en parametre
	 * @param cl : le client qu'on veut comparer avec le client courant
	 * @return
	 */
	public boolean uniqueClient(Client cl) {
		boolean retour = false;
		for(Client c : listeClient) {
			if(c.unique(cl) == true) {
				retour = true;
			}
		};
		return retour;
	}
	
	/**
	 * ajoute un client a la liste de client de la banque
	 * @param client : le client a ajouter a la banque
	 */
	public void ajouterClient(Client client) {
		listeClient.add(client);
	}
	
	/**
	 * le nombre de client contenue dans la liste de client de la banque
	 * @return
	 */
	public int nbTotalClient() {
		return  listeClient.size();
	}
	
	/**
	 * ouvre un compte classique sans solde.
	 * ouvre le compte pour le client a l'indice courant
	 * @throws Exception
	 */
	public void ouvrirCompteClassique() throws Exception {
		CompteBanquaire cb = new Classique(this.numeroSuccursale);
		listeClient.get(indiceCourant).ouvrirCompteBanquaire(cb);
	}
	
	/**
	 * ouvre un compte classique avec solde
	 * @param solde : le solde du compte
	 * @throws Exception
	 */
	public void ouvrirCompteClassique(double solde) throws Exception {
		CompteBanquaire cb = new Classique(solde, this.numeroSuccursale);
		listeClient.get(indiceCourant).ouvrirCompteBanquaire(cb);
	}
	
	/**
	 * ouvre un compte epargne avec un taux d'interet
	 * @param tauxInteret : le taux d'interet du compte
	 * @throws Exception
	 */
	public void ouvrirCompteEpargne(double tauxInteret) throws Exception {
		CompteBanquaire cb = new Epargne(this.numeroSuccursale, tauxInteret);
		listeClient.get(indiceCourant).ouvrirCompteBanquaire(cb);
	}
	
	/**
	 * ouvre un compte epargne avec un solde et un tauxInteret
	 * @param solde : le solde du compte
	 * @param tauxInteret : le taux d'interet du compte
	 * @throws Exception
	 */
	public void ouvrirCompteEpargne(double solde, double tauxInteret) throws Exception {
		CompteBanquaire cb = new Epargne(solde, this.numeroSuccursale, tauxInteret);
		listeClient.get(indiceCourant).ouvrirCompteBanquaire(cb);
	}
	
	/**
	 * ferme le compte banquaire du client de l'indice courant
	 */
	public void fermerCompte(int indiceCompte) {
		listeClient.get(indiceCourant).setIndiceCourant(indiceCompte);
		listeClient.get(indiceCourant).fermerCompteBanquaire();
	}
	
	/**
	 * retourne l'affichage des etats de comptes des client et leurs 
	 * comptes ainsi que leurs transaction
	 * @return
	 */
	public String etatCompteClient() {
		String retour = "";
		for(Client c : listeClient) {
			retour += c.consulterCompte() + "\n";
		}
		return retour;
	}
	
	/**
	 * affiche le contenue des comptes banquaires des clients de la banque
	 */
	public String toString() {
		String retour = "";
		for(Client c : listeClient) {
			retour += c.toString();
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
	 * @return the numeroSuccursale
	 */
	public String getNumeroSuccursale() {
		return numeroSuccursale;
	}
	/**
	 * @param numeroSuccursale the numeroSuccursale to set
	 */
	public void setNumeroSuccursale(String numeroSuccursale) {
		this.numeroSuccursale = numeroSuccursale;
	}
	/**
	 * @return the listeClient
	 */
	public ArrayList<Client> getListeClient() {
		return listeClient;
	}
	/**
	 * @param listeClient the listeClient to set
	 */
	public void setListeClient(ArrayList<Client> listeClient) {
		this.listeClient = listeClient;
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
	 * le test de la class
	 * @param args
	 */
	public static void main(String[] args) {
		Banque bmo = new Banque("BMO", "23");
		Client cl = new Client("paulo", "marco");
		Client cl2 = new Client("travail", "Steve");
		bmo.ajouterClient(cl);
		bmo.ajouterClient(cl2);
		
		bmo.obtenirIndice(cl2.getNom());
		try {
			bmo.ouvrirCompteClassique(200.0);
			bmo.ouvrirCompteEpargne(200.0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println(bmo.getIndiceCourant());
		String ident = bmo.getListeClient().get(indiceCourant).getComptes().get(1).getIdentifiant();
		//bmo.obtenirIndiceCompte(0);
		//bmo.retirer(100);
		//bmo.deposer(300);
		//bmo.obtenirIndiceCompte(1);
		//bmo.deposer(150);
		
		bmo.obtenirIndice(cl.getNom());
		try {
			bmo.ouvrirCompteEpargne(400.0);
			bmo.ouvrirCompteEpargne(400.0);
			bmo.ouvrirCompteClassique(800.0);
		} catch (Exception e) {
			e.printStackTrace();
		}

		//System.out.println(bmo.toString());
		bmo.banqueToXML();
		Banque amd = Banque.XMLToBanque();
		
		//System.out.println(amd.toString());
		//bmo.obtenirIndiceCompte(0);
		//bmo.retirer(100);
		
	//	System.out.println(bmo.toString());
		//System.out.println(bmo.etatCompteClient());
		//System.out.println("le toString\n" + bmo.toString());
		//bmo.obtenirIndice(cl2.getNom());
		//bmo.obtenirIndiceCompte(0);
		//System.out.println(ident);
		//bmo.virement(1, 200);
		//bmo.obtenirIndice(cl2.getNom());
		//bmo.fermerCompte(0);
		//System.out.println("le toString2\n" + bmo.toString());
		
		//System.out.println("avant virement :\n" + bmo.toString());
		
		bmo.obtenirIndice(cl2.getNom());
		bmo.obtenirIndiceCompte(0);
		String identif = bmo.getListeClient().get(indiceCourant).getComptes().get(1).getIdentifiant();
		bmo.virement(identif, 200);
		//System.out.println("apres virement :\n\n" + bmo.toString());
	}

}
