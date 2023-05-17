package com.example.timelineplus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.timelineplus.databinding.HomeBinding;

public class Home extends AppCompatActivity {

    // Declare the FrameLayout and the BottomNavigation
    private HomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = HomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new FragmentHome());

        binding.bottomNav.setOnItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.menuHome:
                    replaceFragment(new FragmentHome());
                    return true;
                case R.id.menuAddPost:
                    replaceFragment(new FragmentAddPost());
                    return true;
                case R.id.menuProfile:
                    replaceFragment(new FragmentProfile());
                    return true;
            }

            return true;
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.homeFrameLayout, fragment);
        fragmentTransaction.commit();
    }

}