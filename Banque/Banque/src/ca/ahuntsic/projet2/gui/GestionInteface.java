package ca.ahuntsic.projet2.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ca.ahuntsic.projet2.classes.Banque;
import ca.ahuntsic.projet2.classes.Classique;
import ca.ahuntsic.projet2.classes.Client;
import ca.ahuntsic.projet2.classes.CompteBanquaire;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JMenu;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JMenuItem;

public class GestionInteface extends JFrame {

	private JPanel contentPane;
	private JTextField textNom;
	private JTextField textPrenom;
	private JTextField textMontant;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JList listCompteDestinataire;
	private JList listCompteClient;
	private JComboBox cbTauxIntert;
	private JComboBox cbClient;
	private JButton btnNewClient;
	private JButton btnReleveClient;
	private JButton btnOuvrirCompte;
	private JButton btnFermerCompte;
	private JButton btnTransaction;
	private JButton btnVirement;
	private JRadioButton rdbtnClassique;
	private JRadioButton rdbtnEpargne;
	private JTextArea textAffichage;
	private JPanel panel_6;
	private JMenuItem mntmSauvegarder;
	private JMenuItem mntmCharger;
	private JMenuItem mntmDeconnecter;
	
	Banque bmo = null;
	int indiceCompte;
	int indiceCourant;

