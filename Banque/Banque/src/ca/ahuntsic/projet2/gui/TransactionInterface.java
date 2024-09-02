package ca.ahuntsic.projet2.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

import ca.ahuntsic.projet2.classes.CompteBanquaire;
import ca.ahuntsic.projet2.classes.Epargne;
import ca.ahuntsic.projet2.classes.Operation;
import ca.ahuntsic.projet2.classes.TypeOperation;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;
import java.awt.Dimension;

public class TransactionInterface extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPanel panel1;
	private JRadioButton rdbtnDepot;
	private JRadioButton rdbtnRetrait;
	private JLabel lblSolde;
	private JLabel lblMontant;
	
	private JPanel panel2;
	private JButton btnConfirmer;
	private JButton btnEtatCompte;
	private JButton btnRetour;
	
	private JPanel panel3;
	private JTextArea textArea;
	
	private JTextField textSolde;
	private JTextField textMontant;
	
	CompteBanquaire bq = null;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Create the dialog.
	 */
	public TransactionInterface(CompteBanquaire b) {
		initialize();
		setModal(true);
		bq = b;
		textSolde.setText(String.valueOf(b.getSolde()));
		if(b.getClass() == Epargne.class) {
			rdbtnRetrait.setEnabled(false);
			rdbtnDepot.setSelected(true);
		}
		
	}
	private void initialize() {
		setBounds(100, 100, 537, 308);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		{
			panel1 = new JPanel();
			contentPanel.add(panel1);
			panel1.setLayout(new GridLayout(0, 2, 0, 0));
			{
				lblSolde = new JLabel("Solde");
				lblSolde.setFont(new Font("Tahoma", Font.BOLD, 14));
				lblSolde.setHorizontalAlignment(SwingConstants.CENTER);
				panel1.add(lblSolde);
			}
			{
				textSolde = new JTextField();
				textSolde.setFont(new Font("Tahoma", Font.PLAIN, 12));
				panel1.add(textSolde);
				textSolde.setColumns(10);
			}
			{
				lblMontant = new JLabel("Montant");
				lblMontant.setHorizontalAlignment(SwingConstants.CENTER);
				lblMontant.setFont(new Font("Tahoma", Font.BOLD, 14));
				panel1.add(lblMontant);
			}
			{
				textMontant = new JTextField();
				textMontant.setFont(new Font("Tahoma", Font.PLAIN, 12));
				panel1.add(textMontant);
				textMontant.setColumns(10);
			}
			{
				rdbtnDepot = new JRadioButton("Depot");
				buttonGroup.add(rdbtnDepot);
				rdbtnDepot.setFont(new Font("Tahoma", Font.BOLD, 14));
				panel1.add(rdbtnDepot);
			}
			{
				rdbtnRetrait = new JRadioButton("Retrait");
				buttonGroup.add(rdbtnRetrait);
				rdbtnRetrait.setFont(new Font("Tahoma", Font.BOLD, 14));
				panel1.add(rdbtnRetrait);
			}
		}
		{
			panel2 = new JPanel();
			contentPanel.add(panel2);
			panel2.setLayout(new GridLayout(0, 3, 0, 0));
			{
				btnConfirmer = new JButton("Confirmer");
				btnConfirmer.addActionListener(new btnClass2());
				btnConfirmer.setFont(new Font("Tahoma", Font.BOLD, 12));
				panel2.add(btnConfirmer);
			}
			{
				btnEtatCompte = new JButton("Etat du compte");
				btnEtatCompte.addActionListener(new btnClass2());
				btnEtatCompte.setFont(new Font("Tahoma", Font.BOLD, 12));
				panel2.add(btnEtatCompte);
			}
			{
				btnRetour = new JButton("Retour");
				btnRetour.addActionListener(new btnClass2());
				btnRetour.setFont(new Font("Tahoma", Font.BOLD, 12));
				panel2.add(btnRetour);
			}
		}
		{
			panel3 = new JPanel();
			panel3.setMinimumSize(new Dimension(10, 1000));
			panel3.setBorder(new TitledBorder(null, "Affichage", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel3);
			panel3.setLayout(new GridLayout(1, 0, 0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				panel3.add(scrollPane);
				{
					textArea = new JTextArea();
					scrollPane.setViewportView(textArea);
				}
			}
		}
	}
	
	/**
	 * gere tous les boutons de l'interface
	 * @author debra
	 *
	 */
	private class btnClass2 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			//le boutton selectionner
			Object source = e.getSource();
			
			/**
			 * gere le bouton eta de compte
			 */
			if(source == btnEtatCompte) {
				textArea.setForeground(Color.black);
				textArea.setText(bq.etatCompte());
			}
			
			/**
			 * gere le bouton retour
			 */
			else if (source == btnRetour){
				dispose();
			}
			
			/**
			 * gere le bouton confirmer
			 */
			else {
				if(source == btnConfirmer) {
	
					if(textMontant.getText().length() > 0) {
						double montant = Double.parseDouble(textMontant.getText());
					/**
					 * gere les depots
					 */
					if(rdbtnDepot.isSelected()) {
						try {
							bq.deposer(montant);
							textSolde.setText(String.valueOf(bq.getSolde()));
							bq.ajouterOperation(new Operation(Date.valueOf(LocalDate.now()), TypeOperation.DEPOT, montant));
							textArea.setForeground(Color.black);
							textArea.setText("le depos de : " + montant + " a ete effectuer avec succes");
						}
						catch (Exception e1) {
							textArea.setForeground(Color.red);
							textArea.setText("" + e1.getMessage());
						}
					}
					/**
					 * gere les retraits
					 */
					else if (rdbtnRetrait.isSelected()){
						try {
							bq.retirer(montant);
							textSolde.setText(String.valueOf(bq.getSolde()));
							bq.ajouterOperation(new Operation(Date.valueOf(LocalDate.now()), TypeOperation.RETRAIT, montant));
							textArea.setForeground(Color.black);
							textArea.setText("le retrait de : " + montant + " a ete effectuer avec succes");
						}
						catch (Exception e1) {
							textArea.setForeground(Color.red);
							textArea.setText("" + e1.getMessage());
						}
					}
					else {
						textArea.setForeground(Color.red);
						textArea.setText("veuillez selectionner le type de transfer");
					}
					}
					else {
						textArea.setForeground(Color.red);
						textArea.setText("veuillez entrer un montant");
					}
				}
			}
		}
	}
	/**
	 * @return the bq
	 */
	public CompteBanquaire getBq() {
		return bq;
	}
	/**
	 * @param bq the bq to set
	 */
	public void setBq(CompteBanquaire bq) {
		this.bq = bq;
	}

}
