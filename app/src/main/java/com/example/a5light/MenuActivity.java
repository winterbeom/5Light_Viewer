package com.example.a5light;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.a5light.constants.Constants;
import com.example.a5light.data.Detect_Data;
import com.example.a5light.fcm.FCMMessagingService;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.messaging.FcmBroadcastProcessor;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuActivity extends AppCompatActivity {
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private Frag1 frag1 = new Frag1();
    private Frag2 frag2 = new Frag2();
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() { //NavigationItemSelecte
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                BottomNavigate(menuItem.getItemId());

                return true;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.viewer);

        updateToken();

    }

    private void BottomNavigate(int id) {  //BottomNavigation 페이지 변경
        String tag = String.valueOf(id);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment currentFragment = fragmentManager.getPrimaryNavigationFragment();
        if (currentFragment != null) {
            fragmentTransaction.hide(currentFragment);
        }

        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment == null) {
            if (id == R.id.viewer) {
                fragment = new Frag1();

            } else if (id == R.id.notification) {

                fragment = new Frag2();
            }

            fragmentTransaction.add(R.id.layout_main_frame, fragment, tag);
        } else {
            fragmentTransaction.show(fragment);
        }

        fragmentTransaction.setPrimaryNavigationFragment(fragment);
        fragmentTransaction.setReorderingAllowed(true);
        fragmentTransaction.commitNow();
    }


    public int getUserId() {
        SharedPreferences pref = getSharedPreferences(Constants.PREFERENCE, MODE_PRIVATE);
        return pref.getInt(Constants.USER_ID_KEY, -1);
    }

    public void updateToken() {
        RetrofitService retrofitService = ApiClient.getClient().create(RetrofitService.class);
        FCMMessagingService.getToken(token -> {
            Call<JsonObject> call = retrofitService.updateToken(Integer.toString(getUserId()), token);
            call.enqueue(new Callback<JsonObject>(){
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {

                }
            });
        });
    }
}


