package com.mohit.puchosampleapp.Fragment;


import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.mohit.puchosampleapp.Activity.MainActivity;
import com.mohit.puchosampleapp.Helper.Helper;
import com.mohit.puchosampleapp.R;

import java.util.Locale;

public class FragmentSetting extends Fragment {

    public Spinner spnChooseLanguage;
    public Context thisContext;

    public FragmentSetting() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        thisContext = FragmentSetting.this.getContext();
        ((MainActivity)getActivity()).setActionBarTitle("Setting");
        spnChooseLanguage = (Spinner) view.findViewById(R.id.spnChooseLanguage);
        setSpinner();
        return view;
    }

    public void setSpinner() {
        final String[] languageList = getResources().getStringArray(R.array.chooseLanguages);
        ArrayAdapter languages = new ArrayAdapter(thisContext,android.R.layout.simple_spinner_dropdown_item,languageList);
        spnChooseLanguage.setAdapter(languages);

        spnChooseLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Helper.makeToast(thisContext,languageList[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

}
