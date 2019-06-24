package com.example.explorun.routes;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.explorun.ImageModel;
import com.example.explorun.R;
import com.example.explorun.SlidingImage_Adapter;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Stadtpark extends AppCompatActivity {
    TextView info;
    ImageView pic;
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private ArrayList<ImageModel> imageModelArrayList;
    private int[] myImageList = new int[]{R.drawable.stadtpark1, R.drawable.stadtpark2,
            R.drawable.stadtpark3, R.drawable.stadtpark4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alsterrunde);
        info = (TextView) findViewById(R.id.alsterinfo);
        pic = (ImageView) findViewById(R.id.alsterpic);
        pic.setImageResource(R.drawable.stadtpark);
        info.setText("Der Stadtpark ist ein Läuferparadies, denn die Variationsmöglichkeiten der Strecken sind unerschöpflich. Einmal um den Stadtpark herum entspricht ungefähr einer Strecke von sechs Kilometern. Möglicher Anfangspunkt ist das Landhaus Walter oder auch die U-Bahn-Station Saarlandstraße. Der Treffpunkt ist sehr variabl, da man von allen Seiten in den Park gelangen kann.\n" +
                "Länge: 6.0 km\n" +
                "Steigung: keine");
        imageModelArrayList = new ArrayList<>();
        imageModelArrayList = populateList();
        init();

    }

    private ArrayList<ImageModel> populateList() {

        ArrayList<ImageModel> list = new ArrayList<>();

        for (int i = 0; i < myImageList.length; i++) {
            ImageModel imageModel = new ImageModel();
            imageModel.setImage_drawable(myImageList[i]);
            list.add(imageModel);
        }

        return list;
    }

    private void init() {

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new SlidingImage_Adapter(Stadtpark.this, imageModelArrayList));

        CirclePageIndicator indicator = (CirclePageIndicator)
                findViewById(R.id.indicator);

        indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;

//Set circle indicator radius
        indicator.setRadius(5 * density);

        NUM_PAGES = imageModelArrayList.size();

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });
    }
}
