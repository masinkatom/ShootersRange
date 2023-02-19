package com.example.shootersrange;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class HighscoresFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_highscores, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btnMenu = getView().findViewById(R.id.btnMenu2);
        ListView hScoresView = getView().findViewById(R.id.lvHighScores);
        Button btnDelete = getView().findViewById(R.id.btnClearScores);


        PlayerManager.instance.players = PlayerManager.instance.readPlayers(getActivity().getApplicationContext());
        PlayerManager.instance.sortniList(PlayerManager.instance.players);
        ArrayAdapter adapter = new ArrayAdapter<Player>(getActivity(), R.layout.listview_highscores,PlayerManager.instance.players);
        hScoresView.setAdapter(adapter);

        getActivity().getApplicationContext();

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayerManager.instance.clearPlayersData(getActivity().getApplicationContext());
                PlayerManager.instance.players.clear();
                adapter.notifyDataSetChanged();
            }
        });

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_highscoresFragment_to_mainFragment);
            }
        });

    }
}