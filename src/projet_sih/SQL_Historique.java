/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_sih;

/**
 *
 * @author Julien
 */

import projet_sih.*;
import BasesDonnees.Connecter;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import javax.swing.JOptionPane;

public class SQL_Historique {
    
    static Connecter con;
    private int err;
    private String connexion;
    /*private Chef_De_Service chef;
    private Manipulateur_Radio manip;
    private PH ph;
    private Interne interne;*/
    private String dateHeureJour;

     public SQL_Historique() throws SQLException, InstantiationException, IllegalAccessException {
        Date date = new Date();
        date = date.dateJour();
        dateHeureJour = date.toString();
        con = new Connecter();

        con.connectDB();
        err = 0;

    }
     
      public void connexionPersonnel(Personnel p) {
        try {

            String requete = "INSERT INTO historique (Date,ID,Type,IPP)"
                    + "Values (?,?,?,?)";
            PreparedStatement prepS = con.creerPreparedStatement(requete);
            
            prepS.setObject(1, dateHeureJour);

            prepS.setObject(2, p.getId().toString());

            prepS.setObject(3, "connexion");

            prepS.setObject(4, "personnel");

            prepS.executeUpdate();

        } catch (SQLException e) {
            err = 1;
            JOptionPane.showMessageDialog(null, e + "\n Une erreur est survenue lors de l'ajout à la base de donnees, contactez un responsable technique avec ce message d'erreur", "Erreur Bases de données", JOptionPane.ERROR_MESSAGE);

        }
    }

}



