package ca.ahuntsic.projet2.classes;

import java.sql.Date;
import java.time.LocalDate;

public class Operation {
	
	// les attributs
	private Date dateOperation;
	private TypeOperation typeOperation;
	private double montant;
	
	/**
	 * constructeur sans parametre qui initialise l'operation avec des valeur par default :
	 * dateOperation = LocalDate.now()
	 * typeOperation = DEPOT
	 * montant = 0.0 $
	 */
	public Operation() {
		this(Date.valueOf(LocalDate.now()), TypeOperation.DEPOT, 0.0);
	}
	
	/**
	 * constructeur avec parametre qui initialise l'operation avec les valeur donnee en parametre
	 * @param dateOperation : la date de l'operation
	 * @param typeOperation : le type de l;operation : DEPOT ou RETRAIT
	 * @param montant : la somme retirer ou ajouter
	 */
	public Operation(Date dateOperation, TypeOperation typeOperation, double montant) {
		setDateOperation(dateOperation);
		setTypeOperation(typeOperation);
		setMontant(montant);
	}

	/**
	 * affiche le contenue de l'operation
	 */
	public String toString() {
		return dateOperation + "\t" + typeOperation + "\t" + montant + "$";
	}
	
	/**
	 * @return the dateOperation
	 */
	public Date getDateOperation() {
		return dateOperation;
	}

	/**
	 * @param dateOperation the dateOperation to set
	 */
	public void setDateOperation(Date dateOperation) {
		this.dateOperation = dateOperation;
	}

	/**
	 * @return the typeOperation
	 */
	public TypeOperation getTypeOperation() {
		return typeOperation;
	}

	/**
	 * @param typeOperation the typeOperation to set
	 */
	public void setTypeOperation(TypeOperation typeOperation) {
		this.typeOperation = typeOperation;
	}

	/**
	 * @return the montant
	 */
	public double getMontant() {
		return montant;
	}

	/**
	 * @param montant the montant to set
	 */
	public void setMontant(double montant) {
		this.montant = montant;
	}
	
	/**
	 * test la classe operation
	 * @param args
	 */
	public static void main(String[] args) {
		Operation test = new Operation(Date.valueOf(LocalDate.now()), TypeOperation.DEPOT, 250.00);
		System.out.println(test);
	}
}
