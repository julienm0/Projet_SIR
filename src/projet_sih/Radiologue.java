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
public class Radiologue extends Personnel{
    
    private String mdp;
    private String fonction;

    public Radiologue(String nom, String prenom, String id) {
        super(nom, prenom, id);
        fonction="radiologue";
    }
    
        public Radiologue(String nom, String prenom, String id, String mdp) {
        super(nom, prenom, id);
        this.mdp = mdp;
        fonction="radiologue";
    }
    
        public String getId(){
        return super.getId();
    }
    
    public String getNom(){
        return super.getNom();
    }
    
    public String getPrenom(){
        return super.getPrenom();
    }
    
    public String getMotDePasse(){
        return mdp;
    }
    
    public String getFonction(){
        return fonction;
    }
}
