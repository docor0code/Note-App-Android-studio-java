package com.draco.noteapp.Utils;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Helper {

    public static void ShowProgressORTextView(int i, ProgressBar progressBar, TextView tv){
        switch (i){
            case 1:
                progressBar.setVisibility(View.VISIBLE);
                tv.setVisibility(View.GONE);
                break;
            case 2:
                progressBar.setVisibility(View.GONE);
                tv.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }
}
