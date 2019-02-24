package com.example.feedr;


import android.os.Bundle;
import android.support.v4.app.Fragment;
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

    public info() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_info, container, false);
        mPetName = (TextView) view.findViewById(R.id.petNameView);
        mPetType = (TextView) view.findViewById(R.id.petTypeView);
        mPetLocation = (TextView) view.findViewById(R.id.petLocationView);

        searchPet();

        return view;
    }

    private void searchPet(){
        String id = "1";
        new FetchPet(mPetName, mPetType, mPetLocation).execute(id);
    }
}
