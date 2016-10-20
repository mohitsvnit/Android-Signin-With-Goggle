package com.mohit.puchosampleapp.Interface;

import android.support.v4.app.Fragment;

/**
 * Created by mohit on 11/10/16.
 */

public interface MainActivityInterface {
    public void loadFragment(Fragment fragment, String tag);
    public void loadFragmentWithBackStack(Fragment fragment,String tag);
}
