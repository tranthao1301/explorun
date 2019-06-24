package com.example.explorun;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.explorun.routes.Alsterrunde;
import com.example.explorun.routes.Eimsbuettel;
import com.example.explorun.routes.Isebekkanal;
import com.example.explorun.routes.Kanalrunde;
import com.example.explorun.routes.Volksparkrunde;


public class FilterActivity extends AppCompatActivity {
    private CheckBox kurz, mittel, lang, mitlicht, ohnelicht, amwasser, nichtamwasser, mitsteigung, ohnesteigung;
    private Button btnFilter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        kurz = (CheckBox)findViewById(R.id.kurz);
        mittel = (CheckBox)findViewById(R.id.mittel);
        lang = (CheckBox)findViewById(R.id.lang);
        mitlicht = (CheckBox)findViewById(R.id.mitlicht);
        ohnelicht = (CheckBox)findViewById(R.id.ohnelicht);
        amwasser = (CheckBox)findViewById(R.id.amwasser);
        nichtamwasser = (CheckBox)findViewById(R.id.ohnewasser);
        mitsteigung = (CheckBox)findViewById(R.id.mitsteigung);
        ohnesteigung = (CheckBox)findViewById(R.id.ohnesteigung);

        btnFilter = (Button)findViewById(R.id.btnfilter);
        btnFilter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(kurz.isChecked() && mitlicht.isChecked() && amwasser.isChecked() && ohnesteigung.isChecked()
                        && !mittel.isChecked()
                        && !lang.isChecked() && !ohnelicht.isChecked() && !nichtamwasser.isChecked() && !mitsteigung.isChecked()){
                    Intent intent = new Intent(getApplicationContext(), Isebekkanal.class);
                    startActivity(intent);
                }
                if(kurz.isChecked() && !mittel.isChecked() && !amwasser.isChecked() && nichtamwasser.isChecked()
                        && ohnesteigung.isChecked() && !mitlicht.isChecked() && ohnelicht.isChecked() && !mitsteigung.isChecked()){
                    Toast.makeText(getApplicationContext(),"No Route found!",Toast.LENGTH_SHORT).show();
                }

                if(kurz.isChecked() && mitlicht.isChecked() && nichtamwasser.isChecked() && !mitsteigung.isChecked() && !mittel.isChecked()
                        && !lang.isChecked() && !ohnelicht.isChecked() && !amwasser.isChecked() && ohnesteigung.isChecked()){
                    Toast.makeText(getApplicationContext(),"No Route found!",Toast.LENGTH_SHORT).show();
                }
                if(kurz.isChecked() && mitlicht.isChecked() && nichtamwasser.isChecked() && mitsteigung.isChecked() && !mittel.isChecked()
                        && !lang.isChecked() && !ohnelicht.isChecked() && !amwasser.isChecked() && !ohnesteigung.isChecked()){
                    Toast.makeText(getApplicationContext(),"No Route found!",Toast.LENGTH_SHORT).show();
                }
                if(kurz.isChecked() && mitlicht.isChecked() && !nichtamwasser.isChecked() && mitsteigung.isChecked() && !mittel.isChecked()
                        && !lang.isChecked() && !ohnelicht.isChecked() && amwasser.isChecked() && !ohnesteigung.isChecked()){
                    Toast.makeText(getApplicationContext(),"No Route found!",Toast.LENGTH_SHORT).show();
                }
                if(kurz.isChecked() && !mitlicht.isChecked() && !nichtamwasser.isChecked() && mitsteigung.isChecked() && !mittel.isChecked()
                        && !lang.isChecked() && ohnelicht.isChecked() && amwasser.isChecked() && !ohnesteigung.isChecked()){
                    Toast.makeText(getApplicationContext(),"No Route found!",Toast.LENGTH_SHORT).show();
                }
                if(kurz.isChecked() && !mitlicht.isChecked() && nichtamwasser.isChecked() && mitsteigung.isChecked() && !mittel.isChecked()
                        && !lang.isChecked() && ohnelicht.isChecked() && !amwasser.isChecked() && !ohnesteigung.isChecked()){
                    Toast.makeText(getApplicationContext(),"No Route found!",Toast.LENGTH_SHORT).show();
                }
                if(kurz.isChecked() && !mitlicht.isChecked() && !nichtamwasser.isChecked() && !mitsteigung.isChecked() && !mittel.isChecked()
                        && !lang.isChecked() && ohnelicht.isChecked() && amwasser.isChecked() && ohnesteigung.isChecked()){
                    Toast.makeText(getApplicationContext(),"No Route found!",Toast.LENGTH_SHORT).show();
                }



