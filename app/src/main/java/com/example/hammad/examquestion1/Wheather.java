package com.example.hammad.examquestion1;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

public class Wheather extends AppCompatActivity {
    Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wheather);

        fragment = new contentday();
        loadfragment(fragment);
    }

    public void FragmentClick(View view){
        switch (view.getId()){
            case R.id.day_Frag:
                fragment = new contentday();
                loadfragment(fragment);
                break;
            case R.id.day1_Frag:
                fragment = new contentday1();
                loadfragment(fragment);
                break;
            case R.id.day2_Frag:
                fragment = new contentday2();
                loadfragment(fragment);
                break;
        }
    }

    public void loadfragment(Fragment fragment){
        FragmentManager FM = getSupportFragmentManager();
        FragmentTransaction transaction = FM.beginTransaction();
        transaction.replace(R.id.day,fragment);
        transaction.commit();
    }
}
