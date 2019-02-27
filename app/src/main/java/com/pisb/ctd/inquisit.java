package com.pisb.ctd;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class inquisit extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inquisit);


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


  public void ic(View view)
  {

      int id = view.getId();
      if(id == R.id.neel)
      {

          Intent intent = new Intent(Intent.ACTION_DIAL);
          intent.setData(Uri.parse("tel:+91 80870 74718"));  //CHANGE!!!
          startActivity(intent);
      }


  }


}
