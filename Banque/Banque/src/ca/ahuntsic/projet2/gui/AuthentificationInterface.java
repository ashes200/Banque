package ca.ahuntsic.projet2.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ca.ahuntsic.projet2.classes.Authentification;

import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.SystemColor;

public class AuthentificationInterface extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textIdentifiant;
	private JPasswordField passwordField;
	private JTextArea lblError;
	private JButton btnOk;
	private JButton btnAnnuler;
	
	// creation d'un objet authentification
	Authentification auth = new Authentification();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AuthentificationInterface frame = new AuthentificationInterface();
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
	public AuthentificationInterface() {
		setTitle("Connection");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		panel.setMaximumSize(new Dimension(32767, 500));
		contentPane.add(panel);
		panel.setLayout(new GridLayout(2, 0, 0, 20));
		
		JLabel lblIdentifiant = new JLabel("Identifiant");
		lblIdentifiant.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdentifiant.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(lblIdentifiant);
		
		textIdentifiant = new JTextField();
		textIdentifiant.setText("debraise");
		textIdentifiant.setFont(new Font("Tahoma", Font.BOLD, 14));
		textIdentifiant.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(textIdentifiant);
		textIdentifiant.setColumns(10);
		
		JLabel lblPassword = new JLabel("Mot de passe");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(lblPassword);
		
		passwordField = new JPasswordField("2braise");
		passwordField.setFont(new Font("Tahoma", Font.BOLD, 14));
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(passwordField);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(null);
		panel_2.setMaximumSize(new Dimension(32767, 120));
		contentPane.add(panel_2);
		panel_2.setLayout(new GridLayout(1, 0, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		panel_2.add(scrollPane);
		
		lblError = new JTextArea();
		lblError.setBackground(SystemColor.menu);
		scrollPane.setViewportView(lblError);
		
		JPanel panel_1 = new JPanel();
		panel_1.setMaximumSize(new Dimension(32767, 20));
		contentPane.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		btnOk = new JButton("Ok");
		btnOk.addActionListener(new BtnNewButtonActionListener());
		btnOk.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_1.add(btnOk);
		
		btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new BtnNewButtonActionListener());
		btnAnnuler.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_1.add(btnAnnuler);
	}

	/**
	 * BtnNewButtonActionListener: Description de la classe Auteur : debra
	 */
	private class BtnNewButtonActionListener implements ActionListener {
		@Override
		/**
		 * verifie le pseudo et password. si ceux-ci sont conforme a ceux enregistrer,
		 * donne acces la la fenetre de gestion
		 */
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton) e.getSource();
			
			/**
			 * redirige vers la page de gestion si les identifiants sons correct
			 */
			if(source == btnOk) {
				String pseudo = textIdentifiant.getText();
				String password = String.valueOf(passwordField.getPassword());
				boolean verif = auth.verification(pseudo, password);
				if (verif) {
					GestionInteface interf = new GestionInteface();
					interf.setTitle("fenetre gestion");
					interf.setVisible(true);
					dispose();
				} else {
					lblError.setText("Essayer a nouveau");
				}
			}
			/**
			 * ferme l'interface
			 */
			else {
				System.exit(0);
			}
		}
	}

}
