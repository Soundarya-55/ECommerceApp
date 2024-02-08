package com.example.ecommerceapp.fragment;

import android.content.Intent;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ecommerceapp.R;

import com.example.ecommerceapp.activities.RegisterActivity;
import com.example.ecommerceapp.fragments.HomeFragment;

public class Home extends AppCompatActivity {

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.home) {

            startActivity(new Intent(Home.this, HomeFragment.class));
            finish();
        }
        return true;
    }
}