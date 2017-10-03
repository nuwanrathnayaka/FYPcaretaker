package com.kerbio.virtualcaretaker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.amazonaws.mobile.AWSMobileClient;
import com.amazonaws.mobilehelper.auth.IdentityManager;
import com.amazonaws.mobilehelper.auth.IdentityProvider;
import com.amazonaws.mobilehelper.auth.StartupAuthErrorDetails;
import com.amazonaws.mobilehelper.auth.StartupAuthResult;
import com.amazonaws.mobilehelper.auth.StartupAuthResultHandler;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.kerbio.virtualcaretaker.Nutrition;
import com.kerbio.virtualcaretaker.NutritionActivity;
import com.roughike.swipeselector.SwipeItem;
import com.roughike.swipeselector.SwipeSelector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt;

public class MainActivity extends AppCompatActivity {
    /** Class name for log messages. */
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    CardView card1,card2,card3;
    Toolbar toolbar;
    ImageButton alert;
    PopupWindow pw;
    Button pop_up_cancel;
    String url = "https://0797wlmaf4.execute-api.us-east-1.amazonaws.com/dev/medication/getMedication";

    LinearLayout main_layout;
    int screan_width,screan_height;
    APICaller caller;

    NavigationView navigationView;
    private DrawerLayout mDrawerLayout;

    private String userID;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        card1= (CardView) findViewById(R.id.card1);
        card2= (CardView) findViewById(R.id.card2);
        card3= (CardView) findViewById(R.id.card3);

        main_layout= (LinearLayout) findViewById(R.id.main_layout);
        screan_height=main_layout.getHeight();
        screan_width=main_layout.getWidth();

        navigationView= (NavigationView) findViewById(R.id.navigation_view);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        alert=(ImageButton)findViewById(R.id.imageButton);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        userID="1234";
        token="59a3ba9d734d1d7ab9ead016";

        caller=new APICaller(this,userID,token);

        alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Alert Button Clicked!",Toast.LENGTH_LONG).show();
                initiatePopupWindow(v);
            }
        });

        SwipeSelector swipeSelector = (SwipeSelector) findViewById(R.id.swipeSelector1);
        swipeSelector.setItems(
                // The first argument is the value for that item, and should in most cases be unique for the
                // current SwipeSelector, just as you would assign values to radio buttons.
                // You can use the value later on to check what the selected item was.
                // The value can be any Object, here we're using ints.
                new SwipeItem(0, "Slide one", "Description for slide one."),
                new SwipeItem(1, "Slide two", "Description for slide two."),
                new SwipeItem(2, "Slide three", "Description for slide three."),
                new SwipeItem(3, "Slide three", "Description for slide three.")
        );



        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MedicationActivity.class));
//                Alarm a=new Alarm();
//                a.setAlarm(MainActivity.this);
            }
        });

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NutritionActivity.class));
            }
        });

        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();
                if(menuItem.getTitle().equals("Profile")){
                    startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                }
                if(menuItem.getTitle().equals("Medication")){
                    startActivity(new Intent(MainActivity.this, MedicationActivity.class));
                }
                if(menuItem.getTitle().equals("Nutrition")){
                    startActivity(new Intent(MainActivity.this, NutritionActivity.class));
                }
//                switch (menuItem.getItemId()){
//                    case R.id.settings:
//                        startActivity(new Intent(MainActivity.this, UserActivity.class));
//                }
                //do the other otions here.ex: for logout etc.
                Toast.makeText(MainActivity.this, menuItem.getTitle(), Toast.LENGTH_LONG).show();
                return true;
            }
        });


//


        /*
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);




        AWSMobileClient.initializeMobileClientIfNecessary(getApplicationContext());
        final IdentityManager identityManager =
                AWSMobileClient.defaultMobileClient().getIdentityManager();

        identityManager.doStartupAuth(this,
                new StartupAuthResultHandler() {
                    @Override
                    public void onComplete(final StartupAuthResult authResults) {
                        if (authResults.isUserSignedIn()) {
                            // User has successfully signed in with an identity provider.
                            final IdentityProvider provider = identityManager.getCurrentIdentityProvider();
                            Log.d(LOG_TAG, "Signed in with " + provider.getDisplayName());
                            // If we were signed in previously with a provider indicate that to the user with a toast.
                            Toast.makeText(SplashActivity.this, String.format("Signed in with %s",
                                    provider.getDisplayName()), Toast.LENGTH_LONG).show();
                        } else if (authResults.isUserAnonymous()) {
                            // User has an unauthenticated anonymous (guest) identity, either because the user never previously
                            // signed in with any identity provider or because refreshing the provider credentials failed.

                            // Optionally, you can check whether refreshing a previously signed in provider failed.
                            final StartupAuthErrorDetails errors = authResults.getErrorDetails();
                            if (errors.didErrorOccurRefreshingProvider()) {
                               Log.w(LOG_TAG, String.format(
                                        "Credentials for Previously signed-in provider %s could not be refreshed.",
                                        errors.getErrorProvider().getDisplayName()), errors.getProviderErrorException());
                            }


                        } else {
                            // User has no identity because authentication was unsuccessful due to a failure.
                            final StartupAuthErrorDetails errors = authResults.getErrorDetails();
                            Log.e(LOG_TAG, "No Identity could be obtained. Continuing with no identity.",
                                    errors.getUnauthenticatedErrorException());
                        }
                        goMain(SplashActivity.this);
                    }
                }, 2000);


        */

                            //set the collapsing image to default

   }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

//        switch (id) {
//            case android.R.id.home:
//                mDrawerLayout.openDrawer(GravityCompat.START);
//                return true;
//            case R.id.action_settings:
//                return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    public void showNavPrompt(View view)
    {
        new MaterialTapTargetPrompt.Builder(this)
                .setTarget(R.id.navfab)
                .setPrimaryText(R.string.example_fab_title)
                .setSecondaryText(R.string.example_fab_description)
                .setAnimationInterpolator(new FastOutSlowInInterpolator())
                .setMaxTextWidth(R.dimen.tap_target_menu_max_width)
                .show();
    }

    private void initiatePopupWindow(View v) {
        try {
            final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
            //We need to get the instance of the LayoutInflater, use the context of this activity
            LayoutInflater inflater = (LayoutInflater) MainActivity.this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //Inflate the view from a predefined XML layout
            View layout = inflater.inflate(R.layout.alert_pop_up,
                    (ViewGroup) findViewById(R.id.popup_element));
            // create a 300px width and 470px height PopupWindow
            pw = new PopupWindow(layout, 700, 1000, true);
            // display the popup in the center
            pw.showAtLocation(v, Gravity.CENTER, 0, 0);

//            TextView mResultText = (TextView) layout.findViewById(R.id.server_status_text);
            Button cancelButton = (Button) layout.findViewById(R.id.pop_up_cancel_button);
            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pw.dismiss();
                }
            });

            ImageButton dangerButton= (ImageButton) layout.findViewById(R.id.danger_icon);
            dangerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.startAnimation(animAlpha);
                    //do the api call here
                    try{
                        caller.alert();
                        Toast.makeText(MainActivity.this, "Successfull!", Toast.LENGTH_SHORT).show();

                    }catch (Exception e){
                        Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                    }

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
