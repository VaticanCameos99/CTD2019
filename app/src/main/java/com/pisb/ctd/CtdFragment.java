package com.pisb.ctd;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class CtdFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        try
        { View view = (View) inflater.inflate(R.layout.bottomnav_ctd, container, false);

        TextView t1 = (TextView) view.findViewById(R.id.ncc);
        TextView t2 = (TextView) view.findViewById(R.id.nth);
        TextView t3 = (TextView) view.findViewById(R.id.unravel);
        TextView t4 = (TextView) view.findViewById(R.id.inquizitive);
        TextView t5 = (TextView) view.findViewById(R.id.wildcard);


      t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), ncc.class);
                startActivity(intent);

            }
        });

        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), nth.class);
                startActivity(intent);

            }
        });

        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), unravel.class);
                startActivity(intent);

            }
        });

        t4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), inquisit.class);
                startActivity(intent);

            }
        });

        t5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), wallstreet.class);
                startActivity(intent);

            }
        });
            return view;
    }
    catch (Exception e)
    {
        Log.e(TAG, "onCreateView", e);

        throw e;
    }



    }

    }
