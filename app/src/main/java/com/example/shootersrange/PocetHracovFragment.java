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
import android.widget.EditText;
import android.widget.Toast;

import com.example.shootersrange.PocetHracovFragmentDirections;


public class PocetHracovFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pocet_hracov, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button nextBtn = getView().findViewById(R.id.btnNextPocetHracov);
        EditText ETpocetHracov = getView().findViewById(R.id.EditPocetHracov);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pocetHracov = 0;
                try {
                    pocetHracov = Integer.parseInt(ETpocetHracov.getText().toString());
                }

                catch (NumberFormatException exception){
                    // osetrenie zadaneho bludu
                }

                if ((!ETpocetHracov.getText().toString().equals("")) && pocetHracov > 0) {
                    // Posielanie argumentov o pocte hracov
                    PocetHracovFragmentDirections.ActionPocetHracovFragmentToMenoHracaFragment action = PocetHracovFragmentDirections.actionPocetHracovFragmentToMenoHracaFragment();
                    action.setPocetHracovArg(pocetHracov);

                    Navigation.findNavController(view).navigate(action);
                }
                else Toast.makeText(getActivity(), "Zadaj správny počet hráčov",Toast.LENGTH_SHORT).show();
            }
        });

    }
}