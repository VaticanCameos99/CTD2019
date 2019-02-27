package com.pisb.ctd;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.messaging.FirebaseMessaging;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    static ActionBarDrawerToggle toggle;
    static DrawerLayout drawer;
    boolean doubleBackToExitPressedOnce = false;

    int[] arr = {0, 0, 0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        BottomNavigationView bottomNavBar = findViewById(R.id.bottomNavBar);
        bottomNavBar.setOnNavigationItemSelectedListener(navListener);

        arr[1] = 1;
        Fragment selectedFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer2, selectedFragment).commit();

        //if version is >= oreo, then create a notification channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel =
                    new NotificationChannel("MyNotification", "MyNotification", NotificationManager.IMPORTANCE_DEFAULT);
            //NOTE: id of the channel is the same as used in the builder. THE above code creates an obj of the channel

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);

        }

        /*The code below is for a specific type (general) type of users, when the notification is created, it will be sent to this type of user only*/

        FirebaseMessaging.getInstance().subscribeToTopic("general")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Checkout the New Notification!";
                        if (!task.isSuccessful()) {
                            msg = "No New notifications";
                        }
                    }
                });


    }

    public void call(View v) {
        int id = v.getId();

        if (id == R.id.contact2) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:+91 70575 92979"));  
            startActivity(intent);
        } else if (id == R.id.contact) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:+91 89837 23300"));
            startActivity(intent);
        }
    }


    public void my(View view) {
        int id = view.getId();

        if (id == R.id.s) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:+91 80873 47255"));
            startActivity(intent);
        } else if (id == R.id.v) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:+91 77987 44422"));
            startActivity(intent);
        }

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = new HomeFragment();
                    switch (menuItem.getItemId()) {
                        case R.id.bottomnav_ieee_info:
                            if (arr[1] == 0) {
                                arr[1] = 1;
                                arr[0] = 0;
                                arr[2] = 0;
                                selectedFragment = new HomeFragment();
                                FragmentTransaction gf = getSupportFragmentManager().beginTransaction();
                                gf.setCustomAnimations(R.anim.e_right, R.anim.ex_left);
                                gf.replace(R.id.fragmentContainer2, selectedFragment);
                                gf.disallowAddToBackStack();
                                gf.commit();
                                // getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer2, selectedFragment).commit();
                            }
                            break;
                        case R.id.bottomnav_ctd_events:
                            if (arr[0] == 0) {
                                arr[0] = 1;
                                arr[1] = 0;
                                arr[2] = 0;
                                selectedFragment = new CtdFragment();
                                FragmentTransaction fg = getSupportFragmentManager().beginTransaction();
                                fg.setCustomAnimations(R.anim.e_left, R.anim.ex_right);
                                fg.replace(R.id.fragmentContainer2, selectedFragment);
                                fg.disallowAddToBackStack();
                                fg.commit();
                            }
                            break;
                        case R.id.bottomnav_Contact_Us:

                            if (arr[2] == 0) {
                                arr[0] = 0;
                                arr[1] = 0;
                                arr[2] = 1;
                                selectedFragment = new ContactUsFragment();
                                FragmentTransaction df = getSupportFragmentManager().beginTransaction();
                                df.setCustomAnimations(R.anim.e_right, R.anim.ex_left);
                                df.replace(R.id.fragmentContainer2, selectedFragment);
                                df.disallowAddToBackStack();
                                df.commit();
                            }
                            //getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer2, selectedFragment).commit();
                            break;
                    }
                    return true;
                }
            };

    @Override
    public void onBackPressed() {

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://docs.google.com/document/d/e/2PACX-1vTsIEifh1Xp5jjUiUqzUvruyaGjJZhCF99amgDQr8dTfyC78ErTNWlF7b0ysXSUiS6bRxUJ1oaARaaR/pub"));
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        arr[0] = 0;
        arr[1] = 0;
        arr[2] = 0;
        int id = item.getItemId();
        Fragment selectedFragment = new HomeFragment();
        if (id == R.id.nav_faq) {
            selectedFragment = new FaqFragment();
        } else if (id == R.id.nav_privacy) {

            selectedFragment = new PrivacyFragment();
        } else if (id == R.id.nav_web) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("http://pictieee.in/"));
            startActivity(intent);
        } else if (id == R.id.nav_share) {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
            String shareMessage = "✨✨ Greetings from PISB! ✨✨\n" +
                    "\n" +
                    "Credenz Tech Days (CTD) is a series of technical and non technical events, that take place during January and February every year.\n" +
                    "\n" +
                    "The CTD app highlights the events that PICT IEEE Student Branch conducts during these two months and gives an overall description of what PISB is about.\n" +
                    "\n" +
                    "Check out our app!\n" +
                    "\n" +
                    "App Link: ";
            shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID;
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            startActivity(Intent.createChooser(shareIntent, "choose one"));
        } else if (id == R.id.feedback) {
            Uri uri = Uri.parse("market://details?id=" + getPackageName());
            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
            goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                    Intent.FLAG_ACTIVITY_NEW_DOCUMENT | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
            try{
                startActivity(goToMarket);
            }catch (ActivityNotFoundException e){
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())));
            }

        }
      /*  else if(id == R.id.fb)
        {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("http://www.facebook.com/credenztechdayz18/"));
            startActivity(intent);

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer2, selectedFragment).commit();
        return true;
    }


}
