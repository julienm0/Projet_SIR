import projet_sih.Date; 

// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.58D61B90-5880-253B-F3E9-ADEFAB56B0A8]
// </editor-fold> 
public class DossierMedicalRadiologique {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.C9EC9F3D-D27B-2EBF-47CE-E91DA5E21A3F]
    // </editor-fold> 
    private double numId;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.14B84813-2EF9-22A9-9329-CC61102B5246]
    // </editor-fold> 
    private String nom;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.7B72F860-EC45-EBA2-31BC-32DD8951D9BB]
    // </editor-fold> 
    private String prenom;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.160AD776-2ADD-BD8B-464B-F4B26F85449B]
    // </editor-fold> 
    private Date dateDeNaissance;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.3C4E3363-3029-489B-272B-73E9D58DB8C0]
    // </editor-fold> 
    private Sexe sexe;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.8CD5C03F-94D6-B1CE-FAA5-8B13B7484D21]
    // </editor-fold> 
    public DossierMedicalRadiologique () {
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.4C7AB457-3D87-7D62-5F91-F125566C132D]
    // </editor-fold> 
    public Date getDateDeNaissance () {
        return dateDeNaissance;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.CA46CEA5-51AF-E989-57ED-F783143EDAE0]
    // </editor-fold> 
    public void setDateDeNaissance (Date val) {
        this.dateDeNaissance = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.55274285-0E53-1920-219F-5E0E3E76832A]
    // </editor-fold> 
    public String getNom () {
        return nom;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.64FE4BF9-8E3B-83CB-590C-8EA57A5C5440]
    // </editor-fold> 
    public void setNom (String val) {
        this.nom = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.DBD04CC7-4BE0-CBBF-4DA3-83759BE52F05]
    // </editor-fold> 
    public double getNumId () {
        return numId;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.1A4E82F2-ACC2-40C5-F5AC-DD8E7719D00B]
    // </editor-fold> 
    public void setNumId (double val) {
        this.numId = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.5041C6B5-FAEF-CBF2-96B7-9633A69B7E87]
    // </editor-fold> 
    public String getPrenom () {
        return prenom;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.E106A019-CC58-C5F0-A7CD-E68BECA76714]
    // </editor-fold> 
    public void setPrenom (String val) {
        this.prenom = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.E396868A-851E-BF6A-BC68-486E867E5911]
    // </editor-fold> 
    public Sexe getSexe () {
        return sexe;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.EF214777-0D9A-E97D-0A6F-FFC3808F7E07]
    // </editor-fold> 
    public void setSexe (Sexe val) {
        this.sexe = val;
    }

}

