package com.example.feedr;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class info extends Fragment {

    private TextView mPetName;
    private TextView mPetType;
    private TextView mPetLocation;

    public MainActivity main_activity;
    public info() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_info, container, false);

        main_activity = (MainActivity) getActivity();

//        Log.d("Tes",main_activity.coba.toS);

        mPetName = (TextView) view.findViewById(R.id.petNameView);
        mPetType = (TextView) view.findViewById(R.id.petTypeView);
        mPetLocation = (TextView) view.findViewById(R.id.petLocationView);

        if (main_activity.pet != null) {
            mPetName.setText(main_activity.pet.getPetName());
            mPetType.setText(main_activity.pet.getType());
//            mPetLocation.setText(main_activity.pet.getLocation());
        }

        return view;
    }

    public void setInfo(){
        mPetName.setText(main_activity.pet.getPetName());
        mPetType.setText(main_activity.pet.getType());
//        mPetLocation.setText(main_activity.pet.getLocation());
    }
//    private void searchPet(){
//        String id = "1";

    @Override
    public void onStart() {
        super.onStart();
        if (main_activity.pet != null) {
            mPetName.setText(main_activity.pet.getPetName());
            mPetType.setText(main_activity.pet.getType());
//            mPetLocation.setText(main_activity.pet.getLocation());
        }
    }
//        new FetchPet(mPetName, mPetType, mPetLocation).execute(id);
//    }

    @Override
    public void onResume() {
        super.onResume();
        if (main_activity.pet != null) {
            mPetName.setText(main_activity.pet.getPetName());
            mPetType.setText(main_activity.pet.getType());
//            mPetLocation.setText(main_activity.pet.getLocation());
        }
    }
}
