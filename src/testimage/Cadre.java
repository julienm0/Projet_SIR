package testimage;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;

import java.awt.event.*;
import java.net.URL;
import javax.swing.ImageIcon;

public class Cadre extends JFrame implements ActionListener {

	private final JMenuBar menuBar = new JMenuBar();
	private final JMenu fichierMenu = new JMenu();
	private final JMenuItem ouvrirMenu = new JMenuItem();
	private final JMenu filtreMenu = new JMenu();
	private final ImageRadio panneau = new ImageRadio(this);
	private final JMenuItem enregistrerMenu = new JMenuItem();
	private final JMenuItem niveauGrisMenu = new JMenuItem();
	private final JMenuItem assombrirMenu = new JMenuItem();
	private final JMenuItem eclaircirMenu = new JMenuItem();
	private final JMenuItem retournerMenu = new JMenuItem();
	//private final JMenuItem convolutionMenu = new JMenuItem();
        private final JMenuItem rotateMenu = new JMenuItem();
	private final JMenu retaillerMenu = new JMenu();
	private final JMenuItem agrandirMenu = new JMenuItem();
	private final JMenuItem reduireMenu = new JMenuItem();
        private final JMenu reinitialiser = new JMenu();
	private final JMenuItem validationReinitialisation = new JMenuItem();


	public Cadre() {
		super("Observation d'un image radiologique");
		setBounds(100, 100, 500, 375);
               this.setPreferredSize(new Dimension(0,20));
              
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		try {
			creerMenu();
		} catch (Throwable e) {
			e.printStackTrace();
		}
                
               // Affichage Icone Syce + Titre 
               this.setIconImage(new ImageIcon(this.getClass().getResource("../Images/Logo_mini.png")).getImage());
               this.setTitle("Syce");
               
	}
	private void creerMenu() throws Exception {

		// construction du menu
		setJMenuBar(menuBar);	
		menuBar.add(fichierMenu);
		fichierMenu.setText("Fichier");
		fichierMenu.add(ouvrirMenu);
		ouvrirMenu.addActionListener((ActionListener)this);
		ouvrirMenu.setText("Charger une image");

		fichierMenu.add(enregistrerMenu);
		enregistrerMenu.addActionListener((ActionListener)this);
		enregistrerMenu.setText("Enregistrer l'image");
		menuBar.add(filtreMenu);	
		filtreMenu.setText("Filtre");

		filtreMenu.add(niveauGrisMenu);
		niveauGrisMenu.addActionListener((ActionListener)this);
		niveauGrisMenu.setText("Niveau de gris");

		filtreMenu.add(retournerMenu);
		retournerMenu.addActionListener((ActionListener)this);
		retournerMenu.setText("Retourner l'image");

		filtreMenu.add(assombrirMenu);
		assombrirMenu.addActionListener((ActionListener)this);
		assombrirMenu.setText("Assombrir");

		filtreMenu.add(eclaircirMenu);
		eclaircirMenu.addActionListener((ActionListener)this);
		eclaircirMenu.setText("Eclaircir");

		/*filtreMenu.add(convolutionMenu);
		convolutionMenu.addActionListener((ActionListener)this);
		convolutionMenu.setText("Convolution");*/
                
                filtreMenu.add(rotateMenu);
		rotateMenu.addActionListener((ActionListener)this);
		rotateMenu.setText("Rotation");

		menuBar.add(retaillerMenu);
		retaillerMenu.setText("Retailler");

		retaillerMenu.add(agrandirMenu);
		agrandirMenu.addActionListener((ActionListener)this);
		agrandirMenu.setText("Zoom +");

		retaillerMenu.add(reduireMenu);
		reduireMenu.addActionListener((ActionListener)this);
		reduireMenu.setText("Zoom -");

                menuBar.add(reinitialiser);
		reinitialiser.setText("Réinitialiser l'image");
		reinitialiser.add(validationReinitialisation);
		validationReinitialisation.addActionListener((ActionListener)this);
		validationReinitialisation.setText("Valider la réinitialisation");
		// ajouter le panneau de dessin
                
		getContentPane().add(panneau);
               
	}
	public void actionPerformed(ActionEvent cliqueMenu) {
		if (cliqueMenu.getSource().equals(ouvrirMenu))
		{
			JFileChooser fileOuvrirImage = new JFileChooser();
			if (fileOuvrirImage.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				panneau.ajouterImage(new File(fileOuvrirImage.getSelectedFile()
						.getAbsolutePath()));
			}
		} else if (cliqueMenu.getSource().equals(enregistrerMenu)) {
			JFileChooser fileEnregistrerImage = new JFileChooser();
			if (fileEnregistrerImage.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
				File fichierEnregistrement = new File(fileEnregistrerImage.getSelectedFile().getAbsolutePath()+ ".JPG");
				panneau.enregistrerImage(fichierEnregistrement);
			}
		} else	if (cliqueMenu.getSource().equals(niveauGrisMenu)) {
				panneau.imageEnNiveauGris();
			} else if (cliqueMenu.getSource().equals(eclaircirMenu)) {
				panneau.imageEclaircie();
			} else if (cliqueMenu.getSource().equals(retournerMenu)) {
				panneau.Retourner();
			} /*else if (cliqueMenu.getSource().equals(convolutionMenu)) {
				panneau.imageConvolue();
				System.out.println("appel convolution");
			}*/ else if (cliqueMenu.getSource().equals(agrandirMenu)) {
				panneau.agrandirImage();
			} else if (cliqueMenu.getSource().equals(reduireMenu)) {
				panneau.reduireImage();
			}else if(cliqueMenu.getSource().equals(assombrirMenu)){
				panneau.imageSombre();	
			}else if(cliqueMenu.getSource().equals(rotateMenu)){
				panneau.rotate();	
			}
	
                else  if (cliqueMenu.getSource().equals(validationReinitialisation)) {
                    //System.out.println("Coucou");
				panneau.reinitialiser();
                }
        }
        
public static void main(String args[]) 
	{
		try {
			Cadre frame = new Cadre();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}











