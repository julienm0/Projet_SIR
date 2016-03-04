
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.BEAC3173-A6DD-4459-12D7-A036B33B3345]
// </editor-fold> 
public class ExamenRadiologique {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.C51BB895-73F2-2FDE-7C21-7F2027C8AF23]
    // </editor-fold> 
    private DateExamen dateExamen;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.42D5403A-FF84-889F-D8AC-D6F2DC6051AD]
    // </editor-fold> 
    private String nomPH;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.53E4A4FF-98A5-A09F-E4E6-80FF07E23A6B]
    // </editor-fold> 
    private CompteRendu compteRendu;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.04A0D833-A6D3-3309-7F8B-3929F93818FD]
    // </editor-fold> 
    private Image image;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.51543FC5-46E5-F697-DB4D-00E56C2C27EA]
    // </editor-fold> 
    private TypeExamen typeExamen;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.C0930E9D-FDB9-CFFB-F3E6-77AB19201007]
    // </editor-fold> 
    public ExamenRadiologique () {
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.4B472F0F-BA51-C613-509C-23139AED258A]
    // </editor-fold> 
    public CompteRendu getCompteRendu () {
        return compteRendu;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.35152E33-E982-B68E-165B-ED51D7022F7F]
    // </editor-fold> 
    public void setCompteRendu (CompteRendu val) {
        this.compteRendu = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.E7E5845A-6E34-5A9A-5D85-E9E078896B46]
    // </editor-fold> 
    public DateExamen getDateExamen () {
        return dateExamen;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.9ABA4097-364C-8F96-C473-F8685FDF4657]
    // </editor-fold> 
    public void setDateExamen (DateExamen val) {
        this.dateExamen = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.B644C554-DBA0-AA24-AC01-26DDD5D92936]
    // </editor-fold> 
    public Image getImage () {
        return image;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.26AEC4C0-2E5A-531D-F715-4117368D8533]
    // </editor-fold> 
    public void setImage (Image val) {
        this.image = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.565BD251-03E3-F6AB-1B41-0B6675C5D3BB]
    // </editor-fold> 
    public String getNomPH () {
        return nomPH;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.B6BAC43F-5C71-24C1-582B-01445D57186E]
    // </editor-fold> 
    public void setNomPH (String val) {
        this.nomPH = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.3F163388-C639-37CD-08ED-419E45BA2B0A]
    // </editor-fold> 
    public TypeExamen getTypeExamen () {
        return typeExamen;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.9D81876A-18BB-8412-E832-7B1224460FB0]
    // </editor-fold> 
    public void setTypeExamen (TypeExamen val) {
        this.typeExamen = val;
    }

}

