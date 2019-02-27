package com.pisb.ctd;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class wallstreet extends AppCompatActivity {


    TextView textView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallstreet);

        textView = (TextView) findViewById(R.id.shivang);

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

    public void wc(View view)
    {
        int id = view.getId();
        if(id == R.id.shivang)
        {

            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:+91 78882 39280"));  //CHANGE!!!
            startActivity(intent);
        }

    }



}
