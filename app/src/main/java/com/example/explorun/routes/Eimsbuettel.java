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

public class Eimsbuettel extends AppCompatActivity {
    TextView info;
    ImageView pic;
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private ArrayList<ImageModel> imageModelArrayList;
     private int[] myImageList = new int[]{R.drawable.eims1, R.drawable.eims2,
     R.drawable.eims3, R.drawable.eims4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eimsbuettel);
        info = (TextView)findViewById(R.id.eimsbuettelinfo);
        pic = (ImageView)findViewById(R.id.eimsbuettelpic);
        pic.setImageResource(R.drawable.eimsbuettel);
        info.setText("Eimsbütteler starten diese Tour an der U-Bahn-Haltestelle Lutterothstraße, laufen um die Lenzsiedlung herum und an Sportstätten und Kleingärten vorbei. Danach geht es durch die Stellinger Schweiz bis zur Amsinckvilla. Es folgt ein großer Bogen durch das Niendorfer Gehege. Vorbei am Stellinger Wasserturm geht es zurück zum Startpunkt an der Lutterothstraße.\n" +
                "Länge: 10 km\n" +
                "Steigung: nein");
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
        mPager.setAdapter(new SlidingImage_Adapter(Eimsbuettel.this, imageModelArrayList));

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

