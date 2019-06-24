package com.example.explorun;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.explorun.routes.Alsterrunde;
import com.example.explorun.routes.Eimsbuettel;
import com.example.explorun.routes.Isebekkanal;
import com.example.explorun.routes.Kanalrunde;
import com.example.explorun.routes.Stadtpark;
import com.example.explorun.routes.Volksparkrunde;


public class RoutesActivity extends AppCompatActivity {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routes);

        CustomListAdapter whatever = new CustomListAdapter(this, nameArray, infoArray, imageArray);
        listView = (ListView) findViewById(R.id.routeslist);
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
    }


}
