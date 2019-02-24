package com.example.feedr;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import android.widget.Toast;
import static android.content.ContentValues.TAG;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    android.support.v7.widget.Toolbar mToolbar;
    SensorManager sensorManager;
    Sensor sensor;
    static int x = 0;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("ASD", Locale.getDefault().getLanguage());

        //
        sensorManager = (SensorManager) getSystemService(Service.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mToolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);

        // Set View
        loadTheme();


        // Set Language
        SharedPreferences sharedPref = android.support.v7.preference.PreferenceManager.getDefaultSharedPreferences(this);
        String languageToLoad  = sharedPref.getString(SettingActivity.KEY_PREF_LANGUAGE, null); // your language
        setLocale(languageToLoad);

        setContentView(R.layout.activity_main);

        // Set Fragment tab and view pager
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.info_label));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.feed_label));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.game_label));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new
                TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        String id = getIntent().getStringExtra("ID");
        Log.d("Firebase", "token : " + FirebaseInstanceId.getInstance().getToken());

        // Set Default Shared Preference
        android.support.v7.preference.PreferenceManager
                .setDefaultValues(this, R.xml.preferences, false);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_settings:
                Intent settingsIntent = new Intent(this,
                        SettingActivity.class);
                startActivity(settingsIntent);
                return true;

            case R.id.edit_pet:
                Intent editIntent = new Intent(this,
                        EditPetActivity.class);
                startActivity(editIntent);
            default:
                // Skip
        }

        return super.onOptionsItemSelected(item);
    }

    public void launchGame(View v) {
        Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.tencent.ig");
        if (launchIntent != null) {
            startActivity(launchIntent);
        } else {
            launchIntent = new Intent(Intent.ACTION_VIEW);
            launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            launchIntent.setData(Uri.parse("market://details?id=" + "com.tencent.ig"));
            startActivity(launchIntent);
        }
    }

    private void loadTheme() {
        if (x==1) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(getResources().getColor(R.color.lightColorPrimaryDark));
            }
            setTheme(R.style.LightAppTheme);
            sensorManager.unregisterListener(this);
        } else if (x==2) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
            }
            setTheme(R.style.AppTheme);
            sensorManager.unregisterListener(this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensor, sensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (x==0) {
            if (event.sensor.getType() == sensor.TYPE_LIGHT) {
                if (event.values[0] > 1000) {
                    x = 1;
                } else {
                    x = 2;
                }
                recreate();
            }
        } else {
            sensorManager.unregisterListener(this);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @SuppressWarnings("deprecation")
    private void setLocale(String languageCode){
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            config.setLocale(locale);
        } else {
            config.locale = locale;
        }
        Resources res = getBaseContext().getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        res.updateConfiguration(config, dm);
    }
}
