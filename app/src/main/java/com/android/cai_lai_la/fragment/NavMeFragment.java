package com.android.cai_lai_la.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.android.cai_lai_la.R;
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
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button touxiang = (Button)view.findViewById(R.id.personalInfo_button_first);
        Drawable drawable1 = getResources().getDrawable(R.drawable.headsculp);
        drawable1.setBounds(0, 0, 80, 80);
        Drawable drawable2 = getResources().getDrawable(R.drawable.jiantou);
        drawable2.setBounds(0, 0, 80, 80);
        touxiang.setCompoundDrawables(drawable1, null, drawable2, null);
        view.findViewById(R.id.personalInfo_button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }


}
