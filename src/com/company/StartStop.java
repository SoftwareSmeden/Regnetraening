package com.company;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class StartStop {

    public void start() throws IOException{

        Elev[] elevIGang = new Elev[100];

        for (int i = 0; i < elevIGang.length; i++) {
            elevIGang[i] = new Elev();
        }

        System.out.println("MATEMATIKMASKINEN!");
        System.out.println("Her kan du øve dig i at plus og minus. \n");

        System.out.println("Inden vi kan starte skal du skrive dit navn.\n");
        System.out.print("Skriv dit navn her og tryk enter: ");

        Elev eleven = new Elev();
        eleven.opretElev();

        File fil = new File(eleven.getNavn()+".txt");
        eleven.indlaesTests(elevIGang,fil);

        Testen elevTest = new Testen();
        Resultater elevRes = new Resultater();

        int antalTests = elevRes.udsAntalTests(eleven);
        tjekElev(elevIGang,elevTest,elevRes,eleven,antalTests,fil);

        System.out.println("Tryk 1: Kun plus-stykker");
        System.out.println("Tryk 2: Kun minus-stykker");
        System.out.println("Tryk 'enter' efter du har valgt en regneart");

        elevTest.plusEllerMinus();
        elevTest.testen();
        elevTest.procentRigtige();

        System.out.println(eleven.getNavn().toUpperCase() + ", du har gennemført opgaverne og har fået følgende:");
        System.out.println("Regneart: " + elevTest.getRegneart());
        System.out.println("Sværhedsgraden: " + elevTest.getSvaerhed());
        System.out.println("ANTAL RIGTIGE: " + elevTest.getRigtige());
        System.out.println("ANTAL FORKERTE: " + elevTest.getForkerte());
        System.out.format("Antal rigtig vist i procent: "+"%d%%\n", (int)elevTest.getProcentRigtige());

        elevRes.skrivResTilFil(elevTest,eleven);

        restart(elevTest);
    }

    //Her kan elev bede om en ny test eller se deres resultater.
    public void tjekElev(Elev[] elevIGang,Testen elevTest, Resultater elevRes, Elev eleven, int testAntal, File fil) throws IOException {

        Scanner input = new Scanner(System.in);
        StartStop kør = new StartStop();

        System.out.println("Vil du tage en test eller tjekke resultater?");
        System.out.println("Tryk 1 for test");
        System.out.println("Tryk 2 for resultater");
        System.out.println("Tryk 3 for en bestemt regneart og procent, afslut med 'enter'!");

        boolean afgør = true;
        while (afgør) {
            String playerInput = input.next();
            switch (playerInput) {

                //Ny test tages.
                case "1" -> {
                    elevTest.svaerhedsgrad();
                    afgør = false;
                }
                //Resultater printes.
                case "2" -> {
                    System.out.println(eleven.getNavn().toUpperCase() + " har gennemført " + testAntal + " test\n");
                    elevRes.udskrivAntOpg(elevIGang,testAntal,fil);
                    elevRes.uds100ProcRigtig(fil);
                    elevRes.udsMin50ProcRigtig(elevIGang,testAntal,fil);
                    elevRes.uds50Til75Rigtig(elevIGang,fil,testAntal);
                    restart(elevTest);
                }
                //Søg efter bestemt resultat.
                case "3" -> {
                    elevRes.udsProcentOgRegneart(elevIGang,fil,testAntal);
                    restart(elevTest);
                    afgør = false;
                }
            }
        }
    }

    //Mulighed for at genstarte eller afslutte her.
    public void restart(Testen elevTest) throws IOException {

        Scanner brugerInput = new Scanner(System.in);

        System.out.println("\nVil du prøve igen? \nTryk 'j' for at prøve igen eller tryk 'n' for afslut. Afslut med 'enter'");

        boolean afgør = true;
        while (afgør) {
            String playerInput = brugerInput.nextLine();
            switch (playerInput) {

                case "j" -> {
                    elevTest.setRigtige(0);
                    elevTest.setForkerte(0);
                    elevTest.setOpgaveNr(0);
                    start();
                    afgør = false;
                }
                case "n" -> {
                    System.out.println("Tak for denne gang! farvel!");
                    System.exit(0);
                }
            }
        }
    }
}
