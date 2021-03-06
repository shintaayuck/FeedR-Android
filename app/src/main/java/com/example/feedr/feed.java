package com.example.feedr;


import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.concurrent.ExecutionException;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class feed extends Fragment {

    Button mbutton;
    TextView mStatus;
    TextView mLastFed;
    TextView mRecommendation;

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;
    public MainActivity main_activity;
    public feed() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_feed, container, false);

        mbutton = view.findViewById(R.id.feed_button);
        mStatus = view.findViewById(R.id.food_status);
        mLastFed = view.findViewById(R.id.last_fed);
        mRecommendation = view.findViewById(R.id.recommendation);

        main_activity = (MainActivity) getActivity();

        if (main_activity.pet != null) {
            if (main_activity.pet.isAvailable()) {
                mStatus.setText("Full");
            } else {
                mStatus.setText("Empty");
            }
            mLastFed.setText(main_activity.pet.getLastFed());
        }

        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedMe(v);
            }
        });

        Intent intent = new Intent(getActivity(), ShakeService.class);
        getActivity().startService(intent);

        // ShakeDetector initialization
        mSensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();
        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {

            @Override
            public void onShake(int count) {
                feedMe(view);
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (main_activity.pet != null) {
            if (main_activity.pet.isAvailable()) {
                mStatus.setText("Full");
                mRecommendation.setText(getString(R.string.full_rec));

            } else {
                mStatus.setText("Empty");
                mRecommendation.setText(getString(R.string.empty_rec));
            }
            mLastFed.setText(main_activity.pet.getLastFed());
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        if (main_activity.pet != null) {
            if (main_activity.pet.isAvailable()) {
                mStatus.setText("Full");
                mRecommendation.setText(getString(R.string.full_rec));

            } else {
                mStatus.setText("Empty");
                mRecommendation.setText(getString(R.string.empty_rec));
            }
            mLastFed.setText(main_activity.pet.getLastFed());
        }
        mSensorManager.registerListener(mShakeDetector, mAccelerometer,	SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(mShakeDetector);
    }

    public void feedMe(View view) {
        try {
            String result = new FeedPetAsync().execute(main_activity.pet).get();
            Log.d(TAG, "feedMe: " + result);
            Toast.makeText(getContext(), "Restock food complete!", Toast.LENGTH_LONG).show();
            Date date = new Date();
            mLastFed.setText(date.toString());
            mStatus.setText("Full");
            mRecommendation.setText(getString(R.string.full_rec));
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (mShakeDetector == null) {
            return;
        }
        if (isVisibleToUser) {
            mSensorManager.registerListener(mShakeDetector, mAccelerometer, SensorManager.SENSOR_DELAY_GAME);
        } else {
            mSensorManager.unregisterListener(mShakeDetector);
        }
    }

}