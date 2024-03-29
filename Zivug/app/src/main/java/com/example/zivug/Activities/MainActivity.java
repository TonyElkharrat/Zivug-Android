package com.example.zivug.Activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.zivug.R;
import com.example.zivug.fragments.intro_fragments.WelcomeFragment;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if(FirebaseDatabase.getInstance()==null)
        {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }

        setContentView(R.layout.activity_main);
        Intent intent = new Intent(MainActivity.this,AuthentificationActivity.class);
        startActivity(intent);
    }

}