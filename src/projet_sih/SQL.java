/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_sih;

import projet_sih.Date;
import BasesDonnees.Connecter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Julien
 */
public class SQL {

    static Connecter con;
    private int err;
    private String connexion;
    private Radiologue radiologue;
    private Chef_De_Service chef;
    private Interne interne;
    private Manip_Radio manip_Radio;
    private SQL_Historique sqlHisto;

    public SQL() throws SQLException, InstantiationException, IllegalAccessException {
        con = new Connecter();
        con.connectDB();
        err = 0;
        sqlHisto = new SQL_Historique();

    }

    public void connexionSIR(String id, String motDePasse) throws SQLException {
        String rq = "SELECT * FROM personnel";
        try {
            boolean boucle = true;
            ResultSet result = con.resultatRequete(rq);

            while (result.next()) {

                if (result.getString("id").equals(id)) {
                    boucle = false;
                    if (result.getString("mdp").equals(motDePasse)) {
                        if (result.getString("fonction").equals("Interne")) {
                            connexion = "interne";
                            interne = new Interne(result.getString("nom"), result.getString("prenom"), result.getString("id"));
                            sqlHisto.connexionPersonnel(interne);
                        }

                        if (result.getString("fonction").equals("radiologue")) {
                            connexion = "radiologue";
                            radiologue = new Radiologue(result.getString("nom"), result.getString("prenom"), result.getString("id"));
                            sqlHisto.connexionPersonnel(radiologue);
                        }

                        if (result.getString("fonction").equals("manipulateur radio")) {
                            connexion = "manipulateur radio";
                            manip_Radio = new Manip_Radio(result.getString("nom"), result.getString("prenom"), result.getString("id"));
                            sqlHisto.connexionPersonnel(manip_Radio);
                        }

                        if (result.getString("fonction").equals("chef de service")) {
                            connexion = "chef de service";
                            chef = new Chef_De_Service(result.getString("nom"), result.getString("prenom"), result.getString("id"));
                            sqlHisto.connexionPersonnel(chef);
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Mot de passe incorrect",
                                "Erreur", JOptionPane.ERROR_MESSAGE);
                        err = 1;
                    }
                }
            }
            if (boucle) {
                con.StopDataBase();
                JOptionPane.showMessageDialog(null, "Identifiant incorrect",
                        "Erreur", JOptionPane.ERROR_MESSAGE);
                err = 1;

            }
        } catch (SQLException e) {
            err = 1;
            JOptionPane.showMessageDialog(null, e,
                    "Erreur", JOptionPane.ERROR_MESSAGE);
        }

    }
     
