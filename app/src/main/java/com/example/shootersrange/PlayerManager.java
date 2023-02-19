package com.example.shootersrange;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.ArrayAdapter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class PlayerManager {
    public static PlayerManager instance = new PlayerManager();
    public ArrayList<Player> players = new ArrayList<>();
    private final String SEPAR = ",,";

    public PlayerManager() {
    }

    public void zapisHraca(Player hrac){

        instance.players.add(hrac);
    }



    // vysortuje arrayList players
    public void sortniList(ArrayList <Player> arrayList){
        Collections.sort(arrayList, new Comparator<Player>() {
            @Override
            public int compare(Player p1, Player p2) {
                return Integer.compare(p2.getScore(), p1.getScore());
            }
        });
    }

    // zapis scores do suboru scores.txt
    public void writePlayers(Context context, ArrayList <Player> players){

        SharedPreferences preferences = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        Set<String> zapisSet = new HashSet<String>();

        for (Player player : players) {
            zapisSet.add(player.toDb());
        }

        editor.putStringSet("users", zapisSet);
        editor.apply();

    }

    // precitanie scores zo suboru a zapis do SharedPreferences
    public ArrayList<Player> readPlayers(Context context){

        SharedPreferences preferences = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);

        Set<String> citajSet = preferences.getStringSet("users", null);
        ArrayList<Player> hraci = new ArrayList<>();

        if (citajSet != null){
            for (String info : citajSet) {
                hraci.add(new Player(info));
            }
        }

        return hraci;
    }

    public void clearPlayersData(Context context){
        SharedPreferences preferences = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        preferences.edit().clear().apply();
    }

    // print highscores
    public void showHighscores(){
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("scores.txt")));
            String [] newLine;
            String novylajn;
            System.out.println("\n--------------------------\n");
            System.out.println("Tabulka najlepsich hracov: ");
            int i = 1;
            while ((novylajn = br.readLine()) != null){
                newLine = novylajn.split(SEPAR);
                System.out.println(i + ". Meno: " + newLine[1] + "\b\t\tSkore: " + newLine[2]);
                i++;
            }

            br.close();
        } catch (IOException e) {
            System.out.println("Ziadny subor");
        }
    }

    // Vracia string s outputom vysledkov
    public String outputScores(){
        String result = "";
        for (Player player : PlayerManager.instance.players) {
            result += "Meno: "+ player.getName() + " \tSkore: "+player.getScore()+"\n";
        }
        return result;
    }



}
