package com.company;

import java.io.*;
import java.util.Scanner;

public class Elev {

    private String navn;
    private int rigtige;
    private String regneart;
    private int svaerhedsgrad;

    public Elev() {
    }

    public Elev(String navn, int rigtige, String regneart, int svaerhedsgrad) {
        this.navn = navn;
        this.rigtige = rigtige;
        this.regneart = regneart;
        this.svaerhedsgrad = svaerhedsgrad;
    }

    @Override
    public String toString() {
        return "Elev{" +
                "navn='" + navn + '\'' +
                ", rigtige=" + rigtige +
                ", regneart='" + regneart + '\'' +
                ", svaerhedsgrad=" + svaerhedsgrad +
                '}';
    }

    //Ny fil bliver oprettet her og elevens navn bruges som filnavn.
    public void opretElev() throws IOException {

        Scanner tastatur = new Scanner(System.in);

        while(tastatur.hasNextInt() || tastatur.hasNextDouble()){

            if(tastatur.hasNextInt()){
                int ugyldig = tastatur.nextInt();
                System.err.println("Forkert! " + ugyldig + ", kan ikke bruges. " + "Du skal skrive dit navn med bogstaver!");
                System.out.print("\nSkriv dit navn her og tryk enter: ");
            }else{
                double ugyldig2 = tastatur.nextDouble();
                System.err.println("\nForkert! " + ugyldig2 + ", kan ikke bruges. " + "Du skal skrive dit navn med bogstaver!");
                System.out.print("\nSkriv dit navn her og tryk enter: ");
            }
        }
        setNavn(tastatur.next());

        System.out.println("Hej " + navn + "!");

        File personX = new File(navn+".txt");
        if (personX.createNewFile()) {
            System.out.println("Velkommen til matematikmaskinen");
        }else {
            System.out.println("Velkommen tilbage :-)\n");
        }
    }

    //Indlæser tests fra fil.
    public void indlaesTests(Elev[] elev,File fil) throws IOException {

        Scanner ind = new Scanner(fil);

        int i = 0;
        while(ind.hasNext()) {
            elev[i].setNavn(getNavn());
            elev[i].setRigtige(ind.nextInt());
            elev[i].setRegneart(ind.next());
            elev[i].setSvaerhedsgrad(ind.nextInt());
            i++;
        }
        ind.close();
    }

    //Getter og setters
    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public int getRigtige() {
        return rigtige;
    }

    public void setRigtige(int rigtige) {
        this.rigtige = rigtige;
    }

    public String getRegneart() {
        return regneart;
    }

    public void setRegneart(String regneart) {
        this.regneart = regneart;
    }

    public void setSvaerhedsgrad(int svaerhedsgrad) {
        this.svaerhedsgrad = svaerhedsgrad;
    }
}
