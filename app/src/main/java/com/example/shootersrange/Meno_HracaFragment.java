package com.example.shootersrange;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Meno_HracaFragment extends Fragment {
    int clicked = 1;
    int pocetHracov = 1;
    int unknown = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meno__hraca, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button next = getView().findViewById(R.id.btnNextMenoHraca);
        EditText ETmenoHraca = getView().findViewById(R.id.EditMenoHraca);
        TextView LabelMeno = getView().findViewById(R.id.LabelMeno);
        // vycistim arraylist (preistotu)
        PlayerManager.instance.players.clear();


        // prijimanie argumentov z PocetHracov okna
        if (getArguments() != null) {
            Meno_HracaFragmentArgs args = Meno_HracaFragmentArgs.fromBundle(getArguments());
            pocetHracov = args.getPocetHracovArg();
        }

        if (clicked == pocetHracov){
            next.setText("Začni hru");
        }

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Player pepo = new Player(ETmenoHraca.getText().toString(), 0);
                // default meno ak nezadal nic ako svoje meno
                if (ETmenoHraca.getText().toString().equals("")){
                    pepo.setName("Neznámy" + unknown);
                    unknown++;
                }
                // pridavam hraca do arrayListu
                PlayerManager.instance.players.add(pepo);

                if (clicked < pocetHracov){
                    LabelMeno.setText("Zadaj meno hráča "+ (clicked+1) + ":");
                    ETmenoHraca.setText("");
                    if (clicked == pocetHracov-1){
                        next.setText("Začni hru");
                    }
                }
                else {
                    // ak som dozadaval mena tak prejdem na preGame okno
                    Navigation.findNavController(view).navigate(R.id.action_meno_HracaFragment_to_pre_gameFragment);
                }
                clicked++;
            }
        });
    }
}