package com.android.cai_lai_la.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.cai_lai_la.R;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NavMeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NavMeFragment extends Fragment {
    public NavMeFragment() {
        // Required empty public constructor
    }

    public static NavMeFragment newInstance(){
        NavMeFragment fragment = new NavMeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nav_me, container, false);
    }
}