                if(mittel.isChecked() && mitlicht.isChecked() && amwasser.isChecked()&& ohnesteigung.isChecked() && !kurz.isChecked()
                        && !lang.isChecked() && !ohnelicht.isChecked() && !nichtamwasser.isChecked() && !mitsteigung.isChecked()){
                    Intent intent = new Intent(getApplicationContext(), Alsterrunde.class);
                    startActivity(intent);
                }

                if(((mittel.isChecked() && !lang.isChecked())||(lang.isChecked() && !mittel.isChecked())) && !kurz.isChecked() && !amwasser.isChecked() && nichtamwasser.isChecked()
                        && ohnesteigung.isChecked() && !mitlicht.isChecked() && ohnelicht.isChecked() && !mitsteigung.isChecked()){
                    Intent intent = new Intent(getApplicationContext(), Eimsbuettel.class);
                    startActivity(intent);
                }

                if(mittel.isChecked() && mitlicht.isChecked() && nichtamwasser.isChecked() && !mitsteigung.isChecked() && !kurz.isChecked()
                        && !lang.isChecked() && !ohnelicht.isChecked() && !amwasser.isChecked() && ohnesteigung.isChecked()){
                    Toast.makeText(getApplicationContext(),"No Route found!",Toast.LENGTH_SHORT).show();
                }
                if(mittel.isChecked() && mitlicht.isChecked() && nichtamwasser.isChecked() && mitsteigung.isChecked() && !kurz.isChecked()
                        && !lang.isChecked() && !ohnelicht.isChecked() && !amwasser.isChecked() && !ohnesteigung.isChecked()){
                    Toast.makeText(getApplicationContext(),"No Route found!",Toast.LENGTH_SHORT).show();
                }
                if(mittel.isChecked() && mitlicht.isChecked() && !nichtamwasser.isChecked() && mitsteigung.isChecked() && !kurz.isChecked()
                        && !lang.isChecked() && !ohnelicht.isChecked() && amwasser.isChecked() && !ohnesteigung.isChecked()){
                    Intent intent = new Intent(getApplicationContext(), Kanalrunde.class);
                    startActivity(intent);
                }
                if(mittel.isChecked() && !mitlicht.isChecked() && !nichtamwasser.isChecked() && mitsteigung.isChecked() && !kurz.isChecked()
                        && !lang.isChecked() && ohnelicht.isChecked() && amwasser.isChecked() && !ohnesteigung.isChecked()){
                    Toast.makeText(getApplicationContext(),"No Route found!",Toast.LENGTH_SHORT).show();
                }
                if(mittel.isChecked() && !mitlicht.isChecked() && nichtamwasser.isChecked() && mitsteigung.isChecked() && !kurz.isChecked()
                        && !lang.isChecked() && ohnelicht.isChecked() && !amwasser.isChecked() && !ohnesteigung.isChecked()){
                    Toast.makeText(getApplicationContext(),"No Route found!",Toast.LENGTH_SHORT).show();
                }
                if(mittel.isChecked() && !mitlicht.isChecked() && !nichtamwasser.isChecked() && !mitsteigung.isChecked() && !kurz.isChecked()
                        && !lang.isChecked() && ohnelicht.isChecked() && amwasser.isChecked() && ohnesteigung.isChecked()){
                    Toast.makeText(getApplicationContext(),"No Route found!",Toast.LENGTH_SHORT).show();
                }

