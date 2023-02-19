package com.example.shootersrange;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.function.Predicate;


public class StatsFragment extends Fragment {
    int pocetKol = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stats, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button menu = getView().findViewById(R.id.btnMenu);
        ListView hScoresView = getView().findViewById(R.id.highscoresList);
        TextView pKolView = getView().findViewById(R.id.pocetKol);

        // prijimanie argumentov z PreGame okna
        if (getArguments() != null) {
            StatsFragmentArgs args = StatsFragmentArgs.fromBundle(getArguments());
            pocetKol = args.getPocetKolArg();
        }

        pKolView.setText(Integer.toString(pocetKol));

        PlayerManager.instance.sortniList(PlayerManager.instance.players);
        ArrayAdapter adapter = new ArrayAdapter<Player>(getActivity(), R.layout.listview_highscores,PlayerManager.instance.players);
        hScoresView.setAdapter(adapter);

        ArrayList<Player>toAppend = PlayerManager.instance.readPlayers(getActivity().getApplicationContext());
        toAppend.addAll(PlayerManager.instance.players);
        PlayerManager.instance.writePlayers(getActivity().getApplicationContext(), toAppend);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_statsFragment_to_mainFragment);
            }
        });

    }
}