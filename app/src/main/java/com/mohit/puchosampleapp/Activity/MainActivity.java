package com.mohit.puchosampleapp.Activity;


import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mohit.puchosampleapp.Fragment.FragmentAddData;
import com.mohit.puchosampleapp.Fragment.FragmentLogin;
import com.mohit.puchosampleapp.Fragment.FragmentSetting;
import com.mohit.puchosampleapp.Helper.Constants;
import com.mohit.puchosampleapp.Helper.Helper;
import com.mohit.puchosampleapp.Interface.MainActivityInterface;
import com.mohit.puchosampleapp.R;

import java.util.Locale;


public class MainActivity extends AppCompatActivity implements MainActivityInterface{

    public ImageView ivSetting, ivAddCard;
    public TextView tvTitle;
    public Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initAppBar();
        loadFragment(new FragmentLogin(), Constants.Tag_Fragment_Login);
    }

    public void setActionBarTitle(String title) {
        tvTitle.setText(title);
    }

    public void initAppBar() {
        tvTitle = (TextView) toolbar.findViewById(R.id.tvTitle);
        ivSetting = (ImageView) toolbar.findViewById(R.id.ivSetting);
        ivAddCard = (ImageView) toolbar.findViewById(R.id.ivAddCard);

        ivSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragmentWithBackStack(new FragmentSetting(),Constants.Tag_Fragment_Setting);
            }
        });

        ivAddCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragmentWithBackStack(new FragmentAddData(),Constants.Tag_Fragment_AddData);
            }
        });
    }

    public void changeLanguage(String languageCode) {
        Locale locale = new Locale("es");
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.setLocale(locale);
        getBaseContext().getResources().updateConfiguration(configuration,
                getBaseContext().getResources().getDisplayMetrics());
        this.setContentView(R.layout.activity_main);
    }

    public void invisibleAddCardButton() {
        ivAddCard.setVisibility(View.GONE);
    }

    public void visibleAddCard(){
        ivAddCard.setVisibility(View.VISIBLE);
    }

    @Override
    public void loadFragment(Fragment fragment,String tag) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.replace(R.id.mainActivity_FragmentID,fragment,tag);
        transaction.commit();
    }
    @Override
    public void loadFragmentWithBackStack(Fragment fragment,String tag) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.replace(R.id.mainActivity_FragmentID,fragment,tag);
        transaction.addToBackStack(tag);
        transaction.commit();
    }
}
