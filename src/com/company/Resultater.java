package com.company;

import java.io.*;
import java.util.Scanner;

public class Resultater {

    //Opg. 2.1: Printer antallet af gennemførte tests.
    public int udsAntalTests(Elev navn) throws IOException {

        BufferedReader laes = new BufferedReader(new FileReader(navn.getNavn()+".txt"));

        int testAntal = 0;
        while(laes.readLine() != null) {
            testAntal++;
        }
        laes.close();

        return testAntal;
    }

    //Opg. 2.2: Printer antal af samlet prøve antal.
    public void udskrivAntOpg(Elev[] elev, int antalTests, File fil) throws FileNotFoundException {

        Scanner ud = new Scanner(fil);

        double plusRigtige = 0;
        double plusOpg = 0;
        double minusRigtige = 0;
        double minusOpg = 0;

        for (int i = 0; i < antalTests; i++) {

            if(elev[i].getRegneart().equals("plus")){
                plusOpg+=10;
                plusRigtige += elev[i].getRigtige();
            }else {
                minusOpg+=10;
                minusRigtige += elev[i].getRigtige();
            }
        }
        ud.close();

        double procentPlus = (plusRigtige / plusOpg) * 100;
        double procentMinus = (minusRigtige / minusOpg) * 100;

        System.out.format("Regneart PLUS: Ud af %.0f opgaver var %.0f rigtige og svarende til %.0f%%\n",plusOpg,plusRigtige,procentPlus);
        System.out.format("Regneart MINUS: Ud af %.0f opgaver var %.0f rigtige og svarende til %.0f%%\n\n",minusOpg,minusRigtige,procentMinus);
    }

    //Opg. 2.3: Finder de opgaver, hvor alle opgaverne var 100% rigtig.
    public void uds100ProcRigtig(File fil) throws IOException {

        Scanner ud = new Scanner(fil);

        int alleRigtig = 0;
        while (ud.hasNext()) {
            if (ud.next().equals("10"))
                alleRigtig++;
        }
        ud.close();

        System.out.println(alleRigtig + " test var 100% rigtig!");
    }

    //Opg. 2.4: Finder de opgaver, hvor alle opgaverne var 50% rigtig eller over.
    public void udsMin50ProcRigtig(Elev[] elev, int antalTests, File fil) throws IOException {

        FileInputStream udFraFil = new FileInputStream(fil);
        Scanner ud = new Scanner(udFraFil);

        int halvEllerOver = 0;

        int i = 0;

        while(ud.hasNext() && i < antalTests){
            if(elev[i].getRigtige() >= 5)
                halvEllerOver++;
                i++;
        }
        udFraFil.close();

        System.out.println(halvEllerOver + " test var 50% rigtig eller over!\n");
    }

    //Finder de opgaver inden for, hver regneart, som var mindst 50% rigtige og op til 75%. Opg. 2.5
    public void uds50Til75Rigtig(Elev[] elev, File fil, int antalTests) throws IOException {

        Scanner ud = new Scanner(fil);

        int plusOpg = 0;
        int minusOpg = 0;

        for (int i = 0; i < antalTests; i++) {
            if (elev[i].getRegneart().equals("plus") && elev[i].getRigtige() >= 5 && elev[i].getRigtige() <= 7) {
                plusOpg++;
            } else if (elev[i].getRegneart().equals("minus") && elev[i].getRigtige() >= 5 && elev[i].getRigtige() <= 7) {
                minusOpg++;
            }
        }
        ud.close();

        System.out.println("Regneart PLUS: " + plusOpg + " test var mindst 50% og højst 75% rigtig");
        System.out.println("Regneart MINUS: " + minusOpg + " test var mindst 50% og højst 75% rigtig");
    }

    //Bruger input procent/regneart. Opg. 2.6
    public void udsProcentOgRegneart(Elev[] elev,File fil, int antalTests) throws IOException {

        Scanner tastatur = new Scanner(System.in);
        boolean run = true;
        while (run) {

            System.out.print("Indtast regneart: ");
            String regneartInput = tastatur.next();

            if (regneartInput.equalsIgnoreCase("plus") || regneartInput.equalsIgnoreCase("minus")) {

                System.out.print("Indtast et tal imellem 0-10, (1 svare til 10%): ");
                int procent = 0;

                while (!tastatur.hasNextInt()) {
                    String ugyldig = tastatur.next();
                    System.err.println("Forkert! " + ugyldig + ", kan ikke bruges. " + "Du skal skrive et tal imelle 1-10!");
                }
                procent = tastatur.nextInt();
                int procentVist = procent*10;

                Scanner ud = new Scanner(fil);

                int antalOpg = 0;
                for (int i = 0; i < antalTests; i++) {
                    if (elev[i].getRegneart().equals(regneartInput) && elev[i].getRigtige() == procent){
                        antalOpg++;
                    }
                }
                ud.close();

                System.out.println("Ved regneart: " + regneartInput.toUpperCase() + " og procent: " + procentVist + ", blev der fundet " + antalOpg + " test\n");

                System.out.println("Vil du søge efter flere resultater eller videre?");
                System.out.println("Tryk 1 for at søge igen");
                System.out.println("Tryk 2 for at gå videre, afslut med 'enter'!");

                StartStop start = new StartStop();
                boolean afgør = true;
                while (afgør) {

                    String brugerInput = tastatur.next();
                    switch (brugerInput) {

                        case "1" -> {
                            udsProcentOgRegneart(elev,fil,antalTests);
                            afgør = false;
                        }
                        case "2" -> {
                            start.start();
                            afgør = false;
                            run = false;
                        }
                        default -> {
                            System.err.println("Forkert!");
                        }
                    }
                }
            }else {
                System.err.println("Forkert! Skriv 'plus' eller 'minus'");
            }
        }
    }

    //Testdata skrives til fil her
    public void skrivResTilFil(Testen elevIgang, Elev navn) throws IOException{

        FileWriter fw = new FileWriter(navn.getNavn()+".txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter ud = new PrintWriter(bw);

        ud.format("%-2d %-2s %d\n",elevIgang.getRigtige(), elevIgang.getRegneart(), elevIgang.getSvaerhed());

        ud.close();
    }
}
