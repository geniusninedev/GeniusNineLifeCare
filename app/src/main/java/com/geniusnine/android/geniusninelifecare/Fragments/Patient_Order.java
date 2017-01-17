package com.geniusnine.android.geniusninelifecare.Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;;
import com.geniusnine.android.geniusninelifecare.R;


/**
 * Created by Dev on 12-01-2017.
 */

public class Patient_Order extends Fragment {
    ProgressBar Progressbar;
    TextView ShowText;
    int progressBarValue = 30;
    Handler handler = new Handler();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.patient_order, null);

        Progressbar = (ProgressBar)v.findViewById(R.id.progressBar);
        ShowText = (TextView)v.findViewById(R.id.textView1);

        Progressbar.setProgress(progressBarValue);
        Progressbar.setSecondaryProgress(50);
        String progressvalue= String.valueOf(progressBarValue);
//        ShowText.setText(progressvalue);




        return v;
    }

}