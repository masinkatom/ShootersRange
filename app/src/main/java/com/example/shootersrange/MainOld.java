package com.example.shootersrange;

import java.util.Scanner;

public class MainOld {

    // vysledky hry po stlaceni X
    public static void endScreen(int rnds){
        System.out.println("\n--------------------------\n");
        System.out.println("Statistiky hry:\n");
        PlayerManager.instance.sortniList(PlayerManager.instance.players);
        System.out.println("Pocet odohranych kol: "+rnds);
        System.out.println("Tu su vysledky hry:");
        System.out.println(PlayerManager.instance.outputScores());
    }

    static void game(int rounds) {
        Scanner sc = new Scanner(System.in);

        sc.close();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // inicializacia hracov
        System.out.println("Zadaj pocet hracov:");
        int pocetHracov = sc.nextInt();

        sc.nextLine();


        for (int i = 0; i < pocetHracov; i++) {
            System.out.println("Zadaj meno hraca s id: " +i);
            String menoHraca = sc.nextLine();
            Player hrac = new Player(menoHraca, 0);
            PlayerManager.instance.zapisHraca(hrac);
        }


        // zacina pocitanie skore
        int rounds = 0;
        boolean looper = true;
        while (looper == true){
            System.out.println("\nZadaj, co chces urobit: (N - nove kolo, H - tabulka highscores, X - ukoncenie hry)");
            String gameState = sc.nextLine().toLowerCase();
            switch(gameState){
                case "x": {
                    // KONIEC HRY
                    endScreen(rounds/pocetHracov);
                    //PlayerManager.instance.writePlayerScores();
                    looper = false;
                    break;
                }
                case "h": {
                    PlayerManager.instance.showHighscores();
                    break;
                }
                case "n": {
                    for (Player player : PlayerManager.instance.players) {

                        System.out.println("\n--------------------------\n");
                        System.out.println("Hrac '"+player.getName()+ "' skore: "+ player.getScore());

                        System.out.println("Zadaj tvoje skore: ");
                        int skorePlayer = sc.nextInt();
                        int prevScore = player.getScore();
                        player.setScore(skorePlayer+prevScore);
                        rounds++;
                    }
                    sc.nextLine();
                    break;
                }
                default: System.out.println("Zadal si blud, tak este raz!");
                    break;
            }

        }
        sc.close();
    }

}