package com.pisb.ctd;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class nth extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nth);

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


    public void nthc(View view)
    {

        int id = view.getId();
        if(id == R.id.shreya)
        {

            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:+91  89994 12539"));  //CHANGE!!!
            startActivity(intent);
        }

    }


}
