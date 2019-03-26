package com.example.androiddz;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static com.example.androiddz.NumElement.updateView;


/**
 * A simple {@link Fragment} subclass.
 */
public class OneNumberFragment extends Fragment {
    private int number;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        number = getArguments().getInt("number");
        NumElement ne = new NumElement(number);
        View rootView = inflater.inflate(R.layout.fragment_one_number, container, false);
        updateView(ne, ((TextView)rootView.findViewById(R.id.oneNumber)));
        return rootView;
    }



}
