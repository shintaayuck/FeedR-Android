package com.example.feedr;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {
    private  String TAG = "INI TAG BANGSAT";

    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
}