    public JTable modeleTableauPatient(JTable table, JTextField nom, JTextField prenom,
            JTextField dateDeNaissance) {

        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("ID Patient");
        model.addColumn("Nom");
        model.addColumn("Prenom");
        model.addColumn("Date de naissance");
        model.addColumn("Sexe");
        model.addColumn("Adresse");

  
        try {
            if ((!nom.getText().equals("")) && (prenom.getText().equals("")) && (dateDeNaissance.getText().equals(""))) {
                String rq = "SELECT * FROM patients WHERE nom=" + "'" + nom.getText() + "'";
                ResultSet result1 = con.resultatRequete(rq);


                while (result1.next()) {
                      model.addRow(new Object[]{result1.getString("id"), result1.getString("nom"), result1.getString("prenom"),
                        result1.getString("date_de_naissance"), result1.getString("sexe"), result1.getString("adresse")});
                }

            } else if ((!nom.getText().equals("")) && (!prenom.getText().equals("")) && (dateDeNaissance.getText().equals(""))) {
                String rq = "SELECT * FROM patients WHERE nom=" + "'" + nom.getText() + "'" + "AND prenom=" + "'" + prenom.getText() + "'";
                ResultSet result1 = con.resultatRequete(rq);

                while (result1.next()) {
                    model.addRow(new Object[]{result1.getString("id"), result1.getString("nom"), result1.getString("prenom"),
                        result1.getString("date_de_naissance"), result1.getString("sexe"), result1.getString("adresse")});
                }

            } else if ((!nom.getText().equals("")) && (prenom.getText().equals("")) && (!dateDeNaissance.getText().equals(""))) {
                String rq = "SELECT * FROM patients WHERE Nom=" + "'" + nom.getText() + "'" + "AND Prenom=" + "'" + prenom.getText() + "'"
                        + " AND date_de_naissance=" + "'" + dateDeNaissance.getText() + "'";
                ResultSet result1 = con.resultatRequete(rq);

                while (result1.next()) {
                    model.addRow(new Object[]{result1.getString("id"), result1.getString("nom"), result1.getString("prenom"),
                        result1.getString("date_de_naissance"), result1.getString("sexe"), result1.getString("adresse")});
                }

            } else if ((nom.getText().equals("")) && (!prenom.getText().equals("")) && (dateDeNaissance.getText().equals(""))) {
                String rq = "SELECT * FROM patients WHERE Prenom=" + "'" + prenom.getText() + "'";
                ResultSet result1 = con.resultatRequete(rq);

                while (result1.next()) {
                    model.addRow(new Object[]{result1.getString("id"), result1.getString("nom"), result1.getString("prenom"),
                        result1.getString("date_de_naissance"), result1.getString("sexe"), result1.getString("adresse")});
                }

            } else if ((nom.getText().equals("")) && (prenom.getText().equals("")) && (!dateDeNaissance.getText().equals(""))) {
                String rq = "SELECT * FROM patients WHERE date_de_naissance=" + "'" + dateDeNaissance.getText() + "'";
                ResultSet result1 = con.resultatRequete(rq);

                while (result1.next()) {
                    model.addRow(new Object[]{result1.getString("id"), result1.getString("nom"), result1.getString("prenom"),
                        result1.getString("date_de_naissance"), result1.getString("sexe"), result1.getString("adresse")});
                }
            } else if ((nom.getText().equals("")) && (!prenom.getText().equals("")) && (!dateDeNaissance.getText().equals(""))) {
                String rq = "SELECT * FROM patients WHERE Prenom=" + "'" + prenom.getText() + "'"
                        + " AND date_de_naissance=" + "'" + dateDeNaissance.getText() + "'";
                ResultSet result1 = con.resultatRequete(rq);

                while (result1.next()) {
                    model.addRow(new Object[]{result1.getString("id"), result1.getString("nom"), result1.getString("prenom"),
                        result1.getString("date_de_naissance"), result1.getString("sexe"), result1.getString("adresse")});
                }
            } else {
                System.out.println("erreur");
            }

        } catch (SQLException e) {
            err = 1;
            JOptionPane.showMessageDialog(null, e + "\n Une erreur est survenue lors de la lecture des patients", "Erreur Bases de données", JOptionPane.ERROR_MESSAGE);
        }

        table.setModel(model);
        return table;
    }

    /* public String getDMR(String nom, String prenom, String dateDeNaissance, String adresse){
        
     if(nom==null){
     nom="*";
     }
        
     if(prenom==null){
     prenom="*";
     }
        
     if(dateDeNaissance==null){
     dateDeNaissance="*";
     }
        
     if(adresse==null){
     adresse="*";
     }
        
        
     String rq = "SELECT * FROM patients WHERE Nom="+nom+"AND Prenom="+prenom
     +"AND Date de naissance="+dateDeNaissance+"AND Adresse="+adresse;
        
     try {
     ResultSet result = con.resultatRequete(rq);

     while (result.next()) {

     = result.getString("Champs_prescription");

     }
     } catch (SQLException e) {
     err = 1;
     JOptionPane.showMessageDialog(null, e,
     "Erreur", JOptionPane.ERROR_MESSAGE);

     }
     return nom;
     }*/
    public int getErreur() {
        return err;
    }

    public void setErreur(int err) {
        this.err = err;
    }

    public String getConnexion() {
        System.out.println("prout");
        return connexion;
    }

    public Interne getInterne() {
        return interne;
    }

    public Manip_Radio getManip_Radio() {
        return manip_Radio;
    }

    public Radiologue getRadiologue() {
        return radiologue;
    }

    public Chef_De_Service getChef() {
        return chef;
    }
}
