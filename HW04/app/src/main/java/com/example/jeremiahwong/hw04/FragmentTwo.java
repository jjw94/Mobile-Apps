package com.example.jeremiahwong.hw04;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Jeremiah Wong on 4/25/2017.
 */

public class FragmentTwo extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        //inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_two,container,false);
    }
}