	private DefaultListModel<CompteBanquaire> listModelClient = new DefaultListModel<>();
	private DefaultListModel<String> listModelDestination = new DefaultListModel<>();
	private DefaultComboBoxModel<String> comboModelClient = new DefaultComboBoxModel<>();
	private DefaultComboBoxModel<Double> comboModelTaux = new DefaultComboBoxModel<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionInteface frame = new GestionInteface();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GestionInteface() {
		initialize();
		bmo = Banque.XMLToBanque();
		
		//System.out.println(bmo);
		cbClient.setModel(comboModelClient);
		cbTauxIntert.setModel(comboModelTaux);
		listCompteClient.setModel(listModelClient);
		listCompteDestinataire.setModel(listModelDestination);
		
		for(Client s : bmo.getListeClient()) {
			comboModelClient.addElement(s.getNom() + " " + s.getPrenom());
		}
		
		ArrayList<Double> listeTaux = new ArrayList<>();
		listeTaux.add(0.5);
		listeTaux.add(0.25);
		listeTaux.add(0.7);
		for(double d : listeTaux) {
			comboModelTaux.addElement(d);
		}
		
	}
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 953, 700);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFichier = new JMenu("Fichier");
		menuBar.add(mnFichier);
		
		mntmSauvegarder = new JMenuItem("Sauvegarder");
		mntmSauvegarder.addActionListener(new btnClass());
		mnFichier.add(mntmSauvegarder);
		
		mntmCharger = new JMenuItem("Charger");
		mnFichier.add(mntmCharger);
		
		mntmDeconnecter = new JMenuItem("se deconnecter");
		mnFichier.add(mntmDeconnecter);
		mntmDeconnecter.addActionListener(new btnClass());
		mntmCharger.addActionListener(new btnClass());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		panel.setMaximumSize(new Dimension(32767, 700));
		contentPane.add(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_3.setMaximumSize(new Dimension(32767, 600));
		panel_3.setBackground(SystemColor.menu);
		panel_3.setBorder(new TitledBorder(null, "Informations du client", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));
		
		JPanel panel_7 = new JPanel();
		panel_3.add(panel_7);
		panel_7.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setHorizontalAlignment(SwingConstants.CENTER);
		lblNom.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNom.setBackground(Color.LIGHT_GRAY);
		panel_7.add(lblNom);
		
		textNom = new JTextField();
		panel_7.add(textNom);
		textNom.setColumns(10);
		
		JLabel lblPrenom = new JLabel("Prenom");
		lblPrenom.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPrenom.setHorizontalAlignment(SwingConstants.CENTER);
		panel_7.add(lblPrenom);
		
		textPrenom = new JTextField();
		panel_7.add(textPrenom);
		textPrenom.setColumns(10);
		
		btnNewClient = new JButton("Nouveau client");
		btnNewClient.addActionListener(new btnClass());
		btnNewClient.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_7.add(btnNewClient);
		
		btnReleveClient = new JButton("Releve client");
		btnReleveClient.addActionListener(new btnClass());
		btnReleveClient.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_7.add(btnReleveClient);
		
		JPanel panel_8 = new JPanel();
		panel_8.setMinimumSize(new Dimension(10, 110));
		panel_8.setMaximumSize(new Dimension(32767, 2000));
		panel_8.setBorder(new TitledBorder(null, "Clients", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.add(panel_8);
		panel_8.setLayout(new GridLayout(1, 0, 0, 0));
		
		cbClient = new JComboBox();
		cbClient.setMinimumSize(new Dimension(29, 100));
		cbClient.setMaximumSize(new Dimension(32767, 100));
		cbClient.addActionListener(new ComboBoxListener());
		panel_8.add(cbClient);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "Gestions client", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_9 = new JPanel();
		panel_4.add(panel_9);
		panel_9.setLayout(new GridLayout(0, 1, 0, 0));
		
		btnOuvrirCompte = new JButton("Ouvrir compte");
		btnOuvrirCompte.addActionListener(new btnClass());
		btnOuvrirCompte.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_9.add(btnOuvrirCompte);
		
		JLabel lblTauxInteret = new JLabel("Taux d'interet");
		lblTauxInteret.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTauxInteret.setHorizontalAlignment(SwingConstants.CENTER);
		panel_9.add(lblTauxInteret);
		
		cbTauxIntert = new JComboBox();
		panel_9.add(cbTauxIntert);
		
		btnFermerCompte = new JButton("Fermer Compte");
		btnFermerCompte.addActionListener(new btnClass());
		btnFermerCompte.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_9.add(btnFermerCompte);
		
		btnTransaction = new JButton("Transaction");
		btnTransaction.addActionListener(new btnClass());
		btnTransaction.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_9.add(btnTransaction);
		
		btnVirement = new JButton("Virement");
		btnVirement.addActionListener(new btnClass());
		btnVirement.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_9.add(btnVirement);
		
		JPanel panel_10 = new JPanel();
		panel_4.add(panel_10);
		panel_10.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_11 = new JPanel();
		panel_10.add(panel_11);
		panel_11.setLayout(new BoxLayout(panel_11, BoxLayout.Y_AXIS));
		
		rdbtnClassique = new JRadioButton("Classique");
		buttonGroup.add(rdbtnClassique);
		rdbtnClassique.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_11.add(rdbtnClassique);
		
		rdbtnEpargne = new JRadioButton("Epargne");
		buttonGroup.add(rdbtnEpargne);
		rdbtnEpargne.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_11.add(rdbtnEpargne);
		
		JPanel panel_12 = new JPanel();
		panel_10.add(panel_12);
		panel_12.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblMontant = new JLabel("Montant");
		lblMontant.setHorizontalAlignment(SwingConstants.CENTER);
		lblMontant.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_12.add(lblMontant);
		
		textMontant = new JTextField();
		panel_12.add(textMontant);
		textMontant.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setMaximumSize(new Dimension(32767, 500));
		panel_1.setBorder(null);
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_5.setSize(new Dimension(50, 0));
		panel_5.setBorder(new TitledBorder(null, "Liste des comptes clients", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.add(panel_5);
		panel_5.setLayout(new GridLayout(1, 0, 0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_5.add(scrollPane_1);
		
		listCompteClient = new JList();
		listCompteClient.addListSelectionListener(new ListListener());
		scrollPane_1.setViewportView(listCompteClient);
		
		panel_6 = new JPanel();
		panel_6.setMinimumSize(new Dimension(50, 10));
		panel_6.setMaximumSize(new Dimension(50, 32767));
		panel_6.setBorder(new TitledBorder(null, "Compte destinataire", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.add(panel_6);
		panel_6.setLayout(new GridLayout(1, 0, 0, 0));
		panel_6.setVisible(false);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setMaximumSize(new Dimension(50, 32767));
		panel_6.add(scrollPane_2);
		
		listCompteDestinataire = new JList();
		listCompteDestinataire.setMaximumSize(new Dimension(50, 0));
		scrollPane_2.setViewportView(listCompteDestinataire);
		
		JPanel panel_2 = new JPanel();
		panel_2.setMinimumSize(new Dimension(10, 500));
		panel_2.setMaximumSize(new Dimension(32767, 1000));
		panel_2.setBorder(new TitledBorder(null, "Affichage", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel_2);
		panel_2.setLayout(new GridLayout(1, 0, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_2.add(scrollPane);
		
		textAffichage = new JTextArea();
		textAffichage.setForeground(Color.RED);
		scrollPane.setViewportView(textAffichage);
	}
	
	/**
	 * gere le combobox en fonction du radio button
	 */
	public void radioSetup() {
		if(rdbtnClassique.isSelected()) {
			cbTauxIntert.setVisible(false);
		}
		else if(rdbtnEpargne.isSelected()){
			cbTauxIntert.setVisible(true);;
		}
	}
	
	/**
	 * gere le combobox sur les clients
	 * @author debra
	 *
	 */
	private class ComboBoxListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			listModelClient.removeAllElements();
			indiceCourant = cbClient.getSelectedIndex();
			bmo.setIndiceCourant(indiceCourant);
			for(CompteBanquaire c : bmo.getListeClient().get(indiceCourant).getComptes()) {
				listModelClient.addElement(c);
			}
			panel_6.setVisible(false);
			textNom.setText("");
			textPrenom.setText("");
		}
	}
	
	/**
	 * gere la liste des comptes du client
	 * @author debra
	 *
	 */
	private class ListListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
			listModelDestination.removeAllElements();
			indiceCompte = listCompteClient.getSelectedIndex();
			bmo.obtenirIndiceCompte(indiceCompte);
			
			/**
			 * initialise la liste
			 */
			for(CompteBanquaire c : bmo.getListeClient().get(indiceCourant).getComptes()) {
				if(c != listCompteClient.getSelectedValue()) {
					listModelDestination.addElement(c.getIdentifiant());
				}
			}
			/**
			 * affiche la liste des compte pour le tranfert
			 */
			if(listCompteClient.getSelectedValue() != null) {
				if(listCompteClient.getSelectedValue().getClass() == Classique.class) {
					rdbtnClassique.setSelected(true);
					panel_6.setVisible(true);
				}
				else {
					rdbtnEpargne.setSelected(true);
					panel_6.setVisible(false);
				}
			}
		}
	}
	
	/**
	 * gere les boutons de la class
	 * @author debra
	 *
	 */
	private class btnClass implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			//le boutton selectionner
			Object source = e.getSource();
			
			/**
			 * gere le boutton nouveau client
			 */
			if(source == btnNewClient) {
				String nom = textNom.getText();
				String prenom = textPrenom.getText();
				Client cl = new Client(nom, prenom);
				/**
				 * verifie que le client n'existe pas deja dans la liste
				 */
				if(bmo.uniqueClient(cl) == false) {
					bmo.ajouterClient(cl);
					comboModelClient.addElement(nom + " " + prenom);
					textNom.setText("");
					textPrenom.setText("");
					textAffichage.setForeground(Color.black);
					textAffichage.setText("le client " + nom + " a bien ete ajouter");
				}
				else {
					textAffichage.setForeground(Color.red);
					textAffichage.setText("ce client existe deja");
				}
			}
			
			/**
			 * gere le boutton fermer compte
			 */
			else if(source == btnFermerCompte) {
				bmo.fermerCompte(indiceCompte);
				textAffichage.setForeground(Color.black);
				textAffichage.setText("le compte a bien ete supprimer");
				listCompteClient.repaint();
				listCompteDestinataire.repaint();
			}
			
			/**
			 * gere le boutton transaction
			 */
			else if(source == btnTransaction) {
				String texteMontant = textMontant.getText();
				if(texteMontant.length() > 0) {
					try {
						double montant = Double.parseDouble(textMontant.getText());
						String identification = String.valueOf(listCompteDestinataire.getSelectedValue());
						bmo.virement(identification, montant);
						textAffichage.setForeground(Color.black);
						textAffichage.setText("le virement de " + montant + " vers le compte " + identification + " a ete reussi");
					} catch (Exception e1) {
						textAffichage.setForeground(Color.red);
						textAffichage.setText("" + e1.getMessage());
					}
				}
				else {
					textAffichage.setForeground(Color.red);
					textAffichage.setText("veuillez entrez un montant pour faire une transaction");
				}
			}
			
			/**
			 * gere le boutton virement
			 */
			else if(source == btnVirement) {
				if(!listCompteClient.isSelectionEmpty()) {
				CompteBanquaire b = bmo.getListeClient().get(indiceCourant).getComptes().get(indiceCompte);
				TransactionInterface transac = new TransactionInterface(b);
				transac.setTitle("Transaction");
				transac.setVisible(true);
				
				b = transac.getBq();
				textAffichage.setForeground(Color.black);
				}
				else {
					textAffichage.setForeground(Color.red);
					textAffichage.setText("Veuillez choisir un compte pour faire des transactions");
				}
			}
			
			/**
			 * gere le boutton relever client
			 */
			else if(source == btnReleveClient) {
				textAffichage.setForeground(Color.black);
				textAffichage.setText(bmo.getListeClient().get(indiceCourant).consulterCompte());
			}
			
			/**
			 * gere le boutton ouvrir compte
			 */
			else if(source == btnOuvrirCompte) {
				/**
				 * gere les comptes classique
				 */
				if(rdbtnClassique.isSelected()) {
					/**
					 * ouvre un compte classique avec solde
					 */
					if(textMontant.getText().length() > 0) {
						double montant = Double.parseDouble(textMontant.getText()); 
						try {
							bmo.ouvrirCompteClassique(montant);
							Client cl = bmo.getListeClient().get(indiceCourant);
							String ident = cl.getComptes().get(cl.getComptes().size() - 1).getIdentifiant();
							textAffichage.setForeground(Color.black);
							textAffichage.setText("le compte " + ident + " a bien ete creer");
							listCompteClient.repaint();
							
						} catch (Exception e1) {
							textAffichage.setForeground(Color.red);
							textAffichage.setText("" + e1.getMessage());
						}
					}
					/**
					 * ouvre un compte classique sans solde
					 */
					else {
						try {
							bmo.ouvrirCompteClassique();
							Client cl = bmo.getListeClient().get(indiceCourant);
							String ident = cl.getComptes().get(cl.getComptes().size() - 1).getIdentifiant();
							textAffichage.setForeground(Color.black);
							textAffichage.setText("le compte " + ident + " a bien ete creer");
							listCompteClient.repaint();
						} catch (Exception e1) {
							textAffichage.setForeground(Color.red);
							textAffichage.setText("" + e1.getMessage());
						}
					}
				}
				/**
				 * gere les comptes epargne
				 */
				else if(rdbtnEpargne.isSelected()) {
					double tauxInteret =  Double.parseDouble(String.valueOf(comboModelTaux.getSelectedItem()));
					/**
					 * ouvre un compte epargne avec solde
					 */
					if(textMontant.getText().length() > 0) {
						double montant = Double.parseDouble(textMontant.getText());
						try {
							bmo.ouvrirCompteEpargne(montant, tauxInteret);
							Client cl = bmo.getListeClient().get(indiceCourant);
							String ident = cl.getComptes().get(cl.getComptes().size() - 1).getIdentifiant();
							textAffichage.setForeground(Color.black);
							textAffichage.setText("le compte " + ident + " a bien ete creer");
							listCompteClient.repaint();
						} catch (Exception e1) {
							textAffichage.setForeground(Color.red);
							textAffichage.setText("" + e1.getMessage());
						}
					}
					/**
					 * ouvre un compte epargne sans solde
					 */
					else {
						try {
							bmo.ouvrirCompteEpargne(tauxInteret);
							Client cl = bmo.getListeClient().get(indiceCourant);
							String ident = cl.getComptes().get(cl.getComptes().size() - 1).getIdentifiant();
							textAffichage.setForeground(Color.black);
							textAffichage.setText("le compte " + ident + " a bien ete creer");
							listCompteClient.repaint();
						} catch (Exception e1) {
							textAffichage.setForeground(Color.red);
							textAffichage.setText("" + e1.getMessage());
						}
					}
				}
				else {
					textAffichage.setForeground(Color.red);
					textAffichage.setText("veuillez selectionner le type de compte : Classique ou Epargne");
				}
			}
			
			/**
			 * gere le menu bouton sauvegarder : sauvegrde le contenue de la banque dans le fichier xml
			 */
			else if(source == mntmSauvegarder) {
				bmo.banqueToXML();
			}
			
			/**
			 * gere le menu bouton charger : rempli la banque avec les information contenu dans le fichier xml
			 */
			else if(source == mntmCharger) {
				bmo = Banque.XMLToBanque();
			}
			
			/**
			 * gere le menu bouton se deconnecter : ferme la fenetre courante et ouvre la fenetre de connection
			 */
			else if(source == mntmDeconnecter) {
				bmo.banqueToXML();
				AuthentificationInterface auth = new AuthentificationInterface();
				auth.setTitle("Connection");
				auth.setVisible(true);
				dispose();
			}
		}
	}

}
