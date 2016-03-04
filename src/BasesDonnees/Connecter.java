/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BasesDonnees;

import java.sql.*;
import javax.swing.*;

/**
 *
 * @author Julien
 */
public class Connecter {
    Connection con;
    
    public static Connection connectDB(){
        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sir","root","");
            return conn;
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
            return null;
        }
    }

    public Connecter() throws SQLException {
        this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sir","root","");
    }
    
    public PreparedStatement creerPreparedStatement(String requete) throws SQLException {
        PreparedStatement stmt = con.prepareStatement(requete);

        return stmt;
    }

    public ResultSet resultatRequete(String requete) throws SQLException {
        ResultSet resultat;
        Statement st = con.createStatement();
        resultat = st.executeQuery(requete);
        return resultat;
    }
    
    public void StopDataBase() throws SQLException {
        con.close();
    }
}
