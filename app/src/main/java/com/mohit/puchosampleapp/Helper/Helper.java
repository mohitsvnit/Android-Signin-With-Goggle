package com.mohit.puchosampleapp.Helper;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.mohit.puchosampleapp.R;

/**
 * Created by mohit on 7/10/16.
 */

public class Helper {
    static public void makeToast(Context thisContext,String string) {
        Toast.makeText(thisContext, string,Toast.LENGTH_SHORT).show();
    }

    static public void log(String string) {
        Log.e(Constants.Tag_Log,string);
    }
}
