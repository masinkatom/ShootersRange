package com.example.shootersrange;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class GameFragment extends Fragment {

    int playerid = 0;
    int klikaneSkore = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button p1 = getView().findViewById(R.id.btnP1);
        Button p5 = getView().findViewById(R.id.btnP5);
        Button m1 = getView().findViewById(R.id.btnM1);
        Button m5 = getView().findViewById(R.id.btnM5);
        Button next = getView().findViewById(R.id.btnNextPlayer);
        TextView score = getView().findViewById(R.id.scorePlayer);
        TextView menoHrac = getView().findViewById(R.id.lblNameScore);

        int pocetHracov = PlayerManager.instance.players.size() - 1;
        klikaneSkore = 0;
        menoHrac.setText("Hráč '" + PlayerManager.instance.players.get(playerid).getName() + "' má " + nahrajSkore(playerid) + " bodov");
        score.setText(Integer.toString(klikaneSkore));

        p1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                klikaneSkore += 1;
                score.setText(Integer.toString(klikaneSkore));
            }
        });
        p5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                klikaneSkore += 5;
                score.setText(Integer.toString(klikaneSkore));
            }
        });
        m1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                klikaneSkore -= 1;
                score.setText(Integer.toString(klikaneSkore));
            }
        });
        m5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                klikaneSkore -= 5;
                score.setText(Integer.toString(klikaneSkore));
            }
        });

        // stlacim tlacidlo dalsieho hraca
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (playerid < pocetHracov) {
                    PlayerManager.instance.players.get(playerid).setScore(nahrajSkore(playerid) + klikaneSkore);
                    playerid++;
                    klikaneSkore = 0;
                    menoHrac.setText("Hráč '" + PlayerManager.instance.players.get(playerid).getName() + "' má " + nahrajSkore(playerid) + " bodov");
                    score.setText(Integer.toString(klikaneSkore));
                    if (playerid == pocetHracov) {
                        next.setText("Koniec kola");
                    }

                }
                else {
                    PlayerManager.instance.players.get(playerid).setScore(nahrajSkore(playerid) + klikaneSkore);
                    Navigation.findNavController(view).navigate(R.id.action_gameFragment_to_pre_gameFragment);
                }
            }
        });


    }

    int nahrajSkore(int id){
        return PlayerManager.instance.players.get(id).getScore();
    }
}