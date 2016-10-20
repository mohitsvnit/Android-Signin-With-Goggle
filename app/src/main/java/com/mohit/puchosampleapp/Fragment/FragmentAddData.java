package com.mohit.puchosampleapp.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.mohit.puchosampleapp.Activity.MainActivity;
import com.mohit.puchosampleapp.Helper.Constants;
import com.mohit.puchosampleapp.Helper.DatabaseHelper;
import com.mohit.puchosampleapp.Interface.MainActivityInterface;
import com.mohit.puchosampleapp.Objects.Data;
import com.mohit.puchosampleapp.R;


public class FragmentAddData extends Fragment {

    private EditText etTitle, etDetails;
    private Button btnAddData;
    private Context thisContext;
    private DatabaseHelper databaseHelper;
    private MainActivityInterface mainActivityInterface;

    public FragmentAddData() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_data, container, false);
        thisContext = FragmentAddData.this.getContext();
        ((MainActivity)getActivity()).setActionBarTitle("Add Blog");
        mainActivityInterface = (MainActivityInterface) thisContext;
        databaseHelper = new DatabaseHelper(thisContext);
        bindView(view);
        setClicks();
        return view;
    }

    public void bindView(View view) {
        etTitle = (EditText) view.findViewById(R.id.etTitle);
        etDetails = (EditText) view.findViewById(R.id.etDetails);
        btnAddData = (Button) view.findViewById(R.id.btnAddData);
    }

    public void setClicks() {
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Constants.User_email != null) {
                    databaseHelper.addData(new Data(Constants.User_email,etTitle.getText().toString(),etDetails.getText().toString()));
                    mainActivityInterface.loadFragment(new FragmentHome(),Constants.Tag_Fragment_Home);
                }
            }
        });
    }

}
