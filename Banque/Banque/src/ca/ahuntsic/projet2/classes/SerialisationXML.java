package ca.ahuntsic.projet2.classes;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class SerialisationXML {
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
		String path = "./ressource/sauvegarde.xml";
		SerialisationXML.objectToXML(bmo, path);
		
		Banque amd = null;
		amd = SerialisationXML.XMLToObject(path);
		//System.out.println(amd.toString());
	}

	public static void objectToXML(Banque banque, String path) {
		try(FileOutputStream fos = new FileOutputStream(path);
				XMLEncoder encodeur = new XMLEncoder(fos);){
			encodeur.writeObject(banque);
		}
		catch(IOException e) {
			System.out.println("Erreur : " + e.getMessage());
		}
	}
	
	public static Banque XMLToObject(String path) {
		Banque bq = null;
		try(FileInputStream fis = new FileInputStream(path);
				XMLDecoder decodeur = new XMLDecoder(
						new BufferedInputStream(fis));){
			bq = (Banque) decodeur.readObject();
		}
		catch(IOException e) {
			System.out.println("Erreur : " + e.getMessage());
		}
		return bq;
	}
}
