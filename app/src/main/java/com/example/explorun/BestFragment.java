package com.example.explorun;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.explorun.routes.Alsterrunde;
import com.example.explorun.routes.Eimsbuettel;
import com.example.explorun.routes.Isebekkanal;
import com.example.explorun.routes.Kanalrunde;
import com.example.explorun.routes.Stadtpark;
import com.example.explorun.routes.Volksparkrunde;


public class BestFragment extends Fragment {
    String[] nameArray = {"Alsterrunde","Kanal","Stadtpark", "Isebekkanal", "Eimsbüttel", "Volkspark"};

    String[] infoArray = {"PLZ: 20148 Hamburg",
            "PLZ: 22081 Hamburg",
            "PLZ: 22081 Hamburg","PLZ: 20144 Hamburg", "PLZ: 20255 Hamburg", "PLZ: 22525 Hamburg"
    };

    Integer[] imageArray = {R.drawable.alster,
            R.drawable.kanal,
            R.drawable.stadtpark,
            R.drawable.isebekkanal,
            R.drawable.eimsbuettel,
            R.drawable.volkspark12km
    };

    ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_best, container, false);
        CustomListAdapter whatever = new CustomListAdapter(getActivity(), nameArray, infoArray, imageArray);
        listView = (ListView) view.findViewById(R.id.routeslist);
        listView.setAdapter(whatever);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    Intent myIntent = new Intent(view.getContext(), Alsterrunde.class);
                    startActivityForResult(myIntent, 0);
                }
                if(position == 1){
                    Intent myIntent = new Intent(view.getContext(), Kanalrunde.class);
                    startActivityForResult(myIntent, 0);
                }
                if(position == 2){
                    Intent myIntent = new Intent(view.getContext(), Stadtpark.class);
                    startActivityForResult(myIntent, 0);
                }
                if(position == 3){
                    Intent myIntent = new Intent(view.getContext(), Isebekkanal.class);
                    startActivityForResult(myIntent, 0);
                }
                if(position == 4){
                    Intent myIntent = new Intent(view.getContext(), Eimsbuettel.class);
                    startActivityForResult(myIntent, 0);
                }
                if(position == 5){
                    Intent myIntent = new Intent(view.getContext(), Volksparkrunde.class);
                    startActivityForResult(myIntent, 0);
                }
            }
        });
        return view;

    }
}