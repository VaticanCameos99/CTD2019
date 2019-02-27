package com.pisb.ctd;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class unravel extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unravel);

    }
    public void onBackPressed()
    {

        int count = getFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();

        } else {
            getFragmentManager().popBackStack();
        }
    }

    public void unw(View v)
    {
        int id = v.getId();
        if(id == R.id.web)
        {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://ctd.pictieee.in"));
            startActivity(intent);
        }


    }


    public void uc(View view)
    {

        int id = view.getId();
        if(id == R.id.bhushan)
        {

            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:+91  86052 00014"));  //CHANGE!!!
            startActivity(intent);
        }


    }


}