                if(lang.isChecked() && mitlicht.isChecked() && nichtamwasser.isChecked() && mitsteigung.isChecked() && !kurz.isChecked()
                        && !mittel.isChecked() && !ohnelicht.isChecked() && !amwasser.isChecked() && !ohnesteigung.isChecked()){
                    Intent intent = new Intent(getApplicationContext(), Volksparkrunde.class);
                    startActivity(intent);
                }
                if(lang.isChecked() && mitlicht.isChecked() && nichtamwasser.isChecked() && !mitsteigung.isChecked() && !kurz.isChecked()
                        && !mittel.isChecked() && !ohnelicht.isChecked() && !amwasser.isChecked() && ohnesteigung.isChecked()){
                    Toast.makeText(getApplicationContext(),"No Route found!",Toast.LENGTH_SHORT).show();
                }
                if(lang.isChecked() && mitlicht.isChecked() && !nichtamwasser.isChecked() && !mitsteigung.isChecked() && !kurz.isChecked()
                        && !mittel.isChecked() && !ohnelicht.isChecked() && amwasser.isChecked() && ohnesteigung.isChecked()){
                    Toast.makeText(getApplicationContext(),"No Route found!",Toast.LENGTH_SHORT).show();
                }
                if(lang.isChecked() && mitlicht.isChecked() && !nichtamwasser.isChecked() && mitsteigung.isChecked() && !kurz.isChecked()
                        && !mittel.isChecked() && !ohnelicht.isChecked() && amwasser.isChecked() && !ohnesteigung.isChecked()){
                    Toast.makeText(getApplicationContext(),"No Route found!",Toast.LENGTH_SHORT).show();
                }
                if(lang.isChecked() && !mitlicht.isChecked() && !nichtamwasser.isChecked() && mitsteigung.isChecked() && !kurz.isChecked()
                        && !mittel.isChecked() && ohnelicht.isChecked() && amwasser.isChecked() && !ohnesteigung.isChecked()){
                    Toast.makeText(getApplicationContext(),"No Route found!",Toast.LENGTH_SHORT).show();
                }
                if(lang.isChecked() && !mitlicht.isChecked() && nichtamwasser.isChecked() && mitsteigung.isChecked() && !kurz.isChecked()
                        && !mittel.isChecked() && ohnelicht.isChecked() && !amwasser.isChecked() && !ohnesteigung.isChecked()){
                    Toast.makeText(getApplicationContext(),"No Route found!",Toast.LENGTH_SHORT).show();
                }
                if(lang.isChecked() && !mitlicht.isChecked() && !nichtamwasser.isChecked() && !mitsteigung.isChecked() && !kurz.isChecked()
                        && !mittel.isChecked() && ohnelicht.isChecked() && amwasser.isChecked() && ohnesteigung.isChecked()){
                    Toast.makeText(getApplicationContext(),"No Route found!",Toast.LENGTH_SHORT).show();
                }
                if((kurz.isChecked() && mittel.isChecked() && lang.isChecked()) || (kurz.isChecked() && mittel.isChecked())
                        || (mittel.isChecked() && lang.isChecked()) || (kurz.isChecked() && lang.isChecked())){
                    Toast.makeText(getApplicationContext(),"Please choose only one checkbox for distance",Toast.LENGTH_SHORT).show();
                }
                if(mitlicht.isChecked() && ohnelicht.isChecked()){
                    Toast.makeText(getApplicationContext(),"Please choose only one checkbox for Lighting",Toast.LENGTH_SHORT).show();
                }
                if(amwasser.isChecked() && nichtamwasser.isChecked()){
                    Toast.makeText(getApplicationContext(),"Please choose only one checkbox for near Water",Toast.LENGTH_SHORT).show();
                }
                if(mitsteigung.isChecked() && ohnesteigung.isChecked()){
                    Toast.makeText(getApplicationContext(),"Please choose only one checkbox for Gradient",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

//    public void onCheckboxClicked(View view){
//        boolean checked = ((CheckBox) view).isChecked();
//        String str = "";
//        switch (view.getId()){
//            case R.id.kurz:
//                str = checked?"weniger als 5 km" : "";
//                break;
//        }
//    }


}
