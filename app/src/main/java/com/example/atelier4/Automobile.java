package com.example.atelier4;

public class Automobile {
    private static final int[][] TARIF_PHYSIQUE={{60,210,385},{120,270,445}};
    private static final int[][] TARIF_MORALE={{120,270,445},{240,390,565}};
    private String matricule;
    private int type;
    private int puissance;
    private String carburent;

    //constructeur
    public Automobile(String matricule, int type, int puissance, String carburent) {
        this.matricule = matricule;
        this.type = type;
        this.puissance = puissance;
        this.carburent = carburent;
    }

    public String getMatricule() {
        return matricule;
    }

    public int getType() {
        return type;
    }

    public int getPuissance() {
        return puissance;
    }

    public String getCarburent() {
        return carburent;
    }
    public int getPrix(){
        int iP=0;
        int iC=0;
        iP=(puissance<=4)?0:1;
        if(carburent.equals("essence"))
            iC=0;
        else if (carburent.equals("diesel"))
            iC=0;
        else
            iC=2;
        if (type==0)
            return TARIF_PHYSIQUE[iP][iC];
        else
            return TARIF_MORALE[iP][iC];
    }

    public String toString(){
        String strType="";
        if(type==0)
            strType="Phy";
        else
            strType="Mor";
        return matricule +"{"+
                strType+
                "- "+puissance+"CH"+
                "- "+carburent+
                "- "+getPrix()+"DT"+
                "}";
    }
}
