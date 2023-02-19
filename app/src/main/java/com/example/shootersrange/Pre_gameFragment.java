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

import com.example.shootersrange.Pre_gameFragmentDirections;


public class Pre_gameFragment extends Fragment {

    static int pocetKol = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pre_game, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button noveKolo = getView().findViewById(R.id.btnKolo);
        Button endGame = getView().findViewById(R.id.btnQuit);

        noveKolo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pocetKol++;
                Navigation.findNavController(view).navigate(R.id.action_pre_gameFragment_to_gameFragment);
            }
        });


        endGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Posielanie argumentov o pocte odohranych kol
                Pre_gameFragmentDirections.ActionPreGameFragmentToStatsFragment action = Pre_gameFragmentDirections.actionPreGameFragmentToStatsFragment();
                action.setPocetKolArg(pocetKol);
                pocetKol = 0;
                // a tak prejdem na stats okno
                Navigation.findNavController(view).navigate(action);
            }
        });



    }
}