package com.example.timelineplus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.timelineplus.databinding.HomeBinding;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    // When user logged in and already on the Home, initialize the Fragment already
    private HomeBinding binding;
    private FragmentHome home;
    private FragmentAddPost addPost;
    private FragmentProfile profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = HomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize the different Fragments in advance
        home = new FragmentHome();
        addPost = new FragmentAddPost();
        profile = new FragmentProfile();
        replaceFragment(home);

        // Initialize the functionality of the navigation buttons
        binding.bottomNav.setOnItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.menuHome:
                    replaceFragment(home);
                    return true;
                case R.id.menuAddPost:
                    replaceFragment(addPost);
                    return true;
                case R.id.menuProfile:
                    replaceFragment(profile);
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