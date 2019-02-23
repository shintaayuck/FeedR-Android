package com.example.feedr;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class game extends Fragment {


    public game() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false);
    }

    public void launchGame(View v) {
        Intent launchIntent = getActivity().getPackageManager().getLaunchIntentForPackage("com.google.android.gm");
        if (launchIntent != null) {
            startActivity(launchIntent);
        }
    }

}
