package projet_sih;

public class DateExamen implements Comparable {
    private int jour;
    private int mois;
    private int annee;
    private int heure;
    private int minutes;
    
    public DateExamen(int annee, int mois, int jour, int heure, int minutes) {
        
        if(((jour<0 || jour>31) || (mois<0 || mois>12) || (heure<0 || heure>23)|| (minutes<0 || minutes>59))){
            System.out.println("Vous avez fait une erreur dans le format de la date.");
        }
         //&& (mois<0 || mois>12) && (heure<00 || heure>23) && (minutes>59 || minutes <0)
        else {
        this.jour = jour;
        this.mois = mois;
        this.annee = annee;
        this.heure = heure;
        this.minutes = minutes;
        //System.out.println(this.toString());
        }
    }
    
    public DateExamen(int annee, int mois, int jour){
        if(((jour<0 || jour>31) || (mois<0 || mois>12))){
            System.out.println("Vous avez fait une erreur dans le format de la date.");
        }
         //&& (mois<0 || mois>12) && (heure<00 || heure>23) && (minutes>59 || minutes <0)
        else {
        this.jour = jour;
        this.mois = mois;
        this.annee = annee;
        this.heure=-1;
        System.out.println(this.toString());
        }
    }
    
    public String toString() {
        if(this.getHeure()==-1){
            return getAnnee() + "/" + getMois() + "/" + getJour();
        }
        else
            return getAnnee() + "/" + getMois() + "/" + getJour() + " à " + getHeure() + ":" + getMinutes();
        }
    
    public boolean equals(Object o) {
        if (o instanceof DateExamen) {
            DateExamen d = (DateExamen)o;
            return (getAnnee() == d.getAnnee()) && (getMois() == d.getMois()) && (getJour() == d.getJour()) && (getHeure() == d.getHeure()) && (getMinutes() == d.getMinutes());
            }
        else
            return false;
        }
    
    public int compareTo(Object o) {
        DateExamen d = (DateExamen)o;
        if (getAnnee() != d.getAnnee())
            return getAnnee() - d.getAnnee();
        if (getMois() != d.getMois())
            return getMois()  - d.getMois();
        if (getJour()!= d.getJour())
            return getJour() - d.getJour();
        if(getHeure() != d.getHeure())
            return getHeure()-d.getHeure();
        return getMinutes()-d.getMinutes();
        }

    /**
     * @return the jour
     */
    public int getJour() {
        return jour;
    }

    /**
     * @return the mois
     */
    public int getMois() {
        return mois;
    }

    /**
     * @return the annee
     */
    public int getAnnee() {
        return annee;
    }

    /**
     * @return the heure
     */
    public int getHeure() {
        return heure;
    }

    /**
     * @return the minutes
     */
    public int getMinutes() {
        return minutes;
    }
    
    }
