package com.company;

import java.util.Random;
import java.util.Scanner;

public class Testen {

    private int opgaveNr = 1;
    private String fortegn = "";
    private int foersteLed;
    private int andetLed;
    private int rigtige;
    private int forkerte;
    private double procentRigtige;
    private int TjekElevSvar;
    private int svaerhed;
    private String regneart;

    Scanner valg = new Scanner(System.in);
    Random randomTal = new Random();

    //Sværhedsgraden vælges her.
    public void svaerhedsgrad(){

        System.out.println("Hvor svært skal det være? \n\nTryk 1 for let \nTryk 2 for svær! \nTryk 'enter' for at fortsætte!");

        while((valg.hasNextDouble() || valg.hasNext()) && !valg.hasNextInt()){
            if(valg.hasNextDouble()){
                double ugyldig = valg.nextDouble();
                System.err.println(ugyldig + ", er ugyldig. Tryk 1 eller 2 og derefter 'enter'!");
            }else{
                String ugyldig2 = valg.nextLine();
                System.err.println(ugyldig2 + ", er ugyldig. Tryk 1 eller 2 og derefter 'enter'!");
            }
        }

        int valget = valg.nextInt();
        if(valget == 1){
            System.out.println("Du har valgt sværhedsgraden: Let \n");
            setSvaerhed(1);
        }else {
            System.out.println("Du har valgt sværhedsgraden: Svær \n");
            setSvaerhed(2);
        }
    }

    //Plus eller minus vælges her.
    public void plusEllerMinus(){

        while ((valg.hasNextDouble() || valg.hasNext()) && !valg.hasNextInt()) {
            if(valg.hasNextDouble()) {
                double ugyldig = valg.nextDouble();
                System.err.println(ugyldig + ", er ugyldig. Tryk 1 eller 2 og derefter 'enter'!");
            }else if(valg.hasNext()){
                String ugyldig2 = valg.next();
                System.err.println(ugyldig2 + ", er ugyldig. Tryk 1 eller 2 og derefter 'enter'!");
            }else{
                System.err.println("Tryk 1 eller 2 og derefter 'enter'!");
            }
        }

        int valget = valg.nextInt();
        if(valget == 1){
            System.out.println("Du har valgt regneart: Plus-stykker! \n");
            setRegneart("plus");
        }else {
            System.out.println("Du har valgt regneart: Minus-stykker! \n");
            setRegneart("minus");
        }
    }

    //Testen køres her.
    public void testen(){

        for (int i = 0; i < 10; i++) {

            System.out.println("Opgave: " + opgaveNr++ + ".");

            //Første led og andet led
            if(getSvaerhed() == 1) {
                this.foersteLed = randomTal.nextInt(45);
                this.andetLed = randomTal.nextInt(40);
            }else {
                this.foersteLed = randomTal.nextInt(50) + 30;
                this.andetLed = randomTal.nextInt(70) + 40;
            }

            //Definere fortegn udfra bruger input.
            if(getRegneart().equals("plus")){
                setFortegn("+");
            }else{
                setFortegn("-");
            }

            //Finder det rigtige fortegn her.
            if(this.fortegn.equals("+")){
                this.TjekElevSvar = foersteLed + andetLed;
            }else{
                this.TjekElevSvar = foersteLed - andetLed;
            }

            //Opgaven printes her.
            System.out.print("Hvad er: " + foersteLed + getFortegn() + andetLed + " = ");

            //Svaret tjekkets her og der gives respons til bruger.
            boolean harSvaret = true;
            while (harSvaret && valg.hasNextInt()) {
                if (this.TjekElevSvar == valg.nextInt()) {
                    rigtige++;
                    System.out.println("Rigtig!\n");
                    harSvaret = false;
                }else {
                    forkerte++;
                    System.out.println("Forkert! Det rigtige svar var: " + this.TjekElevSvar +"\n");
                    harSvaret = false;
                }
            }
        }
    }

    //Beregner antal rigtige i procent %.
    public void procentRigtige(){
        this.procentRigtige = (getRigtige()/10.0) * 100;
    }

    //Getters og setters
    public String getRegneart() {
        return regneart;
    }
    public void setRegneart(String regneart) {
        this.regneart = regneart;
    }

    public int getSvaerhed() {
        return svaerhed;
    }
    public void setSvaerhed(int svaerhed) {
        this.svaerhed = svaerhed;
    }

    public int getRigtige() {
        return rigtige;
    }

    public int getForkerte() {
        return forkerte;
    }

    public void setRigtige(int rigtige) {
        this.rigtige = rigtige;
    }

    public void setForkerte(int forkerte) {
        this.forkerte = forkerte;
    }

    public void setOpgaveNr(int opgaveNr) {
        this.opgaveNr = opgaveNr;
    }

    public String getFortegn() {
        return fortegn;
    }

    public void setFortegn(String fortegn) {
        this.fortegn = fortegn;
    }

    public double getProcentRigtige() {
        return procentRigtige;
    }
}
