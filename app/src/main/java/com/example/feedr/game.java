package com.example.feedr;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class game extends Fragment {

    public MainActivity main_activity;
    TextView mHighScoreView;
    public game() {
        // Required empty public constructor
    }


    @Override
    public void onResume() {
        super.onResume();
        if (main_activity.pet != null) {
            mHighScoreView.setText(main_activity.pet.getHighScore().toString());
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (main_activity.pet != null) {
            mHighScoreView.setText(main_activity.pet.getHighScore().toString());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game, container, false);

        main_activity = (MainActivity) getActivity();

        mHighScoreView = (TextView) view.findViewById(R.id.highScoreView);
        if (main_activity.pet != null) {
            mHighScoreView.setText(main_activity.pet.getHighScore().toString());
        }

        return view;
    }

}
