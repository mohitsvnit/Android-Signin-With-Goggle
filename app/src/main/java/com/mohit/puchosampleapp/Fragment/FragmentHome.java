package com.mohit.puchosampleapp.Fragment;


import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mohit.puchosampleapp.Activity.MainActivity;
import com.mohit.puchosampleapp.Adapter.BlogAdapter;
import com.mohit.puchosampleapp.Helper.Constants;
import com.mohit.puchosampleapp.Helper.DatabaseHelper;
import com.mohit.puchosampleapp.Helper.Helper;
import com.mohit.puchosampleapp.Objects.Data;
import com.mohit.puchosampleapp.R;


public class FragmentHome extends Fragment {

    private RecyclerView rvBlogs;
    private LinearLayoutManager linearLayoutManager;
    private BlogAdapter blogAdapter;
    public Context thisContext;
    private DatabaseHelper databaseHelper;

    public FragmentHome() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        thisContext = FragmentHome.this.getContext();
        ((MainActivity)getActivity()).setActionBarTitle("Home");
        ((MainActivity)getActivity()).visibleAddCard();
        databaseHelper = new DatabaseHelper(thisContext);
        bindView(view);
        if(Constants.User_email != null) {
            Helper.log("Data List size: " + databaseHelper.getUserData(Constants.User_email).size());
            blogAdapter.setDataList(databaseHelper.getUserData(Constants.User_email));
            blogAdapter.notifyDataSetChanged();
        }
        return view;
    }

    public void bindView(View view) {
        rvBlogs = (RecyclerView) view.findViewById(R.id.rvBlogs);
        linearLayoutManager = new LinearLayoutManager(thisContext);
        blogAdapter = new BlogAdapter(thisContext);
        rvBlogs.setLayoutManager(linearLayoutManager);
        rvBlogs.setAdapter(blogAdapter);
    }

}
