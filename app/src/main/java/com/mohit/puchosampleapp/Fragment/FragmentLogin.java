package com.mohit.puchosampleapp.Fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.mohit.puchosampleapp.Activity.MainActivity;
import com.mohit.puchosampleapp.Helper.Constants;
import com.mohit.puchosampleapp.Helper.Helper;
import com.mohit.puchosampleapp.Interface.MainActivityInterface;
import com.mohit.puchosampleapp.R;

import java.security.PrivateKey;

public class FragmentLogin extends Fragment {

    private Context thisContext;
    private FragmentActivity thisActivity;
    public GoogleSignInOptions googleSignInOptions;
    public GoogleApiClient googleApiClient;
    private ImageView btnGooglSignIn;
    private int RC_Sign_In = 1548;
    private MainActivityInterface mainActivityInterface;

    public FragmentLogin() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        thisContext = FragmentLogin.this.getContext();
        thisActivity = getActivity();
        mainActivityInterface = (MainActivityInterface) thisContext;
        ((MainActivity)getActivity()).setActionBarTitle(getResources().getString(R.string.title_login_screen));
        ((MainActivity)getActivity()).invisibleAddCardButton();
        initGoogleSignIn();
        bindView(view);

        return view;
    }

    public void bindView(View view) {
        btnGooglSignIn = (ImageView) view.findViewById(R.id.btnGoogleSignIN);

        btnGooglSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonPressed(btnGooglSignIn);
                signIn();
            }
        });
    }

    public void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(signInIntent,RC_Sign_In);
    }

    public void onButtonPressed(ImageView imageView) {
        imageView.setPadding(20,20,20,20);
    }

    public void onButtonRelease(ImageView imageView) {
        imageView.setPadding(0,0,0,0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_Sign_In) {
            GoogleSignInResult googleSignInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleGoogleSignResult(googleSignInResult);
        }
    }

    public void handleGoogleSignResult(GoogleSignInResult googleSignInResult) {
        onButtonRelease(btnGooglSignIn);
        GoogleSignInAccount googleSignInAccount = googleSignInResult.getSignInAccount();
        if(googleSignInAccount != null) {
            if(googleSignInAccount.getEmail() != null) {
                Constants.User_email = googleSignInAccount.getEmail();
                Helper.makeToast(thisContext,"Welcome, " + googleSignInAccount.getDisplayName());
                mainActivityInterface.loadFragment(new FragmentHome(),Constants.Tag_Fragment_Home);
            }
        }

    }

    public void initGoogleSignIn() {
        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .build();

        googleApiClient = new GoogleApiClient.Builder(thisContext)
                .addApi(Auth.GOOGLE_SIGN_IN_API,googleSignInOptions)
                .build();
    }

}